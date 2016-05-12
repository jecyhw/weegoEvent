package dao;

import base.BaseTest;
import me.weego.dao.WexinDao;
import org.junit.Test;
import util.JsonUtil;

import javax.annotation.Resource;

/**
 * Created by root on 16-5-12.
 */
public class WexinDaoTest extends BaseTest{

    @Resource
    WexinDao wexinDao;

    @Test
    public void getAccessToken() {
        JsonUtil.writeAsString(wexinDao.accessToken());
    }

    @Test
    public void getJsapiTicket() {
        JsonUtil.writeAsString(wexinDao.jsapiTicket());
    }

    @Test
    public void getConfig() {
        JsonUtil.writeAsString(wexinDao.config("http://localhost:8080/event/v1/list"));
    }
}
