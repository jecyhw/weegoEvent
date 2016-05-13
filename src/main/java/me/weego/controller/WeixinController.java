package me.weego.controller;

import me.weego.service.WeixinService;
import me.weego.pojo.ResBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by root on 16-5-11.
 */
@RestController
@RequestMapping("/weixin/v1")
public class WeixinController extends BaseController{
    @Resource
    WeixinService weixinService;

    @RequestMapping(value = "access_token", method = RequestMethod.GET)
    @ResponseBody
    public ResBody accessToken() {
        return ResBody.returnSuccess(weixinService.accessToken());
    }

    @RequestMapping(value = "jsapi_ticket", method = RequestMethod.GET)
    @ResponseBody
    public ResBody jsapiTicket() {
        return ResBody.returnSuccess(weixinService.jsapiTicket());
    }

    @RequestMapping(value = "config", method = RequestMethod.GET)
    @ResponseBody
    public ResBody config(@RequestParam String url) {
        return ResBody.returnSuccess(weixinService.config(url));
    }
}
