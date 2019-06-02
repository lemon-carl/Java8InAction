package com.java.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName : SortArray
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-11-02 10:42
 * @Description :
 * 冒泡排序
 * 直接选择排序
 * 反转排序
 */
public class SortArray {

    /**
     * 冒泡排序（相邻的元素比较）
     * @param array
     */
    public void bubbleSort(int [] array){
        for (int i = 1; i < array.length; i++){
            for(int j = 0; j < array.length - i; j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        showArray(array);
    }

    /**
     * 显示数组中的所有元素
     * @param array  要显示的数组
     */
    private void showArray(int[] array) {
        for (int i : array) {
            System.out.print("\t" + i);
        }
        System.out.println();
    }

    /**
     * 直接选择排序（将指定排序位置与其他数组元素分别对比）
     */
    public void selectSort(int [] array ){
        int index;
        for (int i = 1; i <  array.length; i++){
            index = 0;
            for (int j = 1; j <= array.length -i; j++){
                if(array[j] > array[index]){
                    index = j;
                }
            }

            int temp = array[array.length - i];
            array[array.length - i] = array[index];
            array[index] = temp;
        }

        showArray(array);
    }

    /**
     * 反转排序 （相反的顺序把原有数组的元素重新排序，第一个和最后一个，第二个和倒数第二个替换）
     */
    public void reverseSort(int [] array){
        System.out.println("数组原有内容:");
        showArray(array);
        int temp;
        int len = array.length;
        for (int i = 0; i < len/2; i++){
            temp = array[i];
            array[i] = array[len - 1 - i];
            array[len - 1 - i] = temp;
        }
        System.out.println("反转后的内容：");
        showArray(array);
    }

}
