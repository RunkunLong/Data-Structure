//
// SHORTESTPATHS.JAVA
// Compute shortest paths in a graph.
//
// Your constructor should compute the actual shortest paths and
// maintain all the information needed to reconstruct them.  The
// returnPath() function should use this information to return the
// appropriate path of edge ID's from the start to the given end.
//
// Note that the start and end ID's should be mapped to vertices using
// the graph's get() function.
//
// You can ignore the input and startTime arguments to the constructor
// unless you are doing the extra credit.
//
class ShortestPaths {
    
    public int INF= Integer.MAX_VALUE;
    public Multigraph graph;
    public PriorityQueue<SP> Q;
    public SP[] SParray;
    
    class SP{
    	public Vertex vertex;
    	public int distance;
    	public Handle handle;
    	public Edge edge;
    	public SP parent;
    	
    	public SP(Vertex v)
    	{
    		vertex=v;
    		distance= INF;
    		parent=null;
    	}
    	public String toString()
    	{
    		String printsp= vertex.toString()+","+distance;
    		return printsp;
    	}
    }
    //
    // constructor
    //
    public ShortestPaths(Multigraph G, int startId, 
			 Input input, int startTime) 
    {
    	graph=G;
    	Initialize(graph,startId);
    	while(Q.isEmpty()==false)
    	{
    		SP u= Q.extractMin();
    		if(u.distance==INF)
    			break;
    		Vertex.EdgeIterator temp=u.vertex.adj();
    		while(temp.hasNext())
    		{
    			Edge ve= temp.next();
    			int Weight=ve.weight();
    			SP v=Q.handleGetValue(ve.to().handle);
    			if(Q.decreaseKey(ve.to().handle, u.distance+Weight))
    			{
    				v.distance=u.distance+Weight;
    				v.parent=u;
    				v.edge=ve;
    			}
    		}
    	}
    	
 
	// your code here
    }
    
    private void Initialize(Multigraph graph, int startId) {
		// FIXME Auto-generated method stub
		Q= new PriorityQueue<SP>();
		SParray= new SP[graph.nVertices()];
		Vertex vertex=graph.get(startId);
		SP start= new SP(vertex);
		start.distance=0;
		SParray[start.vertex.id()]=start;
		vertex.handle=Q.insert(0, start);
		for(int i=0;i<graph.nVertices();i++)
		{
			if(i!=startId)
			{
				vertex=graph.get(i);
				SP u=new SP(vertex);
				SParray[u.vertex.id()]=u;
				vertex.handle=Q.insert(INF, u);
			}
		}
	}

	//
    // returnPath()
    // Return an array containing a list of edge ID's forming
    // a shortest path from the start vertex to the specified
    // end vertex.
    //
    public int [] returnPath(int endId) 
    { 
	// your code here
    	SP t=SParray[endId];
    	SP te=t;
    	int counter=1;
    	if(t.parent==null)
    	{
    		int empty[] = new int [0];
    		return empty;
    	}
    	while(t.parent!=null)
    	{
    		t=t.parent;
    		counter++;
    	}
    	int path[]=new int[counter-1];
    	t=te;
    	int j= counter-2;
    	while(t.parent!=null)
    	{
    		path[j]=t.edge.id();
    		t=t.parent;
    		j--;
    	}
	return path;
    }
}
