package MyPractice;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class countInversions {

    //int[] a = {8, 4, 2, 1};
static int []a;

    int countTotal = 0;


    public static void main(String [] args)throws Exception
    {
        LinkedList<Integer> myList = getContentsOfFile();

        a = new int[myList.size()];
        for(int i=0;i<myList.size();i++)
        {
            a[i] = myList.get(i);
        }

        System.out.println("Populated : " + a.length);
        countInversions countFor = new countInversions();
        System.out.println(countFor.countInversionAndMergeSort(0,countFor.a.length-1));
    }

    public static LinkedList<Integer> getContentsOfFile() throws Exception
    {
        LinkedList<Integer> myList = new LinkedList<>();
        File f = new File("C:\\Users\\gupta\\IdeaProjects\\untitled\\src\\MyPractice\\Inversions");
        Scanner sc = new Scanner(f);
        String content;
        while(sc.hasNext())
        {
           // System.out.println("adding : " + Integer.parseInt(sc.nextLine()));
            myList.add(Integer.parseInt(sc.nextLine()));
        }

        return myList;
    }


    public long countInversionsAndMerge(int left, int middle, int right) {

        int[] leftArray = new int[(middle - left) + 1];
        int[] rightArray = new int[right - middle];

        // fill left subarray
        int c = 0;
        for (int i = left; i <= middle; i++) {
            leftArray[c++] = a[i];
        }

        // fill right subarray
        int k = 0;
        for (int i = middle + 1; i <= right; i++) {
            rightArray[k++] = a[i];
        }

        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        int mainPointer = left;
        long count = 0;
        while (leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length) {
            int countThisIteration = 0;
            if (leftArray[leftArrayIndex] <= rightArray[rightArrayIndex]) {
                a[mainPointer++] = leftArray[leftArrayIndex++];
            } else if (leftArray[leftArrayIndex] > rightArray[rightArrayIndex]) {
                count+= ((leftArray.length - leftArrayIndex));
                //System.out.println(leftArray[leftArrayIndex] + " > " + rightArray[rightArrayIndex] + " count contributed " + countThisIteration);
                a[mainPointer++] = rightArray[rightArrayIndex++];
            }

            //count += countThisIteration;
        }

        while (leftArrayIndex < leftArray.length) {
            a[mainPointer++] = leftArray[leftArrayIndex++];
        }

        while (rightArrayIndex < rightArray.length) {
            a[mainPointer++] = rightArray[rightArrayIndex++];
        }

         //System.out.println("returning count : " + count);
        return count;
    }

    public long countInversionAndMergeSort(int left, int right) {
        long countTotal=0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            countTotal += countInversionAndMergeSort(left, mid);
            //System.out.println("returning count : " + countTotal);
            countTotal += countInversionAndMergeSort(mid + 1, right);
               // System.out.println("returning count : " + countTotal);
             countTotal += countInversionsAndMerge(left, mid, right);


        }

        return countTotal;
    }
}
