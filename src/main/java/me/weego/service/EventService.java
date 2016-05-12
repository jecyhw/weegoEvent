package me.weego.service;

import me.weego.pojo.ResBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author tcl
 */
public interface EventService {
    ModelAndView list();
    ModelAndView detail(String id);
    ResBody join(String id, String weixin);
}
