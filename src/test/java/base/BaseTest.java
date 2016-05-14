package base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root on 16-5-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)//使用Spring整合Junit的测试类测试
@ContextConfiguration(locations = {//spring-mvc配置文件
        "file:src/main/resources/applicationContext.xml"
})
@TestPropertySource(locations = {//所用到的properties文件
        "file:src/main/resources/mongodb.properties",
        "file:src/main/resources/weixin.properties"
})
abstract public class BaseTest extends AbstractJUnit4SpringContextTests {
    //其他所有test继承BaseTest即可
}
