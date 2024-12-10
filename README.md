Simulation d'un Système de Livraison de Colis
Description du Projet
Ce projet simule un système de gestion de livraison de colis, inspiré de l'exemple de Rapid Post de la poste tunisienne.
L'objectif est de permettre une gestion concurrente et synchronisée des colis tout en offrant une interface graphique intuitive pour l'interaction utilisateur.


Livraison des Colis

Les colis enregistrés sont livrés et marqués comme "livrés" une fois qu'ils atteignent leur destination.
Affichage de l'État des Colis

Une interface graphique (JavaFX) montre les états des colis :
En attente.
En transit.
Livrés.
Traçabilité des Colis (optionnel)

Les utilisateurs peuvent suivre l'état et la localisation des colis en temps réel.
Technologies Utilisées

Java pour la logique métier et la gestion des threads.
JavaFX pour l'interface graphique.

Threads,et Moniteurs pour gérer la synchronisation et la sécurité des données.

Structure du Projet
Code Source : Organisé en classes pour une meilleure lisibilité.
Interface Graphique : Créée avec JavaFX, intuitive et conviviale.
Synchronisation : Implémentée avec des threads pour gérer l'accès concurrent.
 
L'interface graphique apparaîtra pour interagir avec le système.

La synchronisation est gérée par :
Sémaphores pour limiter le nombre de threads actifs simultanément.
Moniteurs pour assurer des blocs critiques bien définis.
La traçabilité est réalisée à travers une base de données temporaire simulée avec des collections Java.
Exemple de Fonctionnalités
Interface graphique :
Enregistrer un colis.
Suivre son état (en attente, en transit, livré).
Gestion concurrente :
Plusieurs threads peuvent enregistrer des colis sans conflit.

