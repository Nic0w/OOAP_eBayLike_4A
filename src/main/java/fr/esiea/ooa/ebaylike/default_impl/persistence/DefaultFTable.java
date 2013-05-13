package fr.esiea.ooa.ebaylike.default_impl.persistence;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.esiea.ooa.ebaylike.api.persistence.FilteredTable;
import fr.esiea.ooa.ebaylike.api.persistence.Index;
import fr.esiea.ooa.ebaylike.api.persistence.Table;

public class DefaultFTable<T> implements FilteredTable<T> {

	private static final Logger dftLogger = LoggerFactory.getLogger(DefaultFTable.class);
	
	private final Class<T> rowType;
	private final List<T> rows;
	
	private boolean useMethod = false;
	
	private Field filterField;
	private Method filterMethod;
	
	public DefaultFTable(Class<T> rowType, List<T> rows) {
		
		this.rowType = rowType;
		this.rows = rows;
		
	}

	private static Field getFieldFromType(Class<?> source, Class<?> filter) {
		
		for(Field field : source.getDeclaredFields()) 
			if(field.getType().equals(filter))
				return field;
		
		
		dftLogger.warn("Field of type {} was not found in class {}.", filter.getSimpleName(), source.getSimpleName());
		
		return null;
	}
	
	private static Field getFieldFromName(Class<?> source, String filter) {
		
		for(Field field : source.getDeclaredFields()) 
			if(field.getName().equals(filter))
				return field;
		
		dftLogger.warn("Field named {} was not found in class {}.", filter, source.getSimpleName());
		
		return null;
	}
	
	private static Method getMethodByReturnType(Class<?> source, Class<?> filter) {
		
		for(Method m : source.getDeclaredMethods())
			if(m.getReturnType().equals(filter) && m.getParameterTypes().length == 0) { //We ensure that the method has no args.
				
				dftLogger.trace("Method named {} was found in class {}.", m.getName(), source.getSimpleName());
				
				return m;
			}
		
		dftLogger.warn("No Method with correct return type was found in class {}.", source.getSimpleName());
		
		return null;
	}
	
	private static Method getMethodByName(Class<?> source, String fieldName) {
		
		String realFilter = ("get" + fieldName).toLowerCase(); //We search for a getter;
		
		for(Method m : source.getDeclaredMethods())
			if(m.getName().toLowerCase().contentEquals(realFilter) && m.getParameterTypes().length == 0) { //We ensure that the method has no args.
				
				dftLogger.trace("Method named {} was found in class {}.", m.getName(), source.getSimpleName());
				
				return m;
			}
		
		dftLogger.warn("No Method named {} was found in class {}.", realFilter, source.getSimpleName());
		
		return null;
	}
	
	private static Object getIndexValue(Object o) throws IllegalArgumentException, IllegalAccessException {
		
		Class<?> clazz = o.getClass();
		
		Field target = null;
		
		for(Field field : clazz.getDeclaredFields()) {
			
			field.setAccessible(true);
			
			if(field.isAnnotationPresent(Index.class)) {
				target = field;
				break;
			}
		}
		
		if(target == null) {
			//System.out.println("@Index not found in object " + o.getClass().getSimpleName());
			return null;
		}
		
		target.setAccessible(true);
		
		return target.get(o);
	}
	
	private static boolean isPrimitive(Object o) {
		return o.getClass().isPrimitive();
	}
	
	protected FilteredTable<T> byField(Class<?> fieldType) {
		
		this.filterField = null;
		
		Class<?> sourceClass = this.rowType;
		
		//We search firstly in the class, then in every superclass !
		while(!sourceClass.equals(Object.class) && this.filterField == null) { 
			
			this.filterField = getFieldFromType(sourceClass, fieldType);

			sourceClass = sourceClass.getSuperclass();
			if(sourceClass==null) break;
		}
		
		
		if(this.filterField == null) { //If we really cannot find a field, we're going to search for a method.
			this.useMethod = true;
			this.filterMethod = getMethodByReturnType(this.rowType, fieldType);
		}
		
		return this;
	}
	
	protected FilteredTable<T> byName(String fieldName) {
		
		this.filterField = null;
		
		Class<?> sourceClass = this.rowType;
		
		while(!sourceClass.equals(Object.class) && this.filterField == null) {
			
			this.filterField = getFieldFromName(sourceClass, fieldName);

			sourceClass = sourceClass.getSuperclass();
			
			if(sourceClass==null) break;
		}
		
		if(this.filterField == null) {
			this.useMethod = true;
			this.filterMethod = getMethodByName(this.rowType, fieldName);
		}
		
		return this;
	}
	
	@Override
	public Table<T> isEqualTo(Object o) {
		
		List<T> result = new ArrayList<>();
		
		for(T row : this.rows) {
			
			Object fieldValue = null;
			Object test = null;
			
			try {
				
				if(useMethod) {
					
					this.filterMethod.setAccessible(true);
					fieldValue = this.filterMethod.invoke(row);
					
				}
				else {
					
					this.filterField.setAccessible(true);
					fieldValue = this.filterField.get(row);
				}
					
				
				if(isPrimitive(fieldValue))
					test = fieldValue;
				else 
					test = getIndexValue(fieldValue);
				
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			if(test == null) {
				if(fieldValue.equals(o))
					result.add(row);
			}
			else 
				if(test.equals(o))
					result.add(row);
			
		}
		
		return new DefaultTable<>(this.rowType, result);
	}
}
