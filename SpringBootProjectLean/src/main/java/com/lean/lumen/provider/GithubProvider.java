package com.lean.lumen.provider;

import com.alibaba.fastjson.JSON;
import com.lean.lumen.dto.AccessTokenDTO;
import com.lean.lumen.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

//提供第三方功能, 放入provider中
@Component
public class GithubProvider {

    //通过okHttp来获取Github的accessToken
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType header = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(header, JSON.toJSONString(accessTokenDTO));

        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()){
            String[] split = response.body().string().split("&");
            String token = split[0].split("=")[1];
            //System.out.println(token);

            return token;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    //通过accessToken拿到对应的用户信息, 并返回
    public GithubUserDTO getUser(String accesstoken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
//                .header("Authorization", accesstoken)
                .url("https://api.github.com/user?access_token="+accesstoken)
                .get()
                .build();

        //Response execute = null;
        GithubUserDTO userDTO = new GithubUserDTO();
        try (Response execute = client.newCall(request).execute()){
            String body = execute.body().string();
            userDTO = JSON.parseObject(body, GithubUserDTO.class);
            //System.out.println(userDTO);
        } catch (IOException e) {
            userDTO.setName("lumen");
            System.out.println("connect reset");
//            e.printStackTrace();
        }
        return userDTO;
    }
}
