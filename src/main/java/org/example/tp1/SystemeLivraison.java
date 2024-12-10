package org.example.tp1;

import java.util.HashMap;
import java.util.Map;

public class SystemeLivraison {
    private Map<String, Colis> colisMap = new HashMap<>();

    public synchronized void enregistrerColis(Colis colis) {
        colisMap.put(colis.getId(), colis);
        System.out.println("Colis enregistré : " + colis);
    }

    public synchronized void afficherEtatColis() {
        System.out.println("État actuel des colis : " + colisMap);
    }
}
