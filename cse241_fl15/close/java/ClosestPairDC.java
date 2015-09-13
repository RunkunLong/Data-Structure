public class ClosestPairDC {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    public static XYPoint out[]=new XYPoint[2];
    public static double min=INF;
    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "DC (x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    
    public static double findClosestPair(XYPoint pointsByX[], 
				       XYPoint pointsByY[],
				       boolean print)
    {
	 int nPoints = pointsByX.length;
	 double distance;
	//
	// Your code goes here!
	 if(nPoints<=3)
	 {
		 if (nPoints==1)
			{
				distance=INF;
	        }
		 else
		    {
			 for (int i=0;i<nPoints;i++)
				 for (int j=i+1;j<nPoints;j++)
				 {
					 distance=pointsByX[j].dist(pointsByX[i]);
				     if (distance< min)
				     {
				    	 min=distance;
				    	 out[0]=pointsByX[i];
				    	 out[1]=pointsByX[j];
				     }
		         }
		return min;// the foundamental case		
    }
	if(nPoints>3)
	{
		 int mid=(int)Math.ceil(nPoints/2)-1;
		 XYPoint XL[]= new XYPoint[mid];
		 	for(int i=0;i<=mid;i++)
		 		XL[i]=pointsByX[i];
		 XYPoint XR[]= new XYPoint[nPoints-mid];
		    for(int i=mid+1,j=0;i<nPoints-1;i++,j++)
		    	XR[j]=pointsByX[i];
		   
	}
		 
		// if (print)
			//   System.out.println("DC " + ...);
}
