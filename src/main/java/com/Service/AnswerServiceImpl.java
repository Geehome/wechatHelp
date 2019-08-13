package com.Service;

import com.IService.AnswerService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.AnswerMapper;
import com.mapper.QuestionMapper;
import com.mapper.UserMapper;
import com.pojo.Answer;
import com.pojo.Question;
import com.pojo.User;
import com.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;


    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void insertAnswer(int auid, int qid, String desc) {

        Timestamp date = new Timestamp(new Date().getTime());
        int aid = answerMapper.count() + 1;
        int accepted = 0;

        Answer answer = new Answer();
        answer.setAuid(auid);
        answer.setQid(qid);
        answer.setAnswer(desc);
        answer.setAtime(date);
        answer.setAid(aid);
        answer.setAccepted(accepted);
        answerMapper.insert(answer);


    }

    @Override
    public List<Map> getAnswerAndQuestion(int auid, int page) {
        PageHelper.startPage(page, 10);
        List<Answer> answers = answerMapper.selectByAuid(auid);
        PageInfo<Answer> pageInfo = new PageInfo<>(answers);

        if (page <= pageInfo.getPages()) {
            List<Map> list = new ArrayList<>();
            for (Answer answer : pageInfo.getList()) {
                Map<String, Object> map = new HashMap<>();
                map.put("qid", answer.getQid());
                map.put("answer", answer.getAnswer());
                map.put("atime", DateUtil.minFormat(answer.getAtime()));
                list.add(map);
            }
            return list;
        }

        return null;
    }

    @Override
    public List<Question> replyMe(int uid) {
        List<Question> questionList = questionMapper.selectByUserIdLeftJoinAnswer(uid);


        return questionList;
    }

    @Transactional
    @Override
    public JSONObject getAnsPageData(int aid) {

        //增加浏览次数
        answerMapper.updatePvByAid(aid);

        Answer answer = answerMapper.selectByAid(aid);
        Question question = questionMapper.selectById(answer.getQid());
        User user = userMapper.getUserById(answer.getAuid());
        JSONObject res = new JSONObject();
        answer.setTime(DateUtil.minFormat(answer.getAtime()));
        res.put("answer", answer);
        res.put("reply_user_name", user.getName());
        res.put("question", question);


        return res;
    }


}
