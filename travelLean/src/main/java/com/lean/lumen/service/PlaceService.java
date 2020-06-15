package com.lean.lumen.service;

import com.lean.lumen.bean.Place;
import com.lean.lumen.bean.Province;
import com.lean.lumen.mapper.PlaceMapper;
import com.lean.lumen.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    ProvinceMapper provinceMapper;


    public List<Place> getPlaces(Integer page, Integer size, Integer provinceId) {
        Integer start = (page - 1) * size;

        return placeMapper.getPlaceByProvince(start, size, provinceId);
    }

    public Integer getTotal(Integer provinceId) {
        return placeMapper.getTotalPlace(provinceId);
    }

    public List<String> getAllProvince() {
        return placeMapper.getAllProvinceName();
    }

    public void savePlace(Place place) {
        if (place.getId() == null) {
            placeMapper.save(place);

            Province province = provinceMapper.findProvince(place.getProvinceid());
            province.setPlacecounts(province.getPlacecounts() + 1);

            provinceMapper.updateProvinceById(province);
        } else {
            Place oldPlace = findPlace(place.getId());
            if (!oldPlace.getProvinceid().equals(place.getProvinceid())) {
                Province province = provinceMapper.findProvince(place.getProvinceid());
                province.setPlacecounts(province.getPlacecounts() + 1);

                Province province2 = provinceMapper.findProvince(oldPlace.getProvinceid());
                province2.setPlacecounts(province2.getPlacecounts() - 1);

                provinceMapper.updateProvinceById(province);
                provinceMapper.updateProvinceById(province2);
            }

            placeMapper.update(place);
        }
    }

    public void delete(Integer id) {
        placeMapper.delete(id);

        Place place = placeMapper.find(id);
        Province province = provinceMapper.findProvince(place.getProvinceid());
        province.setPlacecounts(province.getPlacecounts() - 1);

        provinceMapper.updateProvinceById(province);
    }

    public Place findPlace(Integer id) {
        return placeMapper.find(id);
    }
}
