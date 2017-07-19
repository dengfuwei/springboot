package com.itopener.tools.generator;

/**
 * @Description: 数据类型对应枚举
 * @author 邓夫伟
 * @date 2016年7月25日 下午3:59:39 
 * @version Ver 1.0
 */
public enum FieldType {
	
	BIT("boolean", "", 4, "java.lang.Boolean", "BIT"),
	FLOAT("float", "", 1, "java.lang.Float", "FLOAT"),
	CHAR("char", "", 1, "java.lang.Character", "CHAR"),
	TIMESTAMP("Timestamp", "java.sql.Timestamp", 3, "java.sql.Timestamp", "TIMESTAMP"),
	OTHER("Object", "", 3, "java.lang.Object", "OTHER"),
	UNDEFINED("Object", "", 3, "java.lang.Object", "UNDEFINED"),
	TINYINT("byte", "", 1, "java.lang.Byte", "TINYINT"),
	REAL("float", "", 1, "java.lang.Float", "REAL"),
	VARCHAR("String", "", 2, "java.lang.String", "VARCHAR"),
	BINARY("byte[]", "", 3, "", "BINARY"),
	BLOG("String", "", 2, "java.lang.String", "BLOG"),
	NVARCHAR("String", "", 2, "java.lang.String", "NVARCHAR"),
	SMALLINT("short", "", 1, "java.lang.Short", "SMALLINT"),
	DOUBLE("double", "", 1, "java.lang.Double", "DOUBLE"),
	LONGVARCHAR("String", "", 2, "java.lang.String", "LONGVARCHAR"),
	VARBINARY("String[]", "", 3, "", "VARBINARY"),
	CLOB("String", "", 2, "java.lang.String", "CLOB"),
	NCHAR("char", "", 1, "java.lang.Character", "NCHAR"),
	INTEGER("int", "", 1, "java.lang.Integer", "INTEGER"),
	NUMERIC("double", "", 1, "java.lang.Double", "NUMERIC"),
	DATE("Date", "java.util.Date", 3, "java.util.Date", "DATE"),
	LONGVARBINARY("String", "", 2, "java.lang.String", "LONGVARBINARY"),
	BOOLEAN("boolean", "", 4, "java.lang.Boolean", "BOOLEAN"),
	NCLOB("String", "", 2, "java.lang.String", "NCLOB"),
	BIGINT("long", "", 1, "java.lang.Long", "BIGINT"),
	DECIMAL("BigDecimal", "java.math.BigDecimal", 3, "java.math.BigDecimal", "DECIMAL"),
	TIME("Timestamp", "java.sql.Timestamp", 3, "java.sql.Timestamp", "TIMESTAMP"),
	NULL("String", "", 2, "java.lang.String", "NULL"),
	CURSOR("float", "", 1, "java.lang.Float", "CURSOR"),
	ARRAY("Object[]", "", 3, "", "ARRAY"),
	TEXT("String", "", 2, "java.lang.String", "VARCHAR"),
	DATETIME("Timestamp", "java.sql.Timestamp", 3, "java.sql.Timestamp", "TIMESTAMP"),
	INT("int", "", 1, "java.lang.Integer", "INTEGER");
	
	/** 数据类型对应的java类型（类或基础数据类型）*/
	private String type;
	
	/** 数据类型对应的java类完整名称，如果不用import则设置为空字符串*/
	private String importName;
	
	/** 数据类型 1:基础数据类型；2：string； 3：对象；4：boolean*/
	private int dataType;
	
	/** 数据类型对应类的全名*/
	private String classFullName;
	
	/** mybatis的jdbcType*/
	private String jdbcType;
	
	FieldType(String type, String importName, int dataType, String classFullName, String jdbcType){
		this.type = type;
		this.importName = importName;
		this.dataType = dataType;
		this.classFullName = classFullName;
		this.jdbcType = jdbcType;
	}
	
	public String getType(){
		return type;
	}
	
	public String getImportName() {
		return importName;
	}

	public int getDataType() {
		return dataType;
	}

	public String getClassFullName() {
		return classFullName;
	}

	public String getJdbcType() {
		return jdbcType;
	}
	
}
