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
    
    public static void findClosestPair(XYPoint pointsByX[], 
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
				return;
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
		return;
	
	// if (print)
	//   System.out.println("DC " + ...);
    }
}
