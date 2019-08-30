package singleton;


public class ZCL{
    public enum Singleton{
        INSTANCE;

        public void print() {
            System.out.println("haha");
        }

    }
    public static void main(String[] args) {
        Singleton.INSTANCE.print();
    }
}
