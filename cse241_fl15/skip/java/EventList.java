//
// EVENTLIST.JAVA
// Skeleton code for your EventList collection type.
//
import java.util.*;

class EventList {
    
    Random randseq;
    public Event[] head=new Event[1]; 
    public Event[] tail=new Event[1];
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
	
	head[0]=new Event(neginf,null);
	head[0].height=1;
	head[0].next=tail;
   // for(int i=0;i<1000;i++)
	//{
	 //   head[i]=new Event(neginf,null);
	 //   head[i].height=1000;
	 //   head[i].next=tail;
    //}
    tail[0]=new Event(posinf,null);
    tail[0].height=1;
    tail[0].next=null;
    //for(int i=0;i<1000;i++)
    //{
    //	tail[i]=new Event(posinf,null);
    //	tail[i].height=1000;
 
   // }

     
    }

    
    //
    // Add an Event to the list.
    //
    public void insert(Event e)
    {
	  int t,l;	  	  
	  t=randomHeight();
	  Event[] E=new Event[t];
	  e.height=t;
	   for(int j=0;j<t;j++)
	   {
		   
		   E[j]=new Event(e.year,e.description);
		   E[j].height=t;
	   }
	   if(t>head[0].height)
	   {
		   int oldh=head[0].height;
		   int newh=head[0].height;
		   while(newh<t)
		   {
			   newh=newh*2;
		   }
		   
		  // System.out.println(newh);
		   Event[] temptail=new Event[newh];
	   		for(int j=0;j<newh;j++)
	   		{
	   			temptail[j]=new Event(posinf,null);
	   			temptail[j].height=newh;
	   			temptail[j].next=null;
	   		}
	   		tail = temptail;
		   Event[] temphead=new Event[newh];
		   		for(int i=0;i<newh;i++){
		   			if(i<oldh)
		   			{
		   				int ty=head[i].year;
		   				String ts=head[i].description;
		   				temphead[i]=new Event(ty,ts);
		   				temphead[i].height=newh;
		   				temphead[i].next=head[i].next;
		   			}
		   			else
		   			{
		   			    temphead[i]=new Event(neginf,null);
		   			    temphead[i].height=newh;
		   			    temphead[i].next=tail;
		   			    
		   			}
		   		}
		   
		   	head=new Event[newh];
		   	for(int s=0;s<newh;s++)
	   		{
	   			int tty=temphead[s].year;
	   			String tts=temphead[s].description;
	   			head[s]=new Event(tty,tts);
	   			head[s].next=temphead[s].next;
	   			head[s].height=newh;
	   		}		   		
	   }
	  Event[] x=head;
	  l=head[0].height-1;
	  while(l>=0)
	  {
		  Event[] y=x[l].next;
		  System.out.print(y[0].year);
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
  		if(v[l].year==year){
  			temp=v[l].next;
  			u[l].next=temp;
  		}
  		else 
  			if(v[l].year<year)
  			{
  			   u=v;
  		    }
  		    else
  		    {
  			  l--;
  		    }
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
    	 int counter=1;              //# of events happened in same year
    	 while((z[l].next!=null) && (z[l].year==z[l].next[l].year))
    	 {
    		 z=z[l].next;            //
    		 counter++;              
    	 }
    	 //System.out.println(counter);
    	 if((z[l].next!=null) && (z[l].next[l].year<=year))
    		 w=z;
    	 else
    	 {
    		 if(l==0)
            {
    			  if(w[0].next[0].year>year)
    				  return null;
    		   int i=0;          //counts for the nodes of same year
    		   temp=w[0].next[0];
    		   while(counter>0)
    		   {
    			   fmr[i]=temp;
    		 	   temp=temp.next[0];
       			   counter--;   
    			   i++;
    		   }
    		   Event[] FMR=new Event[i];
    		   for(int j=0;j<i;j++)
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
    	Event []S=null;                 //store the second element of my return sequence
    	Event []fr=new Event[1000];
    	int l=head[0].height-1;
    	Event[] x=head;
    	Event[] y=null;
    	while(l>=0)
    	{
    	  y=x[l].next;
    	  if(y[l].year<first)
 			    x=y;
 		      else
 		     	  if((y[l].year>=first) && (y[l].year<=last))
 		    	  {
 		    		  if(l==0)
 		     			{
 		    			fr[0]=y[0];
 		   				S=y[0].next;
 		   				int counter=1;
 		   				while(S[0].year<=last)
 		   				{
 		   					fr[counter]=S[0];           //start at the second element after first
 		   					S=S[0].next;
 		   					counter++;
 		   				}                     
 		   			    Event[] FR=new Event[counter];
 		   			    for(int i=0;i<counter;i++)
 		   			    {
 		   			    	int j=fr[i].year;
 		   			    	String s=fr[i].description;
 		   			    	FR[i]=new Event(j,s);
 		   			    }
 		   			    return FR;
 		     			}
 		     		l--;
 		    	  }
 		     	  else
 		   		        l--;	
    		      
    	}
	return null;
    }
}
