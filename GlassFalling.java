/**
 * Glass Falling
 */
public class GlassFalling {
	public int max(int a, int b) {
		if (a > b) {
			return a;
		}
		return b;
	}

	// Do not change the parameters!
	public int glassFallingRecur(int floors, int sheets) {
		if (floors <= 1 || sheets == 1)
			return floors;
		int min = floors;
		int res;
		for (int i = 1; i <= floors; i++) {
			res = max(glassFallingRecur(i - 1, sheets - 1), glassFallingRecur(floors - i, sheets));
			if (res < min)
				min = res;
		}
		return min + 1;
	}

	// Optional:
	// Pick whatever parameters you want to, just make sure to return an int.
	public int glassFallingMemoized(int floors, int sheets) {
		// Fill in here and change the return
		int arr[][] = new int[floors + 1][sheets + 1];
		for (int i = 0; i <= floors; i++) {
			for (int j = 0; j <= sheets; j++) {
				arr[i][j] = -1;
			}
		}
		return glassFallingMemoizedAux(floors, sheets, arr);
	}

	public int glassFallingMemoizedAux(int floors, int sheets, int[][] arr) {
		if (arr[floors][sheets] >= 0) {
			return arr[floors][sheets];
		}
		if (floors <= 1 || sheets == 1)
			return floors;

		int min = floors;
		int res;

		for (int i = 1; i <= floors; i++) {
			res = 1 + max(glassFallingMemoizedAux(i - 1, sheets - 1, arr),
					glassFallingMemoizedAux(floors - i, sheets, arr));
			if (res < min)
				min = res;
		}
		arr[floors][sheets] = min;
		return min;
	}

	// Do not change the parameters!
	public int glassFallingBottomUp(int floors, int sheets) {
		// Fill in here and change the return
		int glassFloor[][] = new int[sheets + 1][floors + 1];
		int res;
		int i, j, k;
		for (i = 1; i <= sheets; i++) {
			glassFloor[i][1] = 1;
			glassFloor[i][0] = 0;
		}
		for (j = 1; j <= floors; j++)
			glassFloor[1][j] = j;

		for (i = 2; i <= sheets; i++) {
			for (j = 2; j <= floors; j++) {
				glassFloor[i][j] = Integer.MAX_VALUE;
				for (k = 1; k <= j; k++) {
					res = 1 + max(glassFloor[i - 1][k - 1], glassFloor[i][j - k]);
					if (res < glassFloor[i][j])
						glassFloor[i][j] = res;
				}
			}
		}
		return glassFloor[sheets][floors];
	}


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
  }
}
