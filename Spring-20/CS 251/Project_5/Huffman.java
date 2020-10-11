import java.util.PriorityQueue;

public class Huffman {


    /**
     * Initialize global variables you create
     */
    public Huffman() { }



    /**
     * Produces the output frequency.txt
     *
     * @param input - File containing the message
     * @throws Exception
     */
    public void frequency(String input) throws Exception { }


    /**
     * Produces the output codes.txt and tree.txt
     *
     * @param freqFile - File containing the frequencies
     * @throws Exception
     */
    public void buildTree(String freqFile) throws Exception {
        /** use the queue below **/
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
    }



    /**
     * Produces the output encode.bin
     *
     * @param code - File containing the bit codes
     * @param message -  File containing the message
     * @throws Exception
     */
    public void encode(String code, String message) throws Exception { }



    /**
     * Produces the output decode.txt
     *
     * @param tree - File containing the Huffman tree
     * @param encode - - File containing the encoded message
     * @throws Exception
     */
    public void decode(String tree, String encode) throws Exception { }



    /**
     * Auxiliary class for Huffman
     *
     */
    class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        char letter;
        int index;
        HuffmanNode left;
        HuffmanNode right;
        HuffmanNode prev;


        /**
         * Uses frequency to determine the nodes order in the queue
         * Note: DO NOT MODIFY THIS FUNCTION
         *
         * @param node of type HuffmanNode
         * @return frequency of key node subtracted by frequency of node from parameter
         */
        @Override
        public int compareTo(HuffmanNode node) {
            return frequency - node.frequency;
        }

    }


}