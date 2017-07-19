package com.itopener.tools.generator;


public class TableField {
	
	/** 列类型*/
	private String columnType;
	
	/** 列名称*/
	private String columnName;
	
	/** 注释*/
	private String comment;
	
	/** 是否是主键*/
	private boolean isPrimaryKey;
	
	/** mybatis jdbcType*/
	private String jdbcType;
	
	
	/** 类名名称*/
	private String typeName;
	
	/** 属性类名所属类名称*/
	private String typeImportName;
	
	/** 属性类完整名*/
	private String typeFullName;
	
	/** 属性名称*/
	private String fieldName;
	
	/** 属性名称首字母大写*/
	private String fieldNameCamel;
	
	/** get方法名*/
	private String getterName;
	
	/** set方法名*/
	private String setterName;
	
	/** 最小值属性名*/
	private String minFieldName;
	
	/** 最小值属性get方法名*/
	private String minGetterName;
	
	/** 最小值属性set方法名*/
	private String minSetterName;

	/** 最大值属性名*/
	private String maxFieldName;
	
	/** 最大值属性get方法名*/
	private String maxGetterName;
	
	/** 最大值属性set方法名*/
	private String maxSetterName;
	
	/** 模糊匹配属性名*/
	private String likeFieldName;
	
	/** 模糊匹配属性get方法名*/
	private String likeGetterName;
	
	/** 模糊匹配属性set方法名*/
	private String likeSetterName;
	

	/**
	 * @desc: 获取属性类型名称
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午5:59:39
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * @desc: 获取属性类型的类名
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午6:00:37
	 */
	public String getTypeImportName() {
		return typeImportName;
	}
	
	public String getTypeFullName() {
		return typeFullName;
	}

	/**
	 * @desc: 获取属性名称
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午6:04:48
	 */
	public String getFieldName() {
		return fieldName;
	}
	
	public String getFieldNameCamel(){
		return fieldNameCamel;
	}
	
	/**
	 * @desc: 获取属性get方法名
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午6:04:58
	 */
	public String getGetterName(){
		return getterName;
	}
	
	/**
	 * @desc: 获取属性set方法名
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午6:05:07
	 */
	public String getSetterName(){
		return setterName;
	}
	
	public String getMinFieldName(){
		return minFieldName;
	}
	
	public String getMaxFieldName(){
		return maxFieldName;
	}
	
	public String getMinGetterName(){
		return minGetterName;
	}
	
	public String getMinSetterName(){
		return minSetterName;
	}
	
	public String getMaxGetterName(){
		return maxGetterName;
	}
	
	public String getMaxSetterName(){
		return maxSetterName;
	}
	
	public String getLikeFieldName(){
		return likeFieldName;
	}
	
	public String getLikeGetterName(){
		return likeGetterName;
	}
	
	public String getLikeSetterName(){
		return likeSetterName;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		StringBuilder fieldName = new StringBuilder(columnName);
		Utils.columnName2JavaFieldName(fieldName);
		this.fieldName = fieldName.toString();
		
		StringBuilder sb = new StringBuilder(columnName);
		Utils.underline2CamelCase(sb);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		this.fieldNameCamel = sb.toString();
		
		this.getterName = "get" + fieldNameCamel;
		this.setterName = "set" + fieldNameCamel;
		this.minFieldName = "min" + fieldNameCamel;
		this.minGetterName = "getMin" + fieldNameCamel;
		this.minSetterName = "setMin" + fieldNameCamel;
		this.maxFieldName = "max" + fieldNameCamel;
		this.maxGetterName = "getMax" + fieldNameCamel;
		this.maxSetterName = "setMax" + fieldNameCamel;
		this.likeFieldName = "like" + fieldNameCamel;
		this.likeGetterName = "getLike" + fieldNameCamel;
		this.likeSetterName = "setLike" + fieldNameCamel;
		
		this.columnName = columnName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		FieldType fieldType = FieldType.valueOf(columnType);
		if(fieldType == null){
			throw new RuntimeException("数据库类型没有找到匹配的定义:" + columnType);
		}
		this.typeName = fieldType.getType();
		this.typeImportName = fieldType.getImportName();
		this.typeFullName = fieldType.getClassFullName();
		this.columnType = columnType;
		this.jdbcType = fieldType.getJdbcType();
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	
}
