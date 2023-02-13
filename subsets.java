package MyPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class subsets {

  // static int [] a = {1,2,33,4};
 static int [] a = {1,2,3,4,5,1,2,2,5};
    List<List<Integer>> list = new ArrayList<>();
    static int computations=0;
    static List<Integer> subset = new ArrayList<>();
    static int[][] cache;

    static HashMap<Integer,Integer> cache1 =new HashMap<>();

    static List<Integer> list1 = new ArrayList<>();

    static List<Integer> list2 = new ArrayList<>();

    public static void main(String [] args)
    {
        //createSubsets(0);
        //createSubsetsSum(0,46,0);
       cache = new int[9000][9000];

       //cache1 = new int [Integer.MAX_VALUE];
        long beginTime = System.currentTimeMillis();
        System.out.println(createParitionSum(0,0,0));
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken : " + (endTime-beginTime));

        System.out.println(computations);
    }

    public static  void createSubsets(int i)
    {
        if(i>=a.length)
        {
            for(int j=0;j<subset.size();j++)
            {
                System.out.print(subset.get(j));
            }
            System.out.println();
            return;
        }

        subset.add(a[i]);
        createSubsets(i+1);
        subset.remove(subset.size()-1);
        createSubsets(i+1);

    }


    public static  void createSubsetsSum(int i,int target,int sum)
    {
        if(i>=a.length)
        {
            return;
        }

        subset.add(a[i]);
        sum=sum+a[i];
        if(sum==target)
        {
            for(int j=0;j<subset.size();j++)
            {
                System.out.print(subset.get(j) + " + ");
            }
            System.out.println();
        }

        createSubsetsSum(i+1,target,sum);
        sum = sum - subset.get(subset.size()-1);
        subset.remove(subset.size()-1);
        createSubsetsSum(i+1,target,sum);

    }

    public static  boolean createParitionSum(int i)
    {
        if(i>=a.length)
        {
            System.out.println("calculating");
            int sum1=0;
            int sum2=0;
            List<Integer> list2 = new ArrayList<>();
           // list2.add(0);
            for(int j=0;j<a.length;j++)
            {
                if(subset.contains(j)) {
                    System.out.print("listy  " + j);
                    sum1 = sum1 + a[j];
                }
                else {
                    System.out.print("list  " + j);
                    sum2 = sum2 + a[j];
                    list2.add(j);
                }
            }

            System.out.println();


            if(list2.size()==0)
                return false;

            if(sum1==sum2) {
                cache[list2.get(0)][sum2]=1;
               System.out.println("true");
            }
            else
                cache[list2.get(0)][sum2]=3;


            return cache[list2.get(0)][sum2]==1?true:false;
        }

        subset.add(i);

        int sum = subset.stream()
                .collect(Collectors.summingInt(Integer::intValue));
       if(cache[subset.get(0)][sum]!=0) {
        System.out.println("found " + subset.get(0) + " - " + sum);
        return cache[subset.get(0)][sum]==1 ? true : false;
    }
        boolean answer = createParitionSum(i+1);
        subset.remove(subset.size()-1);
        answer = answer |  createParitionSum(i+1);
        return  answer;
    }

    public static  boolean createParitionSum(int i,int sum1,int sum2)
    {
       //System.out.println("cal" + subset.size());
        if(i>=a.length)
        {
            if(sum1==sum2)
            {
            //   System.out.println(sum2 + "- " + (subset.size()) + " if " + sum1);
                return true;
            }

            else {
               // System.out.println(sum2 + "- " + (subset.size()) + " if " + sum1 + " unmatched");
                return false;
            }
        }

        subset.add(i);

        sum1 = sum1+a[i];
        //System.out.println(sum2 + " " + subset.size() +  " " + sum1 + "not if");

        computations++;
        boolean answer = createParitionSum(i+1,sum1,sum2);
        sum2=sum2+a[subset.get(subset.size()-1)];
        sum1=Math.abs(sum1-a[subset.get(subset.size()-1)]);
        subset.remove(subset.size()-1);
        answer = answer ||  createParitionSum(i+1,sum1,sum2);
        return  answer;
    }

    public static int sum(int lowerbound, int upperbound)
    {
        int sum=0;

        for(int i=lowerbound;i<=upperbound;i++)
        {
            sum+=a[i];
        }

        return sum;
    }
    public static  boolean createSubsetsPartitionPossible(int lowerbound,int upperbound)
    {

        int partition1Sum = 0;
        int partition2Sum = sum(lowerbound,upperbound);
        while (lowerbound<upperbound)
        {
             partition1Sum = partition1Sum+a[lowerbound];
             partition2Sum = partition2Sum-a[lowerbound];

             if(partition2Sum==partition1Sum)
                 return true;

             lowerbound++;
        }

        return false;
    }

    public static void swap(int pos1,int pos2)
    {
        int temp = a[pos2];
        a[pos2]=a[pos1];
        a[pos1]=temp;
    }
}
