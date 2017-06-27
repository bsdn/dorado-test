package com.bstek.dorado.test.sample;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bstek.dorado.test.core.DoradoTestEngine;

/**
 * @author Lucas
 * @since 2017-06-27
 * junit测试时可以仿照本类实现一基类，其它test继承该类
 */
public abstract class DoradoTestEngingeStarter {
	@BeforeClass
    public static void setUp() throws Exception {
		String pro1 = "com/bstek/dorado/dao/config/dorado-dao.properties";
		String pro2 = "com/bstek/dorado/importer/config/dorado-importer.properties";
		String pro3 = "test/dorado/configure.properties";
		String config1 = "com/bstek/dorado/dao/config/dorado-dao-context.xml";
		String config2 = "com/bstek/dorado/importer/config/dorado-importer-context.xml";
		String config3 = "test/dorado/dorado-test.xml";
    	DoradoTestEngine.startWithBDFModels(DoradoTestEngine.BDF_UFLO_MODEL,pro1,pro2,pro3,config1,config2,config3);
    }
	@Test
	public void test(){
		System.out.println("started ... ok");
	}
    @AfterClass
    public static void tearDown() throws Exception {
        DoradoTestEngine.shutdown();
    }
}
