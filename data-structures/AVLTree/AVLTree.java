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

    public void add(K key){
        if(!(contains(key))){
            this.root = add(this.root, key);
            size += 1;
        }
    }

    private Node<K> add(Node<K> node, K key){
        if (node == null) return new Node<K>(key);
        
        if (node.getKey().compareTo(key) > 0){
            node.setLeftChild(add(node.getLeftChild(), key));
        } else {
            node.setRightChild(add(node.getRightChild(), key));
        }

        update(node);

        return rebalance(node);
    }

    public void remove(K key){
        if (contains(key)){
            this.root = remove(this.root, key);
            this.size -= 1;
        }
    }

    private Node<K> remove(Node<K> node, K key){
        if (node == null) return null;

        if (node.getKey().compareTo(key) > 0){
            node.setLeftChild(remove(node.getLeftChild(), key));
        } else if (node.getKey().compareTo(key) < 0){
            node.setRightChild(remove(node.getRightChild(), key));
        } else {
            if (node.getLeftChild() == null){
                return node.getRightChild();
            }
            else if (node.getRightChild() == null){
                return node.getLeftChild();
            } else {
                if (node.getLeftChild().getHeight() > node.getRightChild().getHeight()){
                    K tmpKey;

                    Node<K> tmp = node.getLeftChild();

                    while (tmp.getRightChild() != null){
                        tmp = tmp.getRightChild();
                    }

                    tmpKey = tmp.getKey();

                    node.setKey(tmpKey);

                    node.setLeftChild(remove(node.getLeftChild(), tmpKey));

                } else {
                    K tmpKey;

                    Node<K> tmp = node.getRightChild();

                    while (tmp.getLeftChild() != null){
                        tmp = tmp.getLeftChild();
                    }

                    tmpKey = tmp.getKey();

                    node.setKey(tmpKey);

                    node.setRightChild(remove(node.getRightChild(), tmpKey));
                }
            }
        }

        update(node);

        return rebalance(node);
    }

    private void update(Node<K> node){
        int leftHeight = 0;
        int rightHeight = 0;

        if (node.getLeftChild() == null){
            leftHeight = -1;
        } else {
            leftHeight = node.getLeftChild().getHeight();
        }

        if (node.getRightChild() == null){
            rightHeight = -1;
        } else {
            rightHeight = node.getRightChild().getHeight();
        }

        node.setHeight(1 + Math.max(leftHeight, rightHeight));
    }

    private Node<K> rebalance(Node<K> node){
        
        if (node.getBalanceFactor() == 2){
            if(node.getRightChild().getBalanceFactor() < 0){
                node.setRightChild(rightRotation(node.getRightChild()));
            }
            return leftRotation(node);
        } else if (node.getBalanceFactor() == -2){
            if(node.getLeftChild().getBalanceFactor() > 0){
                node.setLeftChild(leftRotation(node.getLeftChild()));
            }

            return rightRotation(node);
        } else {
            return node;
        }
    }

    private Node<K> leftRotation(Node<K> node){
        Node<K> tmp = node.getRightChild();

        node.setRightChild(tmp.getLeftChild());

        tmp.setLeftChild(node);

        update(node);
        update(tmp);

        return tmp;
    }

    private Node<K> rightRotation(Node<K> node){
        Node<K> tmp = node.getLeftChild();

        node.setLeftChild(tmp.getRightChild());

        tmp.setRightChild(node);

        update(node);
        update(tmp);

        return tmp;
    }

    public boolean contains(K key){
        return contains(this.root, key);
    }

    private boolean contains(Node<K> node, K key){
        if (node == null) return false;

        if (node.getKey().compareTo(key) > 0){
            return contains(node.getLeftChild(), key);
        } else if (node.getKey().compareTo(key) < 0){
            return contains(node.getRightChild(), key);
        } else {
            return true;
        }
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

    public Node<K> getRoot(){
        return this.root;
    }

    public void visit(Node<K> node){
        System.out.print("( Key: " + node.getKey() + ", Balance Factor: "+ node.getBalanceFactor() + ") ");
    }

    public void print(Node<K> node){
        if (node != null)
        {
            print(node.getLeftChild());
            visit(node);
            print(node.getRightChild());
        }
    }

    private boolean isValidBST(Node<K> node){
        if (node == null) return true;

        K key = node.getKey();

        boolean isValid = true;

        if (node.getLeftChild() != null){
            isValid = ((isValid) && (node.getLeftChild().getKey().compareTo(key) < 0));
        }

        if (node.getRightChild() != null){
            isValid = ((isValid) && (node.getRightChild().getKey().compareTo(key) > 0));
        }

        return ((isValid) && (isValidBST(node.getLeftChild())) && (isValidBST(node.getRightChild())));
    }

    public boolean isValid(Node<K> node){
        if (node == null) return true;

        boolean isValid = ((Math.abs(node.getBalanceFactor()) <= 1) && (isValid(node.getLeftChild())) && (isValid(node.getRightChild())));

        return ((this.isValidBST(this.root)) && (isValid));
    }

    private class Node<K extends Comparable<K>>{
        private K key;
        private Node<K> leftChild;
        private Node<K> rightChild;
        private int height;

        Node(){
            this.leftChild = null;
            this.rightChild = null;
            this.height = 1;
        }

        Node(K key){
            this.key = key;
            this.leftChild = null;
            this.rightChild = null;
            this.height = 1;
        }

        K getKey(){
            return this.key;
        }

        void setKey(K key){
            this.key = key;
        }

        Node<K> getLeftChild(){
            return this.leftChild;
        }

        void setLeftChild(Node<K> leftChild){
            this.leftChild = leftChild;
        }

        Node<K> getRightChild(){
            return this.rightChild;
        }

        void setRightChild(Node<K> rightChild){
            this.rightChild = rightChild;
        }

        int getHeight(){
            return this.height;
        }

        void setHeight(int height){
            this.height = height;
        }

        int getBalanceFactor(){
            int rightHeight = 0;
            int leftHeight = 0;

            if (this.getRightChild() != null){
                rightHeight = this.getRightChild().getHeight();
            }

            if (this.getLeftChild() != null){
                leftHeight = this.getLeftChild().getHeight();
            }
            return (rightHeight - leftHeight);
        }
    }

    public static void main(String[] args){
        AVLTree<Integer> a1 = new AVLTree<Integer>();

        a1.add(13);
        a1.add(15);
        a1.add(16);
        a1.add(10);
        a1.add(11);
        a1.add(5);
        a1.add(4);
        a1.add(8);

        System.out.println("First test - basic tree: ");
        a1.print(a1.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Second test - addition to basic tree: ");
        a1.add(3);

        a1.print(a1.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        AVLTree<Integer> a2 = new AVLTree<Integer>();

        a2.add(30);
        a2.add(5);
        a2.add(35);
        a2.add(32);
        a2.add(40);

        System.out.println("Third test - basic tree: ");
        a2.print(a2.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Fourth test - addition to basic tree: ");
        a2.add(45);

        a2.print(a2.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        AVLTree<Integer> a3 = new AVLTree<Integer>();

        a3.add(13);
        a3.add(10);
        a3.add(15);
        a3.add(5);
        a3.add(11);
        a3.add(4);
        a3.add(6);
        a3.add(16);

        System.out.println("Fifth test - basic tree: ");
        a3.print(a3.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Sixty test - addition to basic tree: ");
        a3.add(7);

        a3.print(a3.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        AVLTree<Integer> a4 = new AVLTree<Integer>();

        a4.add(5);
        a4.add(2);
        a4.add(1);
        a4.add(4);
        a4.add(3);
        a4.add(7);
        a4.add(6);
        a4.add(9);
        a4.add(16);

        System.out.println("Seventh test - basic tree: ");
        a4.print(a4.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Eighth test - addition to basic tree: ");
        a4.add(15);

        a4.print(a4.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Ninth test - remove to tree after addition: ");
        a4.remove(15);

        a4.print(a4.getRoot());
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Final test - test if all the trees are valid AVL Trees");

        System.out.println((a1.isValid(a1.getRoot())) && (a2.isValid(a2.getRoot())) && (a3.isValid(a3.getRoot())) && (a4.isValid(a4.getRoot())));

        System.out.println("-------------------------");
        System.out.println("This code works.");
    }
}