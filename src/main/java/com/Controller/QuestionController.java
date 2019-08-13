package com.Controller;


import com.IService.QuestionService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.AnswerMapper;
import com.mapper.QuestionMapper;
import com.mapper.UserMapper;
import com.pojo.Answer;
import com.pojo.Question;
import com.pojo.User;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class QuestionController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    @RequestMapping("/postQuestion")
    @ResponseBody
    public String publishQuestion(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);

        Integer uid = jsonObject.getInteger("uid");
        String description = jsonObject.getString("description");
        String title = jsonObject.getString("title");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Integer qid = questionService.insertQuestion(uid,description,"",0,0,0,0,timestamp,title);

        return qid.toString();
    }

    @RequestMapping("/questionPage")
    @ResponseBody
    public JSONObject showQuestionPage(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        int uid = jsonObject.getInteger("id");
        int qid = jsonObject.getInteger("qid");
        int page = jsonObject.getInteger("pageNum");

        JSONObject res = questionService.QustionPageData(uid,qid,page);


        return res;

    }

    @RequestMapping("/myQuestionList")
    @ResponseBody
    public JSONObject myQuestionList(@RequestBody JSONObject jsonObject){
        int uid = jsonObject.getInteger("uid");
        int page = jsonObject.getInteger("page");
        PageHelper.startPage(page,10);
        List<Question> questionList = questionMapper.selectByUserId(uid);
        PageInfo<Question> pageInfo = new PageInfo<>(questionList);


        List<Map> list = new ArrayList<>();
        for(Question question:pageInfo.getList()){
            Map map = new HashMap<String,Object>();
            map.put("title",question.getTitle());
            map.put("qid",question.getQid());
            JSONObject jsonObject1 = new JSONObject();
            SimpleDateFormat smft = new SimpleDateFormat("YYYY-MM-dd HH:mm");
            map.put("qtime",smft.format(question.getQtime()));
            map.put("status", question.getStat());
            map.put("pageTotalSize",pageInfo.getTotal());
            list.add(map);
        }
        JSONObject res = new JSONObject();
        res.put("questionList",list);
        return res;
    }


    @RequestMapping("/searchQuestion")
    @ResponseBody
    public JSONObject searchQuestion(@RequestBody JSONObject jsonObject){
        String searchText = jsonObject.getString("searchText");
        return questionService.searchQuestionList(searchText);
    }
}
