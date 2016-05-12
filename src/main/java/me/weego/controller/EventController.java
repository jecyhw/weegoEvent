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
        return eventService.list();
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam String id) {
        return eventService.detail(id);
    }

    @RequestMapping(value = "join", method = RequestMethod.POST)
    @ResponseBody
    public ResBody join(@RequestParam String id, @RequestParam String weixin) {
        return eventService.join(id, weixin);
    }
}
