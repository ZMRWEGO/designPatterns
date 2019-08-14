package LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *     LinkedHashMap排在双向链表前面的判定为老节点，后面的判定为新节点
 */
public class JdkLru extends LinkedHashMap<Integer,Integer> {

    private int capacity;
    public JdkLru(int capacity) {
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }
    public int get(int key){
        //当按照访问顺序组织链表时，get、replace操作会把该节点移动链表尾部
        return super.getOrDefault(key, -1);
    }
    public void put (int key,int value){
        super.put(key, value);
    }
    //这里设置当节点数量大于capacity时，移除头节点
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
