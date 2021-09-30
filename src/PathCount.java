import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PathCount {
    private final int[][] dp; // storage
    int row;
    int col;

    public PathCount(int row, int col) {
        this.dp = new int[row][col];
        this.row = row;
        this.col = col;
    }

    public void solve() {
        //Base Case
        for (int i = 0; i < col; i++)
            dp[0][i] = 1;
        //Base Case
        for (int i = 0; i < row; i++)
            dp[i][0] = 1;


        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
            }
        }
        System.out.println("Total Number of Path: " + dp[row-1][col-1]); // last element of the table is the final answer
    }


    // Another way: using recursion and dp
    public static int pathCounter(int row, int col, int[][] dp) {

        //Base Case
        if (row == 0 || col == 0)
            return 1;
        int prevRowValue;
        int prevColValue;
        if (dp[row - 1][col] > 0) {
            prevRowValue = dp[row - 1][col];
        } else {
            prevRowValue = pathCounter(row - 1, col, dp);
        }
        if (dp[row][col - 1] > 0) {
            prevColValue = dp[row][col - 1];
        } else {
            prevColValue = pathCounter(row, col - 1, dp);
        }
        return dp[row][col] = prevRowValue + prevColValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the row number? ");
        int row = sc.nextInt();
        System.out.println("What is the Column number? ");
        int col = sc.nextInt();
        sc.close();

/*        long startTime = System.nanoTime();
        PathCount pathCount = new PathCount(row, col);
        pathCount.solve();

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);*/

//        Using Recursion
        long startTime2 = System.nanoTime();
        int[][] dp = new int[row][col];
        //Base Case
        for (int i = 0; i < col; i++)
            dp[0][i] = 1;
        //Base Case
        for (int i = 0; i < row; i++)
            dp[i][0] = 1;
        System.out.println(pathCounter(row-1,col-1,dp));

        long stopTime2 = System.nanoTime();
        System.out.println(stopTime2 - startTime2);
    }
}

