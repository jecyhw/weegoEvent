package dao;

import base.BaseTest;
import me.weego.dao.WeixinDao;
import org.junit.Test;
import util.JsonUtil;

import javax.annotation.Resource;

/**
 * Created by root on 16-5-12.
 */
public class WexinDaoTest extends BaseTest{

    @Resource
    WeixinDao weixinDao;

    @Test
    public void accessToken() {
        JsonUtil.writeAsString(weixinDao.accessToken());
    }

    @Test
    public void jsapiTicket() {
        JsonUtil.writeAsString(weixinDao.jsapiTicket());
    }

    @Test
    public void config() {
        JsonUtil.writeAsString(weixinDao.config("http://localhost:8080/event/v1/list"));
    }
}
