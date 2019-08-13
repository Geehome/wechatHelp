package com.IService;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.util.Map;

public interface QuestionService {

    int insertQuestion(int uid, String desc, String img, int pv, int acceptAid, int stat, int reward, Timestamp qtime,String title);


    //问题主页的数据，包含问题和所有答案
    JSONObject QustionPageData(int uid, int qid,int page);

    //搜的到的问题LIst
    JSONObject searchQuestionList(String text);
}
