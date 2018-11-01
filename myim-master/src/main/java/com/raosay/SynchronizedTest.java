package com.raosay;

/**
 *
 *
 * synchronized 关键修饰方法测试
 *
 * 不同对象下  相同的synchronize修饰的方法 不会相互影响
 *
 * @author ron
 * @version $Id: SynchronizedTest, v 0.1 2018/10/30 10:40 Administrator Exp $
 */
public class SynchronizedTest {



    private int i = 0;

    public synchronized int add(){
        if(i<50){
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
        return i++;
    }

    public SynchronizedTest(int i){
        this.i=i;
    }



    public static void main(String[] args){
        SynchronizedTest t1 = new SynchronizedTest(50);
        SynchronizedTest t2 = new SynchronizedTest(0);

        for (int i = 0; i<=20;i++){
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println(t2.add());
                }
            });
            newThread.start();

        }
        for (int i = 0; i<=20;i++){
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println(t1.add());
                }
            });
            newThread.start();
        }


    }
}
