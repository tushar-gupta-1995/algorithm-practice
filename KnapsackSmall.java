package MyPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

//ans for first problem: 2493893
public class KnapsackSmall {

    static PriorityQueue<Item> pq = new PriorityQueue<>(1000,new KnapsackComparator());

    static long [][] dp = new long [20000][20000];

    static Item [] itemArray = new Item[100];
    public static class Item {

        public long weight;
        public long value;

        public double relativeValue;

        public Item(long weight, long value) {
            this.weight = weight;
            this.value = value;
            relativeValue=value/weight;
        }
    }

    public static void main(String [] args)
    {
//        Item i1 = new Item(5,7);
//        Item i2 = new Item(2,9);
//        Item i3 = new Item(3,5);
//
//        itemArray[0]=i1;
//        itemArray[1]=i2;
//        itemArray[2]=i3;

        for (int i = 0; i < dp.length; i++)
            for (int j = 0; j < dp.length; j++)
                dp[i][j] = -1;

        readFromFile();

         System.out.println(implementKnapsack(10000,0));
    }

    public static long implementKnapsack(long maxWeight,int index) {
        if(maxWeight<=0 || index >= itemArray.length)
            return 0;

        if(maxWeight<itemArray[index].weight)
            return dp[(int)maxWeight][index] = implementKnapsack(maxWeight,index+1);

        long valWithIncluded = itemArray[index].value;
        long maxWeightWithIncluded = maxWeight - itemArray[index].weight;

        if(dp[(int)maxWeight][index]==-1)
            dp[(int)maxWeight][index] =   Math.max(valWithIncluded + implementKnapsack(maxWeightWithIncluded,index+1)
                           ,implementKnapsack(maxWeight,index+1));

        return dp[(int)maxWeight][index];
    }


    public static class KnapsackComparator implements Comparator<Item>
    {
        @Override
        public int compare(Item a, Item b)
        {
            if(a.relativeValue<b.relativeValue)
                return 1;
            else if(a.relativeValue==b.relativeValue)
                return 0;
            else
                return -1;
        }
    }

    public static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\knapsack.txt"));
            String line = reader.readLine();

            int index=0;
            int arIndex=0;
            while (line != null) {
                if (index++ == 0) {
                    line = reader.readLine();
                    continue;
                }

                String [] values = line.split("\s");
                int val = Integer.parseInt(values[0]);
                int weight = Integer.parseInt(values[1]);
                Item i = new Item(weight,val);
                itemArray[arIndex++]=i;
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
