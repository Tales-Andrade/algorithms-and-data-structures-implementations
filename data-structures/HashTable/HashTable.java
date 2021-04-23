import java.util.*;

public class HashTable<K, V>{
    private int size;
    private int capacity;
    private static final double MAX_LOAD_FACTOR = 0.75;
    private ArrayList<LinkedList<HashPair<K, V>>> table;

    public HashTable(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.table = new ArrayList<>(this.capacity);

        for (int i = 0; i < this.capacity; i++){
            LinkedList<HashPair<K,V>> initialBucket = new LinkedList<HashPair<K,V>>();
            this.table.add(initialBucket);
        }
    }

    // This hashFunction is made for simplicity.
    // However, there are other methods to create a hashFunction, such as the Multiplication method, Open Addressing (Linear, Quadratic, and Double).
    public int hashFunction(K key){
        return (Math.abs(key.hashCode())%this.capacity);
    }

    public V put(K key, V value){
        HashPair<K, V> entry = new HashPair<K, V>(key, value);

        int index = hashFunction(key);

        LinkedList<HashPair<K, V>> bucket = this.table.get(index);

        if (bucket != null){
            for (HashPair<K, V> pair: bucket){
                if (pair.getKey().equals(key)){
                    V oldValue = pair.getValue();

                    pair.setValue(value);

                    return oldValue;
                }
            }
        }

        bucket.add(entry);
        this.size += 1;

        if ((this.size/this.capacity) > MAX_LOAD_FACTOR){
            rehash();
        }

        return null;
    }

    public void rehash(){
        this.capacity *= 2;

        ArrayList<LinkedList<HashPair<K, V>>> newTable = new ArrayList<LinkedList<HashPair<K, V>>>(this.capacity);

        for (int i = 0; i < this.capacity; i++){
            LinkedList<HashPair<K, V>> bucket = new LinkedList<HashPair<K, V>>();
            newTable.add(bucket);
        }

        ArrayList<LinkedList<HashPair<K, V>>> tmpTable = this.table;

        this.table = newTable;
        this.size = 0;

        for (LinkedList<HashPair<K, V>> bucket : tmpTable){
            for (HashPair<K, V> pair : bucket){
                this.put(pair.getKey(), pair.getValue());
            }
        }
    }

    public V remove(K key){
        int index = hashFunction(key);

        LinkedList<HashPair<K,V>> bucket = this.table.get(index);

        if (bucket != null){
            for (HashPair<K, V> pair : bucket){
                if (pair.getKey().equals(key)){
                    V value = pair.getValue();

                    this.table.remove(index);
                    this.size -= 1;

                    return value;
                }
            }
        }

        return null;
    }

    public V get(K key){
        int index = hashFunction(key);

        LinkedList<HashPair<K,V>> bucket = this.table.get(index);

        if (bucket != null){
            for (HashPair<K, V> pair : bucket){
                if (pair.getKey().equals(key)){
                    return pair.getValue();
                }
            }
        }

        return null;
    }

    public ArrayList<K> getKeys(){
        ArrayList<K> keys = new ArrayList<K>(this.size);

        for (LinkedList<HashPair<K, V>> bucket : this.table){
            for (HashPair<K, V> pair : bucket){
                keys.add(pair.getKey());
            }
        }

        return keys;
    }

    public ArrayList<V> getValues(){
        HashTable<V, K> invertedTable = new HashTable<V, K>(this.capacity);

        for (LinkedList<HashPair<K, V>> bucket : this.table){
            for (HashPair<K, V> pair : bucket){
                invertedTable.put(pair.getValue(), pair.getKey());
            }
        }

        return invertedTable.getKeys();
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return (this.size == 0);
    }

    private class HashPair<K, V>{
        private K key;
        private V value;

        HashPair(K key, V value){
            this.key = key;
            this.value = value;
        }
           
        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }

        public void setValue(V value){
            this.value = value;
        }
        
    }

    public static void main(String[] args){
        HashTable<Integer, Integer> ht = new HashTable<Integer, Integer>(5);

        ht.put(1, 2);
        ht.put(3, 2);
        ht.put(5, 7);
        ht.put(8, 3);
        ht.put(4, 6);
        ht.put(7, 7);

        System.out.println(ht.isEmpty());

        System.out.println(ht.getSize());

        System.out.println(ht.get(4));

        System.out.println(ht.getKeys());

        System.out.println(ht.getValues());

        System.out.println(ht.remove(5));

        System.out.println(ht.getKeys());

        System.out.println("The code is working.");
    }
}