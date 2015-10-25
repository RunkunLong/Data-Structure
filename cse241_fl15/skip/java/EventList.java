//
// EVENTLIST.JAVA
// Skeleton code for your EventList collection type.
//
import java.util.*;

class EventList {
    
    Random randseq;
    public Event head; 
    public Event[] tail;
    public static int neginf= Integer.MIN_VALUE;
    public static int posinf= Integer.MAX_VALUE;
    
    public int h;  //height of the skiplist
    
    ////////////////////////////////////////////////////////////////////
    // Here's a suitable geometric random number generator for choosing
    // pillar heights.  We use Java's ability to generate random booleans
    // to simulate coin flips.
    ////////////////////////////////////////////////////////////////////
    
    int randomHeight()
    {
	int v = 1;
	while (randseq.nextBoolean()) { v++; }
	return v;
    }

    
    //
    // Constructor
    //
    public EventList()
    {
	randseq = new Random(58243); // You may seed the PRNG however you like.
    
    Event t1= new Event(neginf,null);
    t1.pillar=1000;
    Event[] t2= new Event[1000];
    for(int i=0;i<1000;i++)
    {
    	t2[i]=new Event(posinf,null);
    	t2[i].pillar=1000;
    }
    head=t1;
    tail=t2;
    head.next= tail;
     
    }

    
    //
    // Add an Event to the list.
    //
    public void insert(Event e)
    {
	  
    }

    
    //
    // Remove all Events in the list with the specified year.
    //
    public void remove(int year)
    {
	
    }
    
    //
    // Find all events with greatest year <= input year
    //
    public Event [] findMostRecent(int year)
    {
	return null;
    }
    
    
    //
    // Find all Events within the specific range of years (inclusive).
    //
    public Event [] findRange(int first, int last)
    {
	return null;
    }
}
