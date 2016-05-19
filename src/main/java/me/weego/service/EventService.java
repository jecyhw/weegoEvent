package me.weego.service;

import me.weego.model.Event;
import me.weego.model.EventParticipant;
import me.weego.model.EventQuery;
import me.weego.pojo.ResBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author tcl
 */
public interface EventService {
    List<EventQuery> list();
    Event detail(String id);
    List<EventParticipant> participants();
    ResBody join(String id, String weixin);
}
