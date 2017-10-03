package cn.omsfuk.onsale.service;


import cn.omsfuk.onsale.base.Result;
import cn.omsfuk.onsale.base.ResultCache;
import cn.omsfuk.onsale.dao.UserDAO;
import cn.omsfuk.onsale.util.ObjectUtil;
import cn.omsfuk.onsale.util.RandomUtil;
import cn.omsfuk.onsale.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MailService mailService;

    /**
     * 从数据库中查找此用户。默认顺序username，phone，email
     * @param user
     * @return
     */
    private UserVO extractDBUser(UserVO user) {
        UserVO userDB = null;
        if (ObjectUtil.notNull(user.getUsername(), user.getPassword())) {
            if ((userDB = userDAO.findUserByName(user.getUsername())) != null) {
                return userDB;
            }
        }
        if (ObjectUtil.notNull(user.getPhone(), user.getPassword())) {
            if ((userDB = userDAO.findUserByPhone(user.getPhone())) != null) {
                return userDB;
            }
        }
        if (ObjectUtil.notNull(user.getEmail(), user.getPassword())) {
            if ((userDB = userDAO.findUserByEmail(user.getEmail())) != null) {
                return userDB;
            }
        }
        return null;
    }

    public Result login(UserVO user) {
        UserVO userDB = extractDBUser(user);

        if (userDB == null) {
            return ResultCache.NO_SUCH_USER;
        }

        return user.getPassword().equals(userDB.getPassword()) ? ResultCache.getOk(userDB) : ResultCache.WRONG_PASSWORD;
    }

    public Result register(UserVO user) {
        UserVO userDB = extractDBUser(user);
        if (userDB != null) {
            return ResultCache.USER_ALREADY_EXISTS;
        }
        if (ObjectUtil.isNull(user.getUsername(), user.getPhone(), user.getEmail())) {
            return ResultCache.INVALID_INPUT;
        }
        String acCode = RandomUtil.getKey(32);
        userDAO.insertUser(user, acCode);
        if (user.getEmail() != null) {
            valdateEmail(user.getEmail(), acCode);
        }
        return ResultCache.getOk(user);
    }

    private void validatePhone(String phone) {
    }

    private void  valdateEmail(String email, String acCode) {
        mailService.sendActiveEmail(email, acCode);
    }

    public boolean activeAccountByEamil(String email, String acCode) {
        if (userDAO.activeUserByEmail(email, acCode) == 1) {
            userDAO.deleteUserTmpByEmail(email);
            return true;
        }
        return false;
    }

    public Result findUser(UserVO user) {
        if (user.getId() != null) {
            return ResultCache.getOk(userDAO.findUserById(user.getId()));
        }
        if (user.getUsername() != null) {
            return ResultCache.getOk(userDAO.findUserByName(user.getUsername()));
        }
        return ResultCache.NO_SUCH_USER;
    }

}
