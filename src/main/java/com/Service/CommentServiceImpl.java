package com.Service;

import com.IService.CommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.AnswerMapper;
import com.mapper.Commentmapper;
import com.pojo.t_comment;
import com.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    Commentmapper commentmapper;
    @Autowired
    AnswerMapper answerMapper;

    @Override
    public List<t_comment> getCommentPageData(int aid,int page) {
        PageHelper.startPage(page, 10);
        List<t_comment> comments = commentmapper.selectCommentByAid(aid);
        PageInfo<t_comment> pageInfo = new PageInfo<>(comments);
        for(t_comment comment : pageInfo.getList()){
            comment.setTime(DateUtil.minFormat(comment.getC_time()));
        }
        if(pageInfo.getPages()<page)
            return null;
        return pageInfo.getList();

    }

    @Override
    public void insertComment(t_comment comment) {
        commentmapper.insertComment(comment);
        //答案界面评论数+1
        answerMapper.commentIncrement(comment.getC_aid());
    }
}
