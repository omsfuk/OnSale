package cn.omsfuk.onsale.dao;


import cn.omsfuk.onsale.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    UserVO findUserById(int id);

    UserVO findUserByName(String username);

    UserVO findUserByPhone(String phone);

    UserVO findUserByEmail(String email);

    Integer insertUser(@Param("user") UserVO user, @Param("acCode") String acCode);

    Integer updateUser(UserVO user);

    Integer activeUserByEmail(@Param("email") String email, @Param("acCode") String acCode);

    Integer deleteUserTmpByEmail(String email);

}
