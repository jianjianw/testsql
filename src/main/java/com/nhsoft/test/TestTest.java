package com.nhsoft.test;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class TestTest {

    @Test
    public void test01(){

        int[] a = {1,3,6,4,4,2};
        for(int i = 0;i<a.length;i++){
            int temp ;
            for(int j = i+1;j<a.length;j++){
                if( a[j] > a[i]){
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }

        for(int i : a){
            System.out.println(i);
        }
    }

    @Test
    public void test02(){

     String str= " 111 ";
        str.trim();
        int length = str.length();
        System.out.println(length);
        char[] c = new char[length];
        for(int i = 0;i<str.length();i++){

            char c1 = str.charAt(i);
            if(" ".equals(c1)){

            }
        }
    }




    @Test
    public void test03(){
        int i = 0;
        int b = ++i;
        System.out.println(b);
        System.out.println(i);
        ++i;
        System.out.println(i);

        String str = "企鹅七届群殴我欺负";
        char c = str.charAt(2);
        System.out.println(c);
    }


    @Test
    public void test04(){

        String res = "C:/test/新建文本文档.txt";
        String dest = "C:/test/新建文本文档01.txt";
        String charset = "utf-8";
        copyFile(res,dest,charset);

    }

    public void copyFile(String res,String dest,String charset){
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {


            FileInputStream fileInputStream = new FileInputStream(res);
            InputStreamReader reader = new InputStreamReader(fileInputStream,charset);
            bufferedReader = new BufferedReader(reader);


            FileWriter fileWriter = new FileWriter(dest);
            bufferedWriter = new BufferedWriter(fileWriter);


            String s;
            while ((s = bufferedReader.readLine())!= null){

                System.out.println(s);
                char c = s.charAt(0);
                System.out.println(c);
                bufferedWriter.write(c);
               /* System.out.println(s);
                bufferedWriter.write(s);*/
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test05(){

//        List< String> words = Arrays. asList(" Java 8", "Lambdas", "In", "Action");
//        List<Integer> collect = words.stream().map(String::length).collect(Collectors.toList());
//        System.out.println();

        String[] arrayOfWords = {"Goodbye", "World"};
        Stream< String> streamOfwords = Arrays. stream( arrayOfWords);
//        List<String[]> collect = streamOfwords.map(word -> word.split("")).distinct().collect(toList());
//        System.out.println();

        List<Stream<String>> collect1 = streamOfwords.map(word -> word.split("")).map(Arrays::stream).distinct().collect(toList());
        System.out.println();


    }
}
