package com.itopener.tools.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MemoTest {

	
	@Test
	public void init(){
		try {
			Config config = new Config();
			config.setAuthor("fuwei.deng");
			config.setBasePackageModel("com.itopener.memo.model");
			config.setBasePackageMapper("com.itopener.memo.mapper");
			config.setBasePackageDao("com.itopener.memo.dao");
			config.setBasePackageCondition("com.itopener.memo.conditions");
//			config.setImplementsInterface("BaseCondition");
			config.setBaseDao("com.itopener.framework.base.BaseDao");
			config.setBasePath("D:/workspace-itopener/springboot/itopener-parent/memo-parent/memo-model/src/main/java/");
			config.setDriver("com.mysql.jdbc.Driver");
			config.setUrl("jdbc:mysql://127.0.0.1:3306/itopener_memo?useUnicode=true&characterEncoding=utf8&autoReconnect=true");
			config.setPassword("root");
			config.setUser("root");
			config.setTables("t_user,t_memo");
			config.setTablePrefix("t_");
//		Map<String, String> mapperInsertKeyProperty = new HashMap<>();
//		mapperInsertKeyProperty.put("st_activity", "id");
//		config.setMapperInsertKeyProperty(mapperInsertKeyProperty);
//			config.setKeyProperty("id");
			
//			config.setExtendsModel("BaseConditionModel");
			config.setImplementsInterface("Serializable");
			config.getImports().add("java.io.Serializable");
			config.setGenerateSerialVersionUID(true);
			
			config.setConditionInterface("BaseCondition");
			config.getConditionImports().add("com.itopener.framework.base.BaseCondition");
			
			config.setExcludeColumns(new HashSet<String>());
//			config.getExcludeColumns().add("id");
			
			config.setUpdateExcludeColumns(new HashMap<String, Set<String>>());
			config.getUpdateExcludeColumns().put("t_user", new HashSet<String>());
			config.getUpdateExcludeColumns().get("t_user").add("create_time");
			config.getUpdateExcludeColumns().get("t_user").add("user_id");
			config.getUpdateExcludeColumns().put("t_memo", new HashSet<String>());
			config.getUpdateExcludeColumns().get("t_memo").add("create_time");
			
			config.setWhereRangeColumns(new HashMap<String, Set<String>>());
//		config.getWhereRangeColumns().put("sys_dictionary", new HashSet<String>());
//		config.getWhereRangeColumns().get("sys_dictionary").add("create_time");
			
			config.setLikeColumns(new HashMap<String, Set<String>>());
//		config.getLikeColumns().put("st_activity", new HashSet<String>());
//		config.getLikeColumns().get("st_activity").add("title");
			
//		config.setWhereColumns(new HashMap<String, Set<String>>());
//		config.getWhereColumns().put("sys_dictionary", new HashSet<String>());
//		config.getWhereColumns().get("sys_dictionary").add("id");
			
			config.getBaseWhereColumns().add("id");
			
			config.getWhereColumns().put("t_memo", new HashSet<String>());
			config.getWhereColumns().get("t_memo").add("user_id");
			
//		config.getBaseInsertExcludeColumns().add("id");
			
//			config.getBaseUpdateExcludeColumns().add("user_id");
			
//		config.getBaseDeleteSetColumns().add("update_time");
//		config.getBaseDeleteSetColumns().add("update_user");
			
//		config.setFlagColumnName("is_valid");
//		config.setFlagInValidValue("1");
//		config.setFlagValidValue("0");
			
			long start = System.currentTimeMillis();
			new Generator().generate(config);
			System.out.println("use time :" + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
