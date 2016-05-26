package com.goldenweb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.beetl.core.resource.WebAppResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goldenweb.beetl.TemplateUtils;
import com.goldenweb.beetl.text.Commodity;


@Controller
@RequestMapping("/bee")
public class BeetlController{
  
  Logger logger = LogManager.getLogger(this.getClass());
  
  @Autowired
  private TemplateUtils templateUtils;
  
  @RequestMapping(value="/{busName}/{urlName}", method=RequestMethod.GET)
  public void showTest(@PathVariable String busName,
      @PathVariable String urlName, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
   Map<String,Object> map = new HashMap<String, Object>();
    map.put("name", "lee");
    List list = new ArrayList();
    for (int i = 0; i < 10000; i++) {
      Commodity commodity = new Commodity("海盟"+i,"海盟"+i);
      list.add(commodity);
    }
    map.put("list", list);
    String render = templateUtils.renderTo(StringUtils.join(busName,"\\",urlName), WebAppResourceLoader.class, map);
//    String render = templateUtils.render(urlName, WebAppResourceLoader.class, map);
    logger.info(render);
  }
    
}
