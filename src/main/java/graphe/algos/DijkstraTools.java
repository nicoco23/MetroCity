package main.java.graphe.algos;

import main.java.graphe.core.IGraphe;
import main.java.graphe.core.IGrapheConst;


import java.util.*;

public class DijkstraTools {
    public static long time(IGrapheConst g, String source,
                            Map<String, Integer> dist, Map<String, String> prev) {
        long debut = System.currentTimeMillis();
        Dijkstra.dijkstra(g, source, dist, prev);
        long fin = System.currentTimeMillis();
        return fin - debut;
    }

    // reconstruction du plus court chemin (dans prev) de source vers dest en partant de dest
    public static List<String> getPath(String source, String dest, Map<String, String> prev) {
        List<String> path = new ArrayList<>();
        for (String sommet = dest; sommet != null; sommet = prev.get(sommet))
            path.add(sommet);
        Collections.reverse(path);
        return path;
    }
}
