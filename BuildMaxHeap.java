package MyPractice;

public class BuildMaxHeap {

    public static class btree
    {
        int data;
        btree left;
        btree right;

    }

    public static void main(String [] args)
    {
        int [] ar = {16,5,4,17};
        btree tree = buildCompleteBinaryTree(ar,1,null);
        //System.out.println(tree.right.left.data);
        buildMaxHeapFromBinaryTree(tree);
        System.out.println(tree.data);

        btree leave = findAnyLeaveNode(tree);
        System.out.println(leave.data);

        int [] a = doHeapSort(tree,0,ar);

        printArray(a);
    }

    public static void printArray(int [] ar)
    {
        for(int i=0;i<ar.length;i++)
        {
            System.out.println(ar[i]);
        }
    }
    public static void buildMaxHeapFromBinaryTree(btree root)
    {
        if(root==null)
            return;

        buildMaxHeapFromBinaryTree(root.left);
        buildMaxHeapFromBinaryTree(root.right);

        btree largest = maxHeapify(root);
    }

    public static btree maxHeapify(btree root)
    {
        //System.out.println("root data : " + root.data);

        int max = root.data;
        btree largest = root;
        if(root.left!=null) {
            //System.out.println("root left data : " + root.left.data);
            max = Math.max(root.data, root.left.data);
            if(max!= root.data)
                largest = root.left;
        }

        if(root.right!=null) {
            //System.out.println("root right data : " + root.right.data);
            max = Math.max(max, root.right.data);
            if (max > largest.data)
                largest = root.right;
        }

        if(largest!=root)
        {
            swaptreeNodeData(root,largest);
            maxHeapify(largest);
        }

        return largest;
    }

    public static btree buildCompleteBinaryTree(int [] a, int pos,btree root)
    {
        btree tree = new btree();
        if(pos>a.length)
            return null;

        if(root==null) {
            root = tree;
        }

        tree.data = a[pos-1];
       // System.out.println("tree data : " + tree.data);

        tree.left = buildCompleteBinaryTree(a,pos*2,root);
        tree.right = buildCompleteBinaryTree(a,(pos*2)+1,root);

        return tree;
    }

    public static void swaptreeNodeData(btree tree1,btree tree2)
    {
       // System.out.println("swapping : " + tree1.data + " with " + tree2.data);
        int temp = tree1.data;
        tree1.data = tree2.data;
        tree2.data=temp;
    }

    public static btree findAnyLeaveNode(btree root)
    {

        if(root == null)
            return null;

        if(root.left!=null && root.right!=null)

            root =  findAnyLeaveNode(root.left);

        if(root.left==null && root.right!=null)
            root =  findAnyLeaveNode(root.right);

        if(root.right==null && root.left!=null)
            root =  findAnyLeaveNode(root.left);

        return root;
    }

    public static int [] doHeapSort(btree root, int index, int [] ar)
    {
        if(index>ar.length-1)
            return ar;

        ar[ar.length-(index+1)] = root.data;

        btree leave = findAnyLeaveNode(root);
        System.out.println("swapping : " + root.data + " with " + leave.data);


        swaptreeNodeData(root,leave);

        deletenode(root,leave);


        maxHeapify(root);

        return doHeapSort(root,index+1,ar);
    }

    public static void deletenode(btree root, btree todel)
    {
        if(root==null)
            return;

        if(root.left==todel) {
            root.left = null;
            System.out.println("Deleted  " + todel.data);
            return;
        }

        if(root.right==todel) {
            root.right = null;
            System.out.println("Deleted  " + todel.data);
            return;
        }

        deletenode(root.left,todel);
        deletenode(root.right,todel);
    }
}

