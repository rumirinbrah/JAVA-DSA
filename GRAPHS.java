import java.util.*;

public class Graph {

    static class Edge
    {
        int src;
        int dest;
        int wt;

        public Edge(int src,int dest)
        {
            this.src=src;
            this.dest=dest;
            //this.wt=wt;
        }

    }
    public static void create(ArrayList<Edge>[]graph)
    {
        for(int i=0;i<graph.length;i++)
        {
            graph[i]=new ArrayList<>();
        }
    }
    public static void insert(ArrayList<Edge>[]graph,int src,int dest)
    {
        if(src>graph.length)
            return;
        graph[src].add(new Edge(src,dest));
    }
    public static void bfs(ArrayList<Edge>[]graph,int V)
    {
        Queue<Integer>q=new LinkedList<>();
        boolean []vis=new boolean[V];
        q.add(0);
        while(!q.isEmpty())
        {
            int curr=q.remove();
            if(!vis[curr])
            {
                vis[curr]=true;
                System.out.print(curr+" ");
                for(int i = 0; i< graph[curr].size(); i++)
                {
                    Edge e=graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }
    public static void dfs(ArrayList<Edge>[]graph,int curr,boolean[] vis)
    {
        System.out.print(curr+" ");
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++)
        {
            Edge e=graph[curr].get(i);
            if(!vis[e.dest])
                dfs(graph,e.dest,vis);
        }
    }
    public static void getPath(ArrayList<Edge>[]graph,boolean[] vis,int curr,int end,String path) 
    {
        if (curr == end) {
            System.out.println(path + " " + curr);
            return;
        }
            path = path + " " + curr;
            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                if (!vis[e.dest])
                {
                    vis[curr] = true;
                    getPath(graph, vis, e.dest, end, path);
                    vis[curr] = false;
                }
            }
        }
	public static boolean hasCycle(ArrayList<Edge>[]graph,int curr,boolean[] vis,boolean[] rec)
    {
        vis[curr]=true;
        rec[curr]=true;
        for(int i=0;i<graph[curr].size();i++)
        {
            Edge e=graph[curr].get(i);
            if(rec[e.dest]) {
                return true;
            }
            else if(!vis[e.dest]) {
                if (hasCycle(graph,e.dest, vis, rec))
                    return true;
            }
        }
        rec[curr]=false;
        return false;
    }
	public static void topSort(ArrayList<Edge>[]graph,int curr,boolean[] vis,Stack<Integer>s)
    {
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++)
        {
            Edge e=graph[curr].get(i);
            if(!vis[e.dest])
            {
                topSort(graph,e.dest,vis,s);
            }
        }
        s.push(curr);
    }
	//undirected
	public static boolean cycle(ArrayList<Edge>[]graph,int curr,boolean[] vis,int par)
    {
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++)
        {
            Edge e=graph[curr].get(i);
            if(vis[e.dest] && e.dest!=par)
                return true;
            if(!vis[e.dest]) {
                if (cycle(graph, e.dest, vis, curr))
                    return true;
            }
        }
        return false;
    }
	//FORD
    public static void initialize(int[] dis,int src)
    {
        for(int i=0;i<dis.length;i++)
        {
            if(i!=src)
            {
                dis[i]=Integer.MAX_VALUE;
            }
        }
    }
    public static void bFord(ArrayList<Edge>[]graph,int src,int V)
    {
        int[] dis=new int[V];
        initialize(dis,src);
        for(int i=0;i<V-1;i++)
        {
            for(int j=0;j<V;j++)
            {
                for (int k = 0; k < graph[j].size(); k++)
                {
                    Edge e=graph[j].get(k);
                    int u=e.src;
                    int v=e.dest;
                    //in DJ we use a queue so a src value is guaranteed but here,
                    //we're using loops thus we may visit nodes that arent reached yet
                    if(dis[u]!=Integer.MAX_VALUE && dis[v]>u+e.wt)
                    {
                        dis[v]=dis[u]+e.wt;
                    }
                }
            }
        }
        for(int i=0;i<dis.length;i++)
        {
            System.out.print(dis[i]+" ");
        }

    }
    //PRIMS
    static class Pair implements Comparable<Pair>
    {
        int node;
        int wt;
        public Pair(int node,int wt)
        {
            this.node=node;
            this.wt=wt;
        }
        @Override
        public int compareTo(Pair P2)
        {
            return wt-P2.wt;
        }
    }
    public static void prim(ArrayList<Edge>[] graph,int V)
    {
        boolean[] vis=new boolean[V];
        PriorityQueue<Pair>q=new PriorityQueue<Pair>();
        ArrayList<Integer>path=new ArrayList<>();
        int cost=0;
        q.add(new Pair(0,0));
        while(!q.isEmpty())
        {
            Pair curr=q.remove();
            if(!vis[curr.node])
            {
                vis[curr.node]=true;
                path.add(curr.node);
                cost=cost+curr.wt;
                for(int i=0;i<graph[curr.node].size();i++)
                {
                    Edge e=graph[curr.node].get(i);
                    q.add(new Pair(e.dest,e.wt));
                }
            }
        }
        System.out.println("MIN SPAN TREE Is");
        for(int i: path)
        {
            System.out.print(i+"->");
        }
        System.out.println("\nAND COST IS "+cost);
    }
	
	// KOSARAJU SCC (DFS HAS BEEN MODIFIED TO GET FORMATTED OP)
	/*
	public static void dfs(ArrayList<Edge>[]graph,int curr,boolean[]vis,Stack<Integer>s)
    {
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++)
        {
            Edge e=graph[curr].get(i);
            if(!vis[e.dest])
            {
                dfs(graph,e.dest,vis,s);
            }
        }
        s.push(curr);
        while(!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }
	public static void SCC(ArrayList<Edge>[] graph,int V)
    {
        Stack<Integer>s=new Stack<>();
        Stack<Integer>temp=new Stack<>();

        boolean[] vis=new boolean[V];
        //step-1: get a topSort order
        s=topSort(graph,0,vis,s);

        //-- step-2: create new graph & reverse the graph --
        ArrayList<Edge>[] trap=new ArrayList[V];
        create(trap);   //initialize
        for(int i=0;i<V;i++)
        {
            for(int j=0;j<graph[i].size();j++)
            {
                //we reversin
                Edge e=graph[i].get(j);
                insert(trap,e.dest,e.src,e.wt);
            }
        }
        for(int i=0;i<V;i++)
        {
            vis[i]=false;
        }
        int i=1;
        //-- step-3: pop eles from stack and perform dfs --
        while(!s.isEmpty())
        {
            int curr=s.pop();
            if(!vis[curr]) {
                System.out.print("SCC "+i+"-"+" ");
                dfs(trap, curr, vis, temp);
            }
            i++;
            System.out.println();

        }

    }
	*/
	//  ----KOSARAJU END------ 
	
	

    //  ----M   A   I   N----
    public static void main(String[] args)
    {
        Scanner z=new Scanner(System.in);
        int V=7;
        boolean[] vis=new boolean[V];
        ArrayList<Edge>[]graph=new ArrayList[V];
        create(graph);
        insert(graph,0,1);
        insert(graph,0,2);
        insert(graph,1,0);
        insert(graph,1,3);
        insert(graph,2,0);
        insert(graph,2,4);
        insert(graph,3,1);
        insert(graph,3,4);
        insert(graph,3,5);
        insert(graph,4,2);
        insert(graph,4,3);
        insert(graph,4,5);
        insert(graph,5,3);
        insert(graph,5,6);
        getPath(graph,vis,0,5,"");
            //System.out.println("{"+2+","+e.dest+"} wt: "+e.wt);




    }

}
