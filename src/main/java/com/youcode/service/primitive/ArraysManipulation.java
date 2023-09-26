package com.youcode.service.primitive;

public class ArraysManipulation {
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
}
