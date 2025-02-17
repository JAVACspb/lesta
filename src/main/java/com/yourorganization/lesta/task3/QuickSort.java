package com.yourorganization.lesta.task3;

public class QuickSort {

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {

            int pivotIndex = partition(arr, left, right);

            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    /**
     * Метод partition «разбивает» массив относительно pivot-а:
     * - все элементы <= pivot окажутся слева
     * - все элементы > pivot — справа
     * Возвращает индекс pivot-а после разбиения.
     */
    private static int partition(int[] arr, int left, int right) {

        int pivotValue = arr[right];
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] <= pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }

        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
