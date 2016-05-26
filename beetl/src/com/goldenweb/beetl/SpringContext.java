package com.goldenweb.beetl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName: SpringContext
 * @Description: TODO(Spring上下文)
 * @author Lee
 * @date 2014-2-10 下午3:40:16
 */
public class SpringContext implements ApplicationContextAware{

    protected static ApplicationContext context;
    
    private static Logger logger = LoggerFactory.getLogger(SpringContext.class);
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("注入ApplicationContext到SpringContext:" + applicationContext);
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(String name){
    	assertContextInjected();
		return (T) context.getBean(name);
    }
    
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return context.getBean(requiredType);
	}
    
	private static void assertContextInjected() {
		if (context == null) {
			throw new IllegalStateException(
					"applicaitonContext未注入,请在applicationContext.xml中定义SpringContext");
		}
	}
    
}