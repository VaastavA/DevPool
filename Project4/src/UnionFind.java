
/**
 * A simple Union-Find class to build a network
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version July 9th 2019
 */

public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFind(int n) {
        if ( n< 0 ) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p,int q){
        int rootP = find(p);
        int rootQ = find(q);
        if( rootP == rootQ) return;

        if(rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if(rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }

        count--;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int components(){
        return count;
    }
}
