package Generics;

import java.util.List;
//定义一个泛型类
public class Generics<T> {
    T t;
    List<T> list;

    public <K> K test(K e) {
        return e;
    }


    public static void main(String[] args) {
        Generics<String> g = new Generics<String>();
        //String a =(String) new Object();  此处强转就会报错
        System.out.println(g.test("fasdf"));
        System.out.println(g.test(g));
    }
}
