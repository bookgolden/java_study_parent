package com.java.cn.bigdecimal;

import java.io.Serializable;

interface PointInterface<T, U> {
}

public class PointGenericityImpl<T extends Number & Serializable> implements PointInterface<T, Integer> {
}