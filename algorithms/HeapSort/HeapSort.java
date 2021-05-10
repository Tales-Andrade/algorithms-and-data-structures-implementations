import java.util.*;

public class HeapSort<E extends Comparable<E>>{

    public static <E extends Comparable<E>> void heapSort(ArrayList<E> list){
        for (int i = ((list.size()/2) - 1); i >= 0; i--){
            heapify(list, list.size(), i);
        }

        for (int i = (list.size() - 1); i >= 0; i--){
            swap(list, 0, i);
            heapify(list, i, 0);
        }
    }

    private static <E extends Comparable<E>> void heapify(ArrayList<E> list, int indexOne, int indexTwo){
        int left = ((2 * indexTwo) + 1);
        int right = ((2 * indexTwo) + 2);
        int largest = indexTwo;

        if ((right < indexOne) && (list.get(right).compareTo(list.get(largest)) > 0)){
            largest = right;
        }

        if ((left < indexOne) && (list.get(left).compareTo(list.get(largest)) > 0)){
            largest = left;
        }

        if (largest != indexTwo){
            swap(list, largest, indexTwo);
            heapify(list, indexOne, largest);
        }
    }

    private static <E extends Comparable<E>> void swap(ArrayList<E> list, int indexOne, int indexTwo){
        E tmp = list.get(indexOne);
        list.set(indexOne, list.get(indexTwo));
        list.set(indexTwo, tmp);
    }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(5);
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(3);

        System.out.println(list.toString());

        HeapSort.heapSort(list);

        System.out.println(list.toString());
        System.out.println("This code works.");
    }
}