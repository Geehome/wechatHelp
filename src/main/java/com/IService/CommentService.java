package com.IService;

import com.pojo.t_comment;

import java.util.List;

public interface CommentService {

    List<t_comment> getCommentPageData(int aid,int page);

    void insertComment(t_comment comment);
}
