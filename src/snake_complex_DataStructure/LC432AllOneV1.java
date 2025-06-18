package snake_complex_DataStructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
class LC432AllOneV1 {

    class Node {

        int frequency;
        Set<String> keys;
        Node prev, next;

        Node(int frequency) {
            this.frequency = frequency;
            this.keys = new HashSet<>();
            this.prev = null;
            this.next = null;
        }
    }

    //class variables
    //map key to the frequency count
    Map<String, Integer> keyFrequencyMap;
    //map frequency to the double-linkedList node
    Map<Integer, Node> frequencyNodeMap;
    Node head, tail;

    public LC432AllOneV1() {
        keyFrequencyMap = new HashMap<>();
        frequencyNodeMap = new HashMap<>();
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        int currFreq = keyFrequencyMap.getOrDefault(key, 0);
        Node currNode = frequencyNodeMap.get(currFreq);
        //if current node exist
        if (currNode != null) {
            //remove from current
            currNode.keys.remove(key);
            //add to next node if freq matches
            Node nextNode = currNode.next;
            if (nextNode != tail && nextNode.frequency == currFreq + 1) {
                nextNode.keys.add(key);
            } else { //if no match freq node found, create new node and add
                Node newNode = new Node(currFreq + 1);
                newNode.keys.add(key);
                addNodeAfter(currNode, newNode);
                frequencyNodeMap.put(currFreq + 1, newNode);//debug
            }
            //remove currnode if empty, at end of the method
            if (currNode.keys.isEmpty()) {
                removeCurrNode(currNode);
                frequencyNodeMap.remove(currFreq, currNode);//debug
            }
        } else { //insert new node with Frequence 1 after head if not already exists. not need to delete from current node
            Node targetNode = frequencyNodeMap.get(currFreq + 1);
            if (targetNode != null) {
                targetNode.keys.add(key);
            } else { //insert new node of freq 1 after head
                Node newNode = new Node(currFreq + 1);
                newNode.keys.add(key);
                addNodeAfter(head, newNode);
                frequencyNodeMap.put(currFreq + 1, newNode);
            }
        }
        keyFrequencyMap.put(key, currFreq + 1);

    }

    private void addNodeAfter(Node currNode, Node newNode) {
        Node next = currNode.next;
        currNode.next = newNode;
        newNode.prev = currNode;
        newNode.next = next;
        next.prev = newNode;
    }

    private void addNodeBefore(Node currNode, Node newNode) {
        Node prev = currNode.prev;
        currNode.prev = newNode;
        newNode.next = currNode;
        newNode.prev = prev;
        prev.next = newNode;
    }

    private void removeCurrNode(Node currNode) {
        Node prev = currNode.prev;
        Node next = currNode.next;
        prev.next = next;
        next.prev = prev;
    }

    public void dec(String key) {

        int freq = keyFrequencyMap.get(key);
        Node currNode = frequencyNodeMap.get(freq);
        System.out.println(frequencyNodeMap);

        if (currNode != null) {
            currNode.keys.remove(key);
        }
        if (freq - 1 > 0) {
            Node targetNode = frequencyNodeMap.get(freq - 1);
            if (targetNode != null) {
                targetNode.keys.add(key);
            } else {
                Node newNode = new Node(freq - 1);
                newNode.keys.add(key);
                addNodeBefore(currNode == null ? tail : currNode, newNode);
                frequencyNodeMap.put(freq - 1, newNode);
            }
            keyFrequencyMap.put(key, freq - 1);
        } else {
            keyFrequencyMap.remove(key);
        }
        //remove from the currnode if empty, at end of the method
        if (currNode != null && currNode.keys.isEmpty()) {
            removeCurrNode(currNode);
            frequencyNodeMap.remove(freq, currNode);
        }

    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

}
