package com.mapper;

import com.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    public int count();

    public void insertQuestion(Question question);

    public Question selectById(int qid);

    public void changeStatus(@Param(value = "aid") int aid, @Param(value = "qid")int qid);

    public List<Question> selectByUserId(int uid);

    public List selectByUserIdLeftJoinAnswer(int uid);

    void updatePvByQid(int qid);

    List<Question> searchQuestionList(@Param("text") String text);
}
