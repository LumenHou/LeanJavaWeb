package com.lean.lumen.controller;

import com.lean.lumen.bean.Place;
import com.lean.lumen.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("place")
@CrossOrigin
public class PlaceController {

    @Autowired
    PlaceService placeService;


    @PostMapping("getPlaces")
    public Map<String, Object> getPlaces(Integer page, Integer size, Integer provinceId) {
        Map<String, Object> result = new HashMap<>();

        page = page == null ? 1 : page;
        size = size == null ? 3 : size;

        List<Place> places = placeService.getPlaces(page, size, provinceId);
        Integer total = placeService.getTotal(provinceId);

        result.put("places", places);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);

        return result;
    }
}
