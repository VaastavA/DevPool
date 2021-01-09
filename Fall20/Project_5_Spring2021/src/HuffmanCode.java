import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * A HuffmanCode implementation class
 *
 * <p>Purdue University -- CS25100 -- Spring 2021 -- Huffman Encoding</p>
 *
 * @auther Vaastav Arora, arora74@purdue.edu
 * @version January 7, 2021
 */
public class HuffmanCode {

    /**
     *  Node class representing a huffman tree node
     */
    public class Node implements Comparable<Node>{

        private final char ch;
        private final int freq;
        private final Node left, right;

        /**
         *
         * @param ch
         * @param freq
         * @param left
         * @param right
         */
        public Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        /**
         *
         * @return
         */
        public char getCh() {
            return ch;
        }

        /**
         *
         * @return
         */
        public int getFreq() {
            return freq;
        }

        /**
         *
         * @return
         */
        public Node getLeft() {
            return left;
        }

        /**
         *
         * @return
         */
        public Node getRight() {
            return right;
        }

        /**
         *
         * @return
         */
        public boolean isLeaf() { return getLeft() == null && getRight() == null;}

        /**
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }

    private Node huffmanTree;

    /**
     *
     * @return
     */
    public Node getHuffmanTree() {
        return huffmanTree;
    }

    /**
     *
     * @return
     */
    public HashMap<Character, String> getEncoding() {
        return encoding;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Character> getDecoding() {
        return decoding;
    }

    private HashMap<Character, String> encoding;
    private HashMap<String, Character> decoding;
    private String text;

    /**
     *
     * @param text
     */
    public HuffmanCode(String text) {
        this.text = text;
        init();
    }

    private void init() {
        // read the input
        char[] input = this.text.toCharArray();

        // tabulate frequency counts
        HashMap<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < input.length; i++)
            freq.put(input[i], freq.getOrDefault(input[i], 0)+1);

        // build Huffman trie
        this.huffmanTree = buildTree(freq);

        // build code table
        encoding = new HashMap<>();
        decoding = new HashMap<>();
        buildEncoding(this.huffmanTree, "");

    }

    /**
     *
     * @param in
     * @return
     * @throws IllegalArgumentException
     */
    public String compress(String in) throws IllegalArgumentException {

        char[] input = in.toCharArray();
        StringBuilder output = new StringBuilder();
        // use Huffman code to encode input
        for (int i = 0; i < input.length; i++) {
            String code = encoding.get(input[i]);
            if(code == null) throw new IllegalArgumentException("Encoding Not Found");
            output.append(code);
        }

        return output.toString();
    }

    /**
     *
     * @param in
     * @return
     * @throws IllegalArgumentException
     */
    public String expand(String in) throws IllegalArgumentException {

        char[] input = in.toCharArray();
        StringBuilder output = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        // use Huffman code to decode input
        int i = 0;
        while( i < input.length){
            temp.append(input[i]);
            if(decoding.containsKey(temp.toString())){
                output.append(decoding.get(temp.toString()));
                temp = new StringBuilder();
            }

            i++;
        }

        if(!temp.toString().equals("")) throw new IllegalArgumentException("Decoding Not Found!");

        return output.toString();
    }

    private Node buildTree(HashMap<Character, Integer> freq) {


        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Character c: freq.keySet()) {

            pq.add(new Node(c, freq.get(c), null, null));
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }

        return pq.poll();
    }

    private void buildEncoding( Node x, String s) {
        if (!x.isLeaf()) {
            buildEncoding(x.left,  s + '0');
            buildEncoding(x.right, s + '1');
        }
        else {
            this.encoding.put(x.ch, s);
            this.decoding.put(s, x.ch);
        }
    }
}
