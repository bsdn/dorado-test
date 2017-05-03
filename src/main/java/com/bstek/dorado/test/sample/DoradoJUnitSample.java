package com.bstek.dorado.test.sample;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.bstek.dorado.test.core.DoradoTestEngine;

public class DoradoJUnitSample{
 
    @BeforeClass
    public static void setUp() throws Exception {
		String pro1 = "com/bstek/dorado/dao/config/dorado-dao.properties";
		String pro2 = "test/dorado/configure.properties";
		String config1 = "com/bstek/dorado/dao/config/dorado-dao-context.xml";
		String config2 = "test/dorado/dorado-test.xml";
    	DoradoTestEngine.startWithBDFModels(DoradoTestEngine.BDF_UFLO_MODEL,pro1,pro2,config1,config2);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        DoradoTestEngine.shutdown();
    }
    
	 @org.junit.Test
	 public void initData(){
	    //initNoticeRenGouTestData();
	 }
    
}