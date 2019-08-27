package com.java.a;

import java.util.ArrayList;
import java.util.List;

//class User {
//    private String name;
//    private int age;
//
//    public User(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//}

public class UserTest {

    public static <T> List<T> getUserList(String... strs){
        List<Object> list = getObject();
        return (List<T>)list;
    }

    public static List<Object> getObject(){
        List<Object> list = new ArrayList<>();
        User java = new User("java", 10);
        User mysql = new User("mysql", 21);
        list.add(java);
        list.add(mysql);
        return list;
    }

    public static void main(String[] args) {
        List<User> list = getUserList("12");
        for (User u : list)
        System.out.println(u);
    }
}
