package com.java.cn.parameter.aggregation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class Test_Aggregation {
    // 参数聚合
    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1995-05-20",
            "John, Doe, M, 1992-10-22"
    })
    void test1(ArgumentsAccessor arguments) {
        String firstName = arguments.getString(0);
        String lastName = arguments.getString(1);
        Gender gender = arguments.get(2, Gender.class);
        LocalDate localDate = arguments.get(3, LocalDate.class);

        System.out.printf("first name:%s\n", firstName);
        System.out.printf("last name:%s\n", lastName);
        System.out.printf("sex:%s\n", gender.getGender());
        System.out.println("date:" + localDate);
        System.out.println("-----------------------");

        Person person = new Person(firstName, lastName, gender, localDate);
    }
}

