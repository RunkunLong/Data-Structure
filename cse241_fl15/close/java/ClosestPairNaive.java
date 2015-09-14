public class ClosestPairNaive {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    
    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "NAIVE (x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    
    public static void findClosestPair(XYPoint points[], boolean print)
    {
     
	 int nPoints = points.length;
	 double mindist=INF;
	 int i=0;
	 XYPoint a=null;
	 XYPoint b=null;
	 while(i<nPoints-2){
		 int j=i+1;
		 while(j<nPoints-1){
			 double distnow=points[i].dist(points[j]);
			 if(distnow<mindist){
				 mindist=distnow;
				 a=points[i];
				 b=points[j];
			 }
			 j=j+1;
		 }
		 i++;
	 }
	 
	 
	//
	// Your code goes here!
	//
	print=true;
	if(print){
		System.out.println("Naive"+a.toString()+b.toString()+mindist);
	}
	// if (print)
	//   System.out.println("NAIVE " + ...);
    }
}

