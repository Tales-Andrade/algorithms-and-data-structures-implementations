public class RedBlackTree<E extends Comparable<E>>{
    // Constants
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    public final Node<E> NIL;

    private Node<E> root;
    private int size;


    public RedBlackTree(){
        NIL = new Node<E>(null);
        NIL.setColor(BLACK);
        NIL.setLeftChild(NIL);
        NIL.setRightChild(NIL);
        NIL.setParent(NIL);

        this.root = NIL;
    }

    public void insert(E element){
        Node<E> x = this.root;
        Node<E> y = NIL;
        Node<E> z = new Node<E>(RED, element, y, NIL, NIL);

        while (x != NIL){
            y = x;

            if (x.getElement().compareTo(element) > 0){
                x = x.getLeftChild();
            } else if (x.getElement().compareTo(element) < 0) {
                x = x.getRightChild();
            } else{
                return;
            }
        }

        if (y == NIL){
            this.root = z;
        } else if (z.getElement().compareTo(y.getElement()) < 0){
            y.setLeftChild(z);
        } else {
            y.setRightChild(z);
        }

        this.size += 1;

        insertFixup(z);
    }

    public void insertFixup(Node<E> z){
        while (z.getParent().getColor() == RED){
            if (z.getParent() == z.getParent().getParent().getLeftChild()){
                Node<E> y = z.getParent().getParent().getRightChild();

                if (y.getColor() == RED){
                    z.getParent().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getParent().getParent().setColor(RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRightChild()){
                        z = z.getParent();
                        leftRotate(z);
                    }

                    z.getParent().setColor(BLACK);
                    z.getParent().getParent().setColor(RED);
                    rightRotate(z.getParent().getParent());
                }
            } else {
                Node<E> y = z.getParent().getParent().getLeftChild();

                if (y.getColor() == RED){
                    z.getParent().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getParent().getParent().setColor(RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeftChild()){
                        z = z.getParent();
                        rightRotate(z);
                    }

                    z.getParent().setColor(BLACK);
                    z.getParent().getParent().setColor(RED);
                    leftRotate(z.getParent().getParent());
                }
            }
        }

        this.root.setColor(BLACK);
        NIL.setParent(null);
    }

    private void leftRotate(Node<E> x){
        Node<E> y = x.getRightChild();

        x.setRightChild(y.getLeftChild());

        if (y.getLeftChild() != NIL){
            y.getLeftChild().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == NIL){
            this.root = y;
        }

        if (x == x.getParent().getLeftChild()){
            x.getParent().setLeftChild(y);
        } else {
            x.getParent().setRightChild(y);
        }

        y.setLeftChild(x);
        x.setParent(y);
    }

    private void rightRotate(Node<E> y){
        Node<E> x = y.getLeftChild();

        y.setLeftChild(x.getRightChild());

        if (x.getRightChild() != NIL){
            x.getRightChild().setParent(y);
        }

        x.setParent(y.getParent());

        if (y.getParent() == NIL){
            this.root = x;
        }

        if (y == y.getParent().getLeftChild()){
            y.getParent().setLeftChild(x);
        } else {
            y.getParent().setRightChild(x);
        }

        x.setRightChild(y);
        y.setParent(x);
    }

    public boolean contains(Node<E> node, E element)
    {
        if (node == null)
        {
            return false;
        }
        else if (element.compareTo(node.getElement()) == 0)
        {
            return true;
        }
        else if (element.compareTo(node.getElement()) < 0)
        {
            return contains(node.getLeftChild(), element);
        }   
        else
        {
            return contains(node.getRightChild(), element);
        }
    }

    public Node<E> findMin(Node<E> node)
    {
        if (node == null)
        {
            return null;
        }
        else if (node.getLeftChild() == null)
        {
            return node;
        }
        else
        {
            return findMin(node.getLeftChild());
        }
    }

    public Node<E> findMax(Node<E> node)
    {
        if (node == null)
        {
            return null;
        }
        else if (node.getRightChild() == null)
        {
            return node;
        }
        else
        {
            return findMax(node.getRightChild());
        }
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return (this.size == 0);
    }

    public Node<E> getRoot()
    {
        return this.root;
    }

    private class Node<E extends Comparable<E>>
    {
        private boolean color = RED;
        private E element;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private Node<E> parent;

        Node()
        {
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }

        Node(E element)
        {
            this.element = element;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }

        Node(boolean color, E element, Node<E> leftChild, Node<E> rightChild, Node<E> parent){
            this.color = color;
            this.element = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }

        E getElement()
        {
            return this.element;
        }

        void setElement(E element)
        {
            this.element = element;
        }

        Node<E> getLeftChild()
        {
            return this.leftChild;
        }

        void setLeftChild(Node<E> leftChild)
        {
            this.leftChild = leftChild;
        }

        Node<E> getRightChild()
        {
            return this.rightChild;
        }

        void setRightChild(Node<E> rightChild)
        {
            this.rightChild = rightChild;
        }

        Node<E> getParent(){
            return this.parent;
        }

        void setParent(Node<E> parent){
            this.parent = parent;
        }

        boolean getColor(){
            return this.color;
        }

        void setColor(boolean color){
            this.color = color;
        }
    }

    public static void main(String[] args){
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        System.out.println("This code works.");
    }
}