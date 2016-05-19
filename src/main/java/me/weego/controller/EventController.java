package me.weego.controller;

import me.weego.pojo.ResBody;
import me.weego.service.EventService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author tcl
 */
@RestController
@RequestMapping("/event/v1")
public class EventController extends BaseController {
    @Resource
    private EventService eventService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView list() {
        return new ModelAndView("newshare", "eventList", eventService.list());
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam String id) {
        return new ModelAndView("share_detail", "event", eventService.detail(id));
    }

    @RequestMapping(value = "join", method = RequestMethod.POST)
    @ResponseBody
    public ResBody join(@RequestParam String id, @RequestParam String weixin) {
        return eventService.join(id, weixin);
    }

    @RequestMapping(value = "participants", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView participants() {
        return  new ModelAndView("participants", "participants", eventService.participants());
    }
}
