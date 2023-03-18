package MyPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class kclusteringhamming {

    static HashMap<Integer, List<Node>> adjList = new HashMap<Integer, List<Node>>();
    static PriorityQueue<Node> pq = new PriorityQueue<>(200000,new JobComparator());
    public static class Node {
        int src;
        int dst;
        int distance;

        public Node(int src, int dst, int distance) {
            this.src = src;
            this.dst = dst;
            this.distance = distance;
        }
    }


    // if index of tracker array is negative, it means the index represents a leader and the value is the size of the leader set
    // if index of tracker array is porsitive, it means the index represents the pointer to the leader
    // initially the value of all indices =-1, signifying initially all indices are leader with size 1(their set only has them)
    // therefore if trackr value is -1, then it has not yet been included in any set, it exists as a standalone leader
    public static class UnionFind {

        int [] tracker;

        public UnionFind(int size)
        {
            // we are incrementing the size by 1 as the index 0 will never be used..why the hell dont they start vertices with 0.
            tracker = new int [size+1];
            for(int i=0;i<size+1;i++)
            {
                // just put 0 out of the way with randomness
                tracker[0]=9999;
                tracker[i]=-1;
            }
        }


        public void union(int leader,int follower)
        {
            //System.out.println("Fusing " + leader + " and " + follower);
            // step 1: follower may already be a leader..so all its children should now point to the leader
            // step 1b(IMPORTANT) we also need to find the number of nodes following the follower as the leader
            // the count of step 1b will be the number of nodes incremented for the leader
            int countToIncrement=0;
            for(int i=0;i< tracker.length;i++)
            {
                if(tracker[i]==follower)
                {
                    countToIncrement++;
                    tracker[i]=leader;
                }
            }

            //step 2..now all followers of follower point to leader..so follower should also point to leader and countToIncrement increases by 1 to represent this
            countToIncrement++;
            tracker[follower]=leader;

            // leader may be following some other node..so we need to find the leader of the follower
            // once we find the leader we can assign the follower to point to the leader
            if(tracker[leader]>0)
            {
                while(tracker[leader]>0)
                {
                    leader=tracker[leader];
                }
            }

            //now that we have the leader..we can increment its size..point to note is the size is represented in -ve..so instead of adding the count we subtract it
            int size = tracker[leader]-countToIncrement;
            System.out.println(" fused leader : " + leader + " follower " + follower + " with size: " + size);
            tracker[leader]=size;
        }

        // if the tracker at index: vertexToFind is -1 then it has not yet been added, else it has been added
        // if finds then returns the index of the leader
        public int find(int vertexToFind)
        {
            int ans = vertexToFind;
            int leader=tracker[vertexToFind];
            if(leader>-1)
            {
                while(1==1)
                {
                    if(tracker[leader]<0)
                    {
                        ans=leader;
                        break;
                    }
                    leader=tracker[leader];
                }
            }

            System.out.println("vertex: " + vertexToFind + " has leader " + ans);
            return  ans;
        }
    }


    static HashMap<String,Integer> hammoingCodeVertex = new HashMap<>();
    static HashMap<Integer,String> vertexHammingCode = new HashMap<>();

    public static void main(String...args) {
        readFromFile();

        UnionFind uf = new UnionFind(200000);
        for(int i=1;i<=200000;i++)
        {
            String vertex=vertexHammingCode.get(i);
            List<Integer> nodes = new ArrayList<>();
            nodes = calculate0Distance(vertex);
            List<Node> nodeL = new ArrayList<>();
            if(!nodes.isEmpty())
            {
                for(int n : nodes)
                {
                    Node no = new Node(i,n,0);
                    nodeL.add(no);
                    pq.add(no);
                }
            }
            nodes = calculate1Distance(vertex);
            if(!nodes.isEmpty())
            {
                for(int n : nodes)
                {
                    Node no = new Node(i,n,1);
                    nodeL.add(no);
                    pq.add(no);
                }
            }

                nodes = calculate2Distance(vertex);


            if(!nodes.isEmpty())
            {
                for(int n : nodes)
                {
                    Node no = new Node(i,n,2);
                    nodeL.add(no);
                    pq.add(no);
                }
            }

            adjList.put(i,nodeL);

        }

        HashSet<Integer> leaders = new HashSet<>();

        UnionFind myDataStruct = executeClusteringAlgo(pq,200000);

        for(int i=0;i<myDataStruct.tracker.length;i++)
        {
            if(myDataStruct.tracker[i]<0)
            {
                leaders.add(i);
            }
        }


        for(int i:leaders)
        {
            System.out.println("leaders : " + i + " with size : " + myDataStruct.tracker[i]);
        }

        System.out.println("answer : " + leaders.size());
    }

    public static UnionFind executeClusteringAlgo(PriorityQueue<Node> pq, int initialSize)
    {

        UnionFind myDataStruct = new UnionFind(initialSize);

        int count=initialSize;
        while (pq.size()>0)
        {
            Node smallestSourceDestinationPair = pq.poll();
            int srcLeader = myDataStruct.find(smallestSourceDestinationPair.src);
            int dstLeader = myDataStruct.find(smallestSourceDestinationPair.dst);
            // if both src and dst are in same cluster, ignore
            if(srcLeader==dstLeader)
            {
                System.out.println(smallestSourceDestinationPair.src + " and " + smallestSourceDestinationPair.dst + " are already fused with leader : " + myDataStruct.find(smallestSourceDestinationPair.src));
                continue;
            }

            myDataStruct.union(srcLeader,dstLeader);

            count--;
        }

       System.out.println("ans from count : " + count);
        return myDataStruct;
    }

    public static class JobComparator implements Comparator<Node>
    {
        @Override
        public int compare(Node a, Node b)
        {
            if(a.distance>b.distance)
                return 1;
            else if(a.distance==b.distance)
                return 0;
            else
                return -1;
        }
    }

    public static List<Integer> calculate1Distance(String a) {

        a = a.replaceAll("\s","");
        List<Integer> nodes = new ArrayList<>();

        for(int i=0;i<a.length();i++)
        {
            char c = a.charAt(i);
            char flipped = flipBit(c);
            String potential = a.substring(0, i) + flipped
                    + a.substring(i + 1);
           // System.out.println("finding: " + potential);
            if(hammoingCodeVertex.get(potential)!=null)
            {
                nodes.add(hammoingCodeVertex.get(potential));
            }
        }

        return nodes;
    }


    public static List<Integer> calculate0Distance(String a) {

        a = a.replaceAll("\s","");
        List<Integer> nodes = new ArrayList<>();

            // System.out.println("finding: " + potential);
            if(hammoingCodeVertex.get(a)!=null)
            {
                nodes.add(hammoingCodeVertex.get(a));
            }


        return nodes;
    }


    public static List<Integer> calculate2Distance(String a) {

        a = a.replaceAll("\s","");
        List<Integer> nodes = new ArrayList<>();

        for(int i=0;i<a.length();i++)
        {
           for(int j=i+1;j<a.length();j++)
           {
               char firstBit = a.charAt(i);
               char flipped = flipBit(firstBit);
               String potentialPartial = a.substring(0, i) + flipped
                       + a.substring(i + 1);
               char secondBit = potentialPartial.charAt(j);
               char flippedSecond = flipBit(secondBit);
               String potential = potentialPartial.substring(0, j) + flippedSecond
                       + potentialPartial.substring(j + 1);
               if(hammoingCodeVertex.get(potential)!=null)
               {
                   nodes.add(hammoingCodeVertex.get(potential));
               }
           }
        }

        return nodes;
    }

    public static char flipBit(char a)
    {
        if(a=='1')
            return '0';
        else
            return '1';
    }

    public static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\hammingclustertry2.txt"));
            String line = reader.readLine();
            // line = line.replaceAll("\s+","t");

            int index=0;
            int vertex=1;
            while (line != null) {
                if (index++ == 0) {
                    line = reader.readLine();
                    continue;
                }


                // System.out.println(line);
                String value = line.replaceAll("\s","");
                hammoingCodeVertex.put(value,vertex);
                vertexHammingCode.put(vertex,value);
                vertex++;
                line = reader.readLine();
            }
                reader.close();

        } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
