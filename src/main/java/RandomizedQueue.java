import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Item[] data = (Item[]) new Object[DEFAULT_CAPACITY];

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size < 1;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == data.length) {
            ensureCapacity();
        }

        data[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, size);
        Item item = data[index];
        int reminder = size - (index + 1);
        System.arraycopy(data, index + 1, data, index, reminder);
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, size);
        return data[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        //(data);
        return new Iterator<Item>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Item next() {
                if (index >= size) {
                    throw new NoSuchElementException();
                }
                Item item = data[index];
                index++;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void ensureCapacity() {
        int newSize = data.length * 2;
        data = Arrays.copyOf(data, newSize);
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("snot");
        randomizedQueue.enqueue("snog");
        randomizedQueue.enqueue("sniger");

        System.out.println("Size: " + randomizedQueue.size());

        randomizedQueue.forEach(System.out::println);

        System.out.println("---");
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());

        System.out.println("---");
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println("---");

/*
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
*/
    }
}
