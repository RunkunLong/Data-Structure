public class ClosestPairDC {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    public static XYPoint out[]=new XYPoint[2];
    public static double min= INF;
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
    
    public static void findClosestPair(XYPoint pointsByX[], XYPoint pointsByY[],boolean print)
    {
    	 int nPoints = pointsByX.length;
    	 double distance;
    	 min= ClosestPair(pointsByX, pointsByY,nPoints);
    	 if (print) 
    	 {
 			System.out.println("DC " + out[0] +" "+ out[1] +" "+ min);
 		 }

    	 
    }

    public static double ClosestPair(XYPoint pointsByX[], XYPoint pointsByY[], int n)
    {
    	int nPoints = pointsByX.length;	
    	double distance=0;
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
    				}
	    return min;// the foundamental case		
       }
    		int mid=(int)Math.ceil(nPoints/2)-1;
    		XYPoint XL[]= new XYPoint[mid];
    		for(int i=0;i<=mid;i++)
    			XL[i]=pointsByX[i];
    		XYPoint XR[]= new XYPoint[nPoints-mid];
    		for(int i=mid+1,j=0;i<nPoints-1;i++,j++)
    			XR[j]=pointsByX[i];
    		XYPoint YL[]=new XYPoint[mid];
    		XYPoint YR[]=new XYPoint[nPoints-mid];
    		for(int o=0,p=0,q=0;o<nPoints;o++)
    		{
    			if(pointsByY[o].isLeftOf(pointsByX[mid+1]))
    			{
    				YL[p]=pointsByX[o];
    				p++;
    			}
    			else
    			{
    				YR[q]=pointsByX[o];
    				q++;
    			}
    		}
            double disL=ClosestPair(XL,YL,XL.length);
            double disR=ClosestPair(XR,YR,XR.length);
	        double dismin=Math.min(disL, disR);
	        	min=dismin;
            XYPoint midPoint=pointsByX[mid];
            XYPoint [] TEMP=new XYPoint [nPoints];
            int temp=0;
            for(int r=0;r<nPoints;r++)
            {
            	if(Math.abs(pointsByY[r].x-midPoint.x)<dismin)
            	{
            		TEMP[temp]=pointsByY[r];
            		temp++;
            	}
            }
            int s;
            for(int j=0;j<TEMP.length-2;j++)
            {
            	s=j+1;
                while((s<TEMP.length-1)&&(Math.abs(TEMP[s].y-TEMP[j].y)<dismin))
                {
                	double d=TEMP[s].dist(TEMP[j]);
                	if(d<dismin)
                	{
                		min=d;
                		out[0]=TEMP[j];
                		out[1]=TEMP[s];
                	}  
                	 s++;
                }
             }
            return min;
    	}
}
