import java.util.HashMap;

public class MyList<T> {
    private HashMap<T, Node<T>> map;
    private Node<T> head;
    private Node<T> tail;

    private class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public MyList() {
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        map.put(data, newNode);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void remove(T data) {
        Node<T> node = map.get(data);
        if (node == null) {
            return;
        }
        map.remove(data);
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }
}
