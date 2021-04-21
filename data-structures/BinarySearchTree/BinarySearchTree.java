public class BinarySearchTree<K extends Comparable<K>>
{
    private Node<K> root;

    BinarySearchTree()
    {
        this.root = null;
    }

    BinarySearchTree(K key)
    {
        this.root = new Node<K>(key);
    }

    public void add(K key)
    {
        if (this.root == null)
        {
            this.root = new Node<K>(key);
        }
        else
        {
            add(this.root, key);
        }
    }

    public Node<K> add(Node<K> node, K key)
    {
        if (node == null)
        {
            node = new Node<K>(key);
        }
        else if (key.compareTo(node.getKey()) < 0)
        {
            node.setLeftChild(add(node.getLeftChild(), key));
        }
        else if (key.compareTo(node.getKey()) > 0)
        {
            node.setRightChild(add(node.getRightChild(), key));
        }

        return node;
    }

    public Node<K> remove(K key)
    {
        if (this.root == null)
        {
            return null;
        }
        else
        {
            return remove(this.root, key);
        }
    }

    public Node<K> remove(Node<K> node, K key)
    {
        if (node == null)
        {
            return null;
        }
        else if (key.compareTo(node.getKey()) < 0)
        {
            node.setLeftChild(remove(node.getLeftChild(), key));
        }
        else if (key.compareTo(node.getKey()) > 0)
        {
            node.setRightChild(remove(node.getRightChild(), key));
        }
        else if (node.getLeftChild() == null)
        {
            node = node.getRightChild();
        }
        else if (node.getRightChild() == null)
        {
            node = node.getLeftChild();
        }
        else
        {
            node.setKey(findMin(node.getRightChild()).getKey());
            node.setRightChild(remove(node.getRightChild(), node.getKey()));
        }

        return node;
    }

    public boolean contains(Node<K> node, K key)
    {
        if (node == null)
        {
            return false;
        }
        else if (key.compareTo(node.getKey()) == 0)
        {
            return true;
        }
        else if (key.compareTo(node.getKey()) < 0)
        {
            return contains(node.getLeftChild(), key);
        }   
        else
        {
            return contains(node.getRightChild(), key);
        }
    }

    public Node<K> find(Node<K> node, K key)
    {
        if (node == null)
        {
            return null;
        }
        else if (key.compareTo(node.getKey()) == 0)
        {
            return node;
        }
        else if (key.compareTo(node.getKey()) < 0)
        {
            return find(node.getLeftChild(), key);
        }   
        else
        {
            return find(node.getRightChild(), key);
        }
    }

    public Node<K> findMin(Node<K> node)
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

    public Node<K> findMax(Node<K> node)
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

    public Node<K> getRoot()
    {
        return this.root;
    }

    public void visit(Node<K> node)
    {
        System.out.print(node.getKey() + " ");
    }

    public void preorder(Node<K> node)
    {
        if (node != null)
        {
            visit(node);
            preorder(node.getLeftChild());
            preorder(node.getRightChild());
        }
    }

    public void inorder(Node<K> node)
    {
        if (node != null)
        {
            inorder(node.getLeftChild());
            visit(node);
            inorder(node.getRightChild());
        }
    }

    public void postorder(Node<K> node)
    {
        if (node != null)
        {
            postorder(node.getLeftChild());
            postorder(node.getRightChild());
            visit(node);
        }
    }

    private class Node<K extends Comparable<K>>
    {
        private K key;
        private Node<K> leftChild;
        private Node<K> rightChild;

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

    }

    public static void main(String[] args)
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        bst.add(5);
        bst.add(2);
        bst.add(7);
        bst.add(8);
        bst.add(6);

        bst.remove(6);

        System.out.println(bst.findMin(bst.getRoot()).getKey());
        System.out.println(bst.findMax(bst.getRoot()).getKey());
        System.out.println(bst.contains(bst.getRoot(), 7));
        System.out.println(bst.contains(bst.getRoot(), 6));
        bst.preorder(bst.getRoot());
        System.out.println();
        bst.inorder(bst.getRoot());
        System.out.println();
        bst.postorder(bst.getRoot());
        System.out.println();
    }
}