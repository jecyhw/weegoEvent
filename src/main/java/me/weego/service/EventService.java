package me.weego.service;

import me.weego.dao.EventDao;
import me.weego.model.Event;
import me.weego.pojo.ResBody;
import org.bson.types.ObjectId;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author tcl
 */
public interface EventService {
    ModelAndView query();
    ResBody join(String id, String weixin);
}
