import java.util.*;

public class BubbleSort<E extends Comparable<E>>{


    public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> list){
        boolean cont = true;

        while(cont){
            cont = false;

            for (int i = 0; i < (list.size() - 1); i++){
                if (list.get(i).compareTo(list.get(i + 1)) > 0){
                    swap(list, i, (i + 1));

                    cont = true;
                }
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

        list.add(10);
        list.add(7);
        list.add(4);
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(8);
        list.add(9);
        list.add(2);
        list.add(6);

        System.out.println(list.toString());

        BubbleSort.bubbleSort(list);

        System.out.println(list.toString());
    }
}