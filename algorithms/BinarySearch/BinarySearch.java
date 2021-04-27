import java.util.*;

public class BinarySearch<E extends Comparable<E>>{

    // Binary Search in the iterative implementation method
    public static <E extends Comparable<E>> int iterativeBinarySearch(ArrayList<E> list, E element){
        int leftIndex = 0;
        int rightIndex = list.size() - 1;

        while (leftIndex <= rightIndex){
            int midIndex = (leftIndex + rightIndex)/2;

            if ((list.get(midIndex).compareTo(element)) == 0){
                return midIndex;
            }
            else{
                if ((list.get(midIndex).compareTo(element)) > 0){
                    rightIndex = midIndex - 1;
                }
                else{
                    leftIndex = midIndex + 1;
                }
            }
        }
        return -1;
    }

    // Binary Search in the recursive implementation method
     public static <E extends Comparable<E>> int recursiveBinarySearch(ArrayList<E> list, E element, int leftIndex, int rightIndex){
         if (leftIndex <= rightIndex){
             int midIndex = (leftIndex + rightIndex)/2;

             if ((list.get(midIndex).compareTo(element)) == 0){
                 return midIndex;
             }
             else{
                 if ((list.get(midIndex).compareTo(element)) > 0){
                     return recursiveBinarySearch(list, element, leftIndex, midIndex - 1);
                 }
                 else{
                     return recursiveBinarySearch(list, element, midIndex + 1, rightIndex);
                 }
             }
         }

         return -1;
     }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i <= 10; i++){
            list.add(i);
        }

        System.out.println(BinarySearch.iterativeBinarySearch(list, 4));

        System.out.println(BinarySearch.iterativeBinarySearch(list, 8));

        System.out.println(BinarySearch.iterativeBinarySearch(list, -12));

        System.out.println(BinarySearch.recursiveBinarySearch(list, 4, 0, list.size() - 1));

        System.out.println(BinarySearch.recursiveBinarySearch(list, 8, 0, list.size() - 1));

        System.out.println(BinarySearch.recursiveBinarySearch(list, -12, 0, list.size() - 1));

        System.out.println("The code is working.");
    }
}