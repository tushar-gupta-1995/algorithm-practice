package MyPractice;

public class orderStatistic {

    public static void main(String [] args)
    {


        //3,4,5,7,8,9
        int [] myAr = {5,7,8,3,4,9};

        orderStatistic obj = new orderStatistic();
       /* obj.quickSort(myAr,0,myAr.length-1);

        for(int i=0;i< myAr.length;i++)
        {
            System.out.print(myAr[i] + " , ");
        }*/

        System.out.println("Order Statitic : " + obj.findOrderStatistic(myAr,0, myAr.length -1,2));
    }


    /***
     * Array = 5,7,8,3,4
     *
     * delimitter = 0
     * traverser starts from 1
     * 7,8 do nothing
     *
     * at 3(index also 3) :delimitter ++ = 1
     * swap (1,3) = 5,3,8,7,4
     *
     * at 4 same way :delimitter ++ =2 5,3,4,7,8
     *
     * now loop exist ...do one final swap
     * swap(2,0) : swap 4,3,5,7,8
     *
     * thus delimitter always point to the boundary after which elements are bigger then pivot
     *
     * return the new position of pivot
     * @param a
     * @param left
     * @param right
     */
    public int partition(int []a,int left,int right)
    {
        int pivot = a[left];
        int delimitter = left;

        for(int traverser=left+1;traverser<=right;traverser++)
        {
            if(a[traverser]<pivot)
            {
                delimitter++;
                swap(a,traverser,delimitter);
            }
        }

        swap(a,delimitter,left);

        return delimitter;
    }

    public void swap(int []a,int index1,int index2)
    {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    public void quickSort(int []a, int left,int right)
    {
        if(left<right)
        {
            int partition = partition(a,left,right);
            quickSort(a,left,partition);
            quickSort(a,partition+1,right);
        }
    }

    public int findOrderStatistic(int []a, int left,int right, int orderStatistc) {
        if(orderStatistc>(right-left+1))
            return -1;

        if(left>=right)
            return a[right];

        if (left < right) {
            int partition = partition(a,left,right);
            int partiionOrder = partition-left+1;
            if((partiionOrder)==orderStatistc) {
                System.out.println("Index : " + partition);
                return a[partition];
            }
            if((partiionOrder)>orderStatistc) {
                System.out.println("Order Stat : " + orderStatistc);
                System.out.println("Partition order : " + partiionOrder);
                return findOrderStatistic(a, left, partition - 1, orderStatistc);
            }
            else if((partiionOrder)<orderStatistc) {
                System.out.println("Order Stat : " + orderStatistc);
                System.out.println("Partition order : " + partition);
                return findOrderStatistic(a, partition + 1, right, (orderStatistc - (partiionOrder)));
            }

        }

        return -2;
    }
}
