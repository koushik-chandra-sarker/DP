import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SubSetSum {

    private final boolean[][] dp; // storage
    int[] set;
    int summation;

    public SubSetSum(int[] set, int summation) {
        this.dp = new boolean[set.length + 1][summation + 1];
        this.set = set;
        this.summation = summation;
    }

    public void solve() {
        /*initialise dp[0][i] = false; i start from 1; because when summation is not 0 set is empty then
        answer will false*/
        for (int i = 1; i < summation + 1; i++)
            dp[0][i] = false;
        /*initialise dp[i][0] = ture; i start from 0; because when summation is 0 then
        answer will true*/
        for (int i = 0; i < set.length + 1; i++)
            dp[i][0] = true;


        // check the subset is available or not using dp
        for (int row = 1; row < set.length + 1; row++) { // row = index of set
            for (int col = 1; col < summation + 1; col++) { //col=sum-> index wise
                if (col < set[row - 1]) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    if (dp[row - 1][col])
                        dp[row][col] = dp[row - 1][col];
                    else
                        dp[row][col] = dp[row - 1][col - set[row - 1]];
                }
            }
        }
        System.out.println("Subset availability: " + dp[set.length][summation]); // last element of the table is the final answer
    }

    public ArrayList<Integer> showSubset() {
        int lastRow = set.length;
        int lastCol = summation;
        ArrayList<Integer> subset = new ArrayList<>();
        while (lastCol > 0 || lastRow > 0) {
            if (dp[lastRow][lastCol] == dp[lastRow - 1][lastCol]) {
                lastRow -= 1;
            } else {
                subset.add(set[lastRow - 1]);
                lastCol -= set[lastRow - 1];
            }
        }
        return subset;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the size of your Set: ");
        int size = sc.nextInt();
        System.out.println("Enter Your Set's Values: ");
        int[] set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = sc.nextInt();
        }
        System.out.println("What your Desire Summation? ");
        int sum = sc.nextInt();
        sc.close();

        SubSetSum subSetSum = new SubSetSum(set, sum);
        subSetSum.solve();
        System.out.println("Subset: " + Arrays.toString(subSetSum.showSubset().toArray()));
    }
}
