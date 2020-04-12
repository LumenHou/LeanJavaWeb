package com.lean.lumen.service;

import com.lean.lumen.dto.QuestionDTO;
import com.lean.lumen.mapper.QuestionMapper;
import com.lean.lumen.mapper.UserMapper;
import com.lean.lumen.model.Question;
import com.lean.lumen.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Resource
    QuestionMapper questionMapper;
    
    @Resource
    UserMapper userMapper;
    

    public List<QuestionDTO> question_list(){
        List<QuestionDTO> list = new ArrayList<>();
        List<Question> questions = questionMapper.list();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);

            list.add(questionDTO);
        }

        return list;
    }

}
