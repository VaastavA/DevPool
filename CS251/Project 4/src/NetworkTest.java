import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Set of tests for Network class
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version July 20th 2019
 */

@FixMethodOrder(MethodSorters.JVM)
public class NetworkTest {

    /*
     * Checks Constructor and fields of Network class
     */

    @Test
    public void testClassProperty() {
        Constructor<Network> constructor = null;
        Field computerConnections = null;
        Field computerGraph = null;
        Field cluster = null;
        Field routerGraph = null;

        try {
            constructor = Network.class.getConstructor(Scanner.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that Network has a constructor!");
            Assert.fail();
        }

        try {
            computerConnections = Network.class.getDeclaredField("computerConnections");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that Network has a field LinkedList<Integer[]> computerConnections!");
            Assert.fail();
        }
        LinkedList<Integer[]> typeCheckComputerConnection = new LinkedList<>();
        assertEquals("Ensure that computerConnections is of type LinkedList<Integer[]> !", typeCheckComputerConnection.getClass(), computerConnections.getType());

        try {
            computerGraph = Network.class.getDeclaredField("computerGraph");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that Network has a field LinkedList<LinkedList<Integer>> computerGraph!");
            Assert.fail();
        }
        LinkedList<LinkedList<Integer>> typeCheckComputerGraph = new LinkedList<>();
        assertEquals("Ensure that computerGraph is of type LinkedList<LinkedList<Integer>> !", typeCheckComputerGraph.getClass(), computerGraph.getType());


        try {
            cluster = Network.class.getDeclaredField("cluster");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that Network has a field LinkedList<Integer> cluster");
            Assert.fail();
        }

        LinkedList<LinkedList<Integer>> typeCheckCluster = new LinkedList<>();
        assertEquals("Ensure that cluster is of type LinkedList<LinkedList<Integer>> !", typeCheckCluster.getClass(), cluster.getType());

        try {
            routerGraph = Network.class.getDeclaredField("routerGraph");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that Network has a field LinkedList<LinkedList<Integer[]>> routerGraph!");
            Assert.fail();
        }

        LinkedList<LinkedList<Integer[]>> typeCheckRouterGraph = new LinkedList<>();
        assertEquals("Ensure that routerGraph is of type LinkedList<LinkedList<Integer[]>> !", typeCheckRouterGraph.getClass(), routerGraph.getType());

    }

    /*
     * Checks methods of Network class
     */

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
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Network has a method buildComputerNetwork that is declared public !");
        }

        try {
            buildCluster = Network.class.getDeclaredMethod("buildCluster", int.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Network has a method buildCluster(int k) that takes an \'int\' argument and is declared public !");
        }

        try {
            connectCluster = Network.class.getDeclaredMethod("connectCluster");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Network has method connectCluster() that is declared public !");
        }

        try {
            traverseNetwork = Network.class.getDeclaredMethod("traversNetwork", String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Network has a method traverseNetwork() that is declared public !");
        }

        try {
            getComputerConnections = Network.class.getDeclaredMethod("getComputerConnections");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Network has a method getComputerConnections() that is declared public !");
        }

        try {
            getComputerGraph = Network.class.getDeclaredMethod("getComputerGraph");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Network has a method getComputerGraph() that is declared public !");
        }

        try {
            getCluster = Network.class.getDeclaredMethod("getCluster");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Network has a method getCluster() that is declared public !");
        }

        try {
            getRouterGraph = Network.class.getDeclaredMethod("getRouterGraph");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Network has a method getRouterGraph() that is declared public !");
        }
    }

    /*
     * Tests graph within Basic.txt
     */

    @Test
    public void testBasic() {
        testGraph("Basic.txt", 6, 0);
    }

    /*
     * Tests graph within Intermediate_K_Small.txt
     */

    @Test
    public void testIntermediateKSmall() {
        testGraph("Intermediate_K_Small.txt", 7, 0);
    }

    /*
     * Tests graph within Intermediate_K_Large.txt
     */

    @Test
    public void testIntermediateKLarge() {
        testGraph("Intermediate_K_Large.txt", 10, 0);
    }

    /*
     * Tests graph within Advanced_K_Small.txt
     */

    @Test
    public void testAdvancedKSmall() {
        testGraph("Advanced_K_Small.txt", 10, 0);
    }

    /*
     * Tests graph within Advanced_K_Large.txt
     */

    @Test
    public void testAdvancedLarge() {
        testGraph("Advanced_K_Large.txt", 20, 0);
    }

    /*
     * Tests edge case where Source and Dest comps
     * are in the same router with Basic.txt
     */

    @Test
    public void testSameRouter() {
        testGraph("Basic.txt", 6, 1);
    }

    /*
     * Tests edge case where Source or Dest comp
     * does not exist with Basic.txt
     */

    @Test
    public void testCompNotExist() {
        testGraph("Basic.txt", 6, 2);
    }

    /*
     * Tests edge case where Source or Dest router
     * does not exist with Basic.txt
     */

    @Test
    public void testRouterNotExist() {
        testGraph("Basic.txt", 6, 3);
    }

    /*
     * Tests edge case where clusters cannot be build
     * with Basic.txt
     */

    @Test
    public void testClusterNotPossible() {
        testGraph("Basic.txt", 26, 4);
    }

    /**
     * Helper function to run all test graphs
     * @param filename  Name of file that contains testcase
     * @param clusterNum Number of clusters for specified testcase
     */
    private void testGraph(String filename, int clusterNum, int edgeCase) {

        Scanner readerSolution = null;
        Scanner readerTest = null;
        try {
            readerSolution = new Scanner(new File(filename));
            readerTest = new Scanner(new File(filename));
        } catch (IOException e) {
            System.out.println("Reading Oops");
        }


        InputStream stdin = System.in;
        try {

            NetworkSol solution = new NetworkSol(readerSolution);
            Network test = new Network(readerTest);

            solution.buildComputerNetwork();
            test.buildComputerNetwork();

            testBuildComputerNetwork(solution.getComputerConnections(), test.getComputerConnections());

            if (edgeCase == 4) {
                try {
                    test.buildCluster(clusterNum);
                } catch (Exception e) {
                    Assert.assertEquals("Ensure exception with the correct message is thrown when cluster cannot be generated !", "Cannot create clusters", e.getMessage());
                    return;
                }

                Assert.fail("Ensure exception with the correct message is thrown when cluster cannot be generated !");
            }

            solution.buildCluster(clusterNum);
            test.buildCluster(clusterNum);

            testClusterOrComputerGraph(solution.getComputerGraph(), test.getComputerGraph());
            testClusterOrComputerGraph(solution.getCluster(), test.getCluster());

            solution.connectCluster();
            test.connectCluster();

            testRouterGraph(solution.getRouterGraph(), test.getRouterGraph());

            int testNum = Integer.parseInt(readerSolution.nextLine());

            while (testNum > 0 && edgeCase == 0) {
                String testDijkstra = readerSolution.nextLine();
                Assert.assertEquals("Ensure you return the shortest distance in traverseNetwork !", solution.traversNetwork(testDijkstra), test.traversNetwork(testDijkstra));
                testNum--;
            }

            if (edgeCase == 1) {
                String testDijkstra = "53.43 53.27";
                Assert.assertEquals("Ensure you return the shortest distance when Src and Dest computer are in same router !", solution.traversNetwork(testDijkstra), test.traversNetwork(testDijkstra));
            } else if (edgeCase == 2) {
                String testDijkstra = "53.5 53.47";
                Assert.assertEquals("Ensure you return -1 when Src or Dest comp does not exist !", solution.traversNetwork(testDijkstra), test.traversNetwork(testDijkstra));
            } else if (edgeCase == 3) {
                String testDijkstra = "63.44 53.44";
                Assert.assertEquals("Ensure you return -1 when Src or Dest router does not exist !", solution.traversNetwork(testDijkstra), test.traversNetwork(testDijkstra));
            }

        } catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        } finally {
            System.setIn(stdin);
        }
    }

    /**
     * Helper function to test connectivity
     * of graphs generated by createTest
     */
    private void testConnectivity() {

        HashMap<Integer, Integer> nodeToIndex = new HashMap<>();
        HashMap<Integer, Integer> indexToNode = new HashMap<>();
        LinkedList<LinkedList<Integer>> graph = new LinkedList<>();

        Scanner reader = null;
        try {
            reader = new Scanner(new File("Advanced_K_Large.txt"));
        } catch (IOException e) {
            System.out.println("Reading Oops");
        }

        routerSkipper(reader);

        int numEdges = Integer.parseInt(reader.nextLine());
        int nodeCount = 0;

        for (int i = 0; i < numEdges; i++) {
            String[] in = reader.nextLine().split(" ");
            int a = Integer.parseInt(in[0]);
            int b = Integer.parseInt(in[1]);

            int aDex = -1;
            int bDex = -1;

            if (nodeToIndex.containsKey(a)) {
                aDex = nodeToIndex.get(a);
            } else {
                nodeToIndex.putIfAbsent(a, nodeCount);
                indexToNode.putIfAbsent(nodeCount, a);
                aDex = nodeCount;
                graph.add(new LinkedList<>());
                nodeCount++;
            }

            if (nodeToIndex.containsKey(b)) {
                bDex = nodeToIndex.get(b);
            } else {
                nodeToIndex.putIfAbsent(b, nodeCount);
                indexToNode.putIfAbsent(nodeCount, b);
                bDex = nodeCount;
                graph.add(new LinkedList<>());
                nodeCount++;
            }

            graph.get(aDex).add(bDex);
            graph.get(bDex).add(aDex);
        }

        boolean[] visited = new boolean[nodeCount];
        DFS(0, graph, visited);

        for (int i = 0; i < nodeCount; i++) {
            if (!visited[i])
                Assert.fail("Not connected");
        }
    }

    /**
     * Helper function to check connectivity
     *
     * @param cur     current node
     * @param graph   graph containing all nodes
     * @param visited visited boolean array
     */
    private void DFS(int cur, LinkedList<LinkedList<Integer>> graph, boolean[] visited) {

        visited[cur] = true;
        for (var i : graph.get(cur)) {
            if (!visited[i]) DFS(i, graph, visited);
        }
    }

    /**
     * Helper function in testConnectivity
     * Skips all computer edges and sets scanner to router edges
     *
     * @param s Scanner reading required file
     */
    private void routerSkipper(Scanner s) {

        int edges = Integer.parseInt(s.nextLine());

        while (edges > 0) {
            s.nextLine();
            edges--;
        }
    }

    /**
     * Helper function to test buildComputerNetwork()
     *
     * @param expected Expected computerConnections
     * @param actual   Actual computerConnections
     */
    private void testBuildComputerNetwork(LinkedList<Integer[]> expected, LinkedList<Integer[]> actual) {

        Assert.assertTrue("Ensure buildComputerNetwork adds all edges into computerConnections (computerConnections.size() is incorrect) !", expected.size() == actual.size());

        for (int i = 0; i < expected.size(); i++) {
            if (actual.get(i).length != 3) {
                System.out.println("Ensure edge consists of [source] [destination] and [latency] !");
                Assert.fail();
                break;
            }

            boolean correct = true;
            for (int j = 0; j < 3; j++) {
                if (!expected.get(i)[j].equals(actual.get(i)[j])) {
                    correct = false;
                }
            }

            Assert.assertTrue("Ensure all edges are added correctly and in the sequence given to you !", correct);
        }
    }

    //WORKS

    /**
     * Helper function to test either cluster or computerGraph of Network
     *
     * @param expectedCluster Expected cluster/computerGraph
     * @param actualCluster   Actual cluster/computerGraph
     */
    private void testClusterOrComputerGraph(LinkedList<LinkedList<Integer>> expectedCluster, LinkedList<LinkedList<Integer>> actualCluster) {

        Assert.assertTrue("Ensure that cluster is constructed correctly (cluster.size() is incorrect) !", expectedCluster.size() == actualCluster.size());

        HashSet<String> set = new HashSet<>();

        for (var i : expectedCluster) {
            String s = "";
            Collections.sort(i);
            for (var j : i) {
                s += j;
            }
            set.add(s);
        }

        for (var i : actualCluster) {
            String a = "";
            Collections.sort(i);
            for (var j : i) {
                a += j;
            }
            if (!set.contains(a)) {
                Assert.fail("Ensure that cluster has all the correct collections (Try printing it out) !");
            }
        }
    }

    /**
     * Helper function to test routerGraph of Network
     *
     * @param expectedGraph Expected routerGraph
     * @param actualGraph   Actual routerGraph
     */
    private void testRouterGraph(LinkedList<LinkedList<Integer[]>> expectedGraph, LinkedList<LinkedList<Integer[]>> actualGraph) {

        Assert.assertTrue("Ensure that routerGraph is constructed correctly (routerGraph.size() is incorrect) !", expectedGraph.size() == actualGraph.size());

        HashSet<String> lists = new HashSet<>();
        for (var i : expectedGraph) {
            Collections.sort(i, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return o1[0] - o2[0];
                }
            });
            String a = "";
            for (var j : i) {
                a += j[0] + " " + j[1];
            }
            lists.add(a);
        }

        for (var i : actualGraph) {
            Collections.sort(i, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return o1[0] - o2[0];
                }
            });
            String a = "";
            for (var j : i) {
                a += j[0] + " " + j[1];
            }
            if (!lists.contains(a)) {
                Assert.fail("Ensure that routerGraph has all correct edges and weights ( Try printing it out ) !");
            }
        }
    }

    /**
     * Function to create test graphs
     */
    private void createTest() {

        List<String> edges = nodeGenerator(50, 100, 80, 1000);
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("None.txt"));

            writer.write(edges.size() + "\n");

            for (var s : edges) {
                writer.write(s);
                writer.write("\n");
            }

            writer.flush();

        } catch (IOException e) {
            System.out.println("Writing Oops");
        }

        Scanner reader = null;
        try {
            reader = new Scanner(new File("None.txt"));
        } catch (IOException e) {
            System.out.println("Reading Oops");
        }

        String input = "";
        while (reader.hasNextLine()) {
            input += reader.nextLine();
            input += "\n";
        }

        InputStream stdin = System.in;
        try {

            System.setIn(new ByteArrayInputStream(input.getBytes()));
            NetworkSol solution = new NetworkSol(null);
            solution.buildComputerNetwork();
            LinkedList<Integer[]> computerConnections = solution.getComputerConnections();

            solution.buildCluster(7);
            LinkedList<LinkedList<Integer>> computerGraph = solution.getComputerGraph();
            LinkedList<LinkedList<Integer>> cluster = solution.getCluster();
            LinkedList<Integer> routerIPs = new LinkedList<>();

            System.out.println("\nCluster");

            for (int i = 0; i < cluster.size(); i++) {
                routerIPs.add(routerIP(cluster.get(i)));
                Collections.sort(cluster.get(i), Collections.reverseOrder());
                System.out.print(routerIPs.peekLast() + ": ");
                for (var j : cluster.get(i)) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }

            List<String> routerEdges = routerEdgeGenerator(routerIPs, 16, 100);

            try {

                writer.write(routerEdges.size() + "\n");
                for (var s : routerEdges) {
                    writer.write(s);
                    writer.write("\n");
                }

                writer.flush();

            } catch (IOException e) {
                System.out.println("Writing Oops");
                e.printStackTrace();
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("What happened here");
            e.printStackTrace();
        } finally {
            System.setIn(stdin);
        }
    }

    /**
     * Function to a generate a random collection of router edges for a router graph
     *
     * @param routerIp    Collection of routerIps of the router graph
     * @param numEdge     number of edges to be generated
     * @param weightLimit weight limit of edges to be generated
     * @return Collection of router edges
     */
    private List<String> routerEdgeGenerator(LinkedList<Integer> routerIp, int numEdge, int weightLimit) {

        LinkedList<String> routerEdges = new LinkedList<>();
        HashMap<Integer, Integer> nodeToIndex = new HashMap<>();
        HashMap<Integer, Integer> indexToNode = new HashMap<>();
        Random r = new Random();

        for (int i = 0; i < routerIp.size(); i++) {
            nodeToIndex.putIfAbsent(routerIp.get(i), i);
            indexToNode.putIfAbsent(i, routerIp.get(i));
            for (int j = i + 1; j < routerIp.size(); j++) {
                routerEdges.add(routerIp.get(i) + " " + routerIp.get(j));
                routerEdges.add(routerIp.get(j) + " " + routerIp.get(i));
            }
        }

        LinkedList<String> edges = new LinkedList<>();
        HashSet<String> edgeSet = new HashSet<>();
        HashSet<Integer> weights = new HashSet<>();
        UnionFind uf = new UnionFind(routerIp.size());

        for (int i = 0; i < numEdge; i++) {

            int index = r.nextInt(routerEdges.size());
            String[] pieces = routerEdges.get(index).split(" ");
            int a = Integer.parseInt(pieces[0]);
            int b = Integer.parseInt(pieces[1]);
            String s1 = pieces[1] + " " + pieces[0];
            routerEdges.remove(index);

            if (edgeSet.contains(s1)) {
                i--;
                continue;
            }

            if (i == numEdge - 1 && uf.components() != 1 && uf.find(nodeToIndex.get(a)) == uf.find(nodeToIndex.get(b))) {
                i--;
                continue;
            }

            int weight = r.nextInt(weightLimit - 1) + 1;
            while (weights.contains(weight)) {
                weight = r.nextInt(weightLimit - 1) + 1;
            }

            weights.add(weight);
            edgeSet.add(a + " " + b);
            edges.add(a + " " + b + " " + weight);
            uf.union(nodeToIndex.get(a), nodeToIndex.get(b));
        }
        return edges;
    }

    /**
     * Function to generate random collection of nodes for a graph and their corresponding edges
     *
     * @param numNode     number of nodes to be generated
     * @param nodeRange   range of nodes to be generated
     * @param numEdge     number of edges to be generated
     * @param weightLimit weight limit of edges to be generated
     * @return Collection of edges representing the graph
     */
    private List<String> nodeGenerator(int numNode, int nodeRange, int numEdge, int weightLimit) {

        Random r = new Random(9);
        Set<Integer> nodeSet = new HashSet<>();
        HashMap<Integer, Integer> nodeToIndex = new HashMap<>();
        HashMap<Integer, Integer> indexToNode = new HashMap<>();

        for (int i = 0; i < numNode; i++) {
            int node = r.nextInt(nodeRange - 1) + (nodeRange / 10);
            if (nodeSet.contains(node)) i--;
            else {
                nodeSet.add(node);
                nodeToIndex.putIfAbsent(node, i);
                indexToNode.putIfAbsent(i, node);
            }
        }

        Integer[] nodes = new Integer[nodeSet.size()];
        nodes = nodeSet.toArray(nodes);

        LinkedList<String> allEdges = new LinkedList<>();
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i + 1; j < nodes.length; j++) {
                allEdges.add(nodes[i] + " " + nodes[j]);
                allEdges.add(nodes[j] + " " + nodes[i]);
            }
        }

        Collections.shuffle(allEdges);
        LinkedList<String> edges = new LinkedList<>();
        HashSet<String> edgeSet = new HashSet<>();
        HashSet<Integer> weights = new HashSet<>();
        UnionFind uf = new UnionFind(numNode);

        for (int i = 0; i < numEdge; i++) {

            int index = r.nextInt(allEdges.size());
            String[] pieces = allEdges.get(index).split(" ");
            int a = Integer.parseInt(pieces[0]);
            int b = Integer.parseInt(pieces[1]);
            String s1 = pieces[1] + " " + pieces[0];
            allEdges.remove(index);

            if (edgeSet.contains(s1)) {
                i--;
                continue;
            }

            if (i == numEdge - 1 && uf.components() != 1 && uf.find(nodeToIndex.get(a)) == uf.find(nodeToIndex.get(b))) {
                i--;
                continue;
            }

            int weight = r.nextInt(weightLimit - 1) + 1;
            while (weights.contains(weight)) {
                weight = r.nextInt(weightLimit - 1) + 1;
            }

            weights.add(weight);
            edgeSet.add(a + " " + b);
            edges.add(a + " " + b + " " + weight);
            uf.union(nodeToIndex.get(a), nodeToIndex.get(b));
        }
        System.out.println("Components: " + uf.components());
        return edges;
    }

    /**
     * Helper function to find IP address of a cluster
     *
     * @param babyCluster cluster to find IP address of
     * @return IP address of cluster
     */
    private int routerIP(LinkedList<Integer> babyCluster) {

        int maxIP = -1;
        for (Integer c : babyCluster) {
            if (maxIP < c) maxIP = c;
        }
        return maxIP;
    }

    /*public static void main(String[] args) {
        NetworkTest test = new NetworkTest();
        LinkedList<LinkedList<Integer>> expected = new LinkedList<>();
        LinkedList<LinkedList<Integer>> actual = new LinkedList<>();
        Integer[] arr1 = {1, 2, 7, 23, 65, 767, 43154, 253, 65};
        Integer[] arr2 = {34, 546, 78, 123, 6456, 878, 234, 6, 867, 24};
        Integer[] arr3 = {23, 45, 13, 87, 23467, 8, 234, 78};
        Integer[] arr4 = {56, 234467, 5688, 723423, 7678, 24, 121, 545, 76};

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

        for (var i : expected) {
            for (var j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (var i : actual) {
            for (var j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        test.testClusterOrComputerGraph(expected, actual);

    }*/
}