package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        int[] arr1 = new int[]{2, 5, 7, 2};
        int[] arr2 = new int[]{2, 5, 2, 7};
        System.out.println(m.equality(arr1, arr2));

    }

    public boolean equality(int[] arr1, int[] arr2) {
        int sum1 = 0;
        int sum2 = 0;
        boolean result = true;
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                sum1 += arr1[i];
                sum2 += arr2[i];
            }
            if (sum1 == sum2) {
                Arrays.sort(arr1);
                Arrays.sort(arr2);
                for (int i = 0; i < arr1.length; i++) {
                    if (arr1[i] != arr2[i]) {
                        return false;
                    }
                }
                return result;
            } else {
                return false;
            }
        }
        return false;
    }


    public void unionUnique(int[]... mas) {
        Set<Integer> arr1 = new TreeSet<>();
        Arrays.stream(mas).forEach((w) -> {
            for (int temp : w) {
                arr1.add(temp);
            }
        });
        System.out.println(arr1);

    }

    public void crossRoadUpgrade(int[]... mas) {
        Set<Integer> finalResult = new TreeSet<>();
        if (mas.length == 1) {
            System.out.println(Arrays.toString(mas[0]));
            return;
        }
        for (int i = 0; i < mas.length - 1; i++) {
            if (i == 0) {
                finalResult = crossRoad(mas[i], mas[i + 1]);
            } else {
                finalResult = crossRoad(toArray(finalResult), mas[i + 1]);
            }
        }
        System.out.println(finalResult);

    }

    public int[] toArray(Collection<Integer> cl) {
        int[] mas = new int[cl.size()];
        int iterator = 0;
        for (int temp : cl) {
            mas[iterator] = temp;
            iterator++;
        }
        return mas;
    }

    public Set crossRoad(int[] mas1, int[] mas2) {
        Set<Integer> result = new TreeSet<>();
        for (int i = 0; i < mas1.length; i++) {
            for (int j = 0; j < mas2.length; j++) {
                if (mas1[i] == mas2[j]) {
                    result.add(mas1[i]);
                }
            }
        }
        // System.out.println(result);
        return result;
    }
}
