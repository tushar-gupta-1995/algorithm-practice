package MyPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Dijikstra {

    public static class Node {
        int src;
        int dst;
        int distance;
    }

    static HashMap<Integer, List<Node>> adjList = new HashMap<Integer, List<Node>>();
    static boolean [] processed = new boolean[201];

    static int [] distance = new int [201];

    public static void initDistance() {
        for (int i=0;i< distance.length;i++)
        {
            distance[i]=1000000;
        }
    }

    public static void main(String...args) {
        readFromFile();
        initDistance();

        runDijikstra(0);

        //7,37,59,82,99,115,133,165,188,197
        System.out.print(distance[6]+",");
        System.out.print(distance[36]+",");
        System.out.print(distance[58]+",");
        System.out.print(distance[81]+",");
        System.out.print(distance[98]+",");
        System.out.print(distance[114]+",");
        System.out.print(distance[132]+",");
        System.out.print(distance[164]+",");
        System.out.print(distance[187]+",");
        System.out.print(distance[196]+",");

    }

    public static void runDijikstra(int srcNode) {
        LinkedList<Integer> connectedNodes = new LinkedList<>();
        connectedNodes.add(srcNode);
        int minVertex = srcNode;
        distance[srcNode]=0;
        while (connectedNodes.size()>0) {
            //System.out.println("vert" + minVertex);
            if(minVertex==-1)
                break;
            List<Node> nodeList = adjList.get(minVertex);
            for (int i = 0; i < nodeList.size(); i++) {
                Node n = nodeList.get(i);
                connectedNodes.add(n.dst);
                int mindst = Math.min(distance[n.dst], distance[n.src] + n.distance);
               // System.out.println("Min distance between " + n.dst + " and " + n.src + " is " + mindst);
                distance[n.dst]=mindst;
            }
            processed[minVertex]=true;
            connectedNodes.remove(new Integer(minVertex));
            minVertex = getVertexWithMinDist(connectedNodes);
        }


    }

    public static int getVertexWithMinDist(LinkedList<Integer> nodes)
    {

        int vertexToReturn=0;

        int min=100000;

        for(int i=0;i< nodes.size();i++)
        {
            int vertex = nodes.get(i);
            if(processed[vertex]==false && distance[vertex]<min)
            {
                min=distance[vertex];
                vertexToReturn=vertex;
            }

        }

        if(processed[vertexToReturn]==true)
            return -1;
        return vertexToReturn;
    }

    public static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\dijikstra.txt"));
            String line = reader.readLine();
           // line = line.replaceAll("\s+","t");

            while (line != null) {
                // System.out.println(line);
                String [] values = line.split("\t");
               // System.out.println(values[0]);
                int src = Integer.parseInt(values[0])-1;

                List<Node> nodeList = new ArrayList<>();
                for(int i=1;i<values.length;i++)
                {
                    String [] dstDestination = values[i].split(",");
                    int dst = Integer.parseInt(dstDestination[0])-1;
                    int distance = Integer.parseInt(dstDestination[1]);
                    Node n = new Node();
                    n.src=src;
                    n.dst=dst;
                    n.distance=distance;
                    nodeList.add(n);
                }
                adjList.put(src,nodeList);
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
