package me.weego.controller;

import me.weego.pojo.ResBody;
import me.weego.service.EventService;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam String id, @RequestParam String detail, @RequestParam("sign_up") String signUp, @RequestParam String partner, @RequestParam String type) {
        ModelAndView modelAndView = new ModelAndView("share_detail");
        modelAndView.addObject("id", id);
        modelAndView.addObject("detail", detail);
        modelAndView.addObject("sign_up", signUp);
        modelAndView.addObject("partner", partner);
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @RequestMapping(value = "join", method = RequestMethod.POST)
    @ResponseBody
    public ResBody join(@RequestParam String id, @RequestParam String weixin) {
        return eventService.join(id, weixin);
    }
}
