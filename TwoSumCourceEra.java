package MyPractice;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TwoSumCourceEra {

    static HashMap<UserDefinedKey, Boolean> multiKeyMap = new HashMap<>();
    static HashSet<Long> targetSum = new HashSet<>();

    static class UserDefinedKey<K1, K2>
    {
        public K1 key1;
        public K2 key2;

        public UserDefinedKey(K1 key1, K2 key2)
        {
            this.key1 = key1;
            this.key2 = key2;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            UserDefinedKey key = (UserDefinedKey) o;
            if (key1 != null ? !key1.equals(key.key1) : key.key1 != null) {
                return false;
            }

            if (key2 != null ? !key2.equals(key.key2) : key.key2 != null) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode()
        {
            int result = key1 != null ? key1.hashCode() : 0;
            result = 31 * result + (key2 != null ? key2.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "[" + key1 + ", " + key2 + "]";
        }
    }


    static HashMap<Long, List<HashMap<Long, Boolean>>> myTable = new HashMap<>();
    static int mil = 10_00_000;
    static long [] totalNumbers;

    static long whereAmI=-99999;

    static Object mutex = new Object();

    static int counter=0;

    public static void main(String...args) throws Exception {
        readFromFile();

        javax.swing.Timer t;
        t = new Timer((int)TimeUnit.SECONDS.toMillis(50),new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Till now : " + counter);
                synchronized (mutex) {
                    System.out.println("At stage : " + whereAmI);
                }

                //t.stop(); // if you want only one print uncomment this line
            }
        });

        t.start();

        //System.out.println("first val: " + totalNumbers[0]);
        // Total dataset = 20,000
        // 100 threads = 200 per thread
        // i init with -10k goes upto 10k
        //  j from 1 to 200
        //  i decrement by 200

//        ExecutorService es = Executors.newFixedThreadPool(100);
//
//        int blimit = -10_000;
//        while(blimit<=10_000)
//        {
//            final int bl = blimit;
//            es.submit(()->{
//                for(int i=bl;i<bl+200;i++)
//                {
//                    solve2Sum(bl);
//                }
//            });
//            blimit=blimit+200;
//        }
//        es.shutdown();
//        es.awaitTermination(20, TimeUnit.HOURS);

//        for(long i=-5000;i<=0;i++)
//        {
//            whereAmI=i;
//            solve2SumIntraPairDistinct(i);
//        }


        ExecutorService es = Executors.newFixedThreadPool(4);
        es.submit(()->{
            //-10_000 , 0
            for(long i=-10_000;i<=-5000;i++)
            {
                synchronized (mutex) {
                    whereAmI=i;
                    counter=targetSum.size();
                }
                solve2SumIntraPairDistinct(i);
            }
        });

        es.submit(()->{
            //-10_000 , 0
            for(long i=-5001;i<=0;i++)
            {
                synchronized (mutex) {
                    whereAmI=i;
                    counter=targetSum.size();
                }
                solve2SumIntraPairDistinct(i);
            }
        });

        es.submit(()->{
            //1,10_000
            for(long i=1;i<=5000;i++)
            {
                synchronized (mutex) {
                    whereAmI=i;
                    counter=targetSum.size();
                }
                solve2SumIntraPairDistinct(i);
            }
        });

        es.submit(()->{
            //1,10_000
            for(long i=5001;i<=10_000;i++)
            {
                synchronized (mutex) {
                    whereAmI=i;
                    counter=targetSum.size();
                }
                solve2SumIntraPairDistinct(i);
            }
        });

        es.shutdown();
        es.awaitTermination(30, TimeUnit.HOURS);

//        for(int i=0;i< totalNumbers.length;i++)
//        {
//            System.out.println(totalNumbers[i]);
//        }

       // solve2SumIntraPairDistinct(7);
//       OptionalLong max =  Arrays.stream(totalNumbers).max();
//        System.out.println("Max :" + max.getAsLong());
       // Arrays.asList(totalNumbers).stream().max(Long::compare).get();

        t.stop();
        System.out.println("Answer : " + targetSum.size());
    }

    public synchronized static void incrementCounter()
    {
        counter++;
    }

    public static void solve2Sum(long target)
    {
        HashMap<Long,Long> targetFirstKeySet = new HashMap<>();
        targetFirstKeySet.put(target-totalNumbers[0],totalNumbers[0]);
        for(int i=1;i<totalNumbers.length;i++)
        {
            if(targetFirstKeySet.containsKey(totalNumbers[i])) {
                UserDefinedKey<Long,Long> usd = new UserDefinedKey<>(targetFirstKeySet.get(totalNumbers[i]),totalNumbers[i]);
                if(multiKeyMap.containsKey(usd))
                {
                   // System.out.println("Already seen : " + targetFirstKeySet.get(totalNumbers[i])+ " , " + totalNumbers[i]);
                    continue;
                }
               // System.out.println("Key-Pair Found : " + targetFirstKeySet.get(totalNumbers[i])+ " , " + totalNumbers[i]);
                UserDefinedKey<Long,Long> usd1 = new UserDefinedKey<>(totalNumbers[i],targetFirstKeySet.get(totalNumbers[i]));
                multiKeyMap.put(usd,true);
                multiKeyMap.put(usd1,true);
                incrementCounter();
            }
            targetFirstKeySet.put(target-totalNumbers[i],totalNumbers[i]);
        }
    }

    public static void solve2SumIntraPairDistinct(long target) {
        HashMap<Long, Long> targetFirstKeySet = new HashMap<>();
        targetFirstKeySet.put(target - totalNumbers[0], totalNumbers[0]);

        for (int i = 1; i < totalNumbers.length; i++) {
            if (targetFirstKeySet.containsKey(totalNumbers[i]) && targetFirstKeySet.get(totalNumbers[i])!=totalNumbers[i]) {
               // System.out.println("Key-Pair Found : " + targetFirstKeySet.get(totalNumbers[i])+ " , " + totalNumbers[i]);

                synchronized (mutex) {
                    targetSum.add(targetFirstKeySet.get(totalNumbers[i]) + totalNumbers[i]);
                }

            }
            targetFirstKeySet.put(target - totalNumbers[i], totalNumbers[i]);
        }
    }



    public static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\2sumproblem.txt"));
            String line = reader.readLine();
            // line = line.replaceAll("\s+","t");

            HashSet<Long> removeDuplicates = new HashSet<>();

            int index=0;
            while (line != null) {
                long src = Long.parseLong(line);
                removeDuplicates.add(src);
                line = reader.readLine();
            }

            totalNumbers = new long[removeDuplicates.size()];
            //int index=0;
            for(long i:removeDuplicates)
            {
                totalNumbers[index++] = i;
            }


            System.out.println("total elements : " + (totalNumbers.length));

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
