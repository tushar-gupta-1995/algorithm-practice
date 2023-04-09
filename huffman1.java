package MyPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class huffman1 {

    static PriorityQueue<Alphabet> pq = new PriorityQueue<>(1000,new HuffmanComparator());
    static ArrayList<Integer> codeLengths = new ArrayList<>();

    public static void main(String [] args)
    {
      readFromFile();
        Alphabet root = executeHuffman();
       traverse(root,0);
//
        //min = 9
        System.out.println(Collections.max(codeLengths));

//        Alphabet a = new Alphabet(1,1);
//        Alphabet b = new Alphabet(2,2);
//        Alphabet c = new Alphabet(3,3);
//        a.right=b;
//        a.left=c;
//        c.left=new Alphabet(4,4);
//        traverse(a,0);
//        System.out.println(Collections.max(codeLengths));

       // System.out.println(pq.poll().weight);
    }

    public static class Alphabet {

        public long index;
        public long weight;

        public Alphabet left;

        public Alphabet right;

        public Alphabet(long index, long weight) {
            this.index = index;
            this.weight = weight;

            left=null;
            right=null;

        }
    }


    public static void traverse(Alphabet root,int size){

        if(root == null){
            codeLengths.add(size);
            return;
        }

        if(root.left!=null || root.right!=null)
        {
            size=size+1;
        }

        traverse(root.left,size);
        traverse(root.right,size);
    }

    public static class HuffmanComparator implements Comparator<Alphabet>
    {
        @Override
        public int compare(Alphabet a, Alphabet b)
        {
            if(a.weight>b.weight)
                return 1;
            else if(a.weight==b.weight)
                return 0;
            else
                return -1;
        }
    }

    public static Alphabet union(Alphabet a,Alphabet b)
    {
//        if(a==null || b==null)
//            return null;
       // System.out.println("Combinining with weight: " + a.weight + " , " + b.weight);
        Alphabet union = new Alphabet(a.index+b.index,a.weight+b.weight);
        union.left=a;
        union.right=b;

        pq.add(union);
        return union;
    }

    public static Alphabet executeHuffman()
    {
        //Alphabet node = null;
        while (pq.size()>1)
        {

            Alphabet toCombine1 = pq.poll();
            Alphabet toCombine2 = pq.poll();
//            if(toCombine.weight<=0)
//                continue;
            System.out.println("Combinining with weight: " + toCombine1.weight + " , " + toCombine2.weight);
            union(toCombine1,toCombine2);
        }
        return pq.poll();
    }



    public static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\huffman.txt"));
            String line = reader.readLine();

            int index=0;
            while (line != null) {
                if (index++ == 0) {
                    line = reader.readLine();
                    continue;
                }

                int val = Integer.parseInt(line);
                Alphabet a = new Alphabet(index++,val);
                pq.add(a);
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
