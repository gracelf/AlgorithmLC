package snake_complex_DataStructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
class LC432AllOneV2 {

    //doubly linked list node
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

    public LC432AllOneV2() {
        keyFrequencyMap = new HashMap<>();
        frequencyNodeMap = new HashMap<>();
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        int currFreq = keyFrequencyMap.getOrDefault(key, 0);
        //if the key or current node exist
        if (currFreq > 0) {
            Node currNode = frequencyNodeMap.get(currFreq);
            //remove key from current node keys set
            currNode.keys.remove(key);
            //add to next node if freq matches
            Node nextNode = currNode.next;
            if (nextNode != tail && nextNode.frequency == currFreq + 1) {
                nextNode.keys.add(key);
            } else { //if no match freq node found, create new node and add
                addNewNodeAfter(currNode, currFreq + 1, key);
            }
            //remove currnode if empty, at end of the method
            if (currNode.keys.isEmpty()) {
                removeCurrNode(currNode, currFreq);
            }
        } else { //current key doesnot exist, not need to delete from current node
            Node targetNode = frequencyNodeMap.get(currFreq + 1); //Frequence 1 node exist
            if (targetNode != null) {
                targetNode.keys.add(key);
            } else { //insert new node of freq 1 after head
                addNewNodeAfter(head, currFreq + 1, key);
            }
        }
        //lastly, update key's new frequency 
        keyFrequencyMap.put(key, currFreq + 1);

    }

    private void addNewNodeAfter(Node currNode, int freq, String key) {
        Node newNode = new Node(freq);
        newNode.keys.add(key);
        //swap
        Node next = currNode.next;
        currNode.next = newNode;
        newNode.prev = currNode;
        newNode.next = next;
        next.prev = newNode;
        //add new node to the frequency node map
        frequencyNodeMap.put(freq, newNode);
    }

    private void addNewNodeBefore(Node currNode, int currFreq, String key) {
        Node newNode = new Node(currFreq - 1);
        newNode.keys.add(key);
        //swap       
        Node prev = currNode.prev;
        currNode.prev = newNode;
        newNode.next = currNode;
        newNode.prev = prev;
        prev.next = newNode;
        //add new node to the frequency node map
        frequencyNodeMap.put(currFreq - 1, newNode);
    }

    private void removeCurrNode(Node currNode, int currFreq) {
        Node prev = currNode.prev;
        Node next = currNode.next;
        prev.next = next;
        next.prev = prev;
        frequencyNodeMap.remove(currFreq, currNode);//debug
    }

    public void dec(String key) { //assume key exists
        int currFreq = keyFrequencyMap.get(key);
        Node currNode = frequencyNodeMap.get(currFreq);
        currNode.keys.remove(key);
        if (currFreq > 1) {
            Node targetNode = frequencyNodeMap.get(currFreq - 1);
            if (targetNode != null) {
                targetNode.keys.add(key);
            } else {
                addNewNodeBefore(currNode, currFreq, key);
            }
            keyFrequencyMap.put(key, currFreq - 1);
        } else {//if key's new count is 0, no need to add to any new node
            keyFrequencyMap.remove(key);
        }
        //remove from the currnode if empty, at end of the method
        if (currNode.keys.isEmpty()) {
            removeCurrNode(currNode, currFreq);
        }
    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

}
