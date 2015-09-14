public class ClosestPairNaive {
    
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
    //
    public static double minDis=INF;
     static XYPoint x=null;
     static XYPoint y=null;
    public static void findClosestPair(XYPoint pointsByX[], 
				       XYPoint pointsByY[],
				       boolean print)
    {
	// int nPoints = pointsByX.length;
	
	//
	// Your code goes here!
	//
        double distance=INF,irDis=INF;
        int nPoints= pointsByX.length;
        if(nPoints==1){
        	distance= INF;
        	return;
        }
        if(nPoints==2){
        	distance=pointsByX[0].dist(pointsByX[1]);
        	if(distance<minDis){
        		minDis=distance;
        		x=pointsByX[0];
        		y=pointsByX[1];
        		}
        	return;
        }
        double q=(double)nPoints;
        int mid = (int) Math.ceil(q/2)-1;
        XYPoint midPoint=pointsByX[mid];
        XYPoint midPointNext=pointsByX[mid+1];
        XYPoint[] XL = new XYPoint[mid+1];
        for(int i=0;i<=mid;i++){
     	     XL[i]=new XYPoint();
     	     XL[i]=pointsByX[i];
        }
        XYPoint XR[]=new XYPoint[nPoints-mid-1];
        for(int i=mid+1,j=0;i<=nPoints-1;i++,j++){
     	     XR[j]=new XYPoint();
     	     XR[j]=pointsByX[i];
        }
        XYPoint YL[]=new XYPoint[mid+1];
		XYPoint YR[]=new XYPoint[nPoints-mid-1];
		for(int o=0,p=0,q1=0;o<nPoints;o++)
		{
			if(pointsByY[o].isLeftOf(pointsByX[mid+1]))
			{
				YL[p]=new XYPoint();
				YL[p]=pointsByX[o];
				p++;
			}
			else
			{
				YR[q1]=new XYPoint();
				YR[q1]=pointsByX[o];
				q1++;
			}
		}
        for(int i=0;i<=mid;i++){
     	     YL[i]=new XYPoint();
     	     YL[i]=pointsByY[i];
        }
        for(int i=mid+1,j=0;i<=nPoints-1;i++,j++){
     	     YR[j]=new XYPoint();
     	     YR[j]=pointsByY[i];
        }
        findClosestPair(XL,YL,false);
        findClosestPair(XR,YR,false);

        	irDis=minDis;
        XYPoint yStrip[]=new XYPoint[nPoints];
        int l=0;
        for(int k=0;k<nPoints;k++){
        	if((pointsByY[k].x-midPoint.x<irDis)&&(pointsByY[k].x-midPoint.x>(0-irDis))){
        		yStrip[l]=new XYPoint();
        		yStrip[l]=pointsByY[k];
        		l++;
        	}
        }
        for(int j=0;j<yStrip.length-1;j++){
        	int b=j+1;
        	while(b<yStrip.length){
        		if(yStrip[b]==null)
                      break;
        		if((yStrip[b].y-yStrip[j].y<irDis)&&(yStrip[b].y-yStrip[j].y>(0-irDis))){
        		    double d=yStrip[b].dist(yStrip[j]);
        		    if(d<minDis){
        			    minDis=d;
        			    x=yStrip[b];
        			    y=yStrip[j];
        		    }
        		}
        		b++;        		
        	}
        }
       if(print)
     		System.out.println("DC "+x.toString()+" "+y.toString()+" "+minDis );
	// if (print)
	//   System.out.println("DC " + ...);
    }

}

