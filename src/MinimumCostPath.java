import java.util.Scanner;

public class MinimumCostPath {
    int row;
    int col;
    int[][] costArray;

    public MinimumCostPath(int[][] costArray, int row, int col) {
        this.costArray = costArray;
        this.row = row;
        this.col = col;

    }

    private static int min(int x, int y) {
        return Math.min(x, y);
    }


    public void solve() {
        //Base Case
        for (int i = 1; i < col; i++) {
            costArray[0][i] = costArray[0][i - 1] + costArray[0][i];
        }

        //Base Case
        for (int i = 1; i < row; i++) {
            costArray[i][0] = costArray[i - 1][0] + costArray[i][0];
        }


        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                costArray[r][c] = costArray[r][c] + Math.min(costArray[r - 1][c], costArray[r][c - 1]);
                System.out.println(r + "x" + c);
            }
        }
        System.out.println("Minimum Cost: " + costArray[row - 1][col - 1]); // last element of the table is the final answer
    }

    // Another way: using recursion and dp
    public static int minCost(int row, int col, int[][] dp, int[][] costArray) {

        //Base Case
        if (row == 0 || col == 0)
            return dp[row][col];

        int prevRowValue;
        int prevColValue;
        if (dp[row - 1][col] > 0) {
            prevRowValue = dp[row - 1][col];
        } else {
            prevRowValue = minCost(row - 1, col, dp, costArray);
        }
        if (dp[row][col - 1] > 0) {
            prevColValue = dp[row][col - 1];
        } else {
            prevColValue = minCost(row, col - 1, dp, costArray);
        }
        return dp[row][col] = costArray[row][col] + Math.min(prevRowValue, prevColValue);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the row column number? ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] costArray = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("Enter the value of (%dx%d): %n", i, j);
                costArray[i][j] = sc.nextInt();
            }
        }
        System.out.println("What is the target row and col number? ");
        int targetRow = sc.nextInt();
        int targetCol = sc.nextInt();
        sc.close();

     /*
//      long startTime2 = System.nanoTime();
        MinimumCostPath minimumCostPath = new MinimumCostPath(costArray, targetRow, targetCol);
        minimumCostPath.solve();
//        long stopTime2 = System.nanoTime();
//        System.out.println(stopTime2 - startTime2);
*/

        long startTime2 = System.nanoTime();
        int[][] dp = new int[row][col];
        dp[0][0] = costArray[0][0];
        //Base Case
        for (int i = 1; i < col; i++) {
            costArray[0][i] = costArray[0][i - 1] + costArray[0][i];
            dp[0][i] = costArray[0][i];
        }

        //Base Case
        for (int i = 1; i < row; i++) {
            costArray[i][0] = costArray[i - 1][0] + costArray[i][0];
            dp[i][0] = costArray[i][0];
        }

        System.out.println(minCost(targetRow - 1, targetCol - 1, dp, costArray));
        long stopTime2 = System.nanoTime();
        System.out.println(stopTime2 - startTime2);
    }
}
