package me.weego.controller;

import me.weego.pojo.ResBody;
import me.weego.service.EventParticipantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by root on 16-5-6.
 */
@RestController
@RequestMapping("/event_participant")
public class EventParticipantController {
    @Resource
    private EventParticipantService eventParticipantService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ResBody add(@RequestParam String weixin) {
        return eventParticipantService.add(weixin);
    }
}
