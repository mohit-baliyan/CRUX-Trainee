package aug26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	private class Vertex {
		HashMap<String, Integer> edges = new HashMap<>();
	}

	private HashMap<String, Vertex> vces = new HashMap<>();

	public int numVertices() {
		return vces.size();
	}

	public boolean containsVertex(String vname) {
		return vces.containsKey(vname);
	}

	public void addVertex(String vname) {
		if (containsVertex(vname)) {
			return;
		}

		Vertex vtx = new Vertex();
		vces.put(vname, vtx);
	}

	public void removeVertex(String vname) {
		if (this.containsVertex(vname) == false) {
			return;
		}

		Vertex vtx = vces.get(vname);
		ArrayList<String> nbrnames = new ArrayList<>(vtx.edges.keySet());
		for (String nbrname : nbrnames) {
			Vertex nbrvtx = vces.get(nbrname);
			nbrvtx.edges.remove(vname);
		}

		vces.remove(vname);
	}

	public int numEdges() {
		int rv = 0;

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			Vertex vtx = vces.get(vname);
			rv += vtx.edges.size();
		}

		return rv / 2;
	}

	public boolean containsEdge(String v1name, String v2name) {
		Vertex vtx1 = vces.get(v1name);
		Vertex vtx2 = vces.get(v2name);

		if (vtx1 == null || vtx2 == null) {
			return false;
		}

		return vtx1.edges.containsKey(v2name);
	}

	public void addEdge(String v1name, String v2name, int weight) {
		Vertex vtx1 = vces.get(v1name);
		Vertex vtx2 = vces.get(v2name);

		if (vtx1 == null || vtx2 == null) {
			return;
		}

		vtx1.edges.put(v2name, weight);
		vtx2.edges.put(v1name, weight);
	}

	public void removeEdge(String v1name, String v2name) {
		Vertex vtx1 = vces.get(v1name);
		Vertex vtx2 = vces.get(v2name);

		if (vtx1 == null || vtx2 == null) {
			return;
		}

		vtx1.edges.remove(v2name);
		vtx2.edges.remove(v1name);
	}

	public void display() {
		System.out.println("------------------------------------------------------");

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			System.out.println(vname + " => " + vces.get(vname).edges);
		}

		System.out.println("------------------------------------------------------");
	}

	public boolean hasPath(String v1name, String v2name) {
		return hasPathHelper(v1name, v2name, new HashMap<>(), v1name);
	}

	private boolean hasPathHelper(String v1name, String v2name, HashMap<String, Boolean> processed, String psf) {
		if (processed.containsKey(v1name) == true) {
			return false;
		} else {
			processed.put(v1name, true);
		}

		if (v1name.equals(v2name) == true) {
			System.out.println(psf);
			processed.remove(v1name);
			return true;
		}

		Vertex vtx1 = vces.get(v1name);
		ArrayList<String> nbrnames = new ArrayList<>(vtx1.edges.keySet());

		boolean rv = false;
		for (String nbrname : nbrnames) {
			rv = hasPathHelper(nbrname, v2name, processed, psf + nbrname) || rv;
		}

		processed.remove(v1name);

		return rv;
	}

	public boolean bfs(String v1name, String v2name) {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		Pair pair = new Pair(v1name, v1name);
		queue.addLast(pair);

		while (queue.size() != 0) {
			// 1. remove
			pair = queue.removeFirst();

			// 2. print
			System.out.println(pair.vname + " via " + pair.psf);
			if (pair.vname.equals(v2name)) {
				return true;
			}

			// 3. mark processed
			processed.put(pair.vname, true);

			// 4. enqueue unmarked nbrs
			Vertex pairvtx = vces.get(pair.vname);
			ArrayList<String> nbrnames = new ArrayList<>(pairvtx.edges.keySet());
			for (String nbrname : nbrnames) {
				if (!processed.containsKey(nbrname)) {
					Pair nbrpair = new Pair(nbrname, pair.psf + nbrname);
					queue.addLast(nbrpair);
				}
			}
		}

		return false;
	}

	public boolean isConnected() {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		String v1name = vnames.get(0);

		Pair pair = new Pair(v1name, v1name);
		queue.addLast(pair);

		while (queue.size() != 0) {
			// 1. remove
			pair = queue.removeFirst();

			// 2. print
			System.out.println(pair.vname + " via " + pair.psf);

			// 3. mark processed
			processed.put(pair.vname, true);

			// 4. enqueue unmarked nbrs
			Vertex pairvtx = vces.get(pair.vname);
			ArrayList<String> nbrnames = new ArrayList<>(pairvtx.edges.keySet());
			for (String nbrname : nbrnames) {
				if (!processed.containsKey(nbrname)) {
					Pair nbrpair = new Pair(nbrname, pair.psf + nbrname);
					queue.addLast(nbrpair);
				}
			}
		}

		if (processed.size() == vces.size()) {
			return true;
		} else {
			return false;
		}
	}

	public void bft() {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (int i = 0; i < vnames.size(); i++) {
			String v1name = vnames.get(i);
			if (processed.containsKey(v1name)) {
				continue;
			}

			Pair pair = new Pair(v1name, v1name);
			queue.addLast(pair);
			while (queue.size() != 0) {
				// 1. remove
				pair = queue.removeFirst();

				// 1`. processed, continue
				if (processed.containsKey(pair.vname)) {
					continue;
				}

				// 2. print
				System.out.println(pair.vname + " via " + pair.psf);

				// 3. mark processed
				processed.put(pair.vname, true);

				// 4. enqueue unmarked nbrs
				Vertex pairvtx = vces.get(pair.vname);
				ArrayList<String> nbrnames = new ArrayList<>(pairvtx.edges.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair nbrpair = new Pair(nbrname, pair.psf + nbrname);
						queue.addLast(nbrpair);
					}
				}
			}
		}
	}

	public boolean isCyclic() {
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (int i = 0; i < vnames.size(); i++) {
			String v1name = vnames.get(i);
			if (processed.containsKey(v1name)) {
				continue;
			}

			Pair pair = new Pair(v1name, v1name);
			queue.addLast(pair);
			while (queue.size() != 0) {
				// 1. remove
				pair = queue.removeFirst();

				// 1`. processed, continue
				if (processed.containsKey(pair.vname)) {
					System.out.println(pair.vname + " via " + pair.psf);
					return true;
				}

				// 2. print
				System.out.println(pair.vname + " via " + pair.psf);

				// 3. mark processed
				processed.put(pair.vname, true);

				// 4. enqueue unmarked nbrs
				Vertex pairvtx = vces.get(pair.vname);
				ArrayList<String> nbrnames = new ArrayList<>(pairvtx.edges.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair nbrpair = new Pair(nbrname, pair.psf + nbrname);
						queue.addLast(nbrpair);
					}
				}
			}
		}

		return false;
	}

	public ArrayList<String> gcc() {
		ArrayList<String> listcomp = new ArrayList<>();
		
		LinkedList<Pair> queue = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (int i = 0; i < vnames.size(); i++) {
			String v1name = vnames.get(i);
			if (processed.containsKey(v1name)) {
				continue;
			}

			Pair pair = new Pair(v1name, v1name);
			queue.addLast(pair);
			
			String comp = "";
			while (queue.size() != 0) {
				// 1. remove
				pair = queue.removeFirst();

				// 1`. processed, continue
				if (processed.containsKey(pair.vname)) {
					continue;
				}

				// 2. print
				System.out.println(pair.vname + " via " + pair.psf);
				comp += pair.vname;

				// 3. mark processed
				processed.put(pair.vname, true);

				// 4. enqueue unmarked nbrs
				Vertex pairvtx = vces.get(pair.vname);
				ArrayList<String> nbrnames = new ArrayList<>(pairvtx.edges.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair nbrpair = new Pair(nbrname, pair.psf + nbrname);
						queue.addLast(nbrpair);
					}
				}
			}
			listcomp.add(comp);
		}
		
		return listcomp;
	}

	private class Pair {
		String vname;
		String psf;

		public Pair(String vname, String psf) {
			this.vname = vname;
			this.psf = psf;
		}
	}

	public boolean dfs(String v1name, String v2name) {
		LinkedList<Pair> stack = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		Pair pair = new Pair(v1name, v1name);
		stack.addFirst(pair);

		while (stack.size() != 0) {
			// 1. remove
			pair = stack.removeFirst();

			// 2. print
			System.out.println(pair.vname + " via " + pair.psf);
			if (pair.vname.equals(v2name)) {
				return true;
			}

			// 3. mark processed
			processed.put(pair.vname, true);

			// 4. enqueue unmarked nbrs
			Vertex pairvtx = vces.get(pair.vname);
			ArrayList<String> nbrnames = new ArrayList<>(pairvtx.edges.keySet());
			for (String nbrname : nbrnames) {
				if (!processed.containsKey(nbrname)) {
					Pair nbrpair = new Pair(nbrname, pair.psf + nbrname);
					stack.addFirst(nbrpair);
				}
			}
		}

		return false;
	}
	
	public void dft() {
		LinkedList<Pair> stack = new LinkedList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (int i = 0; i < vnames.size(); i++) {
			String v1name = vnames.get(i);
			if (processed.containsKey(v1name)) {
				continue;
			}

			Pair pair = new Pair(v1name, v1name);
			stack.addFirst(pair);
			while (stack.size() != 0) {
				// 1. remove
				pair = stack.removeFirst();

				// 1`. processed, continue
				if (processed.containsKey(pair.vname)) {
					continue;
				}

				// 2. print
				System.out.println(pair.vname + " via " + pair.psf);

				// 3. mark processed
				processed.put(pair.vname, true);

				// 4. enqueue unmarked nbrs
				Vertex pairvtx = vces.get(pair.vname);
				ArrayList<String> nbrnames = new ArrayList<>(pairvtx.edges.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair nbrpair = new Pair(nbrname, pair.psf + nbrname);
						stack.addFirst(nbrpair);
					}
				}
			}
		}
	}


}
