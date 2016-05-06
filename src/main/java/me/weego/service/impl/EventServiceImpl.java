package me.weego.service.impl;

import me.weego.dao.EventDao;
import me.weego.model.Event;
import me.weego.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tcl
 */
@Service
public class EventServiceImpl implements EventService {
    @Resource
    private EventDao eventDao;

    public ModelAndView query() {
        return eventDao.query();
    }
}
