package com.lean.lumen.controller;

import com.lean.lumen.dto.AccessTokenDTO;
import com.lean.lumen.dto.GithubUserDTO;
import com.lean.lumen.mapper.UserMapper;
import com.lean.lumen.model.User;
import com.lean.lumen.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthContorller {

    @Value("${github.client.id}")
    public String client_id;

    @Value("${github.client.secret}")
    public String client_secret;

    @Value("${github.redirecturi}")
    public String Redirect_uri;

    @Resource
    private GithubProvider githubProviderp;

    @Resource
    private UserMapper userMapper;

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletResponse response){
        //HttpServletRequest Spring可以自动获取这两个对象, 并放入上下文
        //HttpServletResponse

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        // callback url
        String accessToken = githubProviderp.getAccessToken(accessTokenDTO);
        GithubUserDTO user = githubProviderp.getUser(accessToken);
        if(null != user){
            // 如果查询到用户了, 就存入数据库
            User newUser = new User();
            newUser.setAccountId(String.valueOf(user.getId()));
            newUser.setName(user.getName());
            String token = UUID.randomUUID().toString();
            newUser.setToken(token);
            newUser.setGmtCreate(System.currentTimeMillis());
            newUser.setGmtModified(newUser.getGmtCreate());
            newUser.setAvatarUrl(user.getAvatar_url());
            userMapper.insert(newUser);

            response.addCookie(new Cookie("token", token));
        }
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        System.out.println("in log out");
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    cookie.setValue(null);
                }
            }
        }
        request.getSession().setAttribute("user", null);

        return "index";
    }
}
