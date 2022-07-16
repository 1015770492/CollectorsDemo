package com.example.demo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private String name;
    private int id;
    private String city;
    private String sex;
    private LocalDateTime birthDay;
}