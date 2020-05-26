package com.lean.lumen.service;

import com.lean.lumen.bean.Province;
import com.lean.lumen.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    public List<Province> findByPage(Integer page, Integer size) {
        Integer start = (page - 1) * size;

        return provinceMapper.findByPage(start, size);
    }

    public Integer count() {
        return provinceMapper.countProvince();
    }

    public void saveProvince(Province province) {
        province.setPlacecounts(0);
        provinceMapper.save(province);
    }

    public void deleteProvince(Integer integer) {
        provinceMapper.delete(integer);
    }
}
