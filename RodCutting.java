/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {
	public int max(int a, int b) {
		if (a > b) {
			return a;
		}
		return b;
	}
  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
	  int arr[]=new int[rodLength+1];
	  for(int i=0;i<rodLength+1;i++){
		  arr[i]=-1;
	  }  
	  
return rodCuttingRecurAux(rodLength,lengthPrices,arr);
  }

  public int rodCuttingRecurAux(int rodLength, int[] lengthPrices, int arr []){
	   
	    int q;
	    
	    if (arr[rodLength]>=0){
	    	return arr[rodLength];
	    }
	    if (rodLength==0){
	    	q=0;
	    }
	    else{
	    	q=-1;
	    	  for(int i=0;i<rodLength;i++){
	    	    	q=max(q,lengthPrices[i]+rodCuttingRecurAux(rodLength-(i+1), lengthPrices,arr));
	    	    }
	    }
	  arr[rodLength]=q;
		  return q;
  }
  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
	  int arr[]= new int[rodLength+1];
	  int q;
	  arr[0]=0;
	  for(int j=1;j<rodLength+1;j++){
		  q=-1;
		  for(int i=0;i<j;i++){
			  q=max(q,lengthPrices[i]+arr[j-(i+1)]);
			  
		  }
		  arr[j]=q;
		
	  }
    return arr[rodLength];
  }



  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
