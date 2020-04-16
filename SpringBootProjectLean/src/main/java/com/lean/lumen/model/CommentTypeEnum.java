package com.lean.lumen.model;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    CommentTypeEnum(Integer i) {
        this.type = i;
    }

    public Integer getType() {
        return type;
    }
}
