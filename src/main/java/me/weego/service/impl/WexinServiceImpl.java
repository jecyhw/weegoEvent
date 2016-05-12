package me.weego.service.impl;

import me.weego.dao.WexinDao;
import me.weego.model.Wexin;
import me.weego.service.WexinService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by root on 16-5-11.
 */
@Service
public class WexinServiceImpl implements WexinService{
    @Resource
    private WexinDao wexinDao;

    public Wexin.AccessToken accessToken() {
        return wexinDao.getAccessToken();
    }

    public Wexin.JsapiTicket jsapiTicket() {
        return wexinDao.getJsapiTicket();
    }

    public Wexin.Config config(String url) {
        checkArgument(StringUtils.isNotBlank(url), "param should with url");
        return wexinDao.getConfig(url);
    }
}
