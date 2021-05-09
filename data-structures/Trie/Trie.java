import java.util.*;

public class Trie{
    private final char rootChar = '\0';
    private Node root = new Node(this.rootChar);

    public void add(String word, int num){
        Node node = this.root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            Node next = node.getChildren().get(c);

            if(next == null){
                next = new Node(c);
                node.addChild(next, c);
            }

            node = next;
            node.setCount(node.getCount() + num);
        }

        if (node != this.root){
            node.setEnd();
        }
    }

    public void add(String word){
        add(word, 1);
    }

    public void remove(String word, int num){
        if (!contains(word)) return;

        Node node = this.root;

        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            Node current = node.getChildren().get(c);
            current.setCount(current.getCount() - num);

            if(current.getCount() <= 0){
                node.getChildren().remove(c);

                current.setChildren(null);
                current = null;
                return;
            }

            node = current;
        }
    }

    public void remove(String word){
        remove(word, 1);
    }

    public int count(String word){
        Node node = this.root;

        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);

            if (node == null){
                return 0;
            }

            node = node.getChildren().get(c);
        }

        if (node != null){
            return node.getCount();
        }

        return 0;
    }

    public boolean contains(String word){
        return (count(word) != 0);
    }

    public void clear(){
        this.root.setChildren(null);
        this.root = new Node(rootChar);
    }

    private void clear(Node node){
        if (node == null) return;

        for (Character c : node.getChildren().keySet()){
            Node next = node.getChildren().get(c);
            clear(next);
            next = null;
        }

        node.getChildren().clear();
        node.setChildren(null);
    }

    private class Node{
        private char c;
        private int count = 0;
        private boolean isEnd = false;
        private HashMap<Character, Node> children = new HashMap<>();

        Node(char c){
            this.c = c;
        }

        void addChild(Node node, char c){
            this.children.put(c, node);
        }

        HashMap<Character, Node> getChildren(){
            return this.children;
        }

        void setChildren(HashMap<Character, Node> children){
            this.children = children;
        }

        char getChar(){
            return this.c;
        }

        void setChar(char c){
            this.c = c;
        }

        int getCount(){
            return this.count;
        }

        void setCount(int count){
            this.count = count;
        }

        boolean isEnd(){
            return this.isEnd;
        }

        void setEnd(){
            this.isEnd = true;
        }
    }

    public static void main(String[] args){
        Trie trie = new Trie();

        trie.add("the");
        trie.add("a");
        trie.add("there");
        trie.add("answer");
        trie.add("hello");
        trie.add("happy");
        trie.add("these");
        trie.add("gorilla");

        System.out.println("First Problem - Words that are fully included in the Trie: ");
        System.out.println(trie.contains("the"));
        System.out.println(trie.contains("a"));
        System.out.println(trie.contains("there"));
        System.out.println(trie.contains("answer"));
        System.out.println(trie.contains("hello"));
        System.out.println(trie.contains("happy"));
        System.out.println(trie.contains("these"));

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Second Problem - Words that are partially included in the Trie: ");
        System.out.println(trie.contains("go"));
        System.out.println(trie.contains("hell"));

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Second Problem - Words that are not included in the Trie: ");
        System.out.println(trie.contains("those"));
        System.out.println(trie.contains("cage"));
        System.out.println("-------------------------------------------------------------------");
        System.out.println("This code works.");
    }
}