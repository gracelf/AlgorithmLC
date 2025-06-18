package snake_complex_DataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LC146LRUCache {

    // Doubly Linked List Node
    class Node {

        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> cache;
    private int capacity;
    private Node head, tail;

    public LC146LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Create dummy head and tail nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);

        // Connect head and tail
        head.next = tail;
        tail.prev = head;
    }

    // Helper function to remove a node from the linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper function to insert a node at the front of the linked list
    //!!!head represents the most recently used element, and the tail represents the least recently used element
    private void insert(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Get the value of the key
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // Move the accessed node to the front, get counts as "used" in LRU
            remove(node);
            insert(node);
            return node.value;
        }
        return -1;
    }

    // Put a key-value pair in the cache
    public void put(int key, int value) {
        if (cache.containsKey(key)) {//this won't change the size of the cache
            // Update the value and move the node to the front
            Node node = cache.get(key);
            node.value = value;
            //remove and then insert a node at the front of the linked list
            remove(node);
            insert(node);
        } else {
            // If the cache is full, remove the least recently used item
            if (cache.size() == capacity) {
                Node toRemove = tail.prev;
                remove(toRemove);
                cache.remove(toRemove.key);
            }
            // Create a new node and insert it at the front
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            insert(newNode);
        }
    }
}
