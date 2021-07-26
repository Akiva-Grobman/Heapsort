package com.akiva;

import java.util.Scanner;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your array type: \n\t1. Integer\n\t2. Character\n\t3. String");
        int type = getValidInput("[1-3]", scanner);
        System.out.println("Please enter your array length");
        int length = getValidInput("[1-9]+", scanner);
        sort(scanner, type, length);
        scanner.close();
    }

    private static void sort(Scanner scanner, int type, int length) {
        switch (type) {
            case 1:
                Integer[] intArray = new Integer[length];
                HeapSort<Integer> integerHeapSort = new HeapSort<>(intArray);
                handleSort(intArray, integerHeapSort, () -> {
                    int scannerInput = scanner.nextInt();
                    scanner.nextLine();
                    return scannerInput;
                });
                break;
            case 2:
                Character[] charArray = new Character[length];
                HeapSort<Character> characterHeapSort = new HeapSort<>(charArray);
                handleSort(charArray, characterHeapSort, () -> scanner.nextLine().charAt(0));
                break;
            case 3:
                String[] stringArray = new String[length];
                HeapSort<String> stringHeapSort = new HeapSort<>(stringArray);
                handleSort(stringArray, stringHeapSort, scanner::nextLine);
        }
    }

    private static <T extends Comparable<T>> void handleSort(T[] array, HeapSort<T> heapSort, Supplier<T> getInput) {
        Consumer<Integer> populate = getConsumer(array, getInput);
        populateArray(array.length, populate);
        sort(heapSort);
    }

    private static int getValidInput(String regex, Scanner scanner) {
        String input = scanner.nextLine();
        if(!input.matches(regex)) {
            System.out.println(input + " isn't a valid input type, bye");
            System.exit(0);
        }
        return Integer.parseInt(input);
    }

    private static void populateArray(int length, Consumer<Integer> populate) {
        for (int i = 0; i < length; i++) {
            System.out.println("Enter an element: ");
            populate.accept(i);
        }
    }

    private static <T extends Comparable<T>> Consumer<Integer> getConsumer(T[] array, Supplier<T> getInput) {
        return i -> array[i] = getInput.get();
    }

    private static <T extends Comparable<T>>void sort(HeapSort<T> heapSort) {
        heapSort.printArray();
        heapSort.sort();
        heapSort.printArray();
    }

}
