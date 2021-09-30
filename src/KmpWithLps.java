import java.util.Scanner;

public class KmpWithLps {

    public static int[] lpsTable(String pattern, int lengthOfPattern) {
        int i = 0;
        int j = i + 1;
        int lps[] = new int[lengthOfPattern];
        // lps[0] is always 0
        lps[0] = 0;
//        calculate lsp length for every index of lsp table
        while (j < lengthOfPattern) {
//            if i & j matched then increment i & j by one
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[j] = i + 1;
                j++;
                i++;
            }
//            otherwise, if i not equal 0 set i with the value of lsp index i-1,
//            except  set lsp[j] with i value and increment j by one
            else {
                if (i != 0) {
                    i = lps[i - 1];
                }
                else
                {
                    lps[j] = i;
                    j++;
                }
            }
        }
        return lps;
    }
    public void search( String text, String pattern)
    {
        int lengthOfPattern = pattern.length();
        int lengthOfText = text.length();
        int[] lps = lpsTable(pattern, lengthOfPattern);
        int i = 0; //   index for text
        int j = 0; //   index for pattern
        while (i < lengthOfText) {
//            compare pattern and text till those are matched, with increment i & j by one
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
//            if j  is equal  length of pattern that means pattern found-> print it's staring index using formula(i-j),
            // set j = value of j-1 index's of lps table
            if (j == lengthOfPattern) {
                System.out.println("Found pattern "
                        + "at index " + (i - j));
                j = lps[j - 1];
            }
            // if patter and text value found mismatched set j equal value of j-1 index's of lps table if j!=0,
            // else set i equal i+1
            else if (i < lengthOfText && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Text: ");
        String text = sc.nextLine();
        System.out.println("Enter a Pattern: ");
        String pattern = sc.nextLine();
        KmpWithLps kmpWithLps = new KmpWithLps();
        kmpWithLps.search(text, pattern);
    }

}
