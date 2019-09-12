package LRU;
import java.util.HashMap;
import java.util.Map;

public class ZclLru {


    class Node{
        Node prev;
        Node next;
        int value;

    }
    private Node head,tail;
    private int capacity,size;
    private Map<Integer,Node> map = new HashMap<>();
    public ZclLru(int capacity){
        this.capacity = capacity;
        size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    //尾插
    public void add(Node node){
        Node temp = tail.prev;
        temp.next = node;
        node.prev = temp;
        node.next = tail;
    }
    //删除头元素
    public void removeFirst(){
        Node next = head.next;
        head.next = next.next;
        next.next.prev = head;
    }
    //更新一个元素  保证最新元素在链表尾部
    public void update(Node node){
        Node temp = node.prev;
        temp.next = node.next;
        node.next.prev = temp;
        add(node);
    }
    //执行lru的get操作
    public int get(int key){
        Node node = map.get(key);
        if (node != null) {
            update(node);
            return node.value;
        }
        return 0;
    }
    //执行put操作
    public void put(int key,int value){
        Node node = new Node();
        node.value = value;
        size++;
        map.put(key, node);
        if (size > capacity) {
            removeFirst();
            size--;
        }
        add(node);

    }


}