package MyPractice;

import java.util.HashMap;

public class countIntersections {

    public static void main(String [] args)
    {
        int [] a = {1,2,3,4,5};
        int [] b = {3,4,8,6,7,5};
        System.out.println("Intersections are : ");
        HashMap<Integer,Integer> map = new HashMap<>();
        int aIndex=0;int bIndex=0;
        while (aIndex<a.length || bIndex<b.length)
        {
            if(aIndex<a.length && !map.containsKey(a[aIndex])) {
                map.put(a[aIndex++], 1);
                continue;
            }
            if(map.containsKey(b[bIndex]))
                System.out.println(b[bIndex]);
            bIndex++;
        }

    }
}
