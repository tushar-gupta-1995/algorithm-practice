package MyPractice;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Prim {
    static HashMap<Integer,Boolean> visitedTracker = new HashMap<>();
    static HashMap<Integer, List<NodeWeight>> mainAdjList = new HashMap<>();

    public static void main(String [] args) {
//        HashMap<Integer, List<NodeWeight>> hm = getTestAdjList();
//        executePrim(hm,1);
        readFromFile();
       executePrim(mainAdjList,1);
    }

    public static HashMap<Integer, List<NodeWeight>> getTestAdjList()
    {
        HashMap<Integer, List<NodeWeight>> hm = new HashMap<>();

       //1's connections
        NodeWeight nw = new NodeWeight(2,4,false);
        NodeWeight nw1 = new NodeWeight(4,5,false);
        visitedTracker.put(1,false);
        visitedTracker.put(2,false);
        visitedTracker.put(4,false);
        hm.put(1, Arrays.asList(nw,nw1));
        //2's connections
        NodeWeight nw3 = new NodeWeight(3,2,false);
        NodeWeight nw4 = new NodeWeight(4,8,false);
        hm.put(2, Arrays.asList(nw3,nw4));
        visitedTracker.put(3,false);
        visitedTracker.put(4,false);
        visitedTracker.put(2,false);
        //3's connections
        NodeWeight nw5 = new NodeWeight(1,7,false);
        NodeWeight nw6 = new NodeWeight(4,3,false);
        hm.put(3, Arrays.asList(nw5,nw6));
        visitedTracker.put(1,false);
        visitedTracker.put(4,false);
        visitedTracker.put(3,false);
        //4's connections
        NodeWeight nw7 = new NodeWeight(1,5,false);
        NodeWeight nw8 = new NodeWeight(2,8,false);
        hm.put(4, Arrays.asList(nw7,nw8));
        visitedTracker.put(1,false);
        visitedTracker.put(4,false);
        visitedTracker.put(2,false);

        return hm;
    }

    public static void executePrim(HashMap<Integer, List<NodeWeight>> adjList,int src) {

        PriorityQueue<NodeWeight> pq = new PriorityQueue<>(adjList.size(),new LeastWeightComparator());
        System.out.println("Visiting : " + src);
        for(NodeWeight nw : adjList.get(src))
        {
            pq.add(nw);
            visitedTracker.put(src,true);
        }

        long spanningTreeWeightCalculator=0;
        while(pq.size()>0)
        {
            NodeWeight nw = pq.poll();
            if(visitedTracker.get(nw.node))
                continue;
            visitedTracker.put(nw.node,true);
            System.out.println("Visiting : " + nw.node);
            spanningTreeWeightCalculator = spanningTreeWeightCalculator + nw.weight;
            if(adjList.get(nw.node)==null)
                continue;
            for(NodeWeight n : adjList.get(nw.node))
            {
                pq.add(n);
            }
        }

        System.out.println("Spanning tree weight : " + spanningTreeWeightCalculator);
    }


    public static class NodeWeight {

        int node;
        int weight;
        boolean visited;

        public NodeWeight(int node, int weight, boolean visited) {
            this.node = node;
            this.weight = weight;
            this.visited = visited;
        }
    }

    public static class LeastWeightComparator implements Comparator<NodeWeight>
    {
        @Override
        public int compare(NodeWeight a, NodeWeight b)
        {
            if(a.weight>b.weight)
                return 1;
            else if(a.weight==b.weight)
                return 0;
            else
                return -1;
        }
    }

    public static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\prim.txt"));
            String line = reader.readLine();
            // line = line.replaceAll("\s+","t");

            boolean firstInstace=true;
            while (line != null) {
                if(firstInstace)
                {
                    line = reader.readLine();
                    firstInstace=false;
                    continue;
                }
                //System.out.println(line);
                String [] values = line.split(" ");
                // System.out.println(values[0]);
                int srcNode = Integer.parseInt(values[0]);
                int destNode = Integer.parseInt(values[1]);
                int weight = Integer.parseInt(values[2]);

                visitedTracker.put(srcNode,false);
                visitedTracker.put(destNode,false);
                if(mainAdjList.get(srcNode)==null)
                {
                    List<NodeWeight> list = new ArrayList<>();
                    mainAdjList.put(srcNode,list);
                }

                List<NodeWeight> l = mainAdjList.get(srcNode);
                boolean exist = l.stream().filter(x->x.node==destNode).collect(Collectors.toList()).size()>0;
                if(!exist) {
                    l.add(new NodeWeight(destNode,weight,false));
                    mainAdjList.put(srcNode,l);
                }

                if(mainAdjList.get(destNode)==null)
                {
                    List<NodeWeight> list = new ArrayList<>();
                    mainAdjList.put(destNode,list);
                }

                l = mainAdjList.get(destNode);
                exist = l.stream().filter(x->x.node==srcNode).collect(Collectors.toList()).size()>0;
                if(!exist) {
                    l.add(new NodeWeight(srcNode,weight,false));
                    mainAdjList.put(destNode,l);
                }

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
