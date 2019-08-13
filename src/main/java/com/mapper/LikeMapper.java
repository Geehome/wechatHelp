package com.mapper;

import com.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeMapper {

    Integer checkRepeatLike(@Param("uid") int uid, @Param("aid")int aid);

    void insertLike(@Param("uid") int uid, @Param("aid")int aid);
}
