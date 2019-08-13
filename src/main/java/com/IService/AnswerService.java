package com.IService;

import com.alibaba.fastjson.JSONObject;
import com.pojo.Answer;
import com.pojo.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface AnswerService {

    void insertAnswer(int auid,int qid,String desc);

    //获取我回答的所有信息
    List<Map>  getAnswerAndQuestion(int auid,int page);

    List<Question> replyMe(int uid);

    //获取回复界面的所有信息
    JSONObject getAnsPageData(int aid);
}
