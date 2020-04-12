package com.lean.lumen.service;

import com.github.pagehelper.Page;
import com.lean.lumen.dto.PaginationDTO;
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
    

    public PaginationDTO<QuestionDTO> question_list(Integer pageNum, Integer pageSize){
        List<QuestionDTO> list = new ArrayList<>();
        List<Question> questions = questionMapper.list(pageNum, pageSize);
        Page questions1 = (Page)questions;
        Long total = questions1.getTotal();

        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO(total.intValue(), pageNum, pageSize);

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);

            list.add(questionDTO);
        }
        paginationDTO.setItems(list);

        return paginationDTO;
    }

}
