package com.springAop.demo.service.test;

import com.springAop.demo.rpc.bip.TestAopInvoker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@ContextConfiguration({"classpath:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AopTest {

    @Resource
    private TestAopInvoker testAopInvoker;

    @Test
    public void testAop() {
        testAopInvoker.testAop(1, "2");
    }

    @Test
    public void testNoAop() {
        testAopInvoker.testNoAop();
    }
}
