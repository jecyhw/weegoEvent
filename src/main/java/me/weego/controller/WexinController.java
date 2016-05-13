package me.weego.controller;

import me.weego.service.WexinService;
import me.weego.pojo.ResBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by root on 16-5-11.
 */
@RestController
@RequestMapping("/wechat/v1")
public class WexinController extends BaseController{
    @Resource
    WexinService wexinService;

    @RequestMapping(value = "access_token", method = RequestMethod.GET)
    @ResponseBody
    public ResBody accessToken() {
        return ResBody.returnSuccess(wexinService.accessToken());
    }

    @RequestMapping(value = "jsapi_ticket", method = RequestMethod.GET)
    @ResponseBody
    public ResBody jsapiTicket() {
        return ResBody.returnSuccess(wexinService.jsapiTicket());
    }

    @RequestMapping(value = "config", method = RequestMethod.GET)
    @ResponseBody
    public ResBody config(@RequestParam String url) {
        return ResBody.returnSuccess(wexinService.config(url));
    }
}
