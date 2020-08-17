package wang.sunce.bean.initalizing;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGCDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitializationDemo.class);
        context.refresh();
        context.close();
        Thread.sleep(5000L);
        System.gc();
        Thread.sleep(5000L);
    }
}
