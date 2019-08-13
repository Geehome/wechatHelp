package com.mapper;

import com.pojo.t_comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Commentmapper {

    List<t_comment> selectCommentByAid(int aid);

    void insertComment(t_comment comment);
}
