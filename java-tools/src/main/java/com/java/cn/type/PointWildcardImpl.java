package com.java.cn.type;

interface PointSingleInterface<T> {
}

public class PointWildcardImpl implements PointSingleInterface<Comparable<? extends Number>> {
}