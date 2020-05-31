package com.lean.lumen;

import com.lean.lumen.bean.Place;
import com.lean.lumen.bean.User;
import com.lean.lumen.service.PlaceService;
import com.lean.lumen.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest(classes = TravelApplication.class)
@RunWith(SpringRunner.class)
public class TravelApplicationTests {

    @Resource
    UserService userService;

    @Resource
    PlaceService placeService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setEmail("test.test@test.com");
        user.setPassword("123456");
        user.setUsername("lumen");

        userService.registerUser(user);
    }

    @Test
    public void test() {
        Place place = new Place();
        place.setName("测试");
        place.setHottime(new Date());
        place.setHotticket(125.00);
        place.setDimticket(25.00);
        place.setPlacedes("1231354789");
        place.setProvinceid(1);

        placeService.savePlace(place);
    }

}
