import org.junit.Assert;
import org.junit.Test;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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

    @Test
    public void testClassMethods() {

        Method buildComputerNetwork;
        Method buildCluster;
        Method connectCluster;
        Method traverseNetwork;

        Method getComputerConnections;
        Method getComputerGraph;
        Method getCluster;
        Method getRouterGraph;

        try {
            buildComputerNetwork = Network.class.getDeclaredMethod("buildComputerNetwork");
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Network has a method buildComputerNetwork that is declared public !");
        }

        try{
            buildCluster = Network.class.getDeclaredMethod("buildCluster", int.class);
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Network has a method buildCluster(int k) that takes an \'int\' argument and is declared public !");
        }

        try {
            connectCluster = Network.class.getDeclaredMethod("connectCluster");
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Network has method connectCluster() that is declared public !");
        }

        try {
            traverseNetwork = Network.class.getDeclaredMethod("traversNetwork");
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Network has a method traverseNetwork() that is declared public !");
        }

        try {
            getComputerConnections = Network.class.getDeclaredMethod("getComputerConnections");
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Network has a method getComputerConnections() that is declared public !");
        }

        try {
            getComputerGraph = Network.class.getDeclaredMethod("getComputerGraph");
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Network has a method getComputerGraph() that is declared public !");
        }

        try {
            getCluster = Network.class.getDeclaredMethod("getCluster");
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Network has a method getCluster() that is declared public !");
        }

        try {
            getRouterGraph = Network.class.getDeclaredMethod("getRouterGraph");
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Network has a method getRouterGraph() that is declared public !");
        }
    }

    @Test
    public void testBasic() {

    }

    @Test
    public void testIntermediateKSmall() {

    }

    @Test
    public void testIntermediateKLarge() {

    }

    @Test
    public void testAdvancedKSmall() {

    }

    @Test
    public void testAdvancedLarge() {

    }

    @Test
    public void testConnectivity() {

        HashMap<Integer,Integer> nodeToIndex = new HashMap<>();
        HashMap<Integer,Integer> indexToNode = new HashMap<>();
        LinkedList<LinkedList<Integer>> graph = new LinkedList<>();

        Scanner reader = null;
        try {
            reader = new Scanner(new File("Basic.txt"));
        } catch (IOException e){
            System.out.println("Reading Oops");
        }

        int numEdges = Integer.parseInt(reader.nextLine());
        int nodeCount = 0;

        for (int i=0;i<numEdges;i++){
            String[] in = reader.nextLine().split(" ");
            int a = Integer.parseInt(in[0]);
            int b = Integer.parseInt(in[1]);

            int aDex = -1;
            int bDex = -1;

            if(nodeToIndex.containsKey(a)){
                aDex = nodeToIndex.get(a);
            }else {
                nodeToIndex.putIfAbsent(a,nodeCount);
                indexToNode.putIfAbsent(nodeCount,a);
                aDex = nodeCount;
                graph.add(new LinkedList<>());
                nodeCount++;
            }

            if(nodeToIndex.containsKey(b)){
                bDex = nodeToIndex.get(b);
            }else {
                nodeToIndex.putIfAbsent(b,nodeCount);
                indexToNode.putIfAbsent(nodeCount,b);
                bDex = nodeCount;
                graph.add(new LinkedList<>());
                nodeCount++;
            }

            graph.get(aDex).add(bDex);
            graph.get(bDex).add(aDex);
        }

        boolean[] visited = new boolean[nodeCount];
        DFS(0,graph,visited);

        for(int i=0;i<nodeCount;i++){
            if(!visited[i])
                System.out.println("Not connected");
        }
    }

    public void testRouterConnectivity() {

        HashMap<Integer,Integer> nodeToIndex = new HashMap<>();
        HashMap<Integer,Integer> indexToNode = new HashMap<>();
        LinkedList<LinkedList<Integer>> graph = new LinkedList<>();

        boolean[] visited = new boolean[0];
        DFS(0,graph,visited);

        for(int i=0;i<0;i++){
            if(!visited[i])
                System.out.println("Not connected");
        }

    }

    public void DFS(int cur,LinkedList<LinkedList<Integer>> graph, boolean[] visited){

        visited[cur] = true;
        for(var i:graph.get(cur)){
            if(!visited[i]) DFS(i,graph,visited);
        }
    }

    public void testBuildComputerNetwork(LinkedList<Integer[]> expected, LinkedList<Integer[]> actual) {

        Assert.assertTrue("Ensure buildComputerNetwork adds all edges into computerConnections (computerConnections.size() is incorrect) !",expected.size() == actual.size());

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

            Assert.assertTrue("Ensure all edges are added correctly and in the sequence given to you !", correct);
        }
    }

    //WORKS
    public void testClusterOrComputerGraph(LinkedList<LinkedList<Integer>> expectedCluster, LinkedList<LinkedList<Integer>> actualCluster) {

        Assert.assertTrue("Ensure that cluster is constructed correctly (cluster.size() is incorrect) !",expectedCluster.size() == actualCluster.size());

        HashSet<String> set = new HashSet<>();

        for(var i:expectedCluster){
            String s = "";
            Collections.sort(i);
            for(var j: i){
                s += j;
            }
            set.add(s);
        }

        for(var i:actualCluster){
            String a = "";
            Collections.sort(i);
            for(var j: i){
                a += j;
            }
            if(!set.contains(a)) {
                Assert.fail("Ensure that cluster has all the correct collections (Try printing it out) !");
            }
        }
    }

    public void testRouterGraph(LinkedList<LinkedList<Integer[]>> expectedGraph,LinkedList<LinkedList<Integer[]>> actualGraph){

        Assert.assertTrue("Ensure that routerGraph is constructed correctly (routerGraph.size() is incorrect) !",expectedGraph.size() == actualGraph.size());

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
            if(!lists.contains(a)) {
                Assert.fail("Ensure that routerGraph has all correct edges and weights ( Try printing it out ) !");
            }
        }
    }

    @Test
    public void createTest () {

        List<String> edges = nodeGenerator(50,100, 80,1000);
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("Intermediate_K_Small.txt"));

            writer.write(edges.size()+"\n");

            for(var s:edges){
                writer.write(s);
                writer.write("\n");
            }

            writer.flush();

        }catch (IOException e){
            System.out.println("Writing Oops");
        }

        Scanner reader = null;
        try {
            reader = new Scanner(new File("Intermediate_K_Small.txt"));
        } catch (IOException e){
            System.out.println("Reading Oops");
        }

        String input = "";
        while (reader.hasNextLine()){
            input += reader.nextLine();
            input += "\n";
        }

        InputStream stdin = System.in;
        try {

            System.setIn(new ByteArrayInputStream(input.getBytes()));
            NetworkSol solution = new NetworkSol();
            solution.buildComputerNetwork();
            LinkedList<Integer[]> computerConnections = solution.getComputerConnections();

            solution.buildCluster(7);
            LinkedList<LinkedList<Integer>> computerGraph = solution.getComputerGraph();
            LinkedList<LinkedList<Integer>> cluster = solution.getCluster();
            LinkedList<Integer> routerIPs = new LinkedList<>();

            System.out.println("\nCluster");

            for (int i=0;i<cluster.size();i++) {
                routerIPs.add(routerIP(cluster.get(i)));
                Collections.sort(cluster.get(i), Collections.reverseOrder());
                System.out.print(routerIPs.peekLast() + ": ");
                for (var j : cluster.get(i)) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }

            List<String> routerEdges = routerEdgeGenerator(routerIPs,16,100);

            try {

                writer.write(routerEdges.size()+"\n");
                for(var s:routerEdges){
                    writer.write(s);
                    writer.write("\n");
                }

                writer.flush();

            }catch (IOException e){
                System.out.println("Writing Oops");
                e.printStackTrace();
            }

            writer.close();

        } catch (Exception e){
            System.out.println("What happened here");
            e.printStackTrace();
        } finally {
            System.setIn(stdin);
        }
    }



    public List<String> routerEdgeGenerator(LinkedList<Integer> routerIp, int numEdge, int weightLimit){

        LinkedList<String> routerEdges = new LinkedList<>();
        HashMap<Integer, Integer> nodeToIndex = new HashMap<>();
        HashMap<Integer, Integer> indexToNode = new HashMap<>();
        Random r = new Random();

        for(int i=0;i<routerIp.size();i++){
            nodeToIndex.putIfAbsent(routerIp.get(i),i);
            indexToNode.putIfAbsent(i,routerIp.get(i));
            for(int j=i+1;j<routerIp.size();j++){
                routerEdges.add(routerIp.get(i)+" "+routerIp.get(j));
                routerEdges.add(routerIp.get(j)+" "+routerIp.get(i));
            }
        }

        LinkedList<String> edges = new LinkedList<>();
        HashSet<String> edgeSet = new HashSet<>();
        HashSet<Integer> weights = new HashSet<>();
        UnionFind uf = new UnionFind(routerIp.size());

        for(int i=0;i<numEdge;i++){

            int index = r.nextInt(routerEdges.size());
            String[] pieces = routerEdges.get(index).split(" ");
            int a = Integer.parseInt(pieces[0]);
            int b = Integer.parseInt(pieces[1]);
            String s1 = pieces[1]+" "+pieces[0];
            routerEdges.remove(index);

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
        }
        return edges;
    }

    public List<String> nodeGenerator(int numNode,int nodeRange, int numEdge, int weightLimit) {

        Random r = new Random(9);
        Set<Integer> nodeSet = new HashSet<>();
        HashMap<Integer, Integer> nodeToIndex = new HashMap<>();
        HashMap<Integer, Integer> indexToNode = new HashMap<>();

        for(int i=0;i<numNode;i++){
            int node = r.nextInt(nodeRange-1)+ (nodeRange/10);
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
        }
        System.out.println("Components: "+uf.components());
        return edges;
    }

    private int routerIP(LinkedList<Integer> babyCluster) {

        int maxIP = -1;
        for (Integer c : babyCluster) {
            if (maxIP < c) maxIP = c;
        }
        return maxIP;
    }

    public static void main(String[] args) {
        NetworkTest test = new NetworkTest();
        LinkedList<LinkedList<Integer>> expected = new LinkedList<>();
        LinkedList<LinkedList<Integer>> actual = new LinkedList<>();
        Integer[] arr1 = {1,2,7,23,65,767,43154,253,65};
        Integer[] arr2 = {34,546,78,123,6456,878,234,6,867,24};
        Integer[] arr3 = {23,45,13,87,23467,8,234,78};
        Integer[] arr4 = {56,234467,5688,723423,7678,24,121,545,76};

        LinkedList<Integer> a1 = new LinkedList<Integer>(Arrays.asList(arr1));
        LinkedList<Integer> a2 = new LinkedList<Integer>(Arrays.asList(arr1));
        LinkedList<Integer> a3 = new LinkedList<Integer>(Arrays.asList(arr1));
        LinkedList<Integer> a4 = new LinkedList<Integer>(Arrays.asList(arr1));

        Collections.shuffle(a1);
        Collections.shuffle(a2);
        Collections.shuffle(a3);
        Collections.shuffle(a4);

        expected.add(new LinkedList<>(a1));
        expected.add(new LinkedList<>(a2));
        expected.add(new LinkedList<>(a3));
        expected.add(new LinkedList<>(a4));

        Collections.shuffle(a1);
        Collections.shuffle(a2);
        Collections.shuffle(a3);
        Collections.shuffle(a4);

        actual.add(a2);
        actual.add(a3);
        actual.add(a1);
        actual.add(a4);

        for(var i:expected){
            for (var j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        System.out.println();
        for(var i:actual){
            for (var j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }

        test.testClusterOrComputerGraph(expected,actual);

    }
}