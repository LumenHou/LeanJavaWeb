package com.lean.lumen.controller;

import com.lean.lumen.dto.AccessTokenDTO;
import com.lean.lumen.dto.GithubUserDTO;
import com.lean.lumen.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                            HttpServletRequest request){

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
            request.getSession().setAttribute("user", user);
        }
        return "redirect:/";
    }
}
