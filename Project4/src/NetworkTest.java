import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class NetworkTest {

    @Test
    public void testClassProperty() {
        Constructor<Network> constructor = null;
        Field computerConnections = null;
        Field computerGraph = null;
        Field cluster = null;
        Field routerGraph = null;

        try{
            constructor = Network.class.getConstructor();
        }catch (NoSuchMethodException e){
            System.out.println("Ensure that Network has a constructor!");
            Assert.fail();
        }

        try{
            computerConnections = Network.class.getDeclaredField("computerConnections");
        }catch (NoSuchFieldException e){
            System.out.println("Ensure that Network has a field LinkedList<Integer[]> computerConnections!");
            Assert.fail();
        }
        LinkedList<Integer[]> typeCheckComputerConnection = new LinkedList<>();
        assertEquals("Ensure that computerConnections is of type LinkedList<Integer[]> !",typeCheckComputerConnection.getClass(),computerConnections.getType());

        try{
            computerGraph = Network.class.getDeclaredField("computerGraph");
        }catch (NoSuchFieldException e){
            System.out.println("Ensure that Network has a field LinkedList<LinkedList<Integer>> computerGraph!");
            Assert.fail();
        }
        LinkedList<LinkedList<Integer>> typeCheckComputerGraph = new LinkedList<>();
        assertEquals("Ensure that computerGraph is of type LinkedList<LinkedList<Integer>> !",typeCheckComputerGraph.getClass(),computerGraph.getType());


        try{
            cluster = Network.class.getDeclaredField("cluster");
        }catch (NoSuchFieldException e){
            System.out.println("Ensure that Network has a field LinkedList<Integer> cluster");
            Assert.fail();
        }

        LinkedList<LinkedList<Integer>> typeCheckCluster = new LinkedList<>();
        assertEquals("Ensure that cluster is of type LinkedList<LinkedList<Integer>> !",typeCheckCluster.getClass(),cluster.getType());

        try{
            routerGraph = Network.class.getDeclaredField("routerGraph");
        }catch (NoSuchFieldException e){
            System.out.println("Ensure that Network has a field LinkedList<LinkedList<Integer[]>> routerGraph!");
            Assert.fail();
        }

        LinkedList<LinkedList<Integer[]>> typeCheckRouterGraph = new LinkedList<>();
        assertEquals("Ensure that routerGraph is of type LinkedList<LinkedList<Integer[]>> !",typeCheckRouterGraph.getClass(),routerGraph.getType());

    }

    public void testBuildComputerNetwork(LinkedList<Integer[]> expected, LinkedList<Integer[]> actual) {

        Assert.assertEquals("Ensure buildComputerNetwork adds all edges into computerConnections (computerConnections.size() is incorrect) !",expected.size(),actual.size());

        for (int i=0;i<expected.size();i++){
            if(actual.get(i).length != 3){
                System.out.println("Ensure edge consists of [source] [destination] and [latency] !");
                Assert.fail();
                break;
            }

            boolean correct = true;
            for(int j=0;j<3;j++){
               if(expected.get(i)[j] != actual.get(i)[j]){
                   correct = false;
               }
            }

            Assert.assertTrue("Ensure all edges are added correctly !",correct);
        }
    }

    public void testComputerGraph(LinkedList<LinkedList<Integer>> expectedGraph, LinkedList<LinkedList<Integer>> actualGraph) {

        if(expectedGraph.size() != actualGraph.size()){
            Assert.fail("Ensure that computerGraph is constructed correctly (computerGraph.size() is incorrect) !");
        }

        HashSet<String> set = new HashSet<>();

        for(var i:expectedGraph){
            String a = "";
            for(var j: i){
                a += j;
            }
            set.add(a);
        }

        for(var i:actualGraph){
            String a = "";
            for(var j: i){
                a += j;
            }
            if(!set.contains(a)) Assert.fail("Ensure that computerGraph has all the correct edges (Try printing it out) !");
        }
    }

    public void testCluster(LinkedList<LinkedList<Integer>> expectedCluster, LinkedList<LinkedList<Integer>> actualCluster) {

        if(expectedCluster.size() != actualCluster.size()){
            Assert.fail("Ensure that cluster is constructed correctly (cluster.size() is incorrect) !");
        }

        HashSet<String> set = new HashSet<>();

        for(var i:expectedCluster){
            String s = "";
            for(var j: i){
                s += j;
            }
            set.add(s);
        }

        for(var i:actualCluster){
            String a = "";
            for(var j: i){
                a += j;
            }
            if(!set.contains(a)) Assert.fail("Ensure that cluster has all the correct collections (Try printing it out) !");
        }
    }

    public void testRouterGraph(LinkedList<LinkedList<Integer[]>> expectedGraph,LinkedList<LinkedList<Integer[]>> actualGraph){

        if(expectedGraph.size() != actualGraph.size()){
            Assert.fail("Ensure that routerGraph is constructed correctly (routerGraph.size() is incorrect) !");
        }

        HashSet<String> lists = new HashSet<>();
        for(var i:expectedGraph){
            Collections.sort(i, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return o1[0]-o2[0];
                }
            });
            String a = "";
            for(var j:i){
                a += j[0]+" "+j[1]+" "+j[2];
            }
            lists.add(a);
        }

        for(var i:actualGraph){
            Collections.sort(i, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return o1[0]-o2[0];
                }
            });
            String a = "";
            for(var j:i){
                a += j[0]+" "+j[1]+" "+j[2];
            }
            if(!lists.contains(a)) Assert.fail("Ensure that routerGraph has all correct edges and weights ( Try printing it out ) !");
        }
    }

    @Test
    public void createTest () {

        List<String> edges = nodeGenerator(5,10, 7,15);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("Basic.txt"));

            System.out.println(edges.size());
            writer.write(edges.size()+"\n");

            for(var s:edges){
                writer.write(s);
                writer.write("\n");
            }

            writer.close();
        }catch (IOException e){
            System.out.println("Oops");
        }
    }

    public List<String> nodeGenerator(int numNode,int nodeRange, int numEdge, int weightLimit) {

        Random r = new Random();
        Set<Integer> nodeSet = new HashSet<>();
        HashMap<Integer, Integer> nodeToIndex = new HashMap<>();
        HashMap<Integer, Integer> indexToNode = new HashMap<>();

        for(int i=0;i<numNode;i++){
            int node = r.nextInt(nodeRange-1)+nodeRange/10;
            if(nodeSet.contains(node)) i--;
            else {
                nodeSet.add(node);
                nodeToIndex.putIfAbsent(node,i);
                indexToNode.putIfAbsent(i,node);
            }
        }

        Integer[] nodes = new Integer[nodeSet.size()];
        nodes = nodeSet.toArray(nodes);

        LinkedList<String> allEdges = new LinkedList<>();
        for(int i=0;i<nodes.length;i++){
            for(int j=i+1;j<nodes.length;j++){
                allEdges.add(nodes[i]+" "+nodes[j]);
                allEdges.add(nodes[j]+" "+nodes[i]);
            }
        }

        Collections.shuffle(allEdges);
        LinkedList<String> edges = new LinkedList<>();
        HashSet<String> edgeSet = new HashSet<>();
        HashSet<Integer> weights = new HashSet<>();
        UnionFind uf = new UnionFind(numNode);

        for(int i=0;i<numEdge;i++){

            int index = r.nextInt(allEdges.size());
            String[] pieces = allEdges.get(index).split(" ");
            int a = Integer.parseInt(pieces[0]);
            int b = Integer.parseInt(pieces[1]);
            String s1 = pieces[1]+" "+pieces[0];
            allEdges.remove(index);

            if(edgeSet.contains(s1)){
                i--;
                continue;
            }

            if(i == numEdge-1 && uf.components()!=1 && uf.find(nodeToIndex.get(a))==uf.find(nodeToIndex.get(b))){
                i--;
                continue;
            }

            int weight = r.nextInt(weightLimit-1)+1;
            while(weights.contains(weight)){
                weight = r.nextInt(weightLimit-1)+1;
            }

            weights.add(weight);
            edgeSet.add(a+" "+b);
            edges.add(a+" "+b+" "+weight);
            uf.union(nodeToIndex.get(a),nodeToIndex.get(b));
            System.out.println("Components: "+uf.components());
        }
        return edges;
    }
}