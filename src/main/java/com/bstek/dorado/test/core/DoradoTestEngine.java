package com.bstek.dorado.test.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

import com.bstek.dorado.core.CommonContext;
import com.bstek.dorado.core.Configure;
import com.bstek.dorado.core.ConfigureStore;
import com.bstek.dorado.core.Context;
import com.bstek.dorado.core.EngineStartupListenerManager;
import com.bstek.dorado.core.el.DefaultExpressionHandler;
import com.bstek.dorado.core.el.Expression;
import com.bstek.dorado.core.el.ExpressionHandler;
import com.bstek.dorado.core.io.BaseResourceLoader;
import com.bstek.dorado.core.pkgs.PackageConfigurer;
import com.bstek.dorado.web.ConsoleUtils;

public class DoradoTestEngine {
	public static Set<String> BDF_MODEL = new HashSet<String>();
	public static Set<String> BDF_UFLO_MODEL = new HashSet<String>();
	private static List<String> propList = new ArrayList<String>();
	private static List<String> confList = new ArrayList<String>();
	static {
		BDF_MODEL.add("bdf");
		BDF_UFLO_MODEL.add("bdf2-uflo");
	}
	public static void main(String[] args) {
		Properties ps =	readProperties("com/bstek/bdf2/job/config/bdf2.job.properties");
		System.out.println(ps);
	}
	private static String locations = "";
    
	private static void initDorado() {
		confList.add("com/bstek/dorado/core/context.xml");
		confList.add("com/bstek/dorado/config/context.xml");
		confList.add("com/bstek/dorado/common/context.xml");
		confList.add("com/bstek/dorado/data/context.xml");
		confList.add("com/bstek/dorado/view/context.xml");
		confList.add("com/bstek/dorado/web/context.xml");
    }
	private static void initBDF(Set<String> bdfModels){
		 //PackageConfigurer configure = new CoreContextLocationConfigurer();
		 //addAddonConfigs(configure);
		//configure = new OrmContextLocationConfigurer();
		//addAddonConfigs(configure);
		propList.add("com/bstek/bdf2/core/config/bdf2.core.properties");
		confList.add("classpath:com/bstek/bdf2/core/config/bdf2-core-configs.xml");
		confList.add("classpath:com/bstek/bdf2/core/config/bdf2-security-configs.xml");
		propList.add("classpath:com/bstek/bdf2/core/orm/config/bdf2.orm.properties");
		confList.add("classpath:com/bstek/bdf2/core/orm/config/bdf2-orm-configs.xml");
		if(bdfModels != null && bdfModels.contains("bdf2-uflo")){
			propList.add("bdf2-uflo.properties");
			propList.add("uflo-client.properties");
			propList.add("uflo-console.properties");
			confList.add("bdf2-uflo-configs.xml");
			confList.add("uflo-context-configs.xml");        
			confList.add("uflo-console-configs.xml");        
			confList.add("uflo-client-configs.xml");
		 }
	}
    @SuppressWarnings("unused")
	private static void addAddonConfigs(PackageConfigurer configure){
		try {
			BaseResourceLoader resourceLoader = new BaseResourceLoader();
			String[] propertieFiles = configure.getPropertiesConfigLocations(resourceLoader);
			for (String propertyFile: propertieFiles){
				propList.add(propertyFile);
			}
			String[] contextFiles = configure.getContextConfigLocations(resourceLoader);
			if(contextFiles!=null)
			for (String contextFile: contextFiles){
				confList.add(contextFile);
			}
			//String[] servletContextFiles = configure.getServletContextConfigLocations(resourceLoader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void loadConfigureProperties(String configureLocation) {
		ConfigureStore configureStore = Configure.getStore();
		// 装载附加的基本配置信息
		ConsoleUtils.outputLoadingInfo("Loading configure from ["
				+ configureLocation + "]...");
		if (StringUtils.isNotEmpty(configureLocation)) {
			Properties properties = readProperties(configureLocation);
			ExpressionHandler expressionHandler = new DefaultExpressionHandler() {
				@Override
				public JexlContext getJexlContext() {
					JexlContext elContext = new MapContext();
					elContext.set("env", System.getenv());
					return elContext;
				}
			};

			for (Map.Entry<?, ?> entry : properties.entrySet()) {
				String text = (String) entry.getValue();
				Object value = text;
				if (StringUtils.isNotEmpty(text)) {
					Expression expression = expressionHandler.compile(text);
					if (expression != null) {
						value = expression.evaluate();
					}
				}
				configureStore.set((String) entry.getKey(), value);
			}
		}
	}
	/**
	 * @param configureLocation com/bstek/demo.properties
	 * @return
	 */
	private static Properties readProperties(String configureLocation) {
		Properties properties = new Properties();
		DefaultResourceLoader resourceLoader =  new DefaultResourceLoader();
		org.springframework.core.io.Resource resource = resourceLoader.getResource(configureLocation);
		if (!resource.exists()) {
				return properties;
		}
		InputStream in;
		try {
			in = resource.getInputStream();
			try {
				properties.load(in);
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	private static void addExtensionContextConfigLocation(String location) {
        if (StringUtils.isNotEmpty(locations)) {
            locations += ';';
        }
        locations += location;
    }
 
    private static String getLocations() {
    	for(String conf : confList){
    		addExtensionContextConfigLocation(conf);
    	}
        return locations;
    }
    public static void start() {
        try {
        	initDorado();
        	Configure.getStore().set("core.contextConfigLocation", getLocations());
        	MockContext.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static void startWithBDFModels(Set<String>bdfModels, String... configLocations) {
    	try {
    		initDorado();
        	initBDF(bdfModels);
        	initLocal(configLocations);
        	for(String prop : propList){
        		loadConfigureProperties(prop);
        	}
        	Configure.getStore().set("core.contextConfigLocation", getLocations());
        	MockContext.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static void startOnlyDorado(String... configLocations) {
    	try {
        	initDorado();
        	initLocal(configLocations);
        	Configure.getStore().set("core.contextConfigLocation", getLocations());
        	MockContext.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void shutdown(){
		CommonContext.dispose();
	}
	private static void initLocal(String... configLocations) {
		if(configLocations.length > 0){
    		for(String config : configLocations){
    				if(config.endsWith(".properties")){
    					if(!propList.contains(config))
        				  propList.add(config);
        			}else if(config.endsWith(".xml")){
    					if(!confList.contains(config))
        				  confList.add(config);
        			}
    		}
    	}
	}
}
class MockContext extends CommonContext {
    
	public static Context init() throws Exception {
        MockContext context = new MockContext();
        
        //DoradoContext dcontext = new DoradoContext();
        
        attachToThreadLocal(context);
 
        // Initialize Spring ApplicationContext
        context.initApplicationContext();
 
        EngineStartupListenerManager.notifyStartup();
        return context;
    }
	
}