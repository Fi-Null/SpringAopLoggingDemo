package com.springAop.demo.rpc.bip;

import com.springAop.demo.domain.annotation.AopCallSuccessJudge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestAopInvoker {
    private static Logger logger = LoggerFactory.getLogger(TestAopInvoker.class);

    @AopCallSuccessJudge(value = "rpcGroupPublicResponseJudge")
    public int testAop(int param, String param1) {
        logger.info("hello, testAop! param is {}", param);
        return param;
    }

    public int testNoAop() {
        logger.info("hello, testAop!");
        return 1;
    }
}
