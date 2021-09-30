import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {
    public static int minCount(int[] arr, int n, int[] dp) {
        if (n==0) return 0;
        int answer = Integer.MAX_VALUE;
        for (int j : arr) {
            if (n - j >= 0) {
                int tempAnswer;
                if (dp[n - j] != -1) {
                    tempAnswer = dp[n - j];
                } else {
                    tempAnswer = minCount(arr, n - j, dp);
                }
                if (tempAnswer != Integer.MAX_VALUE && tempAnswer + 1 < answer) {
                    answer = tempAnswer + 1;
                }
            }
        }
        return dp[n] = answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How Many Coin You have? ");
        int size = sc.nextInt();
        System.out.println("Enter Your Coin's Values: ");
        int[] arr = new int[size];
        for (int i=0; i<size;i++){
            arr[i]= sc.nextInt();
        }
        System.out.println("What your Desire Value? ");
        int n = sc.nextInt();
        sc.close();

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        System.out.println("Minimum Coin Needed: "+minCount(arr, n, dp));
    }


}
