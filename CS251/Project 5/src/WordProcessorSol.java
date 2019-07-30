import java.util.LinkedList;
import java.util.List;

/**
 * A Simple Word Processing class
 *
 * <p>Purdue University -- CS25100 -- Fall 2019 -- Tries</p>
 *
 * @auther Vaastav Arora, arora74@purdue.edu
 * @version July 30, 2019
 */
public class WordProcessorSol {

    private Node wordTrie;  //Root Node of the Trie

    /**
     * A simple Node class representing each
     * individual node of the trie
     */
    class Node {

        char c;
        Node left, equal, right;
        boolean isEnd = false;

        /**
         * Constructor for Node class
         * @param ca Character assigned to the node
         */
        public Node(char ca) {
            c = ca;

            left = null;
            equal = null;
            right = null;

        }
    }

    /**
     * Defualt constructor for the WordProcessor class
     */
    public WordProcessorSol() {

        wordTrie = null;
    }

    public Node getWordTrie() {
        return wordTrie;
    }

    /**
     * Method to add a string to the trie
     * @param s String to be added
     */
    public void addWord(String s) {
        wordTrie = addChars(wordTrie, s, 0);
    }

    /**
     * Helper method to add a string to the trie
     * @param curr Current node of traversal
     * @param s String to be added
     * @param index index of the string currently at
     * @return Node where the current index character in the string was added
     */
    private Node addChars(Node curr, String s, int index) {

        if (curr == null) curr = new Node(s.charAt(index));

        if (s.charAt(index) < curr.c) curr.left = addChars(curr.left, s, index);

        else if (s.charAt(index) > curr.c) curr.right = addChars(curr.right, s, index);

        else {

            if (index + 1 < s.length()) curr.equal = addChars(curr.equal, s, ++index);
            else curr.isEnd = true;
        }

        return curr;
    }

    /**
     * Method to add an array of strings to the trie
     * @param s Array of strings to be added
     */
    public void addAllWords(String[] s) {

        for (String i : s) {
            addWord(i);
        }
    }

    /**
     * Method to check of a string exists in the trie
     * @param s String to be checked
     * @return true if string exists
     */
    public boolean wordSearch(String s) {

        return wordSearchChar(wordTrie, s, 0);
    }

    /**
     * Helper method to search a string in the trie
     * @param curr Current Node of traversal
     * @param s String being searched for
     * @param index index of the string currently at
     * @return returns true if the string exists
     */
    private boolean wordSearchChar(Node curr, String s, int index) {

        if (curr == null) return false;

        if (s.charAt(index) < curr.c) return wordSearchChar(curr.left, s, index);

        else if (s.charAt(index) > curr.c) return wordSearchChar(curr.right, s, index);

        else {

            if (index == s.length() - 1) return curr.isEnd;

            return wordSearchChar(curr.equal, s, ++index);
        }
    }

    /**
     * Method to check if the trie if empty
     * (No stirngs added yet)
     * @return
     */
    public boolean isEmpty()
    {
        return wordTrie == null;
    }

    /**
     * Method to empty the trie
     */
    public void clear()
    {
        wordTrie = null;
    }

    /**
     * Helper method to traverse and
     * print out the nodes of the trie
     */
    public void traverseTrie() {
        String s = "";
        traverseTrieUtil(wordTrie, s);
    }

    /**
     * Helper method to traverse the trie
     * @param curr Current node of traversal
     * @param s String formed so far during traversal
     */
    private void traverseTrieUtil(Node curr, String s) {

        if (curr != null) {

            traverseTrieUtil(curr.left, s);

            s += curr.c;

            if (curr.isEnd) System.out.println(s);

            traverseTrieUtil(curr.equal, s);

            traverseTrieUtil(curr.right, s);
        }
    }

    /**
     * Method to search autocomplete options
     * @param s Prefix string being searched for
     * @return List of strings representing autocomplete options
     */
    public List<String> autoCompleteOptions(String s) {

        LinkedList<String> out = new LinkedList<>();

        if(wordSearch(s)) { //Edge case
            return out;
        }

        Node start = wordSearchOptionsChar(wordTrie, s, 0);

        if(start == null) return out;

        findWords(start.equal, out,s);

        return out;

    }

    /**
     * Helper method to search autocomplete options
     * @param curr Current Node of traversal
     * @param s String being searched for
     * @param index index of the string currently at
     * @return returns Node representing the end of the string searched for
     */
    private Node wordSearchOptionsChar(Node curr, String s, int index) {

        if (curr == null) return null;

        if (s.charAt(index) < curr.c) return wordSearchOptionsChar(curr.left, s, index);

        else if (s.charAt(index) > curr.c) return wordSearchOptionsChar(curr.right, s, index);

        else {

            if (index == s.length() - 1) return curr;

            return wordSearchOptionsChar(curr.equal, s, ++index);
        }
    }

    /**
     * Helper method to search autocomplete options
     * @param curr Current Node of traversal
     * @param out List of strings representing autocomplete options
     * @param s string generated so far during traversal
     */
    private void findWords(Node curr, List<String> out, String s){

        if (curr == null) return;

        if (curr.isEnd) out.add(s+curr.c);
        findWords(curr.left,out,s);
        findWords(curr.right,out,s);
        findWords(curr.equal,out,s+curr.c);
    }

    public static void main(String[] args) {
        WordProcessor wp = new WordProcessor();
        wp.addWord("Mango");
        System.out.println(wp.autoCompleteOptions("M").get(0));
        wp.traverseTrie();
    }
}
