package hw3;


import java.util.*;

/**
 * A undirected graph class
 * 
 * Also includes functions for running dfs and bfs
 *
 */
public class Graph {

    private int n; //number of vertices
    private LinkedList<Integer>[] adj; //adjacency list

    /**
     * Constructs a graph with n vertices (containing no edges)
     * 
     * @param n - the number of vertices in the graph
     */
    @SuppressWarnings("unchecked")
    public Graph(int n) {
	this.n = n;
	adj = (LinkedList<Integer>[]) new LinkedList[n];
	for (int i = 0; i < n; i++) {
	    adj[i] = new LinkedList<>();
	}
    }

    /**
     * add an edge between vertices v and w
     */
    public void addEdge(int v, int w) {
	adj[v].add(w); //add w to v's adjacency list
	adj[w].add(v);
    }

    /**
     * outputs the neighbors of a vertex v
     */
    public List<Integer> neighbors(int v) {
	return adj[v];
    }

    /**
     * @return the number of vertices in the graph
     */
    public int size() {
	return n;
    }

/**
 * 
 * @param s
 * @param t
 * @return
 */
    public int numShortestPaths(int s, int t) {
	int val [] = new int[size()]; //val is the shortest distance from src to x
	int count [] = new int[size()]; //count is the number of shortest path from src to x
	Queue<Integer> toVisit = new LinkedList<Integer>();
	HashSet<Integer> visited = new HashSet<Integer>();
	toVisit.add(s);
	val [s] = 0;
	count [s] = 1;
	while(toVisit.size() > 0){
	    int current = toVisit.poll();
	    visited.add(current);
	    if(current == t){
		break;
	    }

	    for(int neighbor : neighbors(current)){
		/*
		 * 1 CASE: A node is visited first time so it has only one path 
		 * from source till now via u, so shortest path upto v is 
		 * 1 + shortest path upto u, and number of ways to reach v 
		 * via shortest path is same as count[u] because say u has 
		 * 5 ways to reach from source, then only these 5 ways can 
		 * be extended upto v as v is encountered first time via u. 
		 * 
		 */
		if(!visited.contains(neighbor) && !toVisit.contains(neighbor)){
		    val[neighbor] = val[current] + 1;
		    count [neighbor] = count[current];
		    toVisit.add(neighbor);
		}else{
		    if(val[neighbor] == val[current] + 1){
			count[neighbor] = count[neighbor] + count[current];
		    }
		}
	    }


	}

	return count[t];
    }
    


}


