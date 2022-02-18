package ru.knyazev.projects.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.IntStream;

public class TestApp {

    public static final int maxInternalArraySize = 10;
    public static final int globalArraySize = 10;

    // При небольшом setSize, эффективнее использовать эту функцию
    static int[] getUniqueIntsArray(int setSize, int maxValue) {
        if (maxValue + 1 < setSize) {
            throw new IllegalStateException("max integer value can not be lower then array size");
        }

        int[] values = new int[setSize];

        for (int i = 0; i < maxValue; i++) {
            values[i] = i;
        }

        Random random = new Random();
        for (int i = maxValue - 1; i >= 1; i--) {
            int j = random.nextInt(i + 1);
            int temp = values[j];
            values[j] = values[i];
            values[i] = temp;
        }
        return values;
    }

//    При maxValue >> setSize, setSize большое, эффективнее использовать эту функцию
//    static int[] getUniqueIntsArray(int setSize, int maxValue) {
//        if (maxValue + 1 < setSize) {
//            throw new IllegalStateException("max integer value can not be lower then array size");
//        }
//
//        Random random = new Random();
//        HashSet<Integer> ints = new HashSet<>();
//        int[] outArray = new int[setSize];
//
//        int nextInt;
//        for (int i = 0; i < setSize; i++) {
//            do {
//                nextInt = Math.abs(random.nextInt(maxValue));
//            } while (ints.contains(nextInt));
//            ints.add(nextInt);
//            outArray[i] = nextInt;
//        }
//        return outArray;
//    }

    static int[][] function(int n) {
        int[][] outArray = new int[n][];

        Random random = new Random();
        int[] uniqueInts = getUniqueIntsArray(maxInternalArraySize, maxInternalArraySize);

        int index = 0;
        for(int arraySize : uniqueInts) {
                if (index % 2 != 0) {
                    outArray[index] = random.ints(arraySize)
                            .boxed()
                            .sorted(Collections.reverseOrder())
                            .mapToInt(Integer::intValue)
                            .toArray();
                }
                else {
                    outArray[index] = random.ints(arraySize).sorted().toArray();
                }
                index++;
            }
        return outArray;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(function(globalArraySize))
        );
    }
}
