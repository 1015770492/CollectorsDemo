package com.example.demo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MultiGroupByDemo {


    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // data list
        List<User> userList = Arrays.asList(
                User.builder().id(123456).name("Zhang, San").city("ShangHai").sex("man").birthDay(LocalDateTime.parse("2022-07-01 12:00:00", df)).build(),
                User.builder().id(777777).name("Zhang, San").city("ShangHai").sex("woman").birthDay(LocalDateTime.parse("2022-07-01 12:00:00", df)).build(),
                User.builder().id(888888).name("Li, Si").city("ShangHai").sex("man").birthDay(LocalDateTime.parse("2022-07-01 12:00:00", df)).build(),
                User.builder().id(999999).name("Zhan, San").city("HangZhou").sex("woman").birthDay(LocalDateTime.parse("2022-07-01 12:00:00", df)).build(),
                User.builder().id(555555).name("Li, Si").city("NaJin").sex("man").birthDay(LocalDateTime.parse("2022-07-01 12:00:00", df)).build()
        );
        /*
         * maybe we can
         */

        // 1.Use the default vertical separator
        System.out.println("Use the default vertical separator:");
        HashMap<String, List<User>> defaultSpilt = userList.stream().collect(Collectors.groupingBy(User::getName, User::getCity));
        printMap(defaultSpilt);
        System.out.println();

        // 2.Use custom delimiters
        System.out.println("Use custom delimiters:");
        userList.stream().collect(Collectors.groupingBy("--", User::getName, User::getCity, User::getId));
        HashMap<? extends Serializable, List<User>> collect = userList.stream().collect(Collectors.groupingBy("--", User::getName, User::getCity, User::getId));
        printMap(collect);
        System.out.println();

        // 3.Use custom delimiters
        System.out.println("Use custom delimiters:");
        userList.stream().collect(Collectors.groupingBy("--", User::getName, User::getCity, User::getId));
        HashMap<? extends Serializable, List<User>> collect2 = userList.stream().collect(Collectors.groupingBy(User::getName, User::getCity, User::getBirthDay));
        printMap(collect2);


    }

    public static <T> void printMap(Map<? extends Serializable, List<T>> map){
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }

}
