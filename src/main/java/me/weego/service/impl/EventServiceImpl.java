package me.weego.service.impl;

import me.weego.dao.EventDao;
import me.weego.model.Event;
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

    public ModelAndView query() {
        return eventDao.query();
    }

    public ModelAndView detail(String id) {
        return eventDao.detail(id);
    }

    public ResBody join(String id, String weixin) {
        checkArgument(StringUtils.isNotBlank(id), "param id should not blank");
        checkArgument(StringUtils.isNotBlank(weixin), "param weixin should not blank");
        return eventDao.join(id, weixin);
    }
}
