import java.util.*;
class queue
{   private node front;
    private node rear;
    private int size=-1;
    class node {
        int d;
        node next;
        public node(int d)
        {
            next=null;
            this.d=d;
        }
    }
    public void eq(int d)
    {
        node newnode=new node(d);
        if(front==null)
        {
            front=newnode;
            rear=front;
            size++;
            return;
        }
        rear.next=newnode;
        rear=newnode;
        size++;
    }
    public int dq()
    {

        if(front==null)
        {
            System.out.println("queue empty ");
            return 0;
        }
        if(front==rear)
        {
            int f= front.d;
            front=rear=null;
            size--;
            return f;
        }
        int f= front.d;
        front=front.next;
        size--;
        return f;
    }
    public int peek()
    {
        if(front==null)
        {
            System.out.println("empty");
            return 0;
        }
        int f=front.d;
        return f;
    }
    public void dis()
    {
        if(front==null)
        {
            System.out.println("empty");
        }
        node curr=front;
        while (curr!=null)
        {
            System.out.println(front.d);
            curr=curr.next;
        }
    }



}


public class Main {
    public static void main(String[] args)
    {
        queue l=new queue();
        l.eq(4);
        l.eq(6);
        l.eq(3);
        l.eq(1);
        l.dis();


    }
}
