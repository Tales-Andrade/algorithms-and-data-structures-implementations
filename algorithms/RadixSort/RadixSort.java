import java.util.*;

public class RadixSort{

    public static void radixSort(int[] array){
        int max = getMax(array);

        for (int value = 1; (max / value) > 0; value *= 10){
             countSort(array, value);
        }
    }

    private static void countSort(int[] array, int value){
        int range = 10;

        int[] sortedArray = new int[array.length];
        int[] frequency = new int[range];

        for (int i = 0; i < array.length; i++){
            int digit = ((array[i] / value) % range);
            frequency[digit] += 1;
        }

        for (int i = 1; i < range; i++){
            frequency[i] += frequency[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--){
            int digit = ((array[i] / value) % range);
            sortedArray[frequency[digit] - 1] = array[i];
            frequency[digit] -= 1;
        }

        for (int i = 0; i < array.length; i++){
            array[i] = sortedArray[i];
        }
    }

    private static int getMax(int[] array){
        int max = array[0];

        for (int i = 0; i < array.length; i++){
            if (array[i] > max){
                max = array[i];
            }
        }

        return max;
    }

    public static void main(String[] args){
        int[] array = {3, 17, 5, 2, 23, 4};

        System.out.println(Arrays.toString(array));

        RadixSort.radixSort(array);

        System.out.println(Arrays.toString(array));
    }
}