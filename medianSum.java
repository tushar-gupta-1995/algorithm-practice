package MyPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class medianSum {

    static int counter=0;

    static int median=1;

    static int nodesTraversed=0;

    static int medianSum=0;

    static ArrayList<Integer> nodesTraversedList = new ArrayList<>();

    static LinkedList<Integer> queue = new LinkedList<>();

    public static class BSTNode {
        int data;
        BSTNode left;
        BSTNode right;
    }

    public static void main(String...args) {
//        queue.add(3);
//        queue.add(4);
//        queue.add(2);

        readFromFile();
        int rootData = pollAndGetSize(queue);
        BSTNode root = new BSTNode();
        root.data=rootData;
        medianSum=medianSum+rootData;

        while (queue.size()>0)
        {
            int data = pollAndGetSize(queue);
            insertNode(data,root);
            int medianSize = isEven(counter)?counter/2:(counter+1)/2;
            //System.out.println("Median size : " + medianSize);
            nodesTraversedList=new ArrayList<>();
            traverse(root);
          //  System.out.println("Median : " + nodesTraversedList.get(medianSize-1));
            medianSum=medianSum+nodesTraversedList.get(medianSize-1);
        }

        System.out.println("Answer : " + medianSum);
    }

    public static void traverse(BSTNode root)
    {
        if(root==null)
            return;

        traverse(root.left);
        nodesTraversedList.add(root.data);
        traverse(root.right);
    }

    public static int pollAndGetSize(LinkedList<Integer> queue)
    {
        if(queue.size()>0) {
            counter++;
            return queue.poll();
        }

        return -1;
    }

    public static boolean isEven(int n)
    {
        return n%2==0;
    }

    public static BSTNode insertNode(int data, BSTNode root)
    {
        if(root==null)
        {
            BSTNode node = new BSTNode();
            node.data=data;
            return node;
        }

        if(data< root.data)
            root.left= insertNode(data,root.left);

        if(data> root.data)
            root.right= insertNode(data,root.right);

        return root;
    }

    public static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\medianSum.txt"));
            String line = reader.readLine();
            // line = line.replaceAll("\s+","t");

            while (line != null) {
                // System.out.println(line);
                // System.out.println(values[0]);
                int src = Integer.parseInt(line);

                queue.add(src);

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
