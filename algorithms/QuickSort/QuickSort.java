import java.util.*;

public class QuickSort<E extends Comparable<E>>{

    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int indexOne, int indexTwo){
        if (indexOne < indexTwo){
            int i = placeAndDivide(list, indexOne, indexTwo);

            quickSort(list, indexOne, i - 1);
            quickSort(list, i + 1, indexTwo);
        }
    }

    public static <E extends Comparable<E>> int placeAndDivide(ArrayList<E> list, int indexOne, int indexTwo){
        E pivot = list.get(indexTwo);

        int wall = indexOne - 1;

        for (int i = indexOne; i < indexTwo; i++){
            if(list.get(i).compareTo(pivot) < 0){
                wall += 1;
                swap(list, i, wall);
            }
        }

        swap(list, indexTwo, (wall + 1));

        return (wall + 1);
    }

    public static <E extends Comparable<E>> void swap(ArrayList<E> list, int indexOne, int indexTwo){
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

        QuickSort.quickSort(list, 0, list.size() - 1);

        System.out.println(list.toString());
    }
}