package com.lean.lumen.dto;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {

    private List<T> items;

    private Boolean showPrevious;
    private Boolean showFirstPage;

    private Boolean showNext;
    private Boolean showEndPage;

    private Integer total;
    private Integer current;
    private List<Integer> pages = new ArrayList<>();

    // 将页码 参数 设置好
    public PaginationDTO(Integer total, Integer currPage, Integer pageSize){
        Integer totalPage = (total + pageSize-1)/pageSize;

        if(currPage < 1)currPage = 1;
        if(currPage > totalPage) currPage = totalPage;


        this.total = total;

        this.current = currPage;

        pages.add(currPage);
        for(int i = 1; i <= 3; i++){
            if(currPage - i > 0)pages.add(0,currPage - i);
            if(currPage + i <= totalPage)pages.add(currPage + i);
        }

        // 如果当前页数 为1 则 不显示 向前，因为无法向前
        if(currPage == 1)showPrevious = false;
        else showPrevious = true;
        // 如果当前页数 为最后一页 则 不显示 向后，因为无法向后
        if(currPage.equals(totalPage))showNext = false;
        else showNext = true;

        //如果 显示的 页码中 有 第一页则 不显示 主动跳回第一页的按钮 <<
        if(pages.contains(1))showFirstPage = false;
        else showFirstPage = true;
        //如果 显示的 页码中 有 末页 则 不显示 主动跳回末页的按钮 >>
        if(pages.contains(totalPage))showEndPage = false;
        else showEndPage = true;
    }

}
