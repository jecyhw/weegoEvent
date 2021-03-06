package me.weego.service.impl;

import me.weego.dao.EventDao;
import me.weego.model.Event;
import me.weego.model.EventParticipant;
import me.weego.model.EventQuery;
import me.weego.pojo.ResBody;
import me.weego.service.EventService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author tcl
 */
@Service
public class EventServiceImpl implements EventService {
    @Resource
    private EventDao eventDao;

    public List<EventQuery> list() {
        return eventDao.list();
    }

    public Event detail(String id) {
        return eventDao.detail(id);
    }

    public List<EventParticipant> participants() {
        return eventDao.participants();
    }

    public ResBody join(String id, String weixin) {
        checkArgument(StringUtils.isNotBlank(id), "param id should not blank");
        checkArgument(StringUtils.isNotBlank(weixin), "param weixin should not blank");
        return eventDao.join(id, weixin);
    }
}
