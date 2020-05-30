package com.lean.lumen.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Place implements Serializable {
    private Integer id;
    private String name;
    private String picpath;
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date hottime;
    private Double hotticket;
    private Double dimticket;
    private String placedes;
    private Integer provinceid;
}
