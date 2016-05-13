package me.weego.service.impl;

import me.weego.dao.WeixinDao;
import me.weego.model.Weixin;
import me.weego.service.WeixinService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by root on 16-5-11.
 */
@Service
public class WeixinServiceImpl implements WeixinService {
    @Resource
    private WeixinDao weixinDao;

    public Weixin.AccessToken accessToken() {
        return weixinDao.accessToken();
    }

    public Weixin.JsapiTicket jsapiTicket() {
        return weixinDao.jsapiTicket();
    }

    public Weixin.Config config(String url) {
        checkArgument(StringUtils.isNotBlank(url), "param should with url");
        return weixinDao.config(url);
    }
}
