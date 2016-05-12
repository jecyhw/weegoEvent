package me.weego.service;

import me.weego.model.Wexin;

/**
 * Created by root on 16-5-11.
 */
public interface WexinService {
    Wexin.AccessToken accessToken();
    Wexin.JsapiTicket jsapiTicket();
    Wexin.Config config(String url);
}
