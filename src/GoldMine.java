import java.util.Arrays;
import java.util.Scanner;

public class GoldMine {

    private static int maxGold(int[][] gold_mine, int row, int col, int[][] dp) {
        for (int i = col - 1; i >= 0; i--) {
            for (int j = 0; j < row; j++) {

                int prevValue;
                int prevUpValue;
                int prevDownValue;
                if (i == col - 1) {
                    prevValue = 0;
                } else prevValue = dp[j][i + 1];
                if (j == 0 || i == col - 1) {
                    prevUpValue = 0;
                } else prevUpValue = dp[j - 1][i + 1];
                if (j == row - 1 || i == col - 1) {
                    prevDownValue = 0;
                } else prevDownValue = dp[j + 1][i + 1];

                dp[j][i] = gold_mine[j][i]
                        + Math.max(prevValue, Math.max(prevUpValue,
                        prevDownValue));

            }
        }
        int maxValue = 0;
        for (int i = 1; i < row; i++)
            maxValue = Math.max(maxValue, dp[i][0]);

        return maxValue;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("What is the row & column number? ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] gold_mine = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("Enter the value of (%dx%d): %n", i, j);
                gold_mine[i][j] = sc.nextInt();
            }
        }

        int[][] dp = new int[row][col];
        System.out.print("Maximum Gold: "+maxGold(gold_mine, row, col, dp));
    }


}
