package aug12;

public class Client {

	public static void main(String[] args) {
		System.out.println();
		String s = new String();
		System.out.println(s.length());
		
		Graph g = new Graph();

		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addVertex("G");

		g.addEdge("A", "B", 10);
		g.addEdge("A", "D", 40);
		g.addEdge("B", "C", 10);
		g.addEdge("C", "D", 10);
		g.addEdge("D", "E", 2);
		g.addEdge("E", "F", 3);
		g.addEdge("E", "G", 1);
//		g.addEdge("F", "G", 3);

		g.display();

		System.out.println(g.djikstra("B"));
		
		System.out.println(g.IsBipartite());
		
		System.out.println("----------------------------------------------");
//		Graph mst = g.prims();
//		mst.display();

	}

}
