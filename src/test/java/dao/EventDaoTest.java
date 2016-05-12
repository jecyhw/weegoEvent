package dao;

import base.BaseTest;
import me.weego.dao.EventDao;
import me.weego.model.Event;
import me.weego.model.EventQuery;
import org.junit.Test;
import util.JsonUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 16-5-12.
 */
public class EventDaoTest {

//    @Resource
//    EventDao eventDao;
//
//    @Test
//    public void list() {
//        JsonUtil.writeAsString(eventDao.list());
//    }
//
//    @Test
//    public void detail() {
//        List<EventQuery> eventQueries = eventDao.list();
//        if (eventQueries.get(0) != null) {
//            List<Event> events = eventQueries.get(0).getEvents();
//            if (events.get(0) != null) {
//                JsonUtil.writeAsString(eventDao.detail(events.get(0).getId()));
//            }
//        }
//    }
//
//    @Test
//    public void join() {
//        List<EventQuery> eventQueries = eventDao.list();
//        if (eventQueries.get(0) != null) {
//            List<Event> events = eventQueries.get(0).getEvents();
//            if (events.get(0) != null) {
//                JsonUtil.writeAsString(eventDao.join(events.get(0).getId(), "wexintest"));
//            }
//        }
//    }
}
