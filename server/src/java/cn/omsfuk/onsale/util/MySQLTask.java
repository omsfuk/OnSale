package cn.omsfuk.onsale.util;


import cn.omsfuk.onsale.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySQLTask {

    @Autowired
    private UserDAO userDAO;

    @Scheduled(fixedDelay = 900000)
    public void clearInvalidUser() {
        userDAO.clearInvalidUser(15);
    }
}
