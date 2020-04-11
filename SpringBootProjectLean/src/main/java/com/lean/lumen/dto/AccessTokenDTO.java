package com.lean.lumen.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {
    public String client_id;
    public String client_secret;
    public String code;
    public String state;
    public String redirect_uri;
}
