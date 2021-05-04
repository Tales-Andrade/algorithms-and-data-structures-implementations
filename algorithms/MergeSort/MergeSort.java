import java.util.*;

public class MergeSort<E extends Comparable<E>>{
    public static <E extends Comparable<E>> ArrayList<E> mergeSort(ArrayList<E> list){
        if (list.size() == 1){
            return list;
        } else {
            int mid = ((list.size() - 1) / 2);
            ArrayList<E> list1 = new ArrayList<E>(list.subList(0, mid + 1));
            ArrayList<E> list2 = new ArrayList<E>(list.subList(mid + 1, list.size()));
            list1 = mergeSort(list1);
            list2 = mergeSort(list2);
        
            return merge(list1, list2);
        }
    }

    private static <E extends Comparable<E>> ArrayList<E> merge(ArrayList<E> list1, ArrayList<E> list2){
        ArrayList<E> list = new ArrayList<E>();

        while((!list1.isEmpty()) && (!list2.isEmpty())){
            if (list1.get(0).compareTo(list2.get(0)) < 0){
                list.add(list1.remove(0));
            } else {
                list.add(list2.remove(0));
            }
        }

        while(!list1.isEmpty()){
            list.add(list1.remove(0));
        }

        while(!list2.isEmpty()){
            list.add(list2.remove(0));
        }

        return list;
    }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(8);
        list.add(10);
        list.add(3);
        list.add(11);
        list.add(6);
        list.add(1);
        list.add(7);
        list.add(16);
        list.add(2);
        list.add(5);
        list.add(4);
        list.add(13);


        System.out.println(list.toString());

        ArrayList<Integer> sortedList = MergeSort.mergeSort(list);

        System.out.println(sortedList.toString());
    }
}