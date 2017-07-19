package com.itopener.tools.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TMSTest {

	
	@Test
	public void init(){
		try {
			Config config = new Config();
			config.setAuthor("fuwei.deng");
			config.setBasePackageModel("com.itopener.tms.model");
			config.setBasePackageMapper("com.itopener.tms.mapper");
			config.setBasePackageDao("com.itopener.tms.dao");
			config.setBasePackageCondition("com.itopener.tms.conditions");
			config.setBaseDao("com.itopener.framework.base.BaseDao");
			config.setBasePath("D:/workspace-neon/itopener/itopener-parent/tms-parent/tms-model/src/main/java/");
			config.setDriver("com.mysql.jdbc.Driver");
			config.setUrl("jdbc:mysql://127.0.0.1:3306/itopener_tms?useUnicode=true&characterEncoding=utf8&autoReconnect=true");
			config.setPassword("root");
			config.setUser("root");
			config.setTables("t_user");
			config.setTablePrefix("t_");
//		Map<String, String> mapperInsertKeyProperty = new HashMap<>();
//		mapperInsertKeyProperty.put("st_activity", "id");
//		config.setMapperInsertKeyProperty(mapperInsertKeyProperty);
//			config.setKeyProperty("id");
			
//			config.setExtendsModel("BaseConditionModel");
//			config.getImports().add("com.itopener.framework.model.BaseConditionModel");
			config.setGenerateSerialVersionUID(true);
			
			config.setExcludeColumns(new HashSet<String>());
//			config.getExcludeColumns().add("id");
			
			config.setUpdateExcludeColumns(new HashMap<String, Set<String>>());
			config.getUpdateExcludeColumns().put("sale_integral_bll_type", new HashSet<String>());
			config.getUpdateExcludeColumns().get("sale_integral_bll_type").add("create_user");
			config.getUpdateExcludeColumns().get("sale_integral_bll_type").add("create_time");
			
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
			
			config.getWhereColumns().put("sale_integral_bll_type", new HashSet<String>());
			config.getWhereColumns().get("sale_integral_bll_type").add("name");
			config.getWhereColumns().get("sale_integral_bll_type").add("status");
			
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
