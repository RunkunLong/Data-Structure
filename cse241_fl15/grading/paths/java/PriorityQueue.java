import java.util.ArrayList;

//
// PRIORITYQUEUE.JAVA
// A priority queue class supporting sundry operations needed for
// Dijkstra's algorithm.
//

class PriorityQueue<T> {
    
	public ArrayList<Element <T>>flight=new ArrayList<Element<T>>();
	public int size;
	// constructor
    //
	class Element<T>
	{
		public int key;
		public T value;
		public Handle handle;
		public Element(int k, T v, Handle h)
		{
			key=k;
			value=v;
			handle=h;
		}
	}
    public PriorityQueue()
    {
    	flight.add(0,null);
    	size =1;
    }
    
    // Return true iff the queue is empty.
    //
    public boolean isEmpty()
    {
    	if(size==1)return true;
    	else return false;
	
    }
    
    // Insert a pair (key, value) into the queue, and return
    // a Handle to this pair so that we can find it later in
    // constant time.
    //
    Handle insert(int key, T value)
    {
    	int s=size;
    	Handle h =new Handle(s);
    	Element<T> temp=new Element<T> (key,value,h);
    	flight.add(size,temp);
    	int parentindex = (int)Math.floor(s/2);
    	if(parentindex<=1)
    		parentindex=1;
    	int parentkey=flight.get(parentindex).key;
    	while(s>1 && key<parentkey)
    	{
    		swap(s,parentindex);
    		s=parentindex;
    		parentindex=(int)Math.floor(s/2);
    		if(parentindex<1){
        		parentindex=1; 
    		}
    		parentkey = flight.get(parentindex).key;
    	}
    	size++;
    	return h;
    }
    
    public void swap(int p,int q)
    {
    	Element<T> t=flight.get(p);
    	flight.set(p, flight.get(q));
    	flight.set(q,t);
    	flight.get(p).handle.index=p;
    	flight.get(q).handle.index=q;
    }
    // Return the smallest key in the queue.
    //
    public int min()
    {
    	if(isEmpty())
    		return 0;
    	else
    		return flight.get(1).key;
    }
    
    // Extract the (key, value) pair associated with the smallest
    // key in the queue and return its "value" object.
    //
    public T extractMin()
    {
    if(isEmpty())	
    	return null;
    int tempindex=flight.get(1).handle.index;
    T temp=flight.get(1).value;
    flight.get(1).handle.index=0;
    flight.set(1,flight.get(size-1));
    flight.get(1).handle.index=tempindex;
    size--;
    heapify(1);
    return temp;
    
    }
    public void heapify(int i)
    {
    	if(i<=(int)Math.floor(size/2))
    	{
    		int j;
    		if(size<2*i+1 ||flight.get(2*i).key<flight.get(2*i+1).key)
    		{
    			j=2*i;
    		}
    		else
    		{
    			j=2*i+1;
    		}
    		if(flight.get(j).key<flight.get(i).key)
    		{
    			swap(i,j);
    			heapify(j);
    		}
    	}
    }
    
    
    // Look at the (key, value) pair referenced by Handle h.
    // If that pair is no longer in the queue, or its key
    // is <= newkey, do nothing and return false.  Otherwise,
    // replace "key" by "newkey", fixup the queue, and return
    // true.
    //
    public boolean decreaseKey(Handle h, int newkey)
    {
    if(flight.get(h.index)==null)
    	return false;
    if(h.index>size)
    	return false;
    if(flight.get(h.index).key<=newkey)
    	return false;
    int temp=h.index;
    flight.get(temp).key=newkey;
    int parentindex=(int)Math.floor(temp/2);
    while(temp>1 && newkey<flight.get(parentindex).key)
    {
    	swap(temp,parentindex);
    	temp=(int)Math.floor(temp/2);
    	parentindex=(int)Math.floor(temp/2);
    	if(parentindex<=1)
    		parentindex=1;
    }
    h.index=temp;
    return true;  
    }
    
    
    // Get the key of the (key, value) pair associated with a 
    // given Handle. (This result is undefined if the handle no longer
    // refers to a pair in the queue.)
    //
    public int handleGetKey(Handle h)
    {
    	if(flight.get(h.index)!=null)
    		return flight.get(h.index).key;
    	else
    		return 0;
    }

    // Get the value object of the (key, value) pair associated with a 
    // given Handle. (This result is undefined if the handle no longer
    // refers to a pair in the queue.)
    //
    public T handleGetValue(Handle h)
    {
    	if(flight.get(h.index)==null)
    		return null;
    	else
    		return flight.get(h.index).value;
    }
    
    // Print every element of the queue in the order in which it appears
    // in the implementation (i.e. the array representing the heap).
    public String toString()
    {
    String printlist="";
    for(int i=1;i<size;i++)
    {
    	//printlist=printlist+""+flight.get(i).value;
    	printlist = printlist+"("+flight.get(i).key+", "+flight.get(i).value+")\n";
    	//printlist = printlist+flight.get(i).toString()+"\n";
    }
	return printlist;
    }

}
