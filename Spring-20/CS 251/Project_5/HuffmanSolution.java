import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.PriorityQueue;

public class HuffmanSolution {

    static String code;
    static String tree;
    static String decoded;

    public HuffmanSolution() {
        this.code = "";
        this.tree = "";
        this.decoded = "";

    }

    public static void main(String[] args) throws Exception {

        HuffmanSolution h = new HuffmanSolution();
        String input = "abc.txt";
        h.frequency(input);
        h.buildTree("frequency.txt");
        h.encode("codes.txt",input);
        h.decode("tree.txt", "encode.bin");

    }

    public void printCode(HuffmanNode root, String s) throws Exception
    {


        if (root.left == null && root.right == null ) {

            code += root.letter + " " + s + "\n";
            return;
        }

        String left = Character.toString(root.left.letter);
        String rootNode = Character.toString(root.letter);
        String right = Character.toString(root.right.letter);
        if(left.equals("R")){
            left = String.valueOf(root.left.frequency);
        }
        if(right.equals("R")){
            right = String.valueOf(root.right.frequency);
        }
        if(rootNode.equals("R")){
            rootNode = String.valueOf(root.frequency);
        }
        tree += left+"-"+rootNode+"-"+right + "\n";
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");


    }

    public void frequency(String input) throws Exception {
        String str = FiletoString(input);

        File frequency = new File("frequency.txt");

        int[] freq = new int[str.length()];
        int i;
        int j;

        char string[] = str.toCharArray();

        for(i = 0; i <str.length(); i++) {
            freq[i] = 1;
            for(j = i+1; j <str.length(); j++) {
                if(string[i] == string[j]) {
                    freq[i]++;

                    //Set to X to avoid printing visited character
                    string[j] = 'X';
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(frequency));
        for(i = 0; i <freq.length; i++) {
            if(string[i] != 'X') {
                writer.write(string[i] + " " + freq[i] + "\n");
            }

        }
        writer.close();



    }

    public void buildTree(String freqFile) throws Exception {
        File file = new File(freqFile);
        Scanner s = new Scanner(file);
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        HuffmanNode root = null;

        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] node = line.split(" ");
            HuffmanNode hn = new HuffmanNode();
            hn.letter = node[0].charAt(0);
            hn.frequency = Integer.valueOf(node[1]);
            hn.left = null;
            hn.right = null;
            queue.add(hn);
        }


        while (queue.size() > 1) {

            HuffmanNode first = queue.peek();
            queue.poll();

            HuffmanNode second = queue.peek();
            queue.poll();

            HuffmanNode newRoot = new HuffmanNode();
            newRoot.frequency = first.frequency + second.frequency;
            newRoot.letter = 'R';

            newRoot.left = first;
            newRoot.right = second;

            root = newRoot;

            queue.add(newRoot);
        }

        printCode(root,"");
        /* uncomment to print on console */
        //System.out.println(code);
        //System.out.println(tree);
        File codes = new File("codes.txt");
        if(codes.exists()){
            codes.delete();
        }
        BufferedWriter writerCode = new BufferedWriter(new FileWriter(codes, true));
        writerCode.write(code);
        writerCode.close();
        File trees = new File("tree.txt");
        if(trees.exists()){
            trees.delete();
        }
        BufferedWriter writerTree = new BufferedWriter(new FileWriter(trees, true));
        writerTree.write(tree);
        writerTree.close();


    }

    public void decodeTree(HuffmanNode root, String encode) throws Exception
    {
        if (root == null) {
            return;
        }
        String message = FiletoString(encode);
        char[] arr = message.toCharArray();
        int index = 0;
        String ret = "";
        while (index < arr.length) {
            HuffmanNode node = root;
            while (node != null) {
                if (node.left == null && node.right == null) {
                    ret += node.letter;
                    break;
                } else {
                    char c = arr[index];
                    if (c == '0') {
                        node = node.left;
                    } else {
                        node = node.right;
                    }
                    index++;
                }
            }
        }
        ret = ret.replaceAll("S", " ");
        ret = ret.replaceAll("N", "\n");

        File out = new File("decode.txt");
        BufferedWriter writerTree = new BufferedWriter(new FileWriter(out, false));
        writerTree.write(ret+"\n");
        writerTree.close();
        /* uncomment to print to console */
        //  System.out.println(ret);

    }
    public void encode(String code, String message) throws Exception {
        File encode = new File("encode.bin");
        if(encode.exists()){
            encode.delete();
        }
        BufferedWriter writerEncode = new BufferedWriter(new FileWriter(encode, true));
        ArrayList letter = new ArrayList();
        ArrayList bitCode = new ArrayList();
        Scanner scanCode = new Scanner(new File(code));
        String line = "";
        while(scanCode.hasNextLine()) {
            line = scanCode.nextLine();
            String[] arr = line.split(" ");
            letter.add(arr[0]);
            bitCode.add(arr[1]);
        }
        String encoded = FiletoString(message);
        String bits = "";


        for(int i = 0; i < encoded.length(); i++) {
            String c = String.valueOf(encoded.charAt(i));
            for(int j = 0; j < letter.size(); j++) {
                if(c.equals(letter.get(j))) {
                    bits += bitCode.get(j);
                }
            }

        }

        writerEncode.write(bits+"\n");

        writerEncode.close();

    }
    public void decode(String tree, String encode) throws Exception {
        String t = toStringTree(tree);
        String[] branches = t.split(" ");

        String[] nodeStart = branches[0].split("-");

        HuffmanNode Sroot = new HuffmanNode();
        Sroot.letter = 'R';
        Sroot.frequency = Integer.valueOf(nodeStart[1]);
        HuffmanNode root = Sroot;
        int index = 0;
        int count = 0;
        boolean leftFlag = true;
        boolean cont = true;
        while (cont) {

            String[] nodeTree = branches[index].split("-");
            if (leftFlag) {
                root.left = new HuffmanNode();
                if (Character.isDigit(nodeTree[0].charAt(0))) {
                    root.left.letter = 'R';
                    root.left.frequency = Integer.valueOf(nodeTree[0]);

                    root.left.prev = root;
                    root.left.prev.index = index;


                    root = root.left;
                    index++;
                    count++;
                } else {
                    root.left.letter = nodeTree[0].charAt(0);
                    leftFlag = false;

                }

            }
            if(root.right!=null) {


                if(root.prev!=null){
                    root = root.prev;
                    index = root.index;
                }
                else{
                    cont = false;
                }


            }
            else if (!leftFlag && root.right == null) {
                root.right = new HuffmanNode();

                if(Character.isDigit(nodeTree[2].charAt(0))){
                    root.right.letter = 'R';
                    root.right.frequency = Integer.valueOf(nodeTree[2]);

                    root.right.prev = root;
                    root.right.prev.index = index;


                    root = root.right;

                    index++;
                    count++;
                    index = count;


                    leftFlag = true;
                } else {
                    root.right.letter = nodeTree[2].charAt(0);

                    root = root.prev;
                    index = root.index;


                }

            }


        }

        decodeTree(Sroot,encode);




    }



    public String toStringTree(String input) throws Exception {
        Scanner s = new Scanner(new File(input));
        String line = "";
        while(s.hasNextLine()){
            line += s.nextLine();
            if(s.hasNextLine()) {
                line += " ";
            }
        }


        return line;
    }
    public String FiletoString(String input) throws Exception {
        Scanner s = new Scanner(new File(input));
        String line = "";
        while(s.hasNextLine()){
            line += s.nextLine();
            if(s.hasNextLine()) {
                line += "N";
            }
        }

        line = line.replaceAll(" ", "S");


        return line;
    }

    class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        char letter;
        int index;
        HuffmanNode left;
        HuffmanNode right;
        HuffmanNode prev;

        @Override
        public int compareTo(HuffmanNode node) {
            return frequency - node.frequency;
        }

    }

}