package MyPractice;

public class towerHanoi {

    static int stepCount=0;
public static void  main(String [] args)
{
    implement(5,"A","C","B");
}
    public static void implement(int numberOfBlocks,String from,String to,String using)
    {
        if(numberOfBlocks==0)
            return;
        implement(numberOfBlocks-1,from,using,to);
        System.out.println("Move block" + numberOfBlocks + " from " + from + " to " + to + " step is : " + stepCount++);
        implement(numberOfBlocks-1,using,to,from);
    }
}
