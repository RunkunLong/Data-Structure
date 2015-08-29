public class MyThing {
    
    public void count(int x) 
    {
	if(x==0)
	{System.exit(-1);}
	else
	{
		int j=0;
		for(int k=1;k+1<x;k=k+2)
			{
		    System.out.println(k+"...");
		    j=k;
			}
	  System.out.println(j+"!");
		}
    // Print every odd number between 1 and x (inclusive) in the form
	// 1...
        // 3...
	// <etc>
	// x!
    }
}
