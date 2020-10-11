import java.util.ArrayList;

public class KMP {

    private String text;
    private String pattern;

    public KMP(String pattern, String text){
        this.pattern = pattern;
        this.text = text;
    }

    public Integer[] KMPSearch()    {
        int M = this.pattern.length();
        int N = this.text.length();

        int lps[] = new int[M];
        int j = 0;

        ArrayList<Integer> ret = new ArrayList<>();
        computeLPSArray(this.pattern, M, lps);

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

    private void computeLPSArray(String pat, int M, int lps[])
    {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
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
}
