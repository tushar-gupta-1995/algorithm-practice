package MyPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class greedyWeights {

    static List<Jobs> jobList = new ArrayList<>();
    public static class Jobs
    {
        long weight;
        long length;

        long diff;

        public Jobs(long weight,long length)
        {
            this.weight=weight;
            this.length=length;
            this.diff=weight-length;
        }
    }


    public static void main(String...args) {
//        int completionTime=0;
//        int wcomsum=0;
//        PriorityQueue<Jobs> pq = new PriorityQueue<>(4,new JobComparator());
//        Jobs job1=new Jobs(100,3);
//        pq.add(job1);
//        Jobs job2=new Jobs(100,30);
//        pq.add(job2);
//        Jobs job3=new Jobs(5,1);
//        pq.add(job3);
//        Jobs job4=new Jobs(8,7);
//        pq.add(job4);
//
//        while(!pq.isEmpty())
//        {
//            Jobs s = pq.poll();
//            System.out.println((s.weight-s.length) + " with weight : " + s.weight);
//            completionTime=completionTime+s.length;
//            int weighedCompletionTime = s.weight*completionTime;
//            wcomsum+=weighedCompletionTime;
//        }
        readFromFile();
        PriorityQueue<Jobs> pq = new PriorityQueue<>(10000,new JobComparator());

        for(Jobs j : jobList)
        {
            pq.add(j);
        }

        long completionTime=0;
        long wcomsum=0;

        while(!pq.isEmpty())
        {
            Jobs s = pq.poll();
           // System.out.println(" with weight : " + s.weight + " and length " + s.length);
            System.out.println((s.weight-s.length) + " with weight : " + s.weight + " and length " + s.length);
            completionTime=completionTime+s.length;
            System.out.println("Completion time : " + completionTime);
            long weighedCompletionTime = s.weight*completionTime;
            wcomsum+=weighedCompletionTime;
            System.out.println("Wcomsum : " + wcomsum);
        }

       // System.out.println("Answer : " + wcomsum);
    }



    public static class JobComparator implements Comparator<Jobs>
    {
        @Override
        public int compare(Jobs a,Jobs b)
        {
            if(a.diff<b.diff)
                return 1;
            else if(a.diff==b.diff)
            {
                if(a.weight<b.weight)
                    return 1;
                if(a.weight==b.weight)
                    return 0;
                return -1;
            }
            else
                return -1;
        }
    }

    public static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\jobCompletion1.txt"));
            String line = reader.readLine();
            // line = line.replaceAll("\s+","t");

            while (line != null) {
                String [] values = line.split("\s");
                long weight = Long.parseLong(values[0]);
                long length = Long.parseLong(values[1]);
                jobList.add(new Jobs( weight,length));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
