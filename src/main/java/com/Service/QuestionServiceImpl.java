package com.Service;

import com.IService.QuestionService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.AnswerMapper;
import com.mapper.QuestionMapper;
import com.mapper.UserMapper;
import com.pojo.Answer;
import com.pojo.Question;
import com.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public int insertQuestion(int uid, String desc, String img, int pv, int acceptAid, int stat, int reward, Timestamp qtime, String title) {
        int qid = questionMapper.count() + 1;
        Question question = new Question();
        question.setQtime(qtime);
        question.setDescription(desc);
        question.setQid(qid);
        question.setUid(uid);
        question.setTitle(title);
        question.setStat(stat);

        questionMapper.insertQuestion(question);


        return qid;
    }

    @Override
    public JSONObject QustionPageData(int uid, int qid, int page) {
        //更新访问次数
        questionMapper.updatePvByQid(qid);
//        JSONObject redisData = JSON.parseObject(redisTemplate.opsForValue().get("qid" + qid + ":" + "page" + page));

        JSONObject resData = new JSONObject();
//        if (null == redisData) {
            //获取问题信息
            Question question = questionMapper.selectById(qid);

            //获取问题提出人名字
            String questionPosterName = userMapper.getUserById(question.getUid()).getName();
            question.setUserName(questionPosterName);

            //获取该问题当前页数的回复信息
            PageHelper.startPage(page, 10);
            List<Answer> answers = answerMapper.getAnwserByQid(qid);
            PageInfo<Answer> pageInfo = new PageInfo<>(answers);
            question.setAnswers(answers);

            //设置问题的信息到Json
            resData.put("quid", question.getUid());
            resData.put("description", question.getDescription());
            resData.put("title", question.getTitle());
            resData.put("asktime", DateUtil.dayFormat(question.getQtime()));
            resData.put("qNickname", questionPosterName);
            resData.put("page_view", question.getPv());
            resData.put("reply_sum", question.getReply_sum());

            //设置回复的信息
            if (pageInfo.getPages() >= page) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                for (Answer ans : pageInfo.getList()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("desc", ans.getAnswer());
                    map.put("author", ans.getUser().getName());
                    Timestamp date = ans.getAtime();
                    map.put("atime", DateUtil.dayFormat(date));
                    map.put("aid", ans.getAid());
                    map.put("ans_reply_sum", ans.getAns_reply_sum());
                    map.put("ans_pv", ans.getAns_pv());
                    map.put("like_sum", ans.getLike_sum());
                    list.add(map);
                }
                resData.put("answers", list);
                //这里防止最后一页没有存满时就放到了内存里，导致之后一直无法刷新新加入的内容。
//                if (pageInfo.getPages() > page)
//                    redisTemplate.opsForValue().set("qid" + qid + ":" + "page" + page, resData.toJSONString(), 30, TimeUnit.MINUTES);
            } else {
                resData.put("answers", null);
            }
            return resData;
//        } else {
//            return redisData;
        }

    @Override
    public JSONObject searchQuestionList(String text) {

        List<Question> questionList = questionMapper.searchQuestionList(text);

        JSONObject res = new JSONObject();

        res.put("searchList",questionList);

        return res;
    }

}

