package patterns.singleton;

/**
 * 双检锁 懒汉模式
 */
public class SigletonI {
    private static SigletonI sigleton;

    private SigletonI() {

    }

    public static SigletonI getInstance() {
        if (sigleton == null) {
            synchronized (sigleton) {
                if (sigleton == null) {
                    sigleton = new SigletonI();
                    return sigleton;
                }
            }
        }
        return sigleton;
    }


}
