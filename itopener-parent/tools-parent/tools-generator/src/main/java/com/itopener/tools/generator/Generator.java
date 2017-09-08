package com.itopener.tools.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

public class Generator {
	
	private final Logger log = Logger.getLogger(Generator.class);
	
	/**
	 * @desc: 入口
	 * @param config
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午4:02:57
	 */
	public void generate(Config config){
		try {
			//TODO 检查配置是否正确，如必填项
			
			Utils.config = config;
			List<TableObject> tables = getTables();
			createModelFile(tables);
			createConditionFile(tables);
			createXmlFile(tables);
			createDaoFile(tables);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @desc: 获取数据库表的相关对象
	 * @return
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午4:03:07
	 */
	private List<TableObject> getTables(){
		DB db = new DB();
		
		try {
			Connection conn = db.getConn(Utils.config.getDriver(), Utils.config.getUrl(), Utils.config.getUser(), Utils.config.getPassword());
			DatabaseMetaData metaData = conn.getMetaData();
			List<TableObject> objectList = new ArrayList<>();
			ResultSet tablesResult = null;
			
			String tables = Utils.config.getTables();
			if(StringUtils.isNullOrEmpty(tables)){
				tablesResult = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
				handleTableResult(objectList, metaData, tablesResult);
			} else{
				for(String table : tables.split(",")){
					try {
						tablesResult = metaData.getTables(null, "%", table, new String[]{"TABLE"});
						handleTableResult(objectList, metaData, tablesResult);
					} catch (Exception e) {
						log.error("处理表信息出现异常，表名：" + table, e);
					}
				}
			}
			
			return objectList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * @desc: 处理表信息的查询结果
	 * @param objectList
	 * @param metaData
	 * @param tablesResult
	 * @throws SQLException
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午4:12:12
	 */
	private void handleTableResult(List<TableObject> objectList, DatabaseMetaData metaData, ResultSet tablesResult) throws SQLException{
		if(tablesResult == null){
			return ;
		}
		while(tablesResult.next()){
			TableObject object = new TableObject();
			String tableName = tablesResult.getString("TABLE_NAME");
			object.setTableName(tableName);
			object.setClassComment(tablesResult.getString("REMARKS"));
			object.setImports(new HashSet<String>());
			
			//主键
			ResultSet primaryKeyResult = metaData.getPrimaryKeys(null, null, tableName);
			List<TableField> primaryKeyList = new ArrayList<>();
			while(primaryKeyResult.next()){
				String columnName = primaryKeyResult.getString("COLUMN_NAME");
				
				TableField field = new TableField();
				field.setColumnName(columnName);
				primaryKeyList.add(field);
			}
			object.setPrimaryKeys(primaryKeyList);
			
			//一般列
			ResultSet columnsResult = metaData.getColumns(null, null, tableName, "%");
			List<TableField> fieldList = new ArrayList<>();
			while(columnsResult.next()){
				String columnName = columnsResult.getString("COLUMN_NAME");
//				if(excludeColumns != null && excludeColumns.size() > 0 && excludeColumns.contains(columnName)){
//					continue;
//				}
				boolean isPrimaryKey = false;
				for(TableField item: primaryKeyList){
					if(item.getColumnName().equals(columnName)){
						item.setColumnType(columnsResult.getString("TYPE_NAME"));
						item.setComment(columnsResult.getString("REMARKS"));
						item.setPrimaryKey(true);
						object.getImports().add(item.getTypeImportName());
						isPrimaryKey = true;
					}
				}
				if(isPrimaryKey){
					continue;
				}
				
				TableField field = new TableField();
				field.setColumnName(columnName);
				field.setColumnType(columnsResult.getString("TYPE_NAME"));
				field.setComment(columnsResult.getString("REMARKS"));
				field.setPrimaryKey(false);
				object.getImports().add(field.getTypeImportName());
				fieldList.add(field);
			}
			object.setFields(fieldList);
			
			object.getImports().addAll(Utils.config.getImports());
			objectList.add(object);
		}
	}
	
	private String getBasePath(String basePackage){
		//获取生成java文件的路径
		String basePath = Utils.config.getBasePath();
		if(!basePath.endsWith("\\")){
			basePath += "\\";
		}
		
		if(!StringUtils.isNullOrEmpty(basePackage)){
			basePath += basePackage.replace(".", "\\") + "\\";
		}
		return basePath;
	}
	
	private void createFile(String basePath, String fileName, String content){
		FileWriter fw = null;
		BufferedWriter writer = null;
		//创建目录
		try {
			File dir = new File(basePath);
			if(!dir.exists()){
				dir.mkdirs();
			}
			//创建java文件
			File file = new File(basePath + fileName);
			file.createNewFile();
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer != null){
					writer.flush();
					writer.close();
				}
				if(fw != null){
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @desc: 生成java文件
	 * @param tables
	 * @param config
	 * @throws IOException
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午4:32:20
	 */
	private void createModelFile(List<TableObject> tables) throws IOException{
		String basePath = getBasePath(Utils.config.getBasePackageModel());
		
		//写文件到磁盘
		for(TableObject table: tables){
			writeToModelFile(basePath, table);
		}
	}
	
	/**
	 * @desc: 写文件
	 * @param basePath
	 * @param table
	 * @throws IOException
	 * @author 邓夫伟
	 * @date 2016年7月25日 下午4:33:06
	 */
	private void writeToModelFile(String basePath, TableObject table) throws IOException{
		StringBuilder java = new StringBuilder();
		//包声明
		java.append("package ").append(Utils.config.getBasePackageModel()).append(";\n\n");
		//引入类或接口
		if(table.getImports() != null){
			for(String item : table.getImports()){
				if(!StringUtils.isNullOrEmpty(item)){
					java.append("import ").append(item).append(";\n");
				}
			}
			java.append("\n");
		}
		//类注释
		java.append("/**\n");
		java.append(" * @desc ").append(table.getClassComment()).append("\n");
		java.append(" * @author ").append(Utils.config.getAuthor()).append("\n");
		java.append(" * @date ").append(Utils.format(new Date(), "yyyy-MM-dd HH:mm:ss")).append("\n");
		java.append(" */\n");
		//类声明
		java.append("public class ").append(table.getClassName());
		if(!StringUtils.isNullOrEmpty(Utils.config.getExtendsModel())){
			java.append(" extends ").append(Utils.config.getExtendsModel());
		}
		if(!StringUtils.isNullOrEmpty(Utils.config.getImplementsInterface())){
			java.append(" implements ").append(Utils.config.getImplementsInterface());
		}
		java.append(" {");
		//serialVersionUID
		if(Utils.config.isGenerateSerialVersionUID()){
			java.append("\n\n\t/** SerialVersionUID*/\n");
			java.append("\tprivate static final long serialVersionUID = " + new Random().nextLong() + "L;");
		}
		//类属性
		Set<String> excludeColumns = Utils.config.getExcludeColumns();
		for(TableField item: table.getAllField()){
			if(excludeColumns != null && excludeColumns.size() > 0 && excludeColumns.contains(item.getColumnName())){
				continue;
			}
			java.append("\n\n\t/** ").append(item.getComment()).append("*/\n");
			java.append("\tprivate ").append(item.getTypeName()).append(" ").append(item.getFieldName()).append(";");
		}
		
		//类属性的getter和setter
		for(TableField item: table.getAllField()){
			if(excludeColumns != null && excludeColumns.size() > 0 && excludeColumns.contains(item.getColumnName())){
				continue;
			}
			java.append("\n\n");
			//类属性的getter
			java.append("\t").append("public ").append(item.getTypeName()).append(" ").append(item.getGetterName()).append("() {\n");
			java.append("\t\t").append("return ").append(item.getFieldName()).append(";\n");
			java.append("\t}\n\n");
			//类属性的setter
			java.append("\t").append("public ").append(table.getClassName()).append(" ").append(item.getSetterName()).append("(").append(item.getTypeName()).append(" ").append(item.getFieldName()).append(") {\n");
			java.append("\t\t").append("this.").append(item.getFieldName()).append(" = ").append(item.getFieldName()).append(";\n");
			java.append("\t\t").append("return this;\n");
			java.append("\t}");
		}
		
		java.append("\n}");
		
		createFile(basePath, table.getClassName() + ".java", java.toString());
	}
	
	/**
	 * @desc: 生成查询条件的类
	 * @param tables
	 * @throws IOException
	 * @author 邓夫伟
	 * @date 2016年7月26日 下午2:41:06
	 */
	private void createConditionFile(List<TableObject> tables) throws IOException{
		//获取生成java文件的路径
		String basePath = getBasePath(Utils.config.getBasePackageCondition());
		
		//写文件到磁盘
		for(TableObject table: tables){
			writeToConditionFile(basePath, table);
		}
	}
	
	/**
	 * @desc: 写入条件java文件
	 * @param basePath
	 * @param table
	 * @throws IOException
	 * @author 邓夫伟
	 * @date 2016年7月26日 下午2:42:31
	 */
	private void writeToConditionFile(String basePath, TableObject table) throws IOException{
		StringBuilder condition = new StringBuilder();
		//包声明
		condition.append("package ").append(Utils.config.getBasePackageCondition()).append(";\n");
		
		Set<String> whereRangeColumnsSet = Utils.config.getWhereRangeColumns().get(table.getTableName());
		if(whereRangeColumnsSet == null){
			whereRangeColumnsSet = new HashSet<String>();
		}
		whereRangeColumnsSet.addAll(Utils.config.getBaseWhereRangeColumns());
		for(String item: whereRangeColumnsSet){
			TableField field = Utils.getTableField(table.getAllField(), item);
			if(!StringUtils.isNullOrEmpty(field.getTypeImportName())){
				condition.append("\nimport ").append(field.getTypeImportName()).append(";\n");
			}
		}
		
		condition.append("\nimport ").append(Utils.config.getBasePackageModel()).append(".").append(table.getClassName()).append(";\n");
		for(String item : Utils.config.getConditionImports()){
			condition.append("\nimport ").append(item).append(";\n");
		}
		
		//类注释
		condition.append("\n/**\n");
		condition.append(" * @desc ").append(table.getClassName()).append("查询条件辅助类\n");
		condition.append(" * @author ").append(Utils.config.getAuthor()).append("\n");
		condition.append(" * @date ").append(Utils.format(new Date(), "yyyy-MM-dd HH:mm:ss")).append("\n");
		condition.append(" */\n");
		//类声明
		condition.append("public class ").append(table.getConditionClassName());
		condition.append(" extends ").append(table.getClassName());
		if(!StringUtils.isNullOrEmpty(Utils.config.getConditionInterface())){
			condition.append(" implements ").append(Utils.config.getConditionInterface());
		}
		condition.append(" {\n");
		//serialVersionUID
		if(Utils.config.isGenerateSerialVersionUID()){
			condition.append("\n\t/** SerialVersionUID*/\n");
			condition.append("\tprivate static final long serialVersionUID = " + new Random().nextLong() + "L;\n");
		}
		
		StringBuilder fields = new StringBuilder();
		StringBuilder fieldsMethod = new StringBuilder();
		
		//范围查询条件属性
		for(String item: whereRangeColumnsSet){
			TableField field = Utils.getTableField(table.getAllField(), item);
			if(field == null){
				continue;
			}
			
			//field declare
			fields.append("\n\t/** ").append(StringUtils.isNullOrEmpty(field.getComment()) ? field.getFieldName() : field.getComment()).append("--最小值*/\n");
			fields.append("\tprivate ").append(field.getTypeName()).append(" ").append(field.getMinFieldName()).append(";\n");
			fields.append("\n\t/** ").append(StringUtils.isNullOrEmpty(field.getComment()) ? field.getFieldName() : field.getComment()).append("--最大值*/\n");
			fields.append("\tprivate ").append(field.getTypeName()).append(" ").append(field.getMaxFieldName()).append(";\n");
			
			//min getter and setter
			fieldsMethod.append("\n\tpublic void ").append(field.getMinSetterName()).append("(").append(field.getTypeName()).append(" ").append(field.getMinFieldName()).append(") {\n");
			fieldsMethod.append("\t\tthis.").append(field.getMinFieldName()).append(" = ").append(field.getMinFieldName()).append(";\n");
			fieldsMethod.append("\t}\n");
			fieldsMethod.append("\n\tpublic ").append(field.getTypeName()).append(" ").append(field.getMinGetterName()).append("() {\n");
			fieldsMethod.append("\t\treturn ").append(field.getMinFieldName()).append(";\n");
			fieldsMethod.append("\t}\n");
			
			//max getter and setter
			fieldsMethod.append("\n\tpublic void ").append(field.getMaxSetterName()).append("(").append(field.getTypeName()).append(" ").append(field.getMaxFieldName()).append(") {\n");
			fieldsMethod.append("\t\tthis.").append(field.getMaxFieldName()).append(" = ").append(field.getMaxFieldName()).append(";\n");
			fieldsMethod.append("\t}\n");
			fieldsMethod.append("\n\tpublic ").append(field.getTypeName()).append(" ").append(field.getMaxGetterName()).append("() {\n");
			fieldsMethod.append("\t\treturn ").append(field.getMaxFieldName()).append(";\n");
			fieldsMethod.append("\t}\n");
		}
		
		Set<String> likeColumnsSet = Utils.config.getLikeColumns().get(table.getTableName());
		if(likeColumnsSet == null){
			likeColumnsSet = new HashSet<String>();
		}
		likeColumnsSet.addAll(Utils.config.getBaseLikeColumns());
		for(String item: likeColumnsSet){
			TableField field = Utils.getTableField(table.getAllField(), item);
			if(field == null){
				continue;
			}
			
			//field declare
			fields.append("\n\t/** ").append(StringUtils.isNullOrEmpty(field.getComment()) ? field.getFieldName() : field.getComment()).append("--模糊匹配*/\n");
			fields.append("\tprivate ").append(field.getTypeName()).append(" ").append(field.getLikeFieldName()).append(";\n");
			
			//getter and setter
			fieldsMethod.append("\n\tpublic void ").append(field.getLikeSetterName()).append("(").append(field.getTypeName()).append(" ").append(field.getLikeFieldName()).append(") {\n");
			fieldsMethod.append("\t\tthis.").append(field.getLikeFieldName()).append(" = ").append(field.getLikeFieldName()).append(";\n");
			fieldsMethod.append("\t}\n");
			fieldsMethod.append("\n\tpublic ").append(field.getTypeName()).append(" ").append(field.getLikeGetterName()).append("() {\n");
			fieldsMethod.append("\t\treturn ").append(field.getLikeFieldName()).append(";\n");
			fieldsMethod.append("\t}\n");
		}
		
		condition.append(fields).append(fieldsMethod).append("}");
		
		createFile(basePath, table.getConditionClassName() + ".java", condition.toString());
	}
	
	/**
	 * @desc: 生成mapper xml配置文件
	 * @param tables
	 * @author 邓夫伟
	 * @throws IOException 
	 * @date 2016年7月25日 下午6:54:57
	 */
	public void createXmlFile(List<TableObject> tables) throws IOException{
		//获取生成java文件的路径
		String basePath = getBasePath(Utils.config.getBasePackageMapper());
		
		//写文件到磁盘
		for(TableObject table: tables){
			writeToMapperFile(basePath, table);
		}
	}
	
	private void writeToMapperFile(String basePath, TableObject table) throws IOException{
		StringBuilder mapper = new StringBuilder();
		//文件头
		mapper.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		mapper.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
		mapper.append("<mapper namespace=\"").append(Utils.config.getBasePackageMapper()).append(".").append(table.getClassName()).append("Mapper\">\n");
		//resultmap
		mapper.append("\n\t<resultMap id=\"BaseResultMap\" type=\"").append(table.getFullName()).append("\">\n");
		//所有列名
		StringBuilder columns = new StringBuilder();
		StringBuilder insertColumns = new StringBuilder();
		int columnsColNum = -8;
		int insertColNum = -8;
		for(TableField field: table.getAllField()){
			mapper.append("\t\t<").append(field.isPrimaryKey() ? "id" : "result").append(" column=\"").append(field.getColumnName()).append("\" property=\"").append(field.getFieldName()).append("\" jdbcType=\"").append(field.getJdbcType()).append("\" />\n");
			
			Set<String> insertExcludeColumns = Utils.config.getInsertExcludeColumns().get(table.getTableName());
			if(insertExcludeColumns == null){
				insertExcludeColumns = new HashSet<>();
			}
			insertExcludeColumns.addAll(Utils.config.getBaseInsertExcludeColumns());
			
			if(insertExcludeColumns.contains(field.getColumnName())){
				if(columns.length() > 0){
					columns.append(", ");
				}
				if(columns.length() - columnsColNum > Utils.config.getMaxLineWidth()){
					columnsColNum = columns.length() - 8;
					columns.append("\n\t\t");
				}
				columns.append(field.getColumnName());
				continue;
			}
			
			if(insertColumns.length() > 0){
				insertColumns.append(", ");
			}
			if(insertColumns.length() - insertColNum > Utils.config.getMaxLineWidth()){
				insertColNum = insertColumns.length() - 8;
				insertColumns.append("\n\t\t");
			}
			insertColumns.append(field.getColumnName());
		}
		//resultmap
		mapper.append("\t</resultMap>\n");
//			writer.write(mapper.toString());
		//insert column list
		if(columns.length() > 0){
			mapper.append("\n\t<sql id=\"Insert_Column_List\">\n");
			insertColumns.insert(0, "\t\t").append("\n");
			mapper.append(insertColumns);
			mapper.append("\t</sql>\n");
		}
		//all column list
		mapper.append("\n\t<sql id=\"Column_List\">\n");
		if(columns.length() > 0){
			mapper.append(columns.insert(0, "\t\t").append(", <include refid=\"Insert_Column_List\" />").append("\n").toString());
		} else{
			insertColumns.insert(0, "\t\t").append("\n");
			mapper.append(insertColumns);
		}
		mapper.append("\t</sql>\n");
		
		//where
		where(mapper, table);
		
		//select
		select(mapper, table);
		
		//selectCount
		selectCount(mapper, table);
		
		//select by primaryKey
		selectByPrimaryKey(mapper, table);
		
		//insert
		insert(mapper, table, columns, insertColumns);
		
		//update
		update(mapper, table);
		
		//delete
		delete(mapper, table);
		
		mapper.append("</mapper>");
		
		createFile(basePath, table.getClassName() + "Mapper.xml", mapper.toString());
	}
	
	private void where(StringBuilder mapper, TableObject table){
		mapper.append("\n\t<sql id=\"Where\">\n");
		mapper.append("\t\t<where>\n");
		
		Set<String> whereColumnsSet = Utils.config.getWhereColumns().get(table.getTableName());
		if(whereColumnsSet == null){
			whereColumnsSet = new HashSet<String>();
		}
		whereColumnsSet.addAll(Utils.config.getBaseWhereColumns());
		for(String item : whereColumnsSet){
			TableField field = Utils.getTableField(table.getAllField(), item);
			if(field == null){
				continue;
			}
			mapper.append("\t\t\t<if test=\"").append(Utils.getMapperWhere(field.getFieldName(), field.getColumnType())).append("\">\n");
			mapper.append("\t\t\t\tand ").append(field.getColumnName()).append(" = ").append("#{").append(field.getFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}\n");
			mapper.append("\t\t\t</if>\n");
		}
		
		Set<String> likeColumnsSet = Utils.config.getLikeColumns().get(table.getTableName());
		if(likeColumnsSet == null){
			likeColumnsSet = new HashSet<String>();
		}
		for(String item : likeColumnsSet){
			TableField field = Utils.getTableField(table.getAllField(), item);
			if(field == null){
				continue;
			}
			mapper.append("\t\t\t<if test=\"").append(Utils.getMapperWhere(field.getLikeFieldName(), field.getColumnType())).append("\">\n");
			mapper.append("\t\t\t\tand ").append(field.getColumnName()).append(" like concat('%', ").append("#{").append(field.getLikeFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}, '%')\n");
			mapper.append("\t\t\t</if>\n");
		}
		
		Set<String> whereRangeColumnsSet = Utils.config.getWhereRangeColumns().get(table.getTableName());
		if(whereRangeColumnsSet == null){
			whereRangeColumnsSet = new HashSet<String>();
		}
		for(String item : whereRangeColumnsSet){
			TableField field = Utils.getTableField(table.getAllField(), item);
			if(field == null){
				continue;
			}
			mapper.append("\t\t\t<if test=\"").append(Utils.getMapperWhere(field.getMinFieldName(), field.getColumnType())).append("\">\n");
			mapper.append("\t\t\t\tand ").append(field.getColumnName()).append(" &gt; ").append("#{").append(field.getMinFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}\n");
			mapper.append("\t\t\t</if>\n");
			
			mapper.append("\t\t\t<if test=\"").append(Utils.getMapperWhere(field.getMaxFieldName(), field.getColumnType())).append("\">\n");
			mapper.append("\t\t\t\tand ").append(field.getColumnName()).append(" &lt; ").append("#{").append(field.getMinFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}\n");
			mapper.append("\t\t\t</if>\n");
		}
		if(!StringUtils.isNullOrEmpty(Utils.config.getFlagColumnName())){
			mapper.append("\t\t\tand ").append(Utils.config.getFlagColumnName()).append(" = ").append(Utils.config.getFlagValidValue()).append("\n");
		}
		mapper.append("\t\t</where>\n");
		mapper.append("\t</sql>\n");
	}
	
	private void insert(StringBuilder mapper, TableObject table, StringBuilder columns, StringBuilder insertColumns){
		mapper.append("\n\t<!-- 新增 -->\n\t<insert id=\"insert\" parameterType=\"").append(table.getFullName()).append("\"");
		
		String keyProperty = Utils.config.getKeyProperty();
		if(Utils.config.getMapperInsertKeyProperty() != null){
			String temp = Utils.config.getMapperInsertKeyProperty().get(table.getTableName());
			if(!StringUtils.isNullOrEmpty(temp)){
				keyProperty = temp;
			}
		}
		if(!StringUtils.isNullOrEmpty(keyProperty)){
			mapper.append(" keyProperty=\"").append(keyProperty).append("\" useGeneratedKeys=\"true\"");
		}
		mapper.append(">\n");
		
		mapper.append("\t\tinsert into ").append(table.getTableName()).append("(<include refid=\"").append(columns.length() > 0 ? "Insert_Column_List" : "Column_List").append("\" />)\n");	
		mapper.append("\t\tvalues (\n");
		String[] insertColumnsArr = insertColumns.toString().split(",");
		
		for(int i=0; i<insertColumnsArr.length; i++){
			String item = insertColumnsArr[i];
			TableField field = Utils.getTableField(table.getAllField(), item.trim());
			if(field == null){
				throw new RuntimeException("没有找到字段信息，字段为：" + item.trim());
			}
			mapper.append("\t\t\t#{").append(field.getFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}");
			if(i < insertColumnsArr.length - 1){
				mapper.append(",");
			} else{
				mapper.append(")");
			}
			mapper.append("\n");
		}
		mapper.append("\t</insert>\n");
	}
	
	private void update(StringBuilder mapper, TableObject table){
		mapper.append("\n\t<update id=\"update\" parameterType=\"").append(table.getFullName()).append("\">\n");
		mapper.append("\t\tupdate ").append(table.getTableName()).append("\n");
		mapper.append("\t\tset\n");
		boolean isFirst = true;
		for(TableField field: table.getAllField()){
			Set<String> updateExcludeColumns = Utils.config.getUpdateExcludeColumns().get(table.getTableName());
			if(updateExcludeColumns == null){
				updateExcludeColumns = new HashSet<>();
			}
			updateExcludeColumns.addAll(Utils.config.getBaseUpdateExcludeColumns());
			if(updateExcludeColumns.contains(field.getColumnName())){
				continue;
			}
			boolean isPrimaryKey = false;
			for(TableField item: table.getPrimaryKeys()){
				if(item.getColumnName().equals(field.getColumnName())){
					isPrimaryKey = true;
					break;
				}
			}
			if(isPrimaryKey){
				continue;
			}
			if(!isFirst){
				mapper.append(",\n");
			}
			mapper.append("\t\t\t").append(field.getColumnName()).append(" = #{").append(field.getFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}");
			isFirst = false;
		}
		mapper.append("\n\t\twhere ");
		isFirst = true;
		for(TableField field: table.getPrimaryKeys()){
			if(!isFirst){
				mapper.append("\n\t\t\tand");
			}
			mapper.append(field.getColumnName()).append(" = #{").append(field.getFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}");
			isFirst = false;
		}
		if(!StringUtils.isNullOrEmpty(Utils.config.getFlagColumnName())){
			mapper.append(isFirst ? "" : "\n\t\t\tand ").append(Utils.config.getFlagColumnName()).append(" = ").append(Utils.config.getFlagValidValue());
		}
		mapper.append("\n\t</update>\n");
	}
	
	private void delete(StringBuilder mapper, TableObject table){
		boolean isFirst = true;
		if(StringUtils.isNullOrEmpty(Utils.config.getFlagColumnName())){
			mapper.append("\n\t<delete id=\"delete\" parameterType=\"").append(table.getFullName()).append("\">\n");
			mapper.append("\t\tdelete from ").append(table.getTableName()).append("\n");
			mapper.append("\t\twhere ");
			for(TableField field: table.getPrimaryKeys()){
				if(!isFirst){
					mapper.append("\n\t\t\tand");
				}
				mapper.append(field.getColumnName()).append(" = #{").append(field.getFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}");
				isFirst = false;
			}
			mapper.append("\n\t</delete>\n");
		} else{
			Set<String> deleteSetColumns = Utils.config.getDeleteSetColumns().get(table.getTableName());
			if(deleteSetColumns == null) {
				deleteSetColumns = new HashSet<>();
			}
			deleteSetColumns.addAll(Utils.config.getBaseDeleteSetColumns());
			if(deleteSetColumns.size() > 0){
				mapper.append("\n\t<update id=\"delete\" parameterType=\"").append(table.getFullName()).append("\">\n");
				mapper.append("\t\tupdate ").append(table.getTableName()).append("\n");
				mapper.append("\t\tset\n");
				isFirst = true;
				for(TableField field: table.getAllField()){
					if(!deleteSetColumns.contains(field.getColumnName())){
						continue;
					}
					if(!isFirst){
						mapper.append(",\n");
					}
					mapper.append("\t\t\t").append(field.getColumnName()).append(" = #{").append(field.getFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}");
					isFirst = false;
				}
				mapper.append(",\n\t\t\t").append(Utils.config.getFlagColumnName()).append(" = ").append(Utils.config.getFlagInValidValue());
				mapper.append("\n\t\twhere ");
				isFirst = true;
				for(TableField field: table.getPrimaryKeys()){
					if(!isFirst){
						mapper.append("\n\t\t\tand");
					}
					mapper.append(field.getColumnName()).append(" = #{").append(field.getFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}");
					isFirst = false;
				}
				mapper.append(isFirst ? "" : "\n\t\t\tand ").append(Utils.config.getFlagColumnName()).append(" = ").append(Utils.config.getFlagValidValue());
				mapper.append("\n\t</update>\n");
			}
		}
	}
	
	private void selectByPrimaryKey(StringBuilder mapper, TableObject table){
		mapper.append("\n\t<select id=\"selectBy");
		if(table.getPrimaryKeys().size() == 1){
			TableField field = table.getPrimaryKeys().get(0);
			mapper.append(field.getFieldNameCamel()).append("\" parameterType=\"").append(field.getTypeFullName());
		} else if(table.getPrimaryKeys().size() > 1){
			mapper.append("PrimaryKey").append("\" parameterType=\"").append(table.getFullName());
		} else{
			throw new RuntimeException("数据表没有设置主键，表名为：" + table.getTableName());
		}
		mapper.append("\" resultMap=\"BaseResultMap\">\n");
		mapper.append("\t\tselect <include refid=\"Column_List\" />\n");
		mapper.append("\t\tfrom ").append(table.getTableName()).append("\n");
		mapper.append("\t\twhere ");
		boolean isFirst = true;
		for(TableField field: table.getPrimaryKeys()){
			if(!isFirst){
				mapper.append("\n\t\t\tand");
			}
			mapper.append(field.getColumnName()).append(" = #{").append(field.getFieldName()).append(",jdbcType=").append(field.getJdbcType()).append("}");
			isFirst = false;
		}
		if(!StringUtils.isNullOrEmpty(Utils.config.getFlagColumnName())){
			mapper.append(isFirst ? "" : "\n\t\t\tand ").append(Utils.config.getFlagColumnName()).append(" = ").append(Utils.config.getFlagValidValue());
		}
		mapper.append("\n\t</select>\n");
	}
	
	private void select(StringBuilder mapper, TableObject table){
		mapper.append("\n\t<select id=\"select\" parameterType=\"").append(table.getConditionClassFullName()).append("\"");
		mapper.append(" resultMap=\"BaseResultMap\">\n");
		mapper.append("\t\tselect <include refid=\"Column_List\" />\n");
		mapper.append("\t\tfrom ").append(table.getTableName()).append("\n");
		mapper.append("\t\t<include refid=\"Where\" />\n");
		mapper.append("\t</select>\n");
	}
	
	private void selectCount(StringBuilder mapper, TableObject table){
		mapper.append("\n\t<select id=\"selectCount\" parameterType=\"").append(table.getConditionClassFullName()).append("\"");
		mapper.append(" resultType=\"int\">\n");
		mapper.append("\t\tselect count(");
		if(table.getPrimaryKeys().size() == 1){
			mapper.append(table.getPrimaryKeys().get(0).getColumnName());
		} else if(table.getPrimaryKeys().size() > 1){
			mapper.append("1");
		} else{
			throw new RuntimeException("数据表没有设置主键，表名为：" + table.getTableName());
		}
		mapper.append(")\n");
		mapper.append("\t\tfrom ").append(table.getTableName()).append("\n");
		mapper.append("\t\t<include refid=\"Where\" />\n");
		mapper.append("\t</select>\n");
	}
	
	private void createDaoFile(List<TableObject> tables) throws IOException{
		String basePath = getBasePath(Utils.config.getBasePackageDao());
		
		//写文件到磁盘
		for(TableObject table: tables){
			writeToDaoFile(basePath, table);
		}
	}
	
	private void writeToDaoFile(String basePath, TableObject table) throws IOException{
		StringBuilder dao = new StringBuilder();
		//package
		dao.append("package ").append(Utils.config.getBasePackageDao()).append(";\n");
		//import
		dao.append("\nimport java.util.List;\n");
		dao.append("import javax.annotation.Resource;\n");
		dao.append("import org.springframework.stereotype.Repository;\n");
		dao.append("import com.github.pagehelper.PageHelper;\n");
		dao.append("\nimport ").append(Utils.config.getBaseDao()).append(";\n");
		dao.append("import ").append(table.getFullName()).append(";\n");
		dao.append("import ").append(Utils.config.getBasePackageCondition()).append(".").append(table.getConditionClassName()).append(";\n");
		//class annotation
		dao.append("\n@Repository\n");
		//class
		dao.append("public class ").append(table.getClassName()).append("Dao {\n");
		//namespace
		dao.append("\n\tprivate final String NAMESPACE = \"").append(Utils.config.getBasePackageMapper()).append(".").append(table.getClassName()).append("Mapper.\";\n");
		//baseDao annotation
		dao.append("\n\t@Resource\n");
		//baseDao
		StringBuilder baseDao = new StringBuilder(Utils.config.getBaseDao());
		StringBuilder baseDaoName = new StringBuilder(baseDao.substring(baseDao.lastIndexOf(".") + 1));
		dao.append("\tprivate ").append(baseDaoName);
		
		StringBuilder baseDaoFieldName = new StringBuilder(baseDaoName.toString());
		baseDaoFieldName.setCharAt(0, Character.toLowerCase(baseDaoFieldName.charAt(0)));
		dao.append(" ").append(baseDaoFieldName).append(";\n");
		
		//insert
		dao.append("\n\tpublic int insert(").append(table.getClassName()).append(" ").append(table.getClassFieldName()).append(") {\n");
		dao.append("\t\treturn ").append(baseDaoFieldName).append(".insert(NAMESPACE + \"insert\", ").append(table.getClassFieldName()).append(");\n");
		dao.append("\t}\n");
		
		//selectList
		dao.append("\n\tpublic List<").append(table.getClassName()).append("> selectList(").append(table.getConditionClassName()).append(" condition) {\n");
		dao.append("\t\treturn ").append(baseDaoFieldName).append(".selectList(NAMESPACE + \"select\", condition);\n");
		dao.append("\t}\n");
		
		//selectPage
		dao.append("\n\tpublic List<").append(table.getClassName()).append("> selectPage(").append(table.getConditionClassName()).append(" condition) {\n");
		dao.append("\t\tPageHelper.startPage(condition.getPage(), condition.getSize(), false);\n");
		dao.append("\t\treturn ").append(baseDaoFieldName).append(".selectList(NAMESPACE + \"").append("select\", condition);\n");
		dao.append("\t}\n");
		
		//selectCount
		dao.append("\n\tpublic int").append(" selectCount(").append(table.getConditionClassName()).append(" condition) {\n");
		dao.append("\t\treturn ").append(baseDaoFieldName).append(".selectOne(NAMESPACE + \"selectCount\", condition);\n");
		dao.append("\t}\n");
		
		//selectOne
		dao.append("\n\tpublic ").append(table.getClassName()).append(" selectOne(").append(table.getConditionClassName()).append(" condition) {\n");
		dao.append("\t\treturn ").append(baseDaoFieldName).append(".selectOne(NAMESPACE + \"select\", condition);\n");
		dao.append("\t}\n");
		
		//selectByPrimaryKey
		StringBuilder methodName = new StringBuilder();
		methodName.append("selectBy");
		dao.append("\n\tpublic ").append(table.getClassName());
		String fieldName = "";
		if(table.getPrimaryKeys().size() == 1){
			TableField field = table.getPrimaryKeys().get(0);
			fieldName = field.getFieldName();
			methodName.append(field.getFieldNameCamel());
			dao.append(" ").append(methodName).append("(").append(field.getTypeName()).append(" ").append(fieldName);
		} else if(table.getPrimaryKeys().size() > 1){
			fieldName = table.getClassFieldName();
			methodName.append("PrimaryKey");
			dao.append(" ").append(methodName).append("(").append(table.getClassName()).append(" ").append(fieldName);
		} else{
			throw new RuntimeException("数据表没有设置主键，表名为：" + table.getTableName());
		}
		dao.append(") {\n");
		dao.append("\t\treturn ").append(baseDaoFieldName).append(".selectOne(NAMESPACE + \"").append(methodName).append("\", ").append(fieldName).append(");\n");
		dao.append("\t}\n");
		
		//update
		dao.append("\n\tpublic int update(").append(table.getClassName()).append(" ").append(table.getClassFieldName()).append(") {\n");
		dao.append("\t\treturn ").append(baseDaoFieldName).append(".update(NAMESPACE + \"update\", ").append(table.getClassFieldName()).append(");\n");
		dao.append("\t}\n");
		
		//delete
		dao.append("\n\tpublic int delete(").append(table.getClassName()).append(" ").append(table.getClassFieldName()).append(") {\n");
		dao.append("\t\treturn ").append(baseDaoFieldName).append(".update(NAMESPACE + \"delete\", ").append(table.getClassFieldName()).append(");\n");
		dao.append("\t}\n");
		
		dao.append("\n}");
		
		createFile(basePath, table.getClassName() + "Dao.java", dao.toString());
	}
}
