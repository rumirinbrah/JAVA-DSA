import java.util.*;
class stack
{   private node head;
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
    public void push(int data )
    {
        node newnode=new node(data);
        if(head==null)
        {
            head=newnode;
            size++;
            return;
        }
        size++;
        newnode.next=head;
        head=newnode;

    }
    public int pop()
    {
        if(head==null)
        {
            System.out.println("empty");
            return 0;
        }
        int top=head.d;
        head=head.next;
        size--;
        return top;
    }
    public int peek()
    {
            if(head==null)
            {
                System.out.println("empty");
                return 0;
            }
            return head.d;
    }
    public void dis()
    {
        node curr=head;
        while(curr!=null)
        {
            System.out.println(curr.d);
            curr=curr.next;
        }
    }
    public void sort()
    {
        int i,j,t;
        node curr1=head;
        for(i=0;i<=size;i++)
        {
            node curr2=head;
            for(j=0;j<=size-i-1;j++)
            {
                if(curr2.d>curr2.next.d)
                {
                    t=curr2.d;
                    curr2.d=curr2.next.d;
                    curr2.next.d=t;
                }
                curr2=curr2.next;

            }
            if(curr1.next==null)
            {
                break;
            }
            curr1= curr1.next;

        }
    }
    public int getsize()
    {
        return size;
    }
    public void reverse()
    {
        node next=null;
        node prev=null;
        node curr=head;
        while (curr != null) {
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head=prev;
    }
}


public class Main {
    public static void main(String[] args)
    {
        stack s=new stack();
        s.push(4);
        s.push(5);
        s.push(2);
        s.push(11);
        s.push(3);
        s.push(4);
        s.push(7);
        s.dis();
        System.out.println();
        s.sort();
        s.dis();

    }
}
