import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HuffmanTestsRobust {

    /** Test 1 **/

    @Test(timeout = 10000)
    public void testFrequency() throws Exception {
        Huffman h = new Huffman();

        h.frequency("NonLocalFiles/testMessage1.txt");
        String actual = new String(Files.readAllBytes(Paths.get("frequency.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/freq1.txt")));
        Assert.assertEquals(expected, actual);

    }
    @Test(timeout = 10000)
    public void testCodes() throws Exception{
        Huffman h = new Huffman();

        h.buildTree("NonLocalFiles/freq1.txt");
        String actual = new String(Files.readAllBytes(Paths.get("codes.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/codes1.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testTree() throws Exception{
        Huffman h = new Huffman();

        h.buildTree("NonLocalFiles/freq1.txt");
        String actual = new String(Files.readAllBytes(Paths.get("tree.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/tree1.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testEncode() throws Exception{
        Huffman h = new Huffman();

        h.encode("NonLocalFiles/codes1.txt", "NonLocalFiles/testMessage1.txt");
        String actual = new String(Files.readAllBytes(Paths.get("encode.bin")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/encode1.bin")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testDecode() throws Exception {
        Huffman h = new Huffman();

        h.decode("NonLocalFiles/tree1.txt", "NonLocalFiles/encode1.bin");
        String actual = new String(Files.readAllBytes(Paths.get("decode.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/decode1.txt")));
        Assert.assertEquals(expected, actual);


    }

    /** Test 2 **/

    @Test(timeout = 10000)
    public void testFrequency2() throws Exception {
        Huffman h = new Huffman();

        h.frequency("NonLocalFiles/testMessage2.txt");
        String actual = new String(Files.readAllBytes(Paths.get("frequency.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/freq2.txt")));
        Assert.assertEquals(expected, actual);

    }
    @Test(timeout = 10000)
    public void testCodes2() throws Exception{
        Huffman h = new Huffman();

        h.buildTree("NonLocalFiles/freq2.txt");
        String actual = new String(Files.readAllBytes(Paths.get("codes.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/codes2.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testTree2() throws Exception{
        Huffman h = new Huffman();

        h.buildTree("NonLocalFiles/freq2.txt");
        String actual = new String(Files.readAllBytes(Paths.get("tree.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/tree2.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testEncode2() throws Exception{
        Huffman h = new Huffman();

        h.encode("NonLocalFiles/codes2.txt", "NonLocalFiles/testMessage2.txt");
        String actual = new String(Files.readAllBytes(Paths.get("encode.bin")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/encode2.bin")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testDecode2() throws Exception {
        Huffman h = new Huffman();

        h.decode("NonLocalFiles/tree2.txt", "NonLocalFiles/encode2.bin");
        String actual = new String(Files.readAllBytes(Paths.get("decode.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/decode2.txt")));
        Assert.assertEquals(expected, actual);


    }

    /** Test 3 **/

    @Test(timeout = 10000)
    public void testFrequency3() throws Exception {
        Huffman h = new Huffman();

        h.frequency("NonLocalFiles/testMessage3.txt");
        String actual = new String(Files.readAllBytes(Paths.get("frequency.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/freq3.txt")));
        Assert.assertEquals(expected, actual);

    }
    @Test(timeout = 10000)
    public void testCodes3() throws Exception{
        Huffman h = new Huffman();

        h.buildTree("NonLocalFiles/freq3.txt");
        String actual = new String(Files.readAllBytes(Paths.get("codes.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/codes3.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testTree3() throws Exception{
        Huffman h = new Huffman();

        h.buildTree("NonLocalFiles/freq3.txt");
        String actual = new String(Files.readAllBytes(Paths.get("tree.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/tree3.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testEncode3() throws Exception{
        Huffman h = new Huffman();

        h.encode("NonLocalFiles/codes3.txt", "NonLocalFiles/testMessage3.txt");
        String actual = new String(Files.readAllBytes(Paths.get("encode.bin")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/encode3.bin")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testDecode3() throws Exception {
        Huffman h = new Huffman();

        h.decode("NonLocalFiles/tree3.txt", "NonLocalFiles/encode3.bin");
        String actual = new String(Files.readAllBytes(Paths.get("decode.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/decode3.txt")));
        Assert.assertEquals(expected, actual);


    }


    /** Test 4 **/


    @Test(timeout = 10000)
    public void testFrequency4() throws Exception {
        Huffman h = new Huffman();

        h.frequency("NonLocalFiles/testMessage4.txt");
        String actual = new String(Files.readAllBytes(Paths.get("frequency.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/freq4.txt")));
        Assert.assertEquals(expected, actual);

    }
    @Test(timeout = 10000)
    public void testCodes4() throws Exception{
        Huffman h = new Huffman();

        h.buildTree("NonLocalFiles/freq4.txt");
        String actual = new String(Files.readAllBytes(Paths.get("codes.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/codes4.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testTree4() throws Exception{
        Huffman h = new Huffman();

        h.buildTree("NonLocalFiles/freq4.txt");
        String actual = new String(Files.readAllBytes(Paths.get("tree.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/tree4.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testEncode4() throws Exception{
        Huffman h = new Huffman();

        h.encode("NonLocalFiles/codes4.txt", "NonLocalFiles/testMessage4.txt");
        String actual = new String(Files.readAllBytes(Paths.get("encode.bin")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/encode4.bin")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testDecode4() throws Exception {
        Huffman h = new Huffman();

        h.decode("NonLocalFiles/tree4.txt", "NonLocalFiles/encode4.bin");
        String actual = new String(Files.readAllBytes(Paths.get("decode.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/decode4.txt")));
        Assert.assertEquals(expected, actual);


    }

    /** Test 5 **/

    @Test(timeout = 10000)
    public void testFrequency5() throws Exception {
        Huffman h = new Huffman();
        File checkF = new File("frequency.txt");
        File checkC = new File("codes.txt");
        File checkT = new File("tree.txt");
        File checkD = new File("decode.txt");
        File checkE = new File("encode.bin");

        if(checkF.exists()){
            checkF.delete();
        }
        if(checkC.exists()){
            checkC.delete();
        }
        if(checkT.exists()){
            checkT.delete();
        }
        if(checkD.exists()){
            checkD.delete();
        }
        if(checkE.exists()){
            checkE.delete();
        }

        h.frequency("NonLocalFiles/testMessage5.txt");
        String actual = new String(Files.readAllBytes(Paths.get("frequency.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/freq5.txt")));
        Assert.assertEquals(expected, actual);

    }
    @Test(timeout = 10000)
    public void testCodes5() throws Exception{
        Huffman h = new Huffman();
        File checkF = new File("frequency.txt");
        File checkC = new File("codes.txt");
        File checkT = new File("tree.txt");
        File checkD = new File("decode.txt");
        File checkE = new File("encode.bin");

        if(checkF.exists()){
            checkF.delete();
        }
        if(checkC.exists()){
            checkC.delete();
        }
        if(checkT.exists()){
            checkT.delete();
        }
        if(checkD.exists()){
            checkD.delete();
        }
        if(checkE.exists()){
            checkE.delete();
        }

        h.buildTree("NonLocalFiles/freq5.txt");
        String actual = new String(Files.readAllBytes(Paths.get("codes.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/codes5.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testTree5() throws Exception{
        Huffman h = new Huffman();
        File checkF = new File("frequency.txt");
        File checkC = new File("codes.txt");
        File checkT = new File("tree.txt");
        File checkD = new File("decode.txt");
        File checkE = new File("encode.bin");

        if(checkF.exists()){
            checkF.delete();
        }
        if(checkC.exists()){
            checkC.delete();
        }
        if(checkT.exists()){
            checkT.delete();
        }
        if(checkD.exists()){
            checkD.delete();
        }
        if(checkE.exists()){
            checkE.delete();
        }

        h.buildTree("NonLocalFiles/freq5.txt");
        String actual = new String(Files.readAllBytes(Paths.get("tree.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/tree5.txt")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testEncode5() throws Exception{
        Huffman h = new Huffman();
        File checkF = new File("frequency.txt");
        File checkC = new File("codes.txt");
        File checkT = new File("tree.txt");
        File checkD = new File("decode.txt");
        File checkE = new File("encode.bin");

        if(checkF.exists()){
            checkF.delete();
        }
        if(checkC.exists()){
            checkC.delete();
        }
        if(checkT.exists()){
            checkT.delete();
        }
        if(checkD.exists()){
            checkD.delete();
        }
        if(checkE.exists()){
            checkE.delete();
        }

        h.encode("NonLocalFiles/codes5.txt", "NonLocalFiles/testMessage5.txt");
        String actual = new String(Files.readAllBytes(Paths.get("encode.bin")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/encode5.bin")));
        Assert.assertEquals(expected, actual);


    }

    @Test(timeout = 10000)
    public void testDecode5() throws Exception {
        Huffman h = new Huffman();
        File checkF = new File("frequency.txt");
        File checkC = new File("codes.txt");
        File checkT = new File("tree.txt");
        File checkD = new File("decode.txt");
        File checkE = new File("encode.bin");

        if(checkF.exists()){
            checkF.delete();
        }
        if(checkC.exists()){
            checkC.delete();
        }
        if(checkT.exists()){
            checkT.delete();
        }
        if(checkD.exists()){
            checkD.delete();
        }
        if(checkE.exists()){
            checkE.delete();
        }

        h.decode("NonLocalFiles/tree5.txt", "NonLocalFiles/encode5.bin");
        String actual = new String(Files.readAllBytes(Paths.get("decode.txt")));
        String expected = new String(Files.readAllBytes(Paths.get("NonLocalFiles/decode5.txt")));
        Assert.assertEquals(expected, actual);


    }

}
