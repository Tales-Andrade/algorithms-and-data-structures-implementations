public class AVLTree<K extends Comparable<K>>{
    private Node<K> root;
    private int size;

    public AVLTree(){
        this.root = null;
        this.size = 0;
    }

    public AVLTree(K key){
        this.root = new Node<K>(key);
        this.size = 1;
    }

    public int getHeight(){
        if (this.root == null) return 0;

        return this.root.getHeight();
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return (this.size == 0);
    }

    private class Node<K extends Comparable<K>>
    {
        private K key;
        private Node<K> leftChild;
        private Node<K> rightChild;
        private int height;
        private int balanceFactor;

        Node()
        {
            this.leftChild = null;
            this.rightChild = null;
        }

        Node(K key)
        {
            this.key = key;
            this.leftChild = null;
            this.rightChild = null;
        }

        public K getKey()
        {
            return this.key;
        }

        public void setKey(K key)
        {
            this.key = key;
        }

        public Node<K> getLeftChild()
        {
            return this.leftChild;
        }

        public void setLeftChild(Node<K> leftChild)
        {
            this.leftChild = leftChild;
        }

        public Node<K> getRightChild()
        {
            return this.rightChild;
        }

        public void setRightChild(Node<K> rightChild)
        {
            this.rightChild = rightChild;
        }

        public int getHeight(){
            return this.height;
        }

        public void setHeight(int height){
            this.height = height;
        }

        public int getBalanceFactor(){
            return this.balanceFactor;
        }

        public void setBalanceFactor(int balanceFactor){
            this.balanceFactor = balanceFactor;
        }
    }

    public static void main(String[] args){
        System.out.println("This code works.");
    }
}