/**

 Cette classe implémente l'algorithme de Dijkstra pour trouver le plus court chemin dans un graphe.
 @author TASSIN Nicolas
 */
package main.java.graphe.algos;

import main.java.graphe.core.IGrapheConst;
import org.junit.platform.engine.support.descriptor.FileSystemSource;

import java.util.*;

/**

 Cette classe implémente l'algorithme de Dijkstra pour trouver le plus court chemin dans un graphe.
 @author TASSIN Nicolas
 */

public class Dijkstra {


    /**
     * Calcule la distance la plus courte depuis un sommet de départ vers tous les autres sommets du graphe en utilisant l'algorithme de Dijkstra.
     *
     * @param graphe le graphe à parcourir, ne doit pas être nul.
     * @param depart le sommet de départ, ne doit pas être nul et doit être présent dans le graphe.
     * @param dist un dictionnaire de distance, qui sera rempli par la méthode, ne doit pas être nul et toutes les distances doivent être positives ou nulles.
     * @param prev un dictionnaire de prédecesseurs, qui sera rempli par la méthode, ne doit pas être nul.
     * @return une liste contenant tous les sommets du graphe, triée par ordre alphabétique.
     */
    public static List<String> dijkstra(IGrapheConst graphe, String depart, Map<String, Integer> dist, Map<String, String> prev) {
        PriorityQueue<String> nonVisites = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        dist.put(depart, 0);
        nonVisites.add(depart);

        while (!nonVisites.isEmpty()) {
            String sommetCourant = nonVisites.poll();

            for (String successeur : graphe.getSucc(sommetCourant)) {
                int poidsArc = graphe.getValuation(sommetCourant, successeur);
                int distanceViaSommetCourant = dist.get(sommetCourant) + poidsArc;

                if (!dist.containsKey(successeur) || distanceViaSommetCourant < dist.get(successeur)) {
                    dist.put(successeur, distanceViaSommetCourant);
                    prev.put(successeur, sommetCourant);
                    nonVisites.remove(successeur);
                    nonVisites.add(successeur);
                }
            }
        }

        return new ArrayList<>(dist.keySet());
    }

    /**
     * Calcule le plus court chemin entre deux sommets du graphe.
     * @param graphe le graphe à parcourir
     * @param depart le sommet de départ
     * @param arrivee le sommet d'arrivée
     * @return une liste contenant le plus court chemin entre les deux sommets, ou null si aucun chemin n'a été trouvé
     */
    public static List<String> plusCourtChemin(IGrapheConst graphe, String depart, String arrivee) {
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> predecesseur = new HashMap<>();

        dijkstra(graphe, depart, distance, predecesseur);

        if (!predecesseur.containsKey(arrivee)) {
            return null; // Pas de chemin trouvé
        }

        List<String> chemin = new ArrayList<>();
        String sommet = arrivee;

        while (!sommet.equals(depart)) {
            chemin.add(sommet);
            sommet = predecesseur.get(sommet);
        }

        chemin.add(depart);
        Collections.reverse(chemin);
        return chemin;
    }

}