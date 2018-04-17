package com.nhsoft.test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Comparator;

public class MyComparator<T> implements Comparator<T> {

    private String sortField;
    private String sortType;


    public MyComparator() {
    }
    public MyComparator(String sortField, String sortType) {

        this.sortField = toUpperCaseFirst(sortField);
        this.sortType = sortType;
    }

    //首字母转大写
    public static String toUpperCaseFirst(String str){
        if(Character.isUpperCase(str.charAt(0)))
            return str;
        else
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
    }


    @Override
    public int compare(T data01, T data02) {

        int value01 = 0;
        int value02 = 0;
        if("asc".equals(sortType) || "ASC".equals(sortType)){
            value01 = 1;
            value02 = -1;
        }
        if("desc".equals(sortType) || "DESC".equals(sortType)){
            value01 = -1;
            value02 = 1;
        }

        try {
            Class clazz01 = data01.getClass();
            Method method01 = clazz01.getMethod("get" + sortField);

            Class clazz02 = data02.getClass();
            Method method02 = clazz02.getMethod("get" + sortField);


            Type type=method02.getGenericReturnType();
            String end = type.toString();
            String substring = end.substring(type.toString().lastIndexOf(".") + 1, end.length());
            if("Integer".equals(substring)){
                Integer invoke01 = (Integer)method01.invoke(data01);
                Integer invoke02 = (Integer)method02.invoke(data02);

                if (invoke01.compareTo(invoke02) > 0) {
                    return value01;
                } else if (invoke01.compareTo(invoke02) < 0) {
                    return value02;
                } else {
                    return 0;
                }
            }
            if("String".equals(substring)){
                String invoke01 = (String)method01.invoke(data01);
                String invoke02 = (String)method02.invoke(data02);
                if (invoke01.compareTo(invoke02) > 0) {
                    return value01;
                } else if (invoke01.compareTo(invoke02) < 0) {
                    return value02;
                } else {
                    return 0;
                }
            }
            if("BigDecimal".equals(substring)){
                BigDecimal invoke01 = (BigDecimal)method01.invoke(data01);
                BigDecimal invoke02 = (BigDecimal)method02.invoke(data02);
                if (invoke01.compareTo(invoke02) > 0) {
                    return value01;
                } else if (invoke01.compareTo(invoke02) < 0) {
                    return value02;
                } else {
                    return 0;
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }



       /* //默认按门店升序排序
        int i = data01.getBranchNum().compareTo(data02.getBranchNum());
        */

    return 0;

    }
}
