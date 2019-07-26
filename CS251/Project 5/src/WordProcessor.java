public class WordProcessor {

    private Node wordTrie;

    class Node {
        char c;
        Node[] alpha = new Node[26];
        boolean isEnd = false;

        Node(char ca) {
            c = ca;
        }

        Node() {

        }

        void add(Node n) {
            if (alpha[n.c - 97] != null) alpha[n.c - 97] = n;
        }
    }

    public WordProcessor() {

        wordTrie = new Node();
    }

    public boolean addWord(String s) {

        Node[] here = wordTrie.alpha;
        char[] temp = s.toLowerCase().toCharArray(); // lower case IMP

        for (int j = 0; j < temp.length; j++) {
            if (here[temp[j] - 97] == null) {
                Node n = new Node(temp[j]);
                here[temp[j] - 97] = n;
            }
            if (j == temp.length - 1 && !here[temp[j] - 97].isEnd) {
                here[temp[j] - 97].isEnd = true;
                return true;
            }
            here = here[temp[j] - 97].alpha;
        }

        return false;
    }

    public void addAllWords(String[] s) {

        for (String i : s) {
            addWord(i);
        }
    }

    public boolean wordSearch(String s) {

        Node[] here = wordTrie.alpha;
        s = s.toLowerCase();
        char c;
        for(int i = 0;i< s.length() - 1;i++){
            c = s.charAt(i);
            if( here[(int)c-97] != null){
                here = here[(int)c-97].alpha;
            }else return false;
        }

        c = s.charAt(s.length()-1);
        if(here[(int)c -97] != null && here[(int)c -97].isEnd) return true; //isEnd check important
        else return false;

    }

    public static void main(String[] args) {
        WordProcessor wp = new WordProcessor();
        wp.addWord("Mango");
        System.out.println(wp.wordSearch("Mango"));
    }
}
