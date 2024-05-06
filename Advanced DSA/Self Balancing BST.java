
class Node
{
    Node left,right;
    int data;
    public Node(int data)
    {
        this.data=data;
    }

}

public class Main
{

    public static Node insert(Node root,int d)
    {
        if(root==null)
        {
            return new Node(d);
        }
        if(root.data<d)
        {
            root.right=insert(root.right,d);
        }
        else
            root.left=insert(root.left,d);
         return rotate(root);
        //return root;
    }
    public static void inOrder(Node root)
    {
        if(root==null)
            return;
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }
    public static int height(Node root,int leftH,int rightH)
    {
        if(root==null)
            return 0;
        leftH=height(root.left,leftH,rightH);
        rightH=height(root.right,leftH,rightH);
        return Math.max(leftH,rightH)+1;
    }
    public static int leftH(Node root,int ht)
    {
        if(root==null)
            return 0;
        ht=leftH(root.left,ht);
        return ht+1;
    }
    public static int rightH(Node root,int ht)
    {
        if(root==null)
            return 0;
        ht=rightH(root.right,ht);
        return ht+1;
    }
    //RIGHT-ROTATE
    public static Node rotateRight(Node root)
    {
        Node child=root.left;
        //to replace parents left
        Node rep=child.right;
        child.right=root;
        root.left=rep;
        return child;
    }
    //LEFT-ROTATE
    public static Node rotateLeft(Node root)
    {
        Node child=root.right;
        Node rep=child.left;
        child.left=root;
        root.right=rep;
        return child;
    }
    public static Node rotate(Node root)
    {
        //LEFT HEAVY
        if(leftH(root.left,0)-rightH(root.right,0)>1)
        {
            //left left case
            if(leftH(root.left.left,0)-rightH(root.left.right,0)>0)
            {
                return rotateRight(root);
            }
            //left right case
            else //(leftH(root.left.left,0)-rightH(root.left.right,0)<0)
            {
                root.left=rotateLeft(root.left);
                return rotateRight(root);
            }
        }
        //right heavy
        if(leftH(root.left,0)-rightH(root.right,0)<-1)
        {
            // right right case
            if(leftH(root.right.left,0)-rightH(root.right.right,0)<0)
            {
                return rotateLeft(root);
            }
            //right left case
            else // the diff will be >0)
            {
                root.right=rotateRight(root.right);
                return rotateLeft(root);
            }
        }
        return root;
    }
    public static int tempH(Node root,int leftH,int rightH)
    {
        if(root==null)
            return 0;
        leftH=tempH(root.left,leftH,rightH);
        rightH=tempH(root.right,leftH,rightH);
        return Math.max(leftH,rightH)+1;

    }
    public static void main(String[] args)
    {
        int[] nodes={50,20,18,21,25,60};
        Node root=null;
        for(int i =0;i<100;i++)
        {
            root=insert(root,i);
        }
        // yea we gonn have to imporve that leftH and rightH
        //function coz its sort of kam chalau
        //or then again maybe it is correct

        System.out.println();
        System.out.println(height(root,0,0));


    }
}
