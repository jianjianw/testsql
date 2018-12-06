package com.nhsoft.test;

public class SortTest {

    public static void main(String[] args){

        int[] array  = {1,2,4,5,3,8,5,2};
        //selectSort(array);
        bubbleSort(array);
        for (int i = 0; i <array.length ; i++) {
            System.out.println(array[i]);
        }
    }



    public static void bubbleSort(int[] array){

        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j]>array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }

    }
    /*//冒泡排序
    public static void BubbleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);

                }
            }
        }
    }*/


    //选择排序
    public static void selectSort(int [] array){
        for (int i = 0; i <array.length-1 ; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[j] < array[i]){
                    swap(array,i,j);
                }
            }
        }
    }


    //交换元素
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }



    public static class Test{
        public static SortTest instance = new SortTest();
    }


    private SortTest(){

    }

    public static SortTest getInstance(){
        return Test.instance;
    }

}
