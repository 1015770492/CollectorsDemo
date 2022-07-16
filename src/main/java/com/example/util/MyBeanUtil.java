package com.example.util;

import com.example.demo.MultiGroupByDemo;
import com.example.demo.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyBeanUtil {

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
        Map<String, List<User>> groupMap = groupingBy(userList, User::getName, User::getCity);
        MultiGroupByDemo.printMap(groupMap);
    }
    /**
     * 将数据分组，根据方法引用（bean的get方法）
     *
     * @param list      为分组的数据
     * @param functions get方法数组
     */
    @SafeVarargs
    public static <T, R> Map<String, List<T>> groupingBy(List<T> list, Function<T, R>... functions) {
        return list.stream().collect(Collectors.groupingBy(t -> groupingBy(t, functions)));
    }

    /**
     * 分组工具根据函数式接口使用分组，将数据根据分组结果进行拆分
     */
    @SafeVarargs
    public static <T, R> String groupingBy(T t, Function<T, R>... functions) {
        if (functions == null || functions.length == 0) {
            throw new NullPointerException("functions数组不可以为空");
        } else if (functions.length == 1) {
            return functions[0].apply(t).toString();
        } else {
            return Arrays.stream(functions).map(fun -> fun.apply(t).toString()).reduce((str1, str2) -> str1 + "|" + str2).get();
        }
    }
}
