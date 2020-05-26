package com.lean.lumen.controller;

import com.lean.lumen.bean.Province;
import com.lean.lumen.bean.ResultDTO;
import com.lean.lumen.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("province")
@Slf4j
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

    @PostMapping("save")
    public ResultDTO<Province> save(@RequestBody Province province) {
        try {
            provinceService.saveProvince(province);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultDTO.fail(null, "保存失败");
        }
        return ResultDTO.success(province, "保存成功");
    }

    @PostMapping("delete")
    public ResultDTO<Boolean> delete(Integer id) {
        try {
            provinceService.deleteProvince(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultDTO.fail(Boolean.FALSE, "失败");
        }
        return ResultDTO.success(Boolean.TRUE, "保存成功");
    }

}
