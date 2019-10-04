package com.java.cn.parameter.aggregation;

import java.time.LocalDate;

/**
 * 人物
 */
public class Person {
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate localDate;

    public Person(String firstName, String lastName, Gender gender, LocalDate localDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", localDate=" + localDate +
                '}';
    }
}
