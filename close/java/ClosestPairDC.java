public class ClosestPairDC {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    public static int outx1,outx2,outy1,outy2;
    public static double min=INF;
  
    public static double ClosestPair(XYPoint pointsByX[], int nPoints){
    	
    	double distance;
    	
   	  
    	//exit
    	if (nPoints <= 3) {

			for (int i = 0; i < nPoints - 1; i++) {
				
				for (int j = i + 1; j < nPoints; j++) {
					
					distance = pointsByX[j].dist(pointsByX[i]);
					if (distance <= min) {
						min = distance;
						outx1=pointsByX[i].x;
						outx2=pointsByX[j].x;
						outy1=pointsByX[i].y;
						outy2=pointsByX[j].y;
							
					}
				}
			}
			return min;
		}

   /*	 if (nPoints==1){
   		
		distance=Double.POSITIVE_INFINITY;
		 return distance;
	 }
	if (nPoints==2){
		
		distance = pointsByX[0].dist(pointsByX[1]);
			x1out=pointsByX[0].x;
			x2out=pointsByX[1].x;
			System.out.println(pointsByX[0].x+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println(pointsByX[0].y+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			y1out=pointsByX[0].y;
			y2out=pointsByX[1].y;
		return  distance;
		
	}*/
    	
    	if(nPoints>3){
//mid
    	int mid= (int)Math.ceil(nPoints/2);
    	
    
// assignment Xarray	
    	XYPoint XL[] = new XYPoint[mid];
    	for(int i=0; i<=mid-1;i++){
    		XL[i]=pointsByX[i];
    	}
    	XYPoint XR[] = new XYPoint[nPoints-mid];
    	for(int i=mid;i<=nPoints-1;i++){
    		XR[i-mid]=pointsByX[i];
    	}
    	
    	
    	//iteration
    	double distL = ClosestPair(XL,XL.length);
    	double distR = ClosestPair(XR,XR.length);
    	
    	
    	double lrDist=(distL<distR?distL:distR);
    	
    
    	 for(int j=0;j<=mid;j++){
    		 
   		   if(Math.abs(pointsByX[mid].x-pointsByX[j].x)<lrDist){
   			  
   		   for(int k=mid;k<pointsByX.length;k++){
   			   
   			if(pointsByX[k]!=pointsByX[j]&&Math.abs(pointsByX[k].x-pointsByX[mid].x)<Math.abs(lrDist-pointsByX[mid].x)&&Math.abs(pointsByX[k].y-pointsByX[mid].y)<lrDist){
   				
   				double ddd=pointsByX[j].dist(pointsByX[k]);
   				
   				
   				if(ddd<min){
   					min=ddd;
   			
   					outx1=pointsByX[j].x;
   	   				outx2=pointsByX[k].x;
   	   				outy1=pointsByX[j].y;
   	   				outy2=pointsByX[k].y;
   				}
   				
   				
   			
   			}
   		   }
   		   }
   		 
   	   }
    	}
    	
    return min;
    }
    
    
  
    
    //main method
    public static void findClosestPair(XYPoint pointsByX[], 
				       XYPoint pointsByY[],
				       boolean print)
    {
    	double dout = ClosestPair(pointsByX,pointsByX.length);
    	
	if (print){
	   System.out.println("DC "+"("+outx1+","+outy1+")"+","+"("+outx2+","+outy2+")"+","+dout );
    }
    }
}