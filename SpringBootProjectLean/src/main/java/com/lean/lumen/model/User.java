package com.lean.lumen.model;

import lombok.Data;

@Data
public class User {

    private Integer id;

    public String name;

    public String accountId;

    public String token;

    public Long gmt_Created;

    public Long gmt_modified;
}
