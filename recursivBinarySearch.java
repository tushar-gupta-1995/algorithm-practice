package MyPractice;

import java.lang.reflect.Array;
import java.util.Arrays;

public class recursivBinarySearch {


    public static void main(String [] args)
    {
        //int [] a = {5,7,8,9,99,6,233,222,232,233,23232,23232,23232,2323,23232,55454,6464,2446,46535,4254245,25442452,2542454,200};

        int [] a = {5,6,7,8,99,100,101,23,233,56,34,343,343,343,333,111,123,232,2321,232,120};

        Arrays.sort(a);
        for (int i =0;i<a.length;i++)
        {
            System.out.println(i + " : " + a[i]);
        }

        System.out.println("Answer : " + search(a,0,a.length-1,233));
    }

    public static int search(int [] a,int f,int l,int search)
    {

        if(f>l)
            return -1;

        int m = f + (l-f)/2;

        //System.out.println(a[m]);

        if(a[m] == search)
            return m;


        if(a[m]>search)
            return search(a,f,m-1,search);
        else
            return search(a,m+1,l,search);

    }
}
