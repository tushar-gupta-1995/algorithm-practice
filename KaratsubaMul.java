package MyPractice;

import java.math.BigInteger;

public class KaratsubaMul {

    public static void main(String [] args) throws  Exception
    {


        long timeBegin = System.currentTimeMillis();
        System.out.println(new KaratsubaMul().multiply( new BigInteger("3141592653589793238462643383279502884197169399375105820974944592"),new BigInteger("2718281828459045235360287471352662497757247093699959574966967627")));
        long timeEnd = System.currentTimeMillis();

        System.out.println("Compute time  : " + (timeEnd-timeBegin));

         timeBegin = System.currentTimeMillis();
        System.out.println(new BigInteger("3141592653589793238462643383279502884197169399375105820974944592").multiply(new BigInteger("2718281828459045235360287471352662497757247093699959574966967627")));
         timeEnd = System.currentTimeMillis();

        System.out.println("Compute time  : " + (timeEnd-timeBegin));

      //  System.out.println(power(new BigInteger("10"),2));
    }
    public BigInteger multiply(BigInteger num1, BigInteger num2)
    {

        if(num1.compareTo(new BigInteger("10"))==-1 && num2.compareTo(new BigInteger("10"))==-1) {
         //System.out.println(num1 + "*" + num2);
            return num1.multiply(num2);
        }
        long lengtha  = String.valueOf(num1).length();
        long lengthb  = String.valueOf(num2).length();

        long length  = Math.max(lengtha,lengthb);
        length = (long)Math.ceil(length/2);
        //System.out.println(length);
       // long power = (long)Math.pow(10,length);
        BigInteger power = power(new BigInteger("10"),length);

        BigInteger a = num1.divide(new BigInteger(String.valueOf(power)));
        BigInteger b = num1.mod(new BigInteger(String.valueOf(power)));

        BigInteger c = num2.divide(new BigInteger(String.valueOf(power)));
        BigInteger d = num2.mod(new BigInteger(String.valueOf(power)));
        //System.out.println(length);
        //System.out.println(c + "" + d);

        BigInteger ac = multiply(a,c);
        BigInteger aPlusBCPlusD = multiply(a.add(b),c.add(d));
        BigInteger bd = multiply(b,d);

        //

       // BigInteger ans = ((Math.pow(10,2*length)*ac)) + ((long)(Math.pow(10,length)*(aPlusBCPlusD-ac-bd)) )+ (long)(bd);
       // BigInteger ans = (ac.multiply(new BigInteger(String.valueOf((int)Math.pow(10,2*length))).add(aPlusBCPlusD.subtract(ac).subtract(bd).multiply(new BigInteger(String.valueOf((int)Math.pow(10,length))))).add(bd)));

        BigInteger ans = ac.multiply(power(new BigInteger("10"),2*length));
        BigInteger ans1 = aPlusBCPlusD.subtract(ac.add(bd));
        ans1 = ans1.multiply(power(new BigInteger("10"),length));
       // System.out.println(ac + "-" + new BigInteger(String.valueOf((long)Math.pow(10,2*length))));
        BigInteger ans2 = bd;

        //System.out.println(ans);
        return  ans.add(ans1).add(ans2);


    }

    public static BigInteger power(BigInteger base,long exp)
    {
        BigInteger ans = new BigInteger("1");
        for(long i=0;i<exp;i++)
        {
            ans = ans.multiply(base);
        }

        return ans;
    }
}
