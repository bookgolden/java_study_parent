package com.java.cn.parameter.aggregation;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class Test_AggregationDefine {

    // 自定义参数聚合
    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void test1(@AggregateWith(PersonAggregator.class) Person person) {
    }

    public class PersonAggregator implements ArgumentsAggregator {
        @Override
        public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
            return new Person(arguments.getString(0),
                    arguments.getString(1),
                    arguments.get(2, Gender.class),
                    arguments.get(3, LocalDate.class));
        }
    }
}
