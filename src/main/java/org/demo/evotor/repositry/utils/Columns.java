package org.demo.evotor.repositry.utils;

import java.lang.reflect.Field;
import javax.persistence.Column;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public class Columns {
	
		
	public static String getFieldForColumn(Class<?> clazz, String columnName) {
		if (clazz.equals(Object.class)) {
			return null;
		}

		Field[] fieldsThis = clazz.getDeclaredFields();
		for (Field f : fieldsThis) {
			if (f.isAnnotationPresent(Column.class)) {
				Column column = f.getAnnotation(Column.class);
				if ( columnName.equals(column.name())) {
					return f.getName();
				}	
			}			
		}
		
		return getFieldForColumn(clazz.getSuperclass(), columnName);
	}

	
	
}
