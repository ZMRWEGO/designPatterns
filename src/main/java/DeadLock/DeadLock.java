package DeadLock;

public class DeadLock  {
    //两个线程需要共享静态变量，才会出现死锁
    static Object o1 = new Object();
    static Object o2 = new Object();
    int flag ;

    public DeadLock(int flag) {
        this.flag = flag;
    }

    public void m() {
        if (flag == 1) {
            //可重入锁
            synchronized (o1) {
                System.out.println("拥有o1");
                //这里休眠是必须的，防止一个线程过快的执行完，释放掉锁
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("拥有o2");
                }
            }

        }
        if (flag == 0) {
            synchronized (o2) {
                System.out.println("拥有o2");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("拥有o1");
                }
            }

        }
    }

    public static void main(String[] args) {
        DeadLock d1 = new DeadLock(1);
        DeadLock d2 = new DeadLock(0);
        new Thread(() -> d1.m()).start();
        new Thread(()->d2.m()).start();
    }
}
