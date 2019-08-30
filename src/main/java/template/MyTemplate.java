package template;
//https://www.cnblogs.com/wangkaihua/p/9123171.html
public  abstract class MyTemplate {
    //原语操作
    public abstract void operation1();

    public abstract void operation2();

    //模板方法
    public final void templateMethod() {
        operation1();
        operation2();
    }


    class Concrete extends MyTemplate {
        @Override
        public void operation1() {

        }

        @Override
        public void operation2() {

        }
    }
}

