package MyPractice;

public class InsertionSort {

public static void main(String [] args)
{
    int [] a = {10,2,7,9,5,6,1,4,3,8};

    a=doInsertionSort(a);

    print(a);
}
    public static int [] doInsertionSort(int [] a)
    {
        for(int i=1;i<a.length;i++)
        {
            int j=i-1;
            int key = a[i];
            while(j>=0 && a[j]>key)
            {
                a[j+1]=a[j];
                j--;
            }
            j++;
            a[j]=key;
        }

        return a;
    }


    public static void print(int [] a)
    {
        for(int i=0;i<a.length;i++)
        {
            System.out.println(a[i]);
        }
    }
}
