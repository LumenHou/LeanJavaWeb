package com.lean.lumen.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    String name;
    Integer age;
}
