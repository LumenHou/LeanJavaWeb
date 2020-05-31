package com.lean.lumen.controller;

import com.lean.lumen.bean.Place;
import com.lean.lumen.bean.ResultDTO;
import com.lean.lumen.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("allProvince")
    public ResultDTO<List<String>> getAllProvince() {
        try {
            List<String> allProvince = placeService.getAllProvince();
            return ResultDTO.success(allProvince, "成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResultDTO.fail(null, "失败");
        }
    }

    @PostMapping("save")
    public ResultDTO<Boolean> savePlace(@RequestBody Place place) {
        try {
            placeService.savePlace(place);
            return ResultDTO.success(Boolean.TRUE, "成功");
        } catch (Exception e) {
            return ResultDTO.fail(null, "失败");
        }
    }

    @PostMapping("delete")
    public ResultDTO<Boolean> deletePlace(Integer id) {
        try {
            placeService.delete(id);
            return ResultDTO.success(Boolean.TRUE, "成功");
        } catch (Exception e) {
            return ResultDTO.fail(null, "失败");
        }
    }
}
