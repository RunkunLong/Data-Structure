//
// EVENTLIST.JAVA
// Skeleton code for your EventList collection type.
//
import java.util.*;

class EventList {
    
    Random randseq;
    public Event[] head=new Event[1000]; 
    public Event[] tail=new Event[1000];
    public int neginf= Integer.MIN_VALUE;
    public int posinf= Integer.MAX_VALUE;
    
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
	
    for(int i=0;i<1000;i++)
	{
	    head[i]=new Event(neginf,null);
	    head[i].height=1000;
	    head[i].next=tail;
    }
    
    for(int i=0;i<1000;i++)
    {
    	tail[i]=new Event(posinf,null);
    	tail[i].height=1000;
 
    }

     
    }

    
    //
    // Add an Event to the list.
    //
    public void insert(Event e)
    {
	  int t,l;
	  Event[] x=head;
	  
	  t=randomHeight();
	  Event[] E=new Event[t];
	  e.height=t;
	   for(int j=0;j<t;j++)
	   {
		   
		   E[j]=new Event(e.year,e.description);
		   E[j].height=t;
	   }
	  l=999;
	  while(l>=0)
	  {
		  Event[] y=x[l].next;
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
     Event[] w=head;
     Event[] z=null;
     Event temp=null;
     while(l>=0){
    	 z=w[l].next;
    	 if((z[l].year==z[l].next[l].year) && (z[l].next!=null))
    	 {
    		 z=z[l].next;            //calculate all the events in same year
    	 }
    	 //System.out.println(counter);
    	 if((z[l].next[l].year<=year) && (z[l].next!=null))
    		 w=z;
    	 else
    	 {
    		 if(l==0)
            {
    			  if(w[0].next[0].year>year)
    				  return null;
    		   int counter=1;          //counts for the nodes of same year
    		   temp=w[0].next[0];
    		   fmr[0]=temp;
    		   while(temp.year==temp.next[0].year)
    		   {
    		 	  temp=temp.next[0];
    		 	  fmr[counter]=temp;
    			  counter++;   			 
    		   }
    		   Event[] FMR=new Event[counter];
    		   for(int j=0;j<counter;j++)
    		   {
    			  int ty=fmr[j].year;
    			  String td=fmr[j].description;
    			  FMR[j]=new Event(ty,td);
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
