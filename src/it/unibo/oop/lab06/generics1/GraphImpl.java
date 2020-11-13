package it.unibo.oop.lab06.generics1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphImpl<N> implements Graph<N>{

	public Map<N, Set<N>> map = new HashMap<>();
	
	
	
	public void addNode(N node) {
		if (!map.containsKey(node) || node != null) {
			map.put(node, new HashSet<>());
		}
	}

	public void addEdge(N source, N target) {
		if (source != null && target != null) {
			if(map.containsKey(source)) {
				Set<N> p = map.get(source);
				p.add(target);
				map.put(source, p);
			}
		}
	}

	public Set<N> nodeSet() {
		return new HashSet<>(map.keySet());
	}

	public Set<N> linkedNodes(N node) {
		return new HashSet<>(map.get(node));
	}

	public List<N> getPath(N source, N target) {
		return controllo(source, target, new HashSet<N>());
	}
	
	private List<N> controllo (N source, N target, Set<N> controllati) {
		
		if(source == null && target == null) {
			return new ArrayList<>();
		}
		
		if(source.equals(target)) {
			return new ArrayList<>(List.of(target));
		}
		
		if(map.get(source).contains(target)) {
			return new LinkedList<>(List.of(source, target));
		}
		
		List<N> arrivo = new LinkedList<>();
		Set<N> vicini = map.get(source);
		for(N nodo: vicini) {
			if(!controllati.contains(nodo)) {
				controllati.add(nodo);
				arrivo = this.controllo(nodo, target, controllati);
			}
			if(!arrivo.isEmpty()) {
				arrivo.add(0, source);
				return arrivo;
			}
		}
			
		return new ArrayList<>();
	}

}
