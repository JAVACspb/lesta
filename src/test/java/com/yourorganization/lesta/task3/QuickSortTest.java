package com.yourorganization.lesta.task3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    void testEmptyArray() {
        System.out.println("=== testEmptyArray ===");
        int[] arr = {};
        System.out.println("Before sort: " + Arrays.toString(arr));

        QuickSort.quickSort(arr);

        System.out.println("After sort : " + Arrays.toString(arr));
        // Проверяем, что массив остался пустым
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        System.out.println("\n=== testSingleElement ===");
        int[] arr = {42};
        System.out.println("Before sort: " + Arrays.toString(arr));

        QuickSort.quickSort(arr);

        System.out.println("After sort : " + Arrays.toString(arr));
        // Проверяем, что массив из одного элемента не изменился
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testAlreadySorted() {
        System.out.println("\n=== testAlreadySorted ===");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Before sort: " + Arrays.toString(arr));

        QuickSort.quickSort(arr);

        System.out.println("After sort : " + Arrays.toString(arr));
        // Проверяем, что массив остался отсортированным
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseSorted() {
        System.out.println("\n=== testReverseSorted ===");
        int[] arr = {5, 4, 3, 2, 1};
        System.out.println("Before sort: " + Arrays.toString(arr));

        QuickSort.quickSort(arr);

        System.out.println("After sort : " + Arrays.toString(arr));
        // Проверяем, что массив стал {1, 2, 3, 4, 5}
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testRandomArray() {
        System.out.println("\n=== testRandomArray ===");
        int[] arr = {5, 2, 9, 1, 5, 6, 10, 3};
        System.out.println("Before sort: " + Arrays.toString(arr));

        // Делаем копию массива, чтобы потом сравнить результат
        int[] copy = Arrays.copyOf(arr, arr.length);

        // Сортируем «нашим» Quicksort
        QuickSort.quickSort(arr);

        System.out.println("After sort : " + Arrays.toString(arr));

        // Сортируем копию встроенным Arrays.sort
        Arrays.sort(copy);
        // Проверяем, что оба результата совпадают
        assertArrayEquals(copy, arr);

        System.out.println("Проверка пройдена: массив отсортирован корректно!");
    }
}
