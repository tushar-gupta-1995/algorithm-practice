package MyPractice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SleepSort {

    static int [] a = {3,90,5,1,2,4,30,5,6,7,8};
    static int i=0;

    public static void main(String [] args) throws Exception
    {

        ExecutorService es = Executors.newFixedThreadPool(a.length);

        //AtomicInteger index = new AtomicInteger(0);
        int [] b = new int [a.length];

        for(int i=0;i<a.length;i++)
        {
            final int j=i;
            es.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(a[j]);
                    System.out.println("Woke and printing  " + a[j]);
                    b[getAndIncrementIndex()]=a[j];
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        es.shutdown();
        es.awaitTermination(3, TimeUnit.MINUTES);
        for(int i =0;i<b.length;i++)
        {
            System.out.println(b[i]);
        }


    }

    public static synchronized  int getAndIncrementIndex()
    {
        return i++;
    }
}
