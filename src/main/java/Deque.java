import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        Item data;
        Node prev;
        Node next;

        Node(Item item) {
            data = item;
        }
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size < 1;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        size += 1;
        Node node = new Node(item);

        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }

        head = node;

        if (tail == null) {
            tail = node;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        size += 1;
        Node node = new Node(item);

        node.next = null;

        if (head == null) {
            node.prev = null;
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        size -= 1;
        Node node = head;
        head = head.next;
        head.prev = null;
        return node.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        size -= 1;
        Node node = tail;
        tail = node.prev;
        tail.next = null;
        return node.data;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> integerDeque = new Deque<>();

        integerDeque.addFirst(3);
        integerDeque.addFirst(2);
        integerDeque.addFirst(1);
        integerDeque.addLast(4);
        integerDeque.addLast(5);

        System.out.println(integerDeque.isEmpty());
        System.out.println(integerDeque.size());

        System.out.println("---");
        integerDeque.forEach(System.out::println);

        System.out.println("---");
        integerDeque.removeFirst();
        integerDeque.removeFirst();
        integerDeque.removeLast();
        integerDeque.removeLast();
        integerDeque.forEach(System.out::println);
        System.out.println(integerDeque.isEmpty());
        System.out.println(integerDeque.size());
    }
}
