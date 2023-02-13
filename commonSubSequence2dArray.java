package MyPractice;

import java.util.ArrayList;
import java.util.List;

public class commonSubSequence2dArray {

    static String s1 = "dknkdizqxkdczafixidorgfcnkrirmhmzqbcfuvojsxwraxe";
    static String s2 = "dulixqfgvipenkfubgtyxujixspoxmhgvahqdmzmlyhajerqz";

    public static void main(String [] args)
    {
        System.out.println(findCommonSubSequence(s1,s2));
    }
    public static String findCommonSubSequence(String s1,String s2)
    {


        char [] s1Array = new char[s1.length()];
        for(int i=0;i<s1.length();i++)
        {
            s1Array[i]=s1.charAt(i);
        }

        char [] s2Array = new char[s2.length()];
        for(int i=0;i<s2.length();i++)
        {
            s2Array[i]=s2.charAt(i);
        }

        String commonSubsequqnce = "";
        int count=0;
        int [][] matrix = new int[s1.length()][s2.length()];
        boolean [] alreadyIncrementedColumn = new boolean [s2.length()];
        //int max=0;
        for(int i=0;i< s1Array.length;i++)
        {
            boolean alreadyIcremented = false;
            for(int j=0;j< s2Array.length;j++)
            {
                //each row represents the first string and column the second string..maximum match is the maximum of row-1 or col-1
                int initialValue = Math.max(matrix[Math.max(0,i-1)][j],matrix[i][Math.max(0,j-1)]);

                //System.out.println("Comparing " + s1.charAt(i) + " with " + s2.charAt(j));
                if(!alreadyIcremented && !alreadyIncrementedColumn[j] && s1.charAt(i)==s2.charAt(j))
                {
                    if(i==0 || j==0) {
                        initialValue++;
                    }
                    alreadyIcremented = true;
                    alreadyIncrementedColumn[j]=true;
                }

                if(i>0 && j>0 && matrix[i][j-1]==matrix[i-1][j] &&  s1.charAt(i)==s2.charAt(j))
                    initialValue = matrix[i-1][j-1]+1;

                matrix[i][j] = initialValue;
            }
        }


        print2D(matrix);

        // Loop through all rows
        int max=0;
        String sub = "";
        for (int i = 0; i < matrix.length; i++) {
            // Loop through all elements of current row

            for (int j = 0; j < matrix[i].length; j++) {

                if(matrix[i][j]>max)
                {
                    sub = sub + s1.charAt(i);
                    max = matrix[i][j];
                }
            }
        }

        System.out.println(max);
        return "";
    }

    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int i = -1; i < s1.length(); i++) {
            // Loop through all elements of current row
            if(i>-1)
            System.out.print(s1.charAt(i)+" ");
            for (int j = 0; j < s2.length(); j++) {
                if(i==-1) {
                    if(j==0)
                        System.out.print(" ");
                    System.out.print(" "+s2.charAt(j));
                    continue;
                }
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
