package com.Controller;

import com.IService.AnswerService;
import com.alibaba.fastjson.JSONObject;
import com.mapper.AnswerMapper;
import com.mapper.LikeMapper;
import com.mapper.QuestionMapper;
import com.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    AnswerService answerService;

    @Autowired
    LikeMapper likeMapper;

    @ResponseBody
    @RequestMapping("/postAnswer")
    public void postAnswer(@RequestBody JSONObject jsonObject) {

        System.out.println(jsonObject);

        int auid = jsonObject.getInteger("myid");
        int qid = jsonObject.getInteger("qid");
        String myAns = jsonObject.getString("myAns");
        answerService.insertAnswer(auid, qid, myAns);


        System.out.println("success insert new answer");

    }

    @ResponseBody
    @RequestMapping("/adaptAnswer")
    public String adapt(@RequestBody JSONObject jsonObject) {
        int aid = jsonObject.getInteger("aid");

        int qid = answerMapper.selectByAid(aid).getQid();
        answerMapper.changeStatus(aid);
        questionMapper.changeStatus(aid, qid);

        return "success adapted";

    }

    @ResponseBody
    @RequestMapping("/myReply")
    public JSONObject myReplyList(@RequestBody JSONObject jsonObject) {
        int uid = jsonObject.getInteger("uid");
        int page = jsonObject.getInteger("page");
        JSONObject res = new JSONObject();
        res.put("answers", answerService.getAnswerAndQuestion(uid,page));
        return res;
    }

    @ResponseBody
    @RequestMapping("/replyMe")
    public JSONObject replyMe(@RequestBody JSONObject jsonObject) {
        int uid = jsonObject.getInteger("uid");

        JSONObject res = new JSONObject();
        List<Question> list = answerService.replyMe(uid);

        res.put("answers", list);
        return res;
    }

    @ResponseBody
    @RequestMapping("/answerPage")
    public JSONObject answerPage(@RequestBody JSONObject jsonObject) {
        int aid = jsonObject.getInteger("aid");

        JSONObject res = answerService.getAnsPageData(aid);
        return res;
    }

    @ResponseBody
    @RequestMapping("/answerPage/like")
    public String ans_addLike(@RequestBody JSONObject jsonObject) {
        int aid = jsonObject.getInteger("aid");
        int uid = jsonObject.getInteger("id");
        Integer temp = likeMapper.checkRepeatLike(uid, aid);
        if ( temp == null) {
            answerMapper.updateLikeByAid(aid);
            likeMapper.insertLike(uid, aid);
            return "success";
        }

        return "你已经点过赞了";
    }

}
