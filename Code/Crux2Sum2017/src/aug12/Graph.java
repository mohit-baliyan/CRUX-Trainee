package aug12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import july31.GenericHeap;

public class Graph {
	private class Vertex {
		HashMap<String, Integer> nbrs = new HashMap<>();
	}

	private HashMap<String, Vertex> vces = new HashMap<>();

	// o1
	public int numVertices() {
		return vces.size();
	}

	// o1
	public boolean containsVertex(String vname) {
		return vces.containsKey(vname);
	}

	// o1
	public void addVertex(String vname) {
		if (this.containsVertex(vname)) {
			return;
		}

		Vertex vtx = new Vertex();
		vces.put(vname, vtx);
	}

	// ov
	public void removeVertex(String vname) {
		if (!this.containsVertex(vname)) {
			return;
		}

		Vertex vtx = vces.get(vname);
		ArrayList<String> nbrnames = new ArrayList<>(vtx.nbrs.keySet());
		for (String nbrname : nbrnames) {
			Vertex nbrvtx = vces.get(nbrname);
			nbrvtx.nbrs.remove(vname);
		}

		vces.remove(vname);
	}

	// ov
	public int numEdges() {
		int rv = 0;

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			Vertex vtx = vces.get(vname);
			rv += vtx.nbrs.size();
		}

		return rv / 2;
	}

	// o1
	public boolean containsEdge(String v1name, String v2name) {
		Vertex vtx1 = vces.get(v1name);
		Vertex vtx2 = vces.get(v2name);

		if (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(v2name) == false) {
			return false;
		}

		return true;
	}

	// o1
	public void addEdge(String v1name, String v2name, int weight) {
		Vertex vtx1 = vces.get(v1name);
		Vertex vtx2 = vces.get(v2name);

		if (vtx1 == null || vtx2 == null) {
			return;
		}

		vtx1.nbrs.put(v2name, weight);
		vtx2.nbrs.put(v1name, weight);
	}

	// o1
	public void removeEdge(String v1name, String v2name) {
		Vertex vtx1 = vces.get(v1name);
		Vertex vtx2 = vces.get(v2name);

		if (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(v2name) == false) {
			return;
		}

		vtx1.nbrs.remove(v2name);
		vtx2.nbrs.remove(v1name);
	}

	// o (v + e)
	public void display() {
		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			String str = vname + " => ";
			Vertex vtx = vces.get(vname);

			ArrayList<String> nbrnames = new ArrayList<>(vtx.nbrs.keySet());
			for (String nbrname : nbrnames) {
				str += nbrname + "[" + vtx.nbrs.get(nbrname) + "], ";
			}

			System.out.println(str + ".");
		}
	}

	// ov
	public boolean hasPath(String v1name, String v2name) {
		return hasPathHelper(v1name, v2name, new HashMap<>(), v1name);
	}

	private boolean hasPathHelper(String v1name, String v2name, HashMap<String, Boolean> processed, String psf) {
		if (processed.containsKey(v1name) == true) {
			return false;
		}
		processed.put(v1name, true);

		if (this.containsEdge(v1name, v2name) == true) { // if they are nbrs we
															// found a path
			System.out.println(psf + v2name);
			return true;
		}

		Vertex vtx1 = vces.get(v1name);
		ArrayList<String> nbrnames = new ArrayList<>(vtx1.nbrs.keySet());

		for (String nbrname : nbrnames) {
			if (this.hasPathHelper(nbrname, v2name, processed, psf + nbrname) == true) {
				return true;
			}
		}

		return false;
	}

	// o (v + distinct cycles)
	public boolean bfs(String v1name, String v2name) {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		Pair rootpair = new Pair(v1name, v1name);
		queue.addLast(rootpair);

		while (queue.size() != 0) {
			// 1. removeFirst
			Pair rp = queue.removeFirst();

			// 2. check if processed, mark if not
			if (processed.containsKey(rp.vname)) {
				continue;
			}
			processed.put(rp.vname, true);

			// 3. Check, if an edge is found
			System.out.println(rp.vname + " via " + rp.psf);
			if (this.containsEdge(rp.vname, v2name) == true) {
				return true;
			}

			// 4. Add the unprocessed nbrs back
			ArrayList<String> nbrnames = new ArrayList<>(rp.vtx.nbrs.keySet());
			for (String nbrname : nbrnames) {
				if (!processed.containsKey(nbrname)) {
					Pair np = new Pair(nbrname, rp.psf + nbrname);
					queue.addLast(np);
				}
			}
		}

		return false;
	}

	// o (v + distinct cycles)
	public boolean dfs(String v1name, String v2name) {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> stack = new LinkedList<>();

		Pair rootpair = new Pair(v1name, v1name);
		stack.addFirst(rootpair);

		while (stack.size() != 0) {
			// 1. removeFirst
			Pair rp = stack.removeFirst();

			// 2. check if processed, mark if not
			if (processed.containsKey(rp.vname)) {
				continue;
			}
			processed.put(rp.vname, true);

			// 3. Check, if an edge is found
			System.out.println(rp.vname + " via " + rp.psf);
			if (this.containsEdge(rp.vname, v2name) == true) {
				return true;
			}

			// 4. Add the unprocessed nbrs back
			ArrayList<String> nbrnames = new ArrayList<>(rp.vtx.nbrs.keySet());
			for (String nbrname : nbrnames) {
				if (!processed.containsKey(nbrname)) {
					Pair np = new Pair(nbrname, rp.psf + nbrname);
					stack.addFirst(np);
				}
			}
		}

		return false;
	}

	// o (v + distinct cycles)
	public void bft() {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			if (processed.containsKey(vname)) {
				continue;
			}

			Pair rootpair = new Pair(vname, vname);
			queue.addLast(rootpair);
			while (queue.size() != 0) {
				// 1. removeFirst
				Pair rp = queue.removeFirst();

				// 2. check if processed, mark if not
				if (processed.containsKey(rp.vname)) {
					continue;
				}
				processed.put(rp.vname, true);

				// 3. Check, if an edge is found
				System.out.println(rp.vname + " via " + rp.psf);

				// 4. Add the unprocessed nbrs back
				ArrayList<String> nbrnames = new ArrayList<>(rp.vtx.nbrs.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair np = new Pair(nbrname, rp.psf + nbrname);
						queue.addLast(np);
					}
				}
			}
		}
	}

	// o (v + distinct cycles)
	public void dft() {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> stack = new LinkedList<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			if (processed.containsKey(vname)) {
				continue;
			}

			Pair rootpair = new Pair(vname, vname);
			stack.addFirst(rootpair);
			while (stack.size() != 0) {
				// 1. removeFirst
				Pair rp = stack.removeFirst();

				// 2. check if processed, mark if not
				if (processed.containsKey(rp.vname)) {
					continue;
				}
				processed.put(rp.vname, true);

				// 3. Check, if an edge is found
				System.out.println(rp.vname + " via " + rp.psf);

				// 4. Add the unprocessed nbrs back
				ArrayList<String> nbrnames = new ArrayList<>(rp.vtx.nbrs.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair np = new Pair(nbrname, rp.psf + nbrname);
						stack.addFirst(np);
					}
				}
			}
		}
	}

	private class Pair {
		String vname;
		Vertex vtx;
		String psf;
		String color;

		Pair(String vname, String psf) {
			this.vname = vname;
			this.vtx = vces.get(vname);
			this.psf = psf;
		}
	}

	// o (v + distinct cycles)
	public boolean IsConnected() {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();
		int counter = 0;

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			if (processed.containsKey(vname)) {
				continue;
			}

			counter++;
			Pair rootpair = new Pair(vname, vname);
			queue.addLast(rootpair);
			while (queue.size() != 0) {
				// 1. removeFirst
				Pair rp = queue.removeFirst();

				// 2. check if processed, mark if not
				if (processed.containsKey(rp.vname)) {
					continue;
				}
				processed.put(rp.vname, true);

				// 3. Check, if an edge is found
				System.out.println(rp.vname + " via " + rp.psf);

				// 4. Add the unprocessed nbrs back
				ArrayList<String> nbrnames = new ArrayList<>(rp.vtx.nbrs.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair np = new Pair(nbrname, rp.psf + nbrname);
						queue.addLast(np);
					}
				}
			}
		}

		return counter == 1;
	}

	// o (v + distinct cycles)
	public boolean IsCyclic() {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			if (processed.containsKey(vname)) {
				continue;
			}

			Pair rootpair = new Pair(vname, vname);
			queue.addLast(rootpair);
			while (queue.size() != 0) {
				// 1. removeFirst
				Pair rp = queue.removeFirst();

				// 2. check if processed, mark if not
				if (processed.containsKey(rp.vname)) {
					System.out.println(rp.vname + " via " + rp.psf);
					return true;
				}
				processed.put(rp.vname, true);

				// 3. Check, if an edge is found
				System.out.println(rp.vname + " via " + rp.psf);

				// 4. Add the unprocessed nbrs back
				ArrayList<String> nbrnames = new ArrayList<>(rp.vtx.nbrs.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair np = new Pair(nbrname, rp.psf + nbrname);
						queue.addLast(np);
					}
				}
			}
		}

		return false;
	}

	// o (v + distinct cycles)
	public boolean IsTree() {
		return this.IsConnected() && !this.IsCyclic();
	}

	public boolean IsBipartite() {
		HashMap<String, String> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			if (processed.containsKey(vname)) {
				continue;
			}

			Pair rootpair = new Pair(vname, vname);
			rootpair.color = "red";
			queue.addLast(rootpair);
			while (queue.size() != 0) {
				// 1. removeFirst
				Pair rp = queue.removeFirst();

				// 2. check if processed, mark if not
				if (processed.containsKey(rp.vname)) {
					String oldcolor = processed.get(rp.vname);
					String newcolor = rp.color;
					
					if(!oldcolor.equals(newcolor)){
						return false;
					}
					
					continue;
				}
				processed.put(rp.vname, rp.color);

				// 3. Check, if an edge is found
				System.out.println(rp.vname + " via " + rp.psf);

				// 4. Add the unprocessed nbrs back
				ArrayList<String> nbrnames = new ArrayList<>(rp.vtx.nbrs.keySet());
				for (String nbrname : nbrnames) {
					if (!processed.containsKey(nbrname)) {
						Pair np = new Pair(nbrname, rp.psf + nbrname);
						
						if(rp.color.equals("red")){
							np.color = "green";
						} else {
							np.color = "red";
						}
						
						queue.addLast(np);
					}
				}
			}
		}
		
		return true;
	}
	
	// o ((e + v)logv)
	public HashMap<String, DjikstraPair> djikstra(String src) {
		HashMap<String, DjikstraPair> map = new HashMap<>();
		GenericHeap<DjikstraPair> heap = new GenericHeap<>(DjikstraPair.Ctor);

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			DjikstraPair pair = new DjikstraPair(vname, "", Integer.MAX_VALUE);

			if (vname.equals(src)) {
				pair.csf = 0;
				pair.psf = vname;
			}

			map.put(vname, pair);
			heap.add(pair);
		}

		while (heap.size() != 0) {
			DjikstraPair rp = heap.removeHP();
			
			ArrayList<String> nbrnames = new ArrayList<>(vces.get(rp.vname).nbrs.keySet());
			for (String nbrname : nbrnames) {
				DjikstraPair np = map.get(nbrname);

				int ocfn = np.csf;
				int ncfn = rp.csf + vces.get(rp.vname).nbrs.get(np.vname);

				if (ncfn < ocfn) {
					np.csf = ncfn;
					np.psf = rp.psf + np.vname;

					heap.updatePriority(np);
				}
			}
		}

		return map;
	}

	private static class DjikstraPair {
		String vname;
		String psf;
		int csf;
		public static final DjikstraComparator Ctor = new DjikstraComparator();

		public DjikstraPair(String vname, String psf, int csf) {
			this.vname = vname;
			this.psf = psf;
			this.csf = csf;
		}

		private static class DjikstraComparator implements Comparator<DjikstraPair> {

			@Override
			public int compare(DjikstraPair o1, DjikstraPair o2) {
				// TODO Auto-generated method stub
				return o2.csf - o1.csf;
			}

		}

		@Override
		public String toString() {
			return this.psf + "@" + this.csf;
		}
	}

	// o ((e + v)logv)
	public Graph prims() {
		Graph mst = new Graph();

		HashMap<String, PrimsPair> map = new HashMap<>();
		GenericHeap<PrimsPair> heap = new GenericHeap<>(PrimsPair.Ctor);

		ArrayList<String> vnames = new ArrayList<>(vces.keySet());
		for (String vname : vnames) {
			PrimsPair pair = new PrimsPair(vname, null, Integer.MAX_VALUE);
			heap.add(pair);
			map.put(vname, pair);
		}

		while (heap.size() != 0) {
			PrimsPair rp = heap.removeHP();

			// work
			if (rp.avname == null) {
				mst.addVertex(rp.vname);
			} else {
				mst.addVertex(rp.vname);
				mst.addEdge(rp.vname, rp.avname, rp.csf);
			}
			// work

			ArrayList<String> nbrnames = new ArrayList<>(vces.get(rp.vname).nbrs.keySet());
			for (String nbrname : nbrnames) {
				if (mst.containsVertex(nbrname)) {
					continue;
				}

				PrimsPair np = map.get(nbrname);

				int oc = np.csf;
				int nc = vces.get(rp.vname).nbrs.get(np.vname);

				if (nc < oc) {
					np.csf = nc;
					np.avname = rp.vname;
					heap.updatePriority(np);
				}
			}
		}

		return mst;
	}

	private static class PrimsPair {
		String vname;
		String avname;
		int csf;
		public static final PrimsComparator Ctor = new PrimsComparator();

		public PrimsPair(String vname, String avname, int csf) {
			this.vname = vname;
			this.avname = avname;
			this.csf = csf;
		}

		private static class PrimsComparator implements Comparator<PrimsPair> {

			@Override
			public int compare(PrimsPair o1, PrimsPair o2) {
				// TODO Auto-generated method stub
				return o2.csf - o1.csf;
			}

		}

		@Override
		public String toString() {
			return this.avname + "@" + this.csf;
		}
	}

	
	
}
