package MyPractice;

public class MatrixMulNaive {


    public static void main(String [] args)
    {
        int[][] mat1 = { { 1, 1, 1, 1 },
                { 2, 2, 2, 2 },
                { 3, 3, 3, 3 },
                { 4, 4, 4, 4 } };

        int[][] mat2 = { { 1, 1, 1, 1 },
                { 2, 2, 2, 2 },
                { 3, 3, 3, 3 },
                { 4, 4, 4, 4 } };

        int matrix1[][] = { { 2, 4 }, { 3, 4 } };
        int matrix2[][] = { { 1, 2 }, { 1, 3 } };

        print(matrix1);

        System.out.println("+");
        print(matrix2);

        System.out.println("=");
        /*int [][] ans = multiply(mat1,mat2);

        print(ans);

         ans = multiply(matrix1,matrix2);

        print(ans);*/

        int [][] ans = addMatrix(matrix1,matrix2);

        print(ans);


    }

    public static void print(int [][] matrix)
    {
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }


    /***
     * first row of first matrix with first column of second matrix
     * a[0][0]*b[0][0] + a[0][1]*b[1][0]
     * @param a
     * @param b
     * @return
     */
    public static int [][] multiply(int [][] a,int [][] b)
    {
        /***
         * first number : a[0][0]*b[0][0] + a[0][1]*b[1][0] + .......a[0][n]*b[n][0]
         * second number = a[0][0] * b[0][1] + a[0][1] * b[1][1] + ...... + a[0][n]*b[1][0]
         */

        int [][] ans = new int [a.length][a.length];

        int ansIcrementor = 0;
        for(int row1=0;row1<a.length;row1++) {
            ansIcrementor=0;
            for (int row = 0; row < a.length; row++) {

                for (int column = 0; column < b.length; column++) {
                    //System.out.print("First :" + row1 + "-" + column + "  ");
                    //System.out.print(".......................");
                    //System.out.print("Second :" + column + "-" + row + "  ");
                    ans[row1][ansIcrementor] = ans[row1][ansIcrementor] + a[row1][column] * b[column][row];
                }
               // System.out.println("Computed : " + ans[row1][ansIcrementor] + " for " + row1 + " , " +ansIcrementor);
                ansIcrementor++;
               // System.out.println();
            }

        }

        return ans;
    }

    public static int[][] addMatrix(int [][] m1,int [][] m2)
    {
        int [][] ans = new int [m1.length][m1.length];
        for(int i=0;i<m1.length;i++)
        {
            for(int j=0;j<m1[i].length;j++)
            {
                ans[i][j] = m1[i][j] + m2[i][j];
            }
        }

        return ans;
    }
/*
   public int[][] multiplyDivideConquer(int [][] m1,int [][] m2,int rowLength)
    {

        int ans[][] = new int [rowLength][rowLength];

        int [][] topLeftQuadrantm1 = new int[rowLength/2][rowLength/2];
        int [][] topRightQuadrantm1 = new int[rowLength/2][rowLength/2];
        int [][] bottomLeftQuadrantm1 = new int[rowLength/2][rowLength/2];
        int [][] bottomRightQuadrantm1 = new int[rowLength/2][rowLength/2];

        int [][] topLeftQuadrantm2 = new int[rowLength/2][rowLength/2];
        int [][] topRightQuadrantm2 = new int[rowLength/2][rowLength/2];
        int [][] bottomLeftQuadrantm2 = new int[rowLength/2][rowLength/2];
        int [][] bottomRightQuadrantm2 = new int[rowLength/2][rowLength/2];

        int [][] topRight = multiplyDivideConquer(topLeftQuadrantm1,topLeftQuadrantm2,rowLength/2);
        topRight =addMatrix(topRight,multiplyDivideConquer(topLeftQuadrantm1,bottomLeftQuadrantm2,rowLength/2));

        int [][] topRight = multiplyDivideConquer(topLeftQuadrantm1,topLeftQuadrantm2,rowLength/2);
        topRight =addMatrix(topRight,multiplyDivideConquer(topLeftQuadrantm1,bottomLeftQuadrantm2,rowLength/2));

        return addMatrix(,)


        return addMatrix()



    }*/
}
