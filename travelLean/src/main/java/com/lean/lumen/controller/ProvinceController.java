package com.lean.lumen.controller;

import com.lean.lumen.bean.Province;
import com.lean.lumen.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer size) {
        page = page == null ? 1 : page;
        size = size == null ? 4 : size;

        Map<String, Object> result = new HashMap<>();

        List<Province> provinceList = provinceService.findByPage(page, size);

        Integer total = provinceService.count();
        result.put("provinces", provinceList);
        result.put("total", total);
        return result;
    }

}
