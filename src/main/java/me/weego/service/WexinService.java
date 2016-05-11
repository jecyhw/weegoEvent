package me.weego.service;

import me.weego.model.Wexin;

/**
 * Created by root on 16-5-11.
 */
public interface WexinService {
    Wexin.AccessToken getAccessToken();
    Wexin.JsapiTicket getJsapiTicket();
}
