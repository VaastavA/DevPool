import java.util.*;

/**
 * A simple Network class to build a network
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version July 9th 2019
 */
public class NetworkSol {

    private HashMap<Integer, Integer> compToIndex; // Map computer IP address to Index
    private HashMap<Integer, Integer> indexToComp; // Map Index to computer IP address
    /**
     * computerConnections represents list of all inter-computer edges
     * Each edge is an Integer[] of size 3
     * edge[0] = source computer index ( Not IP, it's the Index !)
     * edge[1] = destination computer index ( Not IP, it's the Index !)
     * edge[2] = latency/edge weight
     */
    private LinkedList<Integer[]> computerConnections;
    /**
     * Adjacency List representing computer graph
     * Adjacency List representing computer graph
     */
    private LinkedList<LinkedList<Integer>> computerGraph;
    private HashMap<Integer, Router> IpToRouter; // Map IP of router to Router Object
    private HashMap<Router, Integer> routerToIndex; // Map Router Object to Index
    private HashMap<Integer, Router> indexToRouter; // Map Index to Router Object
    /**
     * LinkedList of clusters where each cluster is represented as a LinkedList of computer IP addresses
     */
    private LinkedList<LinkedList<Integer>> cluster;
    /**
     * Adjacency List representing router graph
     */
    private LinkedList<LinkedList<Integer[]>> routerGraph;

    private int verticesComp; // Number of Computers
    private int verticesRouter; // Number of Routers

    Scanner s; // Scanner to read Stdin input


    /**
     * Default Network constructor, initializes data structures
     * @param s Provided Scanner to be used throughout program
     */
    public NetworkSol(Scanner s) {

        compToIndex = new HashMap<>();
        indexToComp = new HashMap<>();
        computerConnections = new LinkedList<>();
        computerGraph = new LinkedList<>();

        routerToIndex = new HashMap<>();
        indexToRouter = new HashMap<>();
        IpToRouter = new HashMap<>();
        cluster = new LinkedList<>();
        routerGraph = new LinkedList<>();

        verticesComp = 0;
        verticesRouter = 0;

        this.s = s;
    }

    /**
     * Method to parse Stdin input and generate inter-computer edges
     * Edges are stored within computerConnections
     *
     * First line of input => Number of edges
     * All subsequent lines => [IP address of comp 1] [IP address of comp 2] [latency of connection]
     */
    public void buildComputerNetwork() {

        int edges = Integer.parseInt(s.nextLine()); // Number of edges

        for (int i = 0; i < edges; i++) {
            String[] words = s.nextLine().split(" ");

            int src, dest;

            int val1 = Integer.parseInt(words[0]); // IP address of comp 1
            int val2 = Integer.parseInt(words[1]); // IP address of comp 1
            int latency = Integer.parseInt(words[2]); // Latency of connection

            /**
             * Check if IP address exists within compToIndex map
             * If it does => fetch its assigned index
             * else => assign the current computer count (verticesComp) as the index and add to map
             *
             * Perform for both computers
             */
            if (compToIndex.containsKey(val1)) src = compToIndex.get(val1);
            else {
                src = verticesComp;
                compToIndex.putIfAbsent(val1, verticesComp);
                indexToComp.putIfAbsent(verticesComp, val1);
                verticesComp++;
            }

            if (compToIndex.containsKey(val2)) dest = compToIndex.get(val2);
            else {
                dest = verticesComp;
                compToIndex.putIfAbsent(val2, verticesComp);
                indexToComp.putIfAbsent(verticesComp,val2);
                verticesComp++;
            }

            Integer[] edge = {src, dest, latency};
            computerConnections.add(edge);  // Add edge to computerConnections
        }

    }

    /**
     * Method to generate clusters from computer graph
     * @param k number of clusters to be created
     */
    public void buildCluster(int k) {

        UnionFind set = new UnionFind(verticesComp);    // Union-Find for connectivity

        /**
         * PriorityQueue of all edges such that edges are sorted based on edge weights ( latency )
         * Custom Comparator used to do so
         */
        PriorityQueue<Integer[]> edges = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[2] - o2[2];
            }
        });

        //Add edged to PriorityQueue
        for(Integer[] edge: computerConnections){
            edges.add(edge);
        }

        // Initialize Adjacency List of computer graph
        for (int i = 0; i < verticesComp; i++) {
            computerGraph.add(new LinkedList<>());
        }

        while (set.components() > k) { // While Union-Find connected components are less than required clusters

            Integer[] temp = edges.poll();
            int src = temp[0];  //Computer 1 index
            int dest = temp[1]; //Computer 2 index

            if (set.find(src) != set.find(dest)) { // If computers not connected
                set.union(src, dest);
                computerGraph.get(src).add(dest);   //Add comp 2 to adjacency list of comp 1
                computerGraph.get(dest).add(src);   //Add comp 1 to adjacency list of comp 2
            }
        }

        /**
         * After running Kruskal, we detect the connected components ( clusters )
         * using DFS and assign the IP address with the highest value within a cluster
         * as the IP address of the router
         *
         * Eg: cluster => 3, 6, 23, 16
         *  23 would be IP address of the router
         */

        boolean[] visited = new boolean[verticesComp];  //Visited bool array for DFS
        int clusterNum = 0; // Counter to keep track of clusters
        for (int i = 0; i < verticesComp; i++) {
            if (!visited[i]) {
                cluster.add(new LinkedList<>());
                DFS(i, visited, clusterNum);
                clusterNum++;
            }
        }

        /**
         * Creating routers from clusters and assigning
         * them IP addresses
         *
         * Also create Router to Index and Index to Router maps
         */

        LinkedList<Integer> indexes = new LinkedList<>();
        for (int i = 0; i < cluster.size(); i++) {

            int index = routerIP(cluster.get(i));
            Router router = new Router(index);
            indexes.add(index);
            IpToRouter.putIfAbsent(index,router);

            for (Integer c : cluster.get(i)) {
                router.addComp(c);
            }
        }

        Collections.sort(indexes);

        for(int i=0;i<indexes.size();i++){
            Router router = IpToRouter.get(indexes.get(i));
            routerToIndex.putIfAbsent(router, i);
            indexToRouter.putIfAbsent(i, router);
        }


        verticesRouter = cluster.size();

    }

    /**
     * Method to parse Stdin input and generate inter-router edges
     * Graph is stored within routerGraph as an adjacency list
     *
     * First line of input => Number of edges
     * All subsequent lines => [IP address of Router 1] [IP address of Router 2] [latency of connection]
     */
    public void connectCluster() {

        //Initialize Router Graph
        for(int i=0;i<cluster.size();i++){
            routerGraph.add(new LinkedList<>());
        }

        int edges = Integer.parseInt(s.nextLine()); // Number of edges

        for (int i = 0; i < edges; i++) {
            String[] words = s.nextLine().split(" ");

            int src, dest;

            int val1 = routerToIndex.get(IpToRouter.get(Integer.parseInt(words[0]))); // Index of Router 1 from it's IP
            int val2 = routerToIndex.get(IpToRouter.get(Integer.parseInt(words[1]))); // Index of Router 2 from it's IP
            int latency = Integer.parseInt(words[2]); // Latency of connection

            /**
             * Constructing edges and adding
             * to respective Adj Lists
             */
            Integer[] temp1 = {val2, latency};
            Integer[] temp2 = {val1, latency};
            routerGraph.get(val1).add(temp1);
            routerGraph.get(val2).add(temp2);
        }
    }

    /**
     * Method to take a traversal request and find the shortest path for that traversal
     * Traversal request passed in through parameter test
     * Format of Request => [IP address of Source Router].[IP address of Source Computer] [IP address of Destination Router].[IP address of Destination Computer]
     * Eg. 123.456 128.192
     *  123 = IP address of Source Router
     *  456 = IP address of Source Computer
     *  128 = IP address of Destination Router
     *  192 = IP address of Destination Computer
     * @param test String containing traversal input
     * @return Shortest traversal distance between Source and Destination Computer
     */
    public int traversNetwork(String test) {

        //Parse Input
        String[] locations = test.split(" ");
        String[] srcIPs = locations[0].split("\\.");
        String[] destIPs = locations[1].split("\\.");


        int srcRouterIP = Integer.parseInt(srcIPs[0]); // Source Router IP
        int srcCompIP = Integer.parseInt(srcIPs[1]); // Source Computer IP

        int destRouterIP = Integer.parseInt(destIPs[0]); // Destination Router IP
        int destCompIP = Integer.parseInt(destIPs[1]); // Destination Computer IP

        Router src = IpToRouter.get(srcRouterIP);   //Router Object of source
        Router dest = IpToRouter.get(destRouterIP); //Router Object of destination

        if( !src.checkComp(srcCompIP) || !dest.checkComp(destCompIP) ){ //Check if computers exists within Routers
            return -1;
        }

        /**
         * Perform Dijkstra's algorithm to find shortest path
         */
        int[] dist = new int[verticesRouter];
        Set<Integer> settled = new HashSet<>();
        PriorityQueue<Integer[]>  pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1]-o2[1];
            }
        });

        int srcIndex = routerToIndex.get(src);  //Index of source router
        int destIndex = routerToIndex.get(dest); //Index of source router

        for (int i = 0; i < verticesRouter; i++)
            dist[i] = Integer.MAX_VALUE;

        Integer[] temp = {srcIndex,0};
        pq.add(temp);

        // Distance to the source is 0
        dist[srcIndex] = 0;
        while (settled.size() != verticesRouter) {

            // remove the minimum distance node
            // from the priority queue
            Integer[] node = pq.poll();
            int nodeIndex = node[0];
            int distToNode = node[1];
            // adding the node whose distance is
            // finalized
            settled.add(nodeIndex);

            int edgeDistance = -1;
            int newDistance = -1;

            // All the neighbors of v
            for (int i = 0; i < routerGraph.get(nodeIndex).size(); i++) {
                Integer[] v = routerGraph.get(nodeIndex).get(i);

                // If current node hasn't already been processed
                if (!settled.contains(v[0])) {
                    edgeDistance = v[1];
                    newDistance = dist[nodeIndex] + edgeDistance;

                    // If new distance is cheaper in cost
                    if (newDistance < dist[v[0]])
                        dist[v[0]] = newDistance;

                    // Add the current node to the queue
                    Integer[] toAdd = {v[0], dist[v[0]]};
                    pq.add(toAdd);
                }
            }
        }

        return dist[destIndex];
    }


    /**
     * Helper DFS function to detect connected components
     * @param curr  current computer Node
     * @param visited   visited boolean array
     * @param clusterNum Cluster Number current computer Node belongs to
     */
    private void DFS(int curr, boolean[] visited, int clusterNum) {

        visited[curr] = true;
        cluster.get(clusterNum).add(indexToComp.get(curr));
        for (int x : computerGraph.get(curr)) {
            if (!visited[x]) DFS(x, visited, clusterNum);
        }
    }

    /**
     * Helper method to find highest IP address value within cluster
     * @param babyCluster Cluster to find IP address of
     * @return Highest IP address of all computers within cluster
     */
    private int routerIP(LinkedList<Integer> babyCluster) {

        int maxIP = -1;
        for (Integer c : babyCluster) {
            if (maxIP < c) maxIP = c;
        }
        return maxIP;
    }

    public LinkedList<Integer[]> getComputerConnections() {
        return computerConnections;
    }

    public LinkedList<LinkedList<Integer>> getComputerGraph() {
        return computerGraph;
    }

    public LinkedList<LinkedList<Integer>> getCluster() {
        return cluster;
    }

    public LinkedList<LinkedList<Integer[]>> getRouterGraph() {
        return routerGraph;
    }

}
