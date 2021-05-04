import java.util.*;

public class SelectionSort<E extends Comparable<E>>{

    public static <E extends Comparable<E>> void selectionSort(ArrayList<E> list){
        for (int i = 0; i < (list.size() - 1); i++){
            int index = i;

            E min = list.get(i);

            for (int j = (i + 1); j < list.size(); j++){
                if (list.get(j).compareTo(min) < 0){
                    index = j;
                    min = list.get(j);
                }
            }

            if (index != i){
                swap(list, i, index);
            }
        }
    }

    public static <E extends Comparable<E>> void swap(ArrayList<E> list, int indexOne, int indexTwo){
        E tmp = list.get(indexOne);
        list.set(indexOne, list.get(indexTwo));
        list.set(indexTwo, tmp);
    }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(3);
        list.add(17);
        list.add(-5);
        list.add(-2);
        list.add(23);
        list.add(4);

        System.out.println(list.toString());

        SelectionSort.selectionSort(list);

        System.out.println(list.toString());
    }
}