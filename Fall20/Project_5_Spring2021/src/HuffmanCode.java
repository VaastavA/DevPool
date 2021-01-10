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
         * Node class constructor
         * @param ch character stored at node
         * @param freq frequency of node
         * @param left left child node
         * @param right right child node
         */
        public Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        /**
         * <b>ch</b> field accessor
         * @return character stored at node
         */
        public char getCh() {
            return ch;
        }

        /**
         * <b>freq</b> field accessor
         * @return frequency of node
         */
        public int getFreq() {
            return freq;
        }

        /**
         * <b>left</b> field accessor
         * @return left child node
         */
        public Node getLeft() {
            return left;
        }

        /**
         * <b>right</b> field accessor
         * @return right child node
         */
        public Node getRight() {
            return right;
        }

        /**
         *  Method to check if current node is a leaf node
         * @return true if current node is leaf node
         */
        public boolean isLeaf() { return getLeft() == null && getRight() == null;}

        /**
         * CompareTo method to support comparison between two nodes
         * @param that node to compare current node against
         * @return integer comparison value
         */
        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    private Node huffmanTree;                       //Root of huffman tree
    private HashMap<Character, String> encoding;    //Stores Encoding mapping
    private HashMap<String, Character> decoding;    //Stores Decoding mapping
    private String text;                            //Stores Input text to process

    /**
     * <b>huffmanTree</b> field accessor
     * @return Root of huffman tree
     */
    public Node getHuffmanTree() {
        return huffmanTree;
    }

    /**
     * <b>encoding</b> field accessor
     * @return Encoding mapping
     */
    public HashMap<Character, String> getEncoding() {
        return encoding;
    }

    /**
     * <b>decoding</b> field accessor
     * @return Decoding mapping
     */
    public HashMap<String, Character> getDecoding() {
        return decoding;
    }



    /**
     * HuffmanCode constructor. Processes text and build encoding + decoding mapping
     * @param text Input text to be processed
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
     * Takes plain input text and encodes it based on encoding mapping
     * @param in input text
     * @return encoded string
     * @throws IllegalArgumentException when encoding does not exist
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
     * Takes encoded input text and decodes it based on decoding mapping
     * @param in input test
     * @return decoded string
     * @throws IllegalArgumentException when decoding does not exist
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
