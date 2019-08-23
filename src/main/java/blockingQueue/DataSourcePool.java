package blockingQueue;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class DataSourcePool {

    //核心连接数
    private int coreNum;
    //最大连接数
    private int maxNum;

    //当前活跃连接数
    private AtomicInteger activeCount = new AtomicInteger(0);

    //空闲连接
    Vector<Connection> freePool = new Vector<>();

    //正在使用的连接池
    Vector<DataSourceEntry> usingPool = new Vector<>();

    public DataSourcePool(int coreNum,int maxNum) {
        this.coreNum = coreNum;
        this.maxNum = maxNum;
        init();
    }


    public void init() {
        for (int i = 0; i < coreNum; i++) {
            Connection connection = createConn();
            freePool.add(connection);
        }
    }
    //创建连接
    public synchronized Connection createConn() {
        Connection connection=null;
        try {
            connection = new Connection();
            activeCount.getAndIncrement();
            System.out.println("创建了一个连接，当前活跃的连接数目为："+activeCount.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //释放连接
    public synchronized void release(Connection connection) {
        try {
            if (connection.isClosed()) {
                freePool.add(connection);
            }
        } catch (Exception e) {

        }
    }

    public synchronized Connection getConn() {
        Connection connection = null;
        if (!freePool.isEmpty()) {
            connection = freePool.remove(0);
        } else {
            if (activeCount.get() < maxNum) {
                connection = createConn();
            } else {
                try {
                    System.out.println("线程池已满请等待");
                    //自动释放
                    wait(1000);
                    return getConn();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return connection;
    }

    class Connection{
        public boolean isClosed(){
            return false;
        }
    }

    class DataSourceEntry{

    }
}
