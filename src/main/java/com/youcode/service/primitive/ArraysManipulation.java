package com.youcode.service.primitive;

import java.util.Arrays;
import java.util.Random;

public class ArraysManipulation {
    public static void sort2D(int[][] arr) {
        int size = arr.length;

        for (int i = 0; i <size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j][0] < arr[j + 1][0]) {
                    int[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }
    public static int [][][] deleteElement(int[][] array, int index) {
        int size = array.length;
        int [][][] result = new int[2][][];

        if (index < 0 || index >= size) {
            result[0] = new int[0][];
            result[1] = array;
            return result;
        }

        int [] element = array[index];
        int [][] newArray = new int[size - 1][];

        for (int i = 0; i < size -1; i++) {
            if(index != i)
                newArray[i] = array[i];
        }
        result[0] = new int[1][];
        result[0][0] = element;
        result[1] = newArray;

        return result;
    }

    public static int[][] deleteByIndex2D(int[][] array, int index) {
        int size = array.length;

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index :" + index);
        }

        for (int i = index; i < size -1; i++) {
            array[i] = array[i+1];
        }

        return Arrays.copyOf(array, size - 1);
    }

    public static void shuffle2D (int [][] arr) {
        Random random = new Random();
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(size);
            int [] temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

}
