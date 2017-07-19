package com.itopener.tools.generator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {
	
	public static Config config = null;

	/**
	 * @desc: 数据库表名转为java类名
	 * @param tableName
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午4:53:40
	 */
	public static void tableName2JavaClassName(StringBuilder tableName){
		underline2CamelCase(tableName);
		tableName.setCharAt(0, Character.toUpperCase(tableName.charAt(0)));
	}
	
	/**
	 * @desc: 数据库列名转为java属性名
	 * @param columnName
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午4:52:57
	 */
	public static void columnName2JavaFieldName(StringBuilder columnName){
		underline2CamelCase(columnName);
	}
	
	/**
	 * @desc: 下划线命名方式转为驼峰命名法
	 * @param underline
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午4:52:41
	 */
	public static void underline2CamelCase(StringBuilder underline){
		for(int i = 0; i < underline.length(); i++){
			char c = underline.charAt(i);
			if(c == '_' && ++i < underline.length()){
				underline.deleteCharAt(--i);
				underline.setCharAt(i, Character.toUpperCase(underline.charAt(i)));
			}
		}
	}
	
	/**
	 * @desc: 时间格式化
	 * @param date
	 * @param format
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午5:41:17
	 */
	public static String format(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}
	
	public static TableField getTableField(List<TableField> list, String columnName){
		for(TableField item : list){
			if(item.getColumnName().equals(columnName)){
				return item;
			}
		}
		return null;
	}
	
	public static String getMapperWhere(String fieldName, String columnType){
		FieldType fieldType = FieldType.valueOf(columnType);
		if(fieldType == null){
			throw new RuntimeException("数据库类型没有找到匹配的定义:" + columnType);
		}
		if(fieldType.getDataType() == 1){
			return fieldName + " > 0";
		} else if(fieldType.getDataType() == 2){
			return fieldName + " != null and " + fieldName + " != ''";
		} else if(fieldType.getDataType() == 3){
			return fieldName + " != null";
		} else if(fieldType.getDataType() == 4){
			return fieldName;
		} else{
			throw new RuntimeException("没有匹配的java类型，数据库字段类型为：" + columnType);
		}
	}
}
