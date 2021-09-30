import java.util.Arrays;
import java.util.Scanner;

public class MatrixChainMultiplication {
   private final int[][] dp;
    int [] matrixIndex;


    public MatrixChainMultiplication(int[] matrixIndex) {
        this.matrixIndex = matrixIndex;
        this.dp = new int[matrixIndex.length][matrixIndex.length];
        for (int[] i : this.dp) {
            Arrays.fill(i, -1);
        }
    }
    private int minimumMultiplication(int[] arr, int[][] dp, int i, int j){
        //        base case
        if (i == j) {
            return 0;
        }
//        already calculated
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = 1; k < j; k++) {
            if (i <= k) {
                dp[i][j] = Math.min(dp[i][j], minimumMultiplication(arr, dp, i, k) + minimumMultiplication(arr, dp, k + 1, j) + arr[i - 1] * arr[k] * arr[j]);
            }
        }
        return dp[i][j];
    }

    private void solve() {
        int minimum = minimumMultiplication(this.matrixIndex, this.dp,1,this.matrixIndex.length-1);
        System.out.println(minimum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How Many matrix you want to multiply:");
        int matrixSize = sc.nextInt();
        matrixSize += 1;
        int[] matrixIndex = new int[matrixSize];
        System.out.println("Enter the matrices dimension like this:There are 4 matrices of dimensions 10x30, 30x40," +
                " 40x10 and 10x5 then you have input 10 30 40 10 5:");
        for (int i = 0; i < matrixSize; i++) {
            matrixIndex[i] = sc.nextInt();
        }
//        int[][] dp = new int[matrixSize][matrixSize];

        MatrixChainMultiplication matrixChainMultiplication = new MatrixChainMultiplication(matrixIndex);
        matrixChainMultiplication.solve();

    }


}
