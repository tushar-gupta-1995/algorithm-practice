package MyPractice;

public class SelectionSort {

    static int count=0;
    public static void main(String [] args)
    {
        //int [] a = {10,8,3,7,6,4,2,5,1,9};
        int [] a = {1,2,3,4,5,6,7,8,9,10};
       // int [] sortedArray = selectionSort(a,0);
        int [] sortedArray = bubble(a);
        print(sortedArray);
        System.out.println("Passes taken  : " + count);
    }

    public static void print(int [] ar)
    {
        for(int i=0;i<ar.length;i++)
        {
            System.out.println(ar[i]);
        }
    }

    public static int [] selectionSort(int [] ar,int left)
    {
        int right=ar.length-1;
        if(left==right)
            return ar;

        int minPos = findMin(left,right,ar);
        swap(ar,left,minPos);

        return selectionSort(ar,left+1);

    }

    public static int findMin(int left,int right,int [] ar)
    {
        int min=left;
        left++;
        while(left<=right)
        {
            if(ar[left]<ar[min])
                min = left;
            left++;
           // count++;
        }
        return min;
    }

    public static void swap(int [] ar, int index1, int index2)
    {
        int temp = ar[index1];
        ar[index1] = ar[index2];
        ar[index2]=temp;
        count++;
    }

    public static int [] bubble(int [] ar)
    {
        for(int i=0;i<ar.length;i++)
        {
            for(int j=0;j<ar.length-i-1;j++)
            {
                if(ar[j]>ar[j+1])
                {
                    swap(ar,j,j+1);
                }
            }
        }

        return ar;
    }
}
