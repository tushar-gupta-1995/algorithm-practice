package MyPractice;

public class MinSubSequenceArray {


    static int [] a = {1,2,3,4,1,2,3,4,5,6,1,2,3,4,5,6,7,8,9};

    static int [] maxLength =new int [a.length];


    public static void main(String...args) {

       maxLength[0]=1;

        for (int i=0;i<a.length-1;i++) {

            if(a[i+1]>=a[i])
            {
                    maxLength[i+1]   = maxLength[i] +1;
            }
            else
                maxLength[i+1]=1;
        }

        int mlength=1;
        for(int i=0;i<maxLength.length;i++)
        {
            if(maxLength[i]>mlength)
                mlength=maxLength[i];
        }

        System.out.println(mlength);
    }
}
