public class ClosestPairDC {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;

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
    static XYPoint a=null;
    static XYPoint b=null;
    public static double mindist=INF;

    //
    
    public static void findClosestPair(XYPoint pointsByX[], 
				       XYPoint pointsByY[],
				       boolean print)
    {
	// int nPoints = pointsByX.length;
    	
 	// if (print)
 	//   System.out.println("DC " + ...);
    	int nPoints=pointsByX.length;
        double lrdist=INF;
        double distance_now=INF;
        if(nPoints==1){
     	   distance_now= INF;
     	   return;
        }
        if(nPoints==2){
     	  distance_now=pointsByX[0].dist(pointsByX[1]);
     	  if(distance_now<mindist){
     	  mindist=distance_now;
     	  a=pointsByX[0];
     	  b=pointsByX[1];
        }
     	  return;
        }
        int mid=0;
        mid=(int)Math.ceil((double)nPoints/2)-1;
        XYPoint[] PointsXL=new XYPoint[mid];
        XYPoint[] PointsXR=new XYPoint[nPoints-mid];
        for(int i=0;i<mid;i++){
     	   PointsXL[i]=pointsByX[i];
        }
        for(int i=mid,j=0;i<nPoints;i++){
     	   PointsXR[j]=pointsByX[i];
     	   j++;
        }
        XYPoint[] PointsYL=new XYPoint[mid];
        XYPoint[] PointsYR=new XYPoint[nPoints-mid];
        
        for(int i=0,j=0,k=0;i<nPoints;i++){
     	   if(pointsByY[i].isLeftOf(pointsByX[mid])){
     		   PointsYL[j]=pointsByY[i];
     		   j++;
     	   }
     	   else{
     		   PointsYR[k]=pointsByY[i];
     		   k++;
     	   }
        }
        findClosestPair(PointsXL,PointsYL,false);
        findClosestPair(PointsXR,PointsYR,false);
        lrdist=mindist;
        XYPoint[] yStrip=new XYPoint[nPoints];
        XYPoint midpoint=pointsByX[mid];
        for(int i=0,k=0;i<nPoints;i++){
     	   if(Math.abs(pointsByY[i].x-midpoint.x)<lrdist){
     		   yStrip[k]=pointsByY[i];
        		   k++;
     	   }
        }
        
        for(int i=0,j=0;i<yStrip.length-1;i++){
     	   j=i+1;
     	   while(j<yStrip.length){
     		   if(yStrip[j]==null)
     			   break;
     		   if(Math.abs(yStrip[i].y-yStrip[j].y)<lrdist){
     			   distance_now=yStrip[i].dist(yStrip[j]);
     			   if(distance_now<mindist){
     				   mindist=distance_now;
     				   a=yStrip[i];
     				   b=yStrip[j];
     				   
     			   }
     		   }
     		   j++;
     	   }
        }
        if(print){
     	   System.out.println("DC "+a.toString()+" "+b.toString()+" "+mindist);
     	   
        }

      
	
	//
	// Your code goes here!
	//
       

	// if (print)
	//   System.out.println("DC " + ...);
    }
}
