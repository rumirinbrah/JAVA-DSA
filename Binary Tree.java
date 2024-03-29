import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int c=0;
    private static int count=0;
    static class node
    {
        int data;
        node left,right;
        node(int data)
        {
            this.data=data;
            left=null;
            right=null;
        }
    }
    static class Tree
    {
        static int i=-1;
        public static node build(int nodes[])
        {
            i++;
            if(nodes[i]==-1)
            {
                return null;
            }
            node curr=new node(nodes[i]);
            curr.left=build(nodes);
            curr.right=build(nodes);
            return curr;
        }

    }
    //----------------
    public static void pre(node root)   // O(N)
    {
        if(root==null)
        {
            //System.out.print(-1+" ");
            return;
        }
        System.out.print(root.data+" ");
        pre(root.left);
        pre(root.right);
    }
    public static void inOrder(node root)  //O(N)
    {
        if(root==null)
        {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }
    public static void post(node root)  // O(N)
    {
        if(root==null)
        {
            return;
        }
        post(root.left);
        post(root.right);
        System.out.print(root.data+" ");
    }
    public static void level(node root)
    {
        if(root==null)
        {
            return;
        }
        Queue<node>q=new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty())
        {
            node curr=q.remove();
            if(curr==null)
            {
                System.out.println();
                if(q.isEmpty())
                    break;
                else
                    q.add(null);
            }
            else
            {
                System.out.print(curr.data+" ");
                if(curr.left!=null)
                {
                    q.add(curr.left);
                }
                if(curr.right!=null)
                {
                    q.add(curr.right);
                }
            }
        }
    }
    //-----------

    public static int count(node root)
    {
        if(root==null)
        {
            return 0;
        }
        c++;
        count(root.left);
        count(root.right);
        return  c;
    }
    public static int sum(node root)
    {
        if(root==null)
        {
            return 0;
        }
        count=count+root.data;
        sum(root.left);
        sum(root.right);
        return  count;
    }
    public static int diameter(node root)
    {
        return 0;
    }

    public static boolean isSame(node root1,node root2)
    {
        if(root1==null && root2==null)
        {
            return true;
        }
        if(root1==null || root2==null)
        {
            return false;
        }
        if(root1.data==root2.data) {
            return (isSame(root1.left, root2.left) && isSame(root1.right, root2.right));
        }
        else
            return false;
    }
    public static boolean subTree(node root1,node root2)
    {

        if(root2==null)
        {
            return true;
        }
        if(root1==null) //tree doesnt exist
        {
            return false;
        }
        if(root1.data==root2.data)  //aint equal
        {
            if(isSame(root1,root2))
            {
                return true;
            }
        }

        return subTree(root1.left,root2) ||  subTree(root1.right,root2);
    }
    static int f=0;
    public static void search(node root,int d)
    {
        if(root==null)
        {
            return ;
        }
        if(root.data==d)
        {
            System.out.println("found");
            return;
        }
        search(root.left,d);
        search(root.right,d);
    }

    /*public static int height(node root,int leftH,int rightH)
    {
        if(root==null)
        {
            return 0;
        }
        if(root.left!=null)
        {
            leftH++;
            height(root.left,leftH,rightH);
        }
        if(root.right!=null)
        {
            rightH++;
            height(root.left,leftH,rightH);
        }
        return leftH+rightH;
    }*/


    //  ----M   A   I   N----
    public static void main(String[] args)
    {
        Scanner z=new Scanner(System.in);
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        Tree tree=new Tree();
        node root=tree.build(nodes);
        level(root);
        System.out.println();
        int subnode[]={1,2,4,-1,-1,5,-1,-1,3,-1,-1};
        Tree subT=new Tree();
        subT.i=-1;
        node subroot=subT.build(subnode);

        search(root,6);

    }
}
