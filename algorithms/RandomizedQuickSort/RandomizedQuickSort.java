import java.util.*;

public class RandomizedQuickSort<E extends Comparable<E>>{

    public static <E extends Comparable<E>> void randomizedQuickSort(ArrayList<E> list, int indexOne, int indexTwo){
        if (indexOne < indexTwo){
            int i = randomizedPlaceAndDivide(list, indexOne, indexTwo);

            randomizedQuickSort(list, indexOne, i - 1);
            randomizedQuickSort(list, i + 1, indexTwo);
        }
    }

    public static <E extends Comparable<E>> int randomizedPlaceAndDivide(ArrayList<E> list, int indexOne, int indexTwo){
        Random random = new Random();
		int randomIndex = random.nextInt((indexTwo - indexOne) + 1) + indexOne;

        swap(list, randomIndex, indexTwo);

        return placeAndDivide(list, indexOne, indexTwo);
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

        RandomizedQuickSort.randomizedQuickSort(list, 0, list.size() - 1);

        System.out.println(list.toString());
    }
}