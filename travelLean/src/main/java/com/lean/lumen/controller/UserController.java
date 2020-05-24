package com.lean.lumen.controller;

import com.lean.lumen.bean.ResultDTO;
import com.lean.lumen.bean.User;
import com.lean.lumen.service.UserService;
import com.lean.lumen.utils.ValidateImageCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public ResultDTO login(@RequestBody User user, HttpServletRequest request) {
        log.info(user.toString());
        User userDao = userService.findUser(user.getUsername());
        if (userDao == null) {
            return ResultDTO.fail(Boolean.FALSE, "用户不存在");
        }
        if (user.getPassword().equals(userDao.getPassword())) {
            request.getServletContext().setAttribute(userDao.getId(), userDao);
            return ResultDTO.success(userDao, "登录成功");
        } else {
            return ResultDTO.fail(Boolean.FALSE, "用户名或者密码错误");
        }
    }

    @GetMapping("getImage")
    public void getImage(HttpServletResponse response, HttpSession session) throws IOException {
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        log.info("securityCode: {}", securityCode);
        session.setAttribute("code", securityCode);

        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }

    @PostMapping("register")
    @ResponseBody
    public ResultDTO<Boolean> register(String code, @RequestBody User user, HttpSession httpSession) {
        String sessionCode = httpSession.getAttribute("code").toString();
        if (sessionCode.equals(code)) {
            try {
                userService.registerUser(user);
                return ResultDTO.success(Boolean.TRUE, "成功");
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResultDTO.fail(Boolean.FALSE, "失败");
            }
        } else {
            return ResultDTO.fail(Boolean.FALSE, "验证码错误");
        }
    }
}
