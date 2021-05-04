import java.util.*;

public class InsertionSort<E extends Comparable<E>>{

    public static <E extends Comparable<E>> void insertionSort(ArrayList<E> list){
        for (int i = 1; i < list.size(); i++){
            E tmp = list.get(i);

            int j = i;

            while((j > 0) && (tmp.compareTo(list.get(j - 1)) < 0)){
                list.set(j, list.get(j - 1));

                j -= 1;
            }

            list.set(j, tmp);
        }
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

        InsertionSort.insertionSort(list);

        System.out.println(list.toString());
    }
}