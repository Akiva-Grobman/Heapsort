package com.akiva;

public class HeapSort <T extends Comparable<T>>{

    private final T[] array;

    public HeapSort(T[] array) {
        this.array = array;
    }

    public void sort() {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            sort(n, i);

        for (int i = n - 1; i >= 0; i--) {

            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            sort(i, 0);
        }
    }

    private void sort(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && array[l].compareTo(array[largest]) >= 1)
            largest = l;

        if (r < n && array[r].compareTo(array[largest]) >= 1)
            largest = r;

        if (largest != i) {

            T swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            sort(n, largest);
        }
    }

    public void printArray() {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

