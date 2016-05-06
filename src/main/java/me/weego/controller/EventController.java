package me.weego.controller;

import me.weego.pojo.ResBody;
import me.weego.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author tcl
 */
@RestController()
@RequestMapping("/event")
public class EventController extends BaseController {
    @Resource
    private EventService eventService;

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView query() {
        return eventService.query();
    }
}
