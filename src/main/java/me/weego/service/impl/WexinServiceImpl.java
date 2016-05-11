package me.weego.service.impl;

import me.weego.dao.WexinDao;
import me.weego.model.Wexin;
import me.weego.service.WexinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by root on 16-5-11.
 */
@Service
public class WexinServiceImpl implements WexinService{
    @Resource
    private WexinDao wexinDao;

    public Wexin.AccessToken getAccessToken() {
        return wexinDao.getAccessToken();
    }

    public Wexin.JsapiTicket getJsapiTicket() {
        return wexinDao.getJsapiTicket();
    }
}
