package com.lean.lumen.service;

import com.github.pagehelper.Page;
import com.lean.lumen.dto.PaginationDTO;
import com.lean.lumen.dto.QuestionDTO;
import com.lean.lumen.exception.CustomizeException;
import com.lean.lumen.mapper.QuestionMapper;
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
    UserSevice userSevice;

    public PaginationDTO<QuestionDTO> question_list(Integer pageNum, Integer pageSize) {
        List<QuestionDTO> list = new ArrayList<>();
        List<Question> questions = questionMapper.list(pageNum, pageSize);
        Page questions1 = (Page) questions;
        Long total = questions1.getTotal();

        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO(total.intValue(), pageNum, pageSize);

        for (Question question : questions) {
            User user = userSevice.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);

            list.add(questionDTO);
        }
        paginationDTO.setItems(list);

        return paginationDTO;
    }

    public PaginationDTO<QuestionDTO> question_list(Integer id, Integer pageNum, Integer pageSize){
        List<QuestionDTO> list = new ArrayList<>();
        List<Question> questions = questionMapper.listById(id, pageNum, pageSize);
        Page questions1 = (Page)questions;
        Long total = questions1.getTotal();

        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO(total.intValue(), pageNum, pageSize);

        for (Question question : questions) {
            User user = userSevice.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);

            list.add(questionDTO);
        }
        paginationDTO.setItems(list);

        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.getQuestionById(id);
        if (question == null) {
            throw new CustomizeException(2001, "问题未找到");
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userSevice.findById(question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            questionMapper.create(question);
        } else {
            questionMapper.updateQuestionById(question);
        }
    }

    public void create(Question question) {
        questionMapper.create(question);
    }

    public void incView(Integer id) {
        questionMapper.updateQuestionIncById(id);

    }
}
