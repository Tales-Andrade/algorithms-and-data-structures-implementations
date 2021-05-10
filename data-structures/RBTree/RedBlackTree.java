public class RedBlackTree<E extends Comparable<E>>{
    // Constants
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    public final Node<E> NIL;

    private Node<E> root;
    private int size;


    public RedBlackTree(){
        NIL = new Node<E>(BLACK, null, null, null, null);
        NIL.setLeftChild(NIL);
        NIL.setRightChild(NIL);
        NIL.setParent(NIL);

        this.root = NIL;
        this.size = 0;
    }

    public boolean contains(E element){
        return contains(this.root, element);
    }

    private boolean contains(Node<E> node, E element)
    {
        if ((node == NIL) || (node == null))
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

    public void insert(E element){
        Node<E> x = this.root;
        Node<E> y = NIL;
        Node<E> z = new Node<E>(RED, element, NIL, NIL, NIL);

        while (x != NIL){
            y = x;

            if (x.getElement().compareTo(element) > 0){
                x = x.getLeftChild();
            } else if (x.getElement().compareTo(element) < 0){
                x = x.getRightChild();
            } else {
                return;
            }
        }

        z.setParent(y);

        if (y == NIL){
            this.root = z;
        } else if (z.getElement().compareTo(y.getElement()) < 0){
            y.setLeftChild(z);
        } else {
            y.setRightChild(z);
        }

        insertFixup(z);

        this.size += 1;
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
                        leftRotate(z.getParent());
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
                        rightRotate(z.getParent());
                    }

                    z.getParent().setColor(BLACK);
                    z.getParent().getParent().setColor(RED);
                    leftRotate(z.getParent().getParent());
                }
            }
        }

        this.root.setColor(BLACK);
    }

    public void delete(E element){
        Node<E> z = search(this.root, element);

        if (z == NIL){
            return;
        } else {
            Node<E> x;
            Node<E> y = z;
            boolean initialColor = y.getColor();

            if (z.getLeftChild() == NIL){
                x = z.getRightChild();
                transplant(z, z.getRightChild());
            } else if (z.getRightChild() == NIL){
                x = z.getLeftChild();
                transplant(z, z.getLeftChild());
            } else {
                y = successor(z.getRightChild());
                initialColor = y.getColor();
                x = y.getRightChild();

                if (y.getParent() == z){
                    x.setParent(y);
                } else {
                    transplant(y, y.getRightChild());
                    y.setRightChild(z.getRightChild());
                    y.getRightChild().setParent(y);
                }

                transplant(z, y);
                y.setLeftChild(z.getLeftChild());
                y.getLeftChild().setParent(y);
                y.setColor(z.getColor());
            }

            if (initialColor == BLACK){
                deleteFixup(x);
            }
    
            this.size -= 1;
        }
    }

    private void deleteFixup(Node<E> x){
        while ((x != this.root) && (x.getColor() == BLACK)){
            if (x == x.getParent().getLeftChild()){
                Node<E> y = x.getParent().getRightChild();

                if (y.getColor() == RED){
                    y.setColor(BLACK);
                    x.getParent().setColor(RED);
                    leftRotate(x.getParent());
                    y = x.getParent().getRightChild();
                }

                if ((y.getLeftChild().getColor() == BLACK) && (y.getRightChild().getColor() == BLACK)){
                    y.setColor(RED);
                    x = x.getParent();
                    continue;
                } else if (y.getRightChild().getColor() == BLACK){
                    y.getLeftChild().setColor(BLACK);
                    y.setColor(RED);
                    rightRotate(y);
                    y = x.getParent().getRightChild();
                }

                if (y.getRightChild().getColor() == RED){
                    y.setColor(x.getParent().getColor());
                    x.getParent().setColor(BLACK);
                    y.getRightChild().setColor(BLACK);
                    leftRotate(x.getParent());
                    x = this.root;
                }

            } else {
                Node<E> y = x.getParent().getLeftChild();

                if (y.getColor() == RED){
                    y.setColor(BLACK);
                    x.getParent().setColor(RED);
                    rightRotate(x.getParent());
                    y = x.getParent().getLeftChild();
                }

                if ((y.getRightChild().getColor() == BLACK) && (y.getLeftChild().getColor() == BLACK)){
                    y.setColor(RED);
                    x = x.getParent();
                    continue;
                } else if (y.getLeftChild().getColor() == BLACK){
                    y.getRightChild().setColor(BLACK);
                    y.setColor(RED);
                    leftRotate(y);
                    y = x.getParent().getLeftChild();
                }

                if (y.getLeftChild().getColor() == RED){
                    y.setColor(x.getParent().getColor());
                    x.getParent().setColor(BLACK);
                    y.getLeftChild().setColor(BLACK);
                    rightRotate(x.getParent());
                    x = this.root;
                }
            }
        }

        x.setColor(BLACK);
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
        } else if (x == x.getParent().getLeftChild()){
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
        } else if (y == y.getParent().getLeftChild()){
            y.getParent().setLeftChild(x);
        } else {
            y.getParent().setRightChild(x);
        }

        x.setRightChild(y);
        y.setParent(x);
    }

    public Node<E> findMin(Node<E> node)
    {
        if (node.getLeftChild() != NIL){
            return findMin(node.getLeftChild());
        } else {
            return node;
        }
    }

    public Node<E> findMax(Node<E> node)
    {
        if (node.getRightChild() != NIL){
            return findMax(node.getRightChild());
        } else {
            return node;
        }
    }

    private Node<E> search(Node<E> node, E element){
        if (node == NIL){
            return NIL;
        } else if (element.compareTo(node.getElement()) == 0){
            return node;
        } else if (element.compareTo(node.getElement()) > 0){
            return search(node.getRightChild(), element);
        } else {
            return search(node.getLeftChild(), element);
        }
    }

    private Node<E> successor(Node<E> node){
        if ((node == NIL) || (node.getLeftChild() == NIL)){
            return node;
        } else {
            return successor(node.getLeftChild());
        }
    }

    private void transplant(Node<E> nodeOne, Node<E> nodeTwo){
        if (nodeOne.getParent() == NIL){
            this.root = nodeTwo;
        } else if (nodeOne == nodeOne.getParent().getLeftChild()){
            nodeOne.getParent().setLeftChild(nodeTwo);
        } else {
            nodeOne.getParent().setRightChild(nodeTwo);
        }

        nodeTwo.setParent(nodeOne.getParent());
    } 

    private void updateChild(Node<E> parent, Node<E> newNode, Node<E> oldNode){
        if (parent != NIL){
            if (parent.getLeftChild() == oldNode){
                parent.setLeftChild(newNode);
            } else {
                parent.setRightChild(newNode);
                
            }

            newNode.setParent(parent);
            oldNode.setParent(NIL);
        }
    }

    private void swapColors(Node<E> nodeOne, Node<E> nodeTwo){
        boolean tmp = nodeOne.getColor();
        nodeOne.setColor(nodeTwo.getColor());
        nodeTwo.setColor(tmp);
    }

    public int getHeight(){
        return getHeight(this.root);
    }

    private int getHeight(Node<E> node){
        if (node == NIL){
            return 0;
        }

        if ((node.getLeftChild() == NIL) && (node.getRightChild() == NIL)){
            return 1;
        }

        return (1 + Math.max((getHeight(node.getLeftChild())), (getHeight(node.getRightChild()))));
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

    public void visit(Node<E> node)
    {
        System.out.println("Node: " + node.getElement() + ", Color: " + node.getColor());
    }

    public void print(Node<E> node)
    {
        if (node != NIL)
        {
            print(node.getLeftChild());
            visit(node);
            print(node.getRightChild());
        }
    }

    private class Node<E extends Comparable<E>>
    {
        private boolean color = RED;
        private E element;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private Node<E> parent;

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

        rbt.insert(13);
        rbt.insert(8);
        rbt.insert(1);
        rbt.insert(11);
        rbt.insert(6);
        rbt.insert(17);
        rbt.insert(15);
        rbt.insert(25);
        rbt.insert(22);
        rbt.insert(27);

        System.out.println("Basic Red-Black Tree: ");

        rbt.print(rbt.getRoot());

        System.out.println("End of tree");
        System.out.println("-------------------------------------------------");
        RedBlackTree<Integer> rbt2 = new RedBlackTree<Integer>();

        rbt2.insert(30);
        rbt2.insert(20);
        rbt2.insert(40);
        rbt2.insert(50);
        rbt2.insert(35);

        System.out.println("Another Basic Red-Black Tree: ");

        rbt2.print(rbt2.getRoot());

        System.out.println("End of tree");
        System.out.println("-------------------------------------------------");
        
        System.out.println("Red-Black Tree after deletion: ");
        rbt2.delete(20);

        rbt2.print(rbt2.getRoot());

        System.out.println("End of tree");
        System.out.println("-------------------------------------------------");
        
        System.out.println("This code works.");
    }
}