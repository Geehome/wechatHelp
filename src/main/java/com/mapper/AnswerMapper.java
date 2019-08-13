package com.mapper;

import com.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper {

    //查询全部回答，并连接了用户nickname
    public List getAnwserByQid(int qid);

    public int count();

    public void insert(Answer answer);

    public void changeStatus(int aid);

    public Answer selectByAid(int aid);

    public List<Answer> selectByAuid(int auid);
    //仅仅查询全部回答
    public List<Answer> selectByQid(int qid);

    public int countByQid(int qid);

    //增加pv
    void updatePvByAid(int aid);

    void updateLikeByAid(int aid);

    void commentIncrement(int aid);
}
