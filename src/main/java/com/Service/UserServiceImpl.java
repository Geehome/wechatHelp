package com.Service;

import com.IService.UserService;
import com.mapper.UserMapper;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper ;

    //这里电话和年纪没有录入，而是等register页面在录入电话和年纪
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public int insertUser(String openid, String name, String gender, String city) {
        User user = new User();
        Integer id = userMapper.getTotalNum()+1;
        user.setId(id);
        user.setOpenId(openid);
        user.setGender(gender);
        user.setName(name);
        user.setTel("");
        user.setAge(0);
        user.setCollection(500);
        userMapper.insertUser(user);
        return id;
    }
}
