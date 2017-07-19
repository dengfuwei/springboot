package com.itopener.tools.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Config {

	/** 数据库驱动类*/
	private String driver;
	
	/** 数据库连接*/
	private String url;
	
	/** 数据库用户名*/
	private String user;
	
	/** 数据库连接密码*/
	private String password;
	
	/** 实体类基础包名*/
	private String basePackageModel;
	
	/** mybatis映射文件基础包名*/
	private String basePackageMapper;
	
	/** 查询条件类的基础包名*/
	private String basePackageCondition;
	
	/** DAO的基础包名*/
	private String basePackageDao;
	
	/** 查询条件类的类名后缀*/
	private String conditionSuffix = "Condition";
	
	/** 生成文件的基础路径*/
	private String basePath;
	
	/** 要生成文件的数据库表名，多个用英文逗号隔开*/
	private String tables;
	
	/** 表名前缀，设置后生成的java类名会去掉前缀*/
	private String tablePrefix;
	
	/** 父类，直接拼接，多个用逗号分隔*/
	private String extendsModel;
	
	/** 条件类的父类，直接拼接，多个用逗号分隔*/
	private String conditionExtendsModel;
	
	/** 实现接口*/
	private String implementsInterface;
	
	/** 是否生成serialVersionUID，默认为true*/
	private boolean generateSerialVersionUID = true;
	
	/** 额外的引入类或接口的完整名*/
	private Set<String> imports = new HashSet<>();
	
	/** 排除列名，排除的列不会生成到java类里面，多个用英文逗号隔开，此配置所有表公用*/
	private Set<String> excludeColumns = new HashSet<>();
	
	/** 最大行宽*/
	private int maxLineWidth = 100;
	
	/** mapper文件里面where条件的列*/
	private Map<String, Set<String>> whereColumns = new HashMap<String, Set<String>>();
	
	/** 公共 whereColumns*/
	private Set<String> baseWhereColumns = new HashSet<>();
	
	/** mapper文件里面where使用范围做条件的列*/
	private Map<String, Set<String>> whereRangeColumns = new HashMap<String, Set<String>>();
	
	/** 公共whereRangeColumns*/
	private Set<String> baseWhereRangeColumns = new HashSet<>();
	
	/** mapper文件里面where使用模糊匹配的列*/
	private Map<String, Set<String>> likeColumns = new HashMap<String, Set<String>>();
	
	/** 公共likeColumns*/
	private Set<String> baseLikeColumns = new HashSet<>();
	
	/** 作者*/
	private String author = "itopener generator";
	
	/** 插入语句需要排除的字段*/
	private Map<String, Set<String>> insertExcludeColumns = new HashMap<String, Set<String>>();
	
	/** 公共insertExcludeColumns*/
	private Set<String> baseInsertExcludeColumns = new HashSet<>();
	
	/** 修改语句需要排除的字段*/
	private Map<String, Set<String>> updateExcludeColumns = new HashMap<String, Set<String>>();
	
	/** 公共updateExcludeColumns*/
	private Set<String> baseUpdateExcludeColumns = new HashSet<>();
	
	/** 删除时需要设置的字段（逻辑删除）*/
	private Map<String, Set<String>> deleteSetColumns = new HashMap<String, Set<String>>();
	
	/** 公共deleteSetColumns*/
	private Set<String> baseDeleteSetColumns = new HashSet<>();
	
	/** mapper文件中插入语句配置中，要返回自动生成的属性，指定表对应的keyProperty，优先级高于keyProperty属性的配置*/
	private Map<String, String> mapperInsertKeyProperty = new HashMap<>();
	
	/** mapper文件中插入语句配置中，要返回自动生成的属性*/
	private String keyProperty;
	
	/** 逻辑删除标识列的名称*/
	private String flagColumnName;
	
	/** 逻辑删除标识有效的值*/
	private String flagValidValue;
	
	/** 逻辑删除标识无效的值（即已删除）*/
	private String flagInValidValue;
	
	/** baseDao完整名*/
	private String baseDao;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBasePackageModel() {
		return basePackageModel;
	}

	public void setBasePackageModel(String basePackageModel) {
		this.basePackageModel = basePackageModel;
	}

	public String getBasePackageMapper() {
		return basePackageMapper;
	}

	public void setBasePackageMapper(String basePackageMapper) {
		this.basePackageMapper = basePackageMapper;
	}

	public String getBasePackageCondition() {
		return basePackageCondition;
	}

	public void setBasePackageCondition(String basePackageCondition) {
		this.basePackageCondition = basePackageCondition;
	}

	public String getConditionSuffix() {
		return conditionSuffix;
	}

	public void setConditionSuffix(String conditionSuffix) {
		this.conditionSuffix = conditionSuffix;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getTables() {
		return tables;
	}

	public void setTables(String tables) {
		this.tables = tables;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getExtendsModel() {
		return extendsModel;
	}

	public void setExtendsModel(String extendsModel) {
		this.extendsModel = extendsModel;
	}

	public String getConditionExtendsModel() {
		return conditionExtendsModel;
	}

	public void setConditionExtendsModel(String conditionExtendsModel) {
		this.conditionExtendsModel = conditionExtendsModel;
	}

	public String getImplementsInterface() {
		return implementsInterface;
	}

	public void setImplementsInterface(String implementsInterface) {
		this.implementsInterface = implementsInterface;
	}

	public boolean isGenerateSerialVersionUID() {
		return generateSerialVersionUID;
	}

	public void setGenerateSerialVersionUID(boolean generateSerialVersionUID) {
		this.generateSerialVersionUID = generateSerialVersionUID;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void setImports(Set<String> imports) {
		this.imports = imports;
	}

	public Set<String> getExcludeColumns() {
		return excludeColumns;
	}

	public void setExcludeColumns(Set<String> excludeColumns) {
		this.excludeColumns = excludeColumns;
	}

	public int getMaxLineWidth() {
		return maxLineWidth;
	}

	public void setMaxLineWidth(int maxLineWidth) {
		this.maxLineWidth = maxLineWidth;
	}

	public Map<String, Set<String>> getWhereColumns() {
		return whereColumns;
	}

	public void setWhereColumns(Map<String, Set<String>> whereColumns) {
		this.whereColumns = whereColumns;
	}

	public Map<String, Set<String>> getWhereRangeColumns() {
		return whereRangeColumns;
	}

	public void setWhereRangeColumns(Map<String, Set<String>> whereRangeColumns) {
		this.whereRangeColumns = whereRangeColumns;
	}

	public Set<String> getBaseWhereRangeColumns() {
		return baseWhereRangeColumns;
	}

	public void setBaseWhereRangeColumns(Set<String> baseWhereRangeColumns) {
		this.baseWhereRangeColumns = baseWhereRangeColumns;
	}

	public Map<String, Set<String>> getLikeColumns() {
		return likeColumns;
	}

	public void setLikeColumns(Map<String, Set<String>> likeColumns) {
		this.likeColumns = likeColumns;
	}

	public Set<String> getBaseLikeColumns() {
		return baseLikeColumns;
	}

	public void setBaseLikeColumns(Set<String> baseLikeColumns) {
		this.baseLikeColumns = baseLikeColumns;
	}

	public Map<String, Set<String>> getInsertExcludeColumns() {
		return insertExcludeColumns;
	}

	public void setInsertExcludeColumns(Map<String, Set<String>> insertExcludeColumns) {
		this.insertExcludeColumns = insertExcludeColumns;
	}

	public Set<String> getBaseInsertExcludeColumns() {
		return baseInsertExcludeColumns;
	}

	public void setBaseInsertExcludeColumns(Set<String> baseInsertExcludeColumns) {
		this.baseInsertExcludeColumns = baseInsertExcludeColumns;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Map<String, Set<String>> getUpdateExcludeColumns() {
		return updateExcludeColumns;
	}

	public void setUpdateExcludeColumns(Map<String, Set<String>> updateExcludeColumns) {
		this.updateExcludeColumns = updateExcludeColumns;
	}

	public Set<String> getBaseUpdateExcludeColumns() {
		return baseUpdateExcludeColumns;
	}

	public void setBaseUpdateExcludeColumns(Set<String> baseUpdateExcludeColumns) {
		this.baseUpdateExcludeColumns = baseUpdateExcludeColumns;
	}

	public Map<String, Set<String>> getDeleteSetColumns() {
		return deleteSetColumns;
	}

	public void setDeleteSetColumns(Map<String, Set<String>> deleteSetColumns) {
		this.deleteSetColumns = deleteSetColumns;
	}

	public Set<String> getBaseDeleteSetColumns() {
		return baseDeleteSetColumns;
	}

	public void setBaseDeleteSetColumns(Set<String> baseDeleteSetColumns) {
		this.baseDeleteSetColumns = baseDeleteSetColumns;
	}

	public Map<String, String> getMapperInsertKeyProperty() {
		return mapperInsertKeyProperty;
	}

	public void setMapperInsertKeyProperty(Map<String, String> mapperInsertKeyProperty) {
		this.mapperInsertKeyProperty = mapperInsertKeyProperty;
	}

	public String getFlagColumnName() {
		return flagColumnName;
	}

	public void setFlagColumnName(String flagColumnName) {
		this.flagColumnName = flagColumnName;
	}

	public String getFlagValidValue() {
		return flagValidValue;
	}

	public void setFlagValidValue(String flagValidValue) {
		this.flagValidValue = flagValidValue;
	}

	public String getFlagInValidValue() {
		return flagInValidValue;
	}

	public void setFlagInValidValue(String flagInValidValue) {
		this.flagInValidValue = flagInValidValue;
	}

	public String getBasePackageDao() {
		return basePackageDao;
	}

	public void setBasePackageDao(String basePackageDao) {
		this.basePackageDao = basePackageDao;
	}

	public String getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(String baseDao) {
		this.baseDao = baseDao;
	}

	public String getKeyProperty() {
		return keyProperty;
	}

	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	public Set<String> getBaseWhereColumns() {
		return baseWhereColumns;
	}

	public void setBaseWhereColumns(Set<String> baseWhereColumns) {
		this.baseWhereColumns = baseWhereColumns;
	}
	
}
