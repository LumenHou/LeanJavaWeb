package com.lean.lumen.dto;

import lombok.Data;

@Data
public class GithubUserDTO {
    public String name;
    public long id;
    public String bio;
    private String avatar_url;
}
