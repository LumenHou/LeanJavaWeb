package com.lean.lumen.service;

import com.lean.lumen.bean.Place;
import com.lean.lumen.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    public List<Place> getPlaces(Integer page, Integer size, Integer provinceId) {
        Integer start = (page - 1) * size;

        return placeMapper.getPlaceByProvince(start, size, provinceId);
    }

    public Integer getTotal(Integer provinceId) {
        return placeMapper.getTotalPlace(provinceId);
    }

    public List<String> getAllProvince(){
        return placeMapper.getAllProvinceName();
    }
}
