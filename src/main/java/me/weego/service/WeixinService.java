package me.weego.service;

import me.weego.model.Weixin;

/**
 * Created by root on 16-5-11.
 */
public interface WeixinService {
    Weixin.AccessToken accessToken();
    Weixin.JsapiTicket jsapiTicket();
    Weixin.Config config(String url);
}
