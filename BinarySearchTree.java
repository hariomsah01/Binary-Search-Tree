import java.util.Iterator;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {
    
    public void insert(E data) {
        Node<E> temp = new Node<E>(data);
        root = insert(root, temp);
    }

    private Node<E> insert(Node<E> curr, Node<E> newNode) {
        if (curr == null) {
            return newNode;
        } else if (curr.data.compareTo(newNode.data) >= 0) {
            curr.left = insert(curr.left, newNode);
        } else if (curr.data.compareTo(newNode.data) < 0) {
            curr.right = insert(curr.right, newNode);
        }
        return curr;
    }
    
    public Iterator<E> iterator() {
        vector = new Vector<E>();
        traverse(root);
        return vector.iterator();
    }

    public void remove(E data) {
        root = remove(root, data);
    }

    private Node<E> remove(Node<E> curr, E data) {
        if (curr == null) {
            return curr;
        } else if (curr.data.compareTo(data) > 0) {
            curr.left = remove(curr.left, data);
        } else if (curr.data.compareTo(data) < 0) {
            curr.right = remove(curr.right, data);
        } else {
            if (curr.left == null) {
                return curr.right;
            } else if (curr.right == null) {
                return curr.left;
            }
            
            Node<E> iop = findIOP(curr);
            curr.data = iop.data;

            curr.left = remove(curr.left, curr.data);
        }
        return curr;
    }

    private Node<E> findIOP(Node<E> curr) {
        curr = curr.left;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    public boolean search(E data) {
        return search(root, data);
    }

    private boolean search(Node<E> curr, E data) {
        if (curr == null) {
            return false;
        } else if (curr.data.compareTo(data) == 0) {
            return true;
        } else if (curr.data.compareTo(data) > 0) {
            return search(curr.left, data);
        }
        return search(curr.right, data);
    }

    
    private void traverse(Node<E> curr) {
        if (curr != null) {
            traverse(curr.left);
            vector.add(curr.data);
            traverse(curr.right);
        }
    }

    private Vector<E> vector;
}

