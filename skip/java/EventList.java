//
// EVENTLIST.JAVA
// Skeleton code for your EventList collection type.
//
import java.util.*;

class EventList {
    
    Random randseq;
    public Event[] head; 
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
	Event[] t1= new Event[1000];
    for(int i=0;i<1000;i++)
	{
	    t1[i]=new Event(posinf,null);
	    t1[i].height=1000;
	    t1[i].next=tail;
    }
    Event[] t2= new Event[1000];
    for(int i=0;i<1000;i++)
    {
    	t2[i]=new Event(posinf,null);
    	t2[i].height=1000;
    	t2[i].pre=head;
    }
    head=t1;
    tail=t2;
     
    }

    
    //
    // Add an Event to the list.
    //
    public void insert(Event e)
    {
	  int t,l;
	  Event[] x=head;
	  Event[] y=null;
	  t=randomHeight();
	  Event[] E=new Event[t];
	  e.height=t;
	   for(int j=0;j<t;j++)
		   E[j]=e;
	  
	  l=999;
	  while(l>=0)
	  {
		  y=x[l].next;
		  if(y[l].year<e.year)
			  x=y;
		  else
		  {
			  if (l<t)
			      {
					 x[l].next=E;
					 E[l].next=y;
				  }  
			  l--;
		  }
	  }
	  
    }

    
    //
    // Remove all Events in the list with the specified year.
    //
    public void remove(int year)
    {
      int l=head[0].height-1;
      Event[] u=head;
  	  Event[] v=null;
  	  Event[] temp=null; //
  	  while(l>=0)
  	  {
  		  v=u[l].next;
  		  if(v[l].year<=year)
  		  {
  		    if(v[l].year==year)
  		    {
  			    temp=v[l].next;
  			    u[l].next=temp;
  		    }
  		    else
  			    u=v;
  		  }
  		  else
  			  l--;
  	  }
  	  
    }
    
    //
    // Find all events with greatest year <= input year
    //
    public Event [] findMostRecent(int year)
    {
     int l=head[0].height-1;
     Event[] fmr=new Event[1000];
     int counter=0;
     Event[] w=head;
     Event[] z=null;
     Event temp=null;
     while(l>=0){
    	 z=w[l].next;
    	 if(z[l].next[l].year<=year && z[l].year==z[l].next[l].year){
    		 z=z[l].next;            //calculate all the events in same year
    		 counter++;              //calculate all the events in same year
    	 }
    	 if(z[l].next[l].year<=year && z[l].next!=null)
    		 w=z;
    	 else
    	 {
    		 if(l==0)
            {
    			  if(w[0].next[0].year>year)
    				  return null;
    		   int i=1;
    		   temp=w[0].next[0];
    		   fmr[0]=temp;
    		   while(counter>0)
    		   {
    		 	  temp=temp.next[0];
    		 	  fmr[i]=temp;
    			  i++;
    			  counter--;    			 
    		   }
    		   Event[] FMR=new Event[i];
    		   for(int j=0;j<i;j++)
    		   {
    			  FMR[j]=fmr[j+1];
    		   }
    		   return FMR; 			 
    	     }
    		 l--;
           }
     }return null;
    }
    
    
    //
    // Find all Events within the specific range of years (inclusive).
    //
    public Event [] findRange(int first, int last)
    {
    	Event F=null;
    	Event L=null;
    	int l=head[0].height-1;
    	Event[] x=head;
    	Event[] y=null;
    	while(l>=0)
    	{
    		y=x[l].next;
    		 if(y[l].year<first && y[l].year!=posinf)
   			  x=y;
   		  else
   		  {
   			if(l==0)
   			{
 				x=y;
 				y=x[0].next;
 				F=x[0];
 				int counter=1;
 				while(y[0].year<=last)
 				{
 					x[0]=y[0];           //start at the second element after first
 					y=y[0].next;
 					counter++;
 				}                     //end when y[0].year>last,that is x[0].next.year>last
 			    Event[] fR=new Event[counter];
 			    for(int i=0;i<counter;i++)
 			    {
 			    	fR[i]=F;
 			    	F=F.next[0];
 			    }
 			   return fR;
   			}
   			l--;
   		  }	  
    	}
	return null;
    }
}
