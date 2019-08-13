package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.pojo.User;

@Mapper
public interface UserMapper {

    public User getUserById(Integer id);

    public User getUserByOpenId(String openId);

    public int getTotalNum();

    public void insertUser(User user);

    public void updateUser(Integer id);

    public void insertAgeAndTelById(User user);

}
