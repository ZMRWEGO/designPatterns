package LRU;

import java.util.HashMap;

public class ZclLru {


    public ZclLru(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DlinkedNode();
        tail = new DlinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DlinkedNode node = cache.get(key);
        if (node==null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DlinkedNode node = cache.get(key);
        if (node == null) {
            DlinkedNode newNode = new DlinkedNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            add(newNode);
            ++size;
        }
        if (size > capacity) {
            DlinkedNode node1 = removeEldest();
            cache.remove(node1.value);
            --size;
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    class DlinkedNode {
        int key;
        int value;
        DlinkedNode prev;
        DlinkedNode next;
    }
    private DlinkedNode head, tail;
    private int size, capacity;
    private HashMap<Integer, DlinkedNode> cache = new HashMap<>();
    //总是插入head的右端
    public void add(DlinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void remove(DlinkedNode node) {
        DlinkedNode pre = node.prev;
        DlinkedNode next = node.next;
        pre.next = next;
        next.prev = pre;
    }

    public void moveToHead(DlinkedNode node) {
        remove(node);
        add(node);
    }

    public DlinkedNode removeEldest() {
        DlinkedNode last = tail.prev;
        remove(last);
        return last;
    }




//    public




}
