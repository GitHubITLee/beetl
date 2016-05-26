package com.goldenweb.beetl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.misc.BeetlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemplateUtils {
  
  @Autowired
  private GroupTemplateUtils groupTemplateUtils;
	
  private Template getTemplate(String template, Class c, Map<?, ?> map) throws IOException{
    template = StringUtils.join(ConfigUtils.getStr("template.path"), template,
        ConfigUtils.getStr("page.suffix"));
    GroupTemplate groupTemplate = groupTemplateUtils.getGroupTemplate(c);
    
    Map<String,Class> tagMap = new HashMap<String, Class>();
    tagMap.put("tag", SimpleTag.class);
    groupTemplateUtils.registerTag(groupTemplate, tagMap);
    
    Template t = groupTemplate.getTemplate(template);
    t.binding(map);
    return t;
  }
	
  /**
   * 渲染结果
   * @param template 模板路径
   * @param c 资源加载器
   * @param map 参数
   * @return String
   * @author Lee
   * @throws IOException 
   */
	public String render(String template, Class c, Map<?, ?> map) throws IOException{
	  Template t = this.getTemplate(template, c, map);
    return t.render();
	}
	
	/**
   * 渲染结果输出FileOutputStream
   * @param template 模板路径
   * @param c 资源加载器
   * @param map 参数
   * @author Lee
	 * @throws IOException 
   */
	public String renderTo(String template, Class c, Map<?, ?> map) throws IOException{
	  Template t = this.getTemplate(template, c, map);
    String path = StringUtils.join(BeetlUtil.getWebRoot(),
        ConfigUtils.getStr("page.path"), template,
        ConfigUtils.getStr("page.suffix"));
    File file = new File(path);
    FileUtils.createFile(file);
    FileOutputStream fos = new FileOutputStream(file);
    t.renderTo(fos);
    fos.flush();
    fos.close();
    return StringUtils.join(template,  ConfigUtils.getStr("page.suffix"));
  }
}
