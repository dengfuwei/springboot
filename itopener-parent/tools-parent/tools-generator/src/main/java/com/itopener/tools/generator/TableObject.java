package com.itopener.tools.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.StringUtils;

public class TableObject {

	/** 引入类*/
	private Set<String> imports;
	
	/** 类注释*/
	private String classComment;
	
	/** 表名*/
	private String tableName;
	
	/** 类属性*/
	private List<TableField> fields;
	
	/** 主键*/
	private List<TableField> primaryKeys;
	
	
	/** 类名*/
	private String className;
	
	/** 类名--首字母小写，作为属性时使用*/
	private String classFieldName;
	
	/** 包名+类名*/
	private String fullName;
	
	/** 全部列*/
	private List<TableField> allField = new ArrayList<>();
	
	/** 查询条件类名*/
	private String conditionClassName;
	
	/** 查询条件类完整名*/
	private String conditionClassFullName;
	
	/**
	 * @desc: 获取类名
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午6:05:43
	 */
	public String getClassName(){
		return className;
	}
	
	public String getFullName(){
		return fullName;
	}
	
	public String getClassFieldName() {
		return classFieldName;
	}

	/**
	 * @desc: 获取所有属性
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月26日 上午11:42:17
	 */
	public List<TableField> getAllField(){
		return allField;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void setImports(Set<String> imports) {
		this.imports = imports;
	}

	public String getClassComment() {
		return classComment;
	}

	public void setClassComment(String classComment) {
		this.classComment = classComment;
	}

	public String getTableName() {
		return tableName;
	}
	
	public String getConditionClassName() {
		return conditionClassName;
	}

	public String getConditionClassFullName() {
		return conditionClassFullName;
	}

	public void setTableName(String tableName) {
		String tablePrefix = Utils.config.getTablePrefix();
		StringBuilder className = null;
		if(StringUtils.isNullOrEmpty(tablePrefix)){
			className = new StringBuilder(tableName);
		} else{
			className = new StringBuilder(tableName.substring(tablePrefix.length()));
		}
		Utils.tableName2JavaClassName(className);
		this.className = className.toString();
		
		StringBuilder classField = new StringBuilder(className);
		classField.setCharAt(0, Character.toLowerCase(className.charAt(0)));
		this.classFieldName = classField.toString();
		
		this.fullName = Utils.config.getBasePackageModel() + "." + getClassName();
		
		this.tableName = tableName;
		
		this.conditionClassName = this.className + Utils.config.getConditionSuffix();
		this.conditionClassFullName = Utils.config.getBasePackageCondition() + "." + this.conditionClassName;
	}

	public List<TableField> getFields() {
		return fields;
	}

	public void setFields(List<TableField> fields) {
		allField.clear();
		if(primaryKeys != null && primaryKeys.size() > 0){
			allField.addAll(primaryKeys);
		}
		if(fields != null && fields.size() > 0){
			allField.addAll(fields);
		}
		this.fields = fields;
	}

	public List<TableField> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<TableField> primaryKeys) {
		allField.clear();
		if(primaryKeys != null && primaryKeys.size() > 0){
			allField.addAll(primaryKeys);
		}
		if(fields != null && fields.size() > 0){
			allField.addAll(fields);
		}
		this.primaryKeys = primaryKeys;
	}
	
}
