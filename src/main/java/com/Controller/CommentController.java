package com.Controller;

import com.IService.CommentService;
import com.alibaba.fastjson.JSONObject;
import com.pojo.t_comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/commentPage")
    public JSONObject commentPage(@RequestBody JSONObject jsonObject){
        int aid = jsonObject.getInteger("aid");
        int page = jsonObject.getInteger("page");
        List<t_comment> list = commentService.getCommentPageData(aid,page);

        JSONObject res = new JSONObject();
        res.put("comments",list);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/postComment")
    public String postComment(@RequestBody JSONObject jsonObject){
        int aid = jsonObject.getInteger("aid");
        int uid = jsonObject.getInteger("uid");
        String descriptioin = jsonObject.getString("desc");
        Timestamp time = new Timestamp(new Date().getTime());
        t_comment comment = new t_comment();
        comment.setC_time(time);
        comment.setDescription(descriptioin);
        comment.setComment_uid(uid);
        comment.setC_aid(aid);
        commentService.insertComment(comment);

        return "success";
    }
}
