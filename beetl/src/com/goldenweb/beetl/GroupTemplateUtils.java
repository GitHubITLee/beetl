package com.goldenweb.beetl;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.om.ObjectUtil;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupTemplateUtils {
  
  @Autowired
  private BeetlGroupUtilConfiguration beetlConfig;
  
  private GroupTemplate getGroupTemplate(
      ResourceLoader resourceLoader, Configuration configuration) {
    beetlConfig.setResourceLoader(resourceLoader);
    GroupTemplate groupTemplate = beetlConfig.getGroupTemplate();
    groupTemplate.setConf(configuration);
    return groupTemplate;
  }

  /**
   * @Description WebApp资源加载
   * @Title getWebAppGroupTemplate
   * @return GroupTemplate
   * @author Lee
   * @throws IOException 
   * @time 2016-5-12 上午10:30:15
   */
  public GroupTemplate getWebAppGroupTemplate() throws IOException {
    return getGroupTemplate(new WebAppResourceLoader(), Configuration.defaultConfiguration());
  }

	/**
	 * @Description String资源加载
	 * @Title getStringGroupTemplate
	 * @return GroupTemplate
	 * @throws IOException
	 * @author Lee
	 * @time 2016-5-12 上午10:31:01
	 */
	public GroupTemplate getStringGroupTemplate() throws IOException {
	  return getGroupTemplate(new StringTemplateResourceLoader(), Configuration.defaultConfiguration());
	}
	
	/**
	 * 注册标签
	 * @Title registerTag
	 * @param groupTemplate
	 * @param map
	 * @author Lee
	 */
	public void registerTag(GroupTemplate groupTemplate, Map<String,Class> map){
    for(Entry<String, Class> entry : map.entrySet()){
      String name = entry.getKey();
      Class clsName = entry.getValue();
      groupTemplate.registerTag(name, clsName);
    }
  }
	
	/**
   * @Description 关闭
   * @Title closeGroupTemplate
   * @author Lee
   * @time 2016-5-12 上午10:34:09
   */
  public void closeGroupTemplate(){
    this.beetlConfig.getGroupTemplate().close();
  }
  
  /**
   * @Description 初始化GroupTemplate
   * @Title getGroupTemplate
   * @param c
   * @return GroupTemplate
   * @throws IOException
   * @author Lee
   * @time 2016-5-24 下午2:20:12
   */
  public GroupTemplate getGroupTemplate(Class c) throws IOException{
    GroupTemplate g = null;
    if(c.getSimpleName().equals("WebAppResourceLoader")){
      g = this.getWebAppGroupTemplate();
    }
    if(c.getSimpleName().equals("StringTemplateResourceLoader")){
      g = this.getStringGroupTemplate();
    }
    return g;
  }
}
