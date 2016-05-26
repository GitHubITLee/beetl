package com.goldenweb.beetl.text;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.om.ObjectUtil;
import org.beetl.core.resource.FileResourceLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/*.xml"})
public class GroupTemplateFactoryTest {
	
	Logger logger = LogManager.getLogger(this.getClass());
	
  @Before
	public void Before(){
		logger.info("Before");
	}
	
	@BeforeTransaction
	public void BeforeTransaction(){
		logger.info("BeforeTransaction");
		
	}
	
	@Test
	public void groupTemplate() throws Exception{
//	  Map<String,String> map = new HashMap<String, String>();
//    map.put("name", "lee");
//    String render = templateUtils.binding("index", WebAppResourceLoader.class, map);
//    logger.info(render);
//	  Configuration configuration = Configuration.defaultConfiguration();
//	  FileResourceLoader webAppResourceLoader = new FileResourceLoader();
//	  GroupTemplate groupTemplate = new GroupTemplate(webAppResourceLoader, configuration);
//	  Template t = groupTemplate.getTemplate("D:\\eclipse\\workspace\\beetl\\WebContent\\WEB-INF\\config\\template\\index.html");
//	  Map<String,String> map = new HashMap<String, String>();
//	  map.put("name", "lee");
//	  t.binding(map);
	  logger.info(ObjectUtil.getClassByName("SimpleTag"));
	}
	
	@AfterTransaction
	public void AfterpageTest(){
		logger.info("AfterTransaction");
	}
}
