import java.util.ArrayList;

/**
 * A Knut-Morris-Pratt class implementation
 *
 * @author Vaastav Arora
 * @version January 9, 2021
 */
public class KMPSolution {

    private String text;        //Input Target String
    private String pattern;     //Input Pattern String
    private int[] lps;          //Longest [prefix that is also suffix] array

    /**
     * KMP class constructor
     * @param pattern Input Pattern String
     * @param text  Input Target String
     */
    public KMPSolution(String pattern, String text){
        this.pattern = pattern;
        this.text = text;
        lps = new int[this.pattern.length()];
    }

    /**
     * KMP preprocessing method. Constructs LPS array.
     */
    public void computeLPSArray() {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < this.pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else
            {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    /**
     * KMP search method. Returns integer array of all pattern match indexes
     * @return integer array of all pattern match indexes
     */
    public Integer[] KMPSearch()    {
        int M = this.pattern.length();
        int N = this.text.length();
        int j = 0;

        ArrayList<Integer> ret = new ArrayList<>();

        int i = 0;
        while (i < N) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                ret.add(i-j);
                j = lps[j - 1];
            }

            else if (i < N && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }

        return (Integer[])ret.toArray();
    }

}
