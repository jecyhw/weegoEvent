package me.weego.service.impl;

import me.weego.dao.EventParticipantDao;
import me.weego.pojo.ResBody;
import me.weego.service.EventParticipantService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by root on 16-5-6.
 */
@Service
public class EventParticipantServiceImpl implements EventParticipantService {
    @Resource
    private EventParticipantDao eventParticipantDao;

    public ResBody add(String weixin) {
        checkArgument(StringUtils.isNotBlank(weixin), "param weixin should not blank");
        return eventParticipantDao.add(weixin);
    }
}
