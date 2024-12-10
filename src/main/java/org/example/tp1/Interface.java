package org.example.tp1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Interface extends Application {

    private SystemeLivraison systemeLivraison = new SystemeLivraison();
    private ListView<String> enAttenteList = new ListView<>();
    private ListView<String> enTransitList = new ListView<>();
    private ListView<String> livresList = new ListView<>();
    private Map<String, String> colisEtat = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        // Layout principal
        VBox root = new VBox(10);

        // Champ pour ajouter des colis
        TextField colisIdField = new TextField();
        colisIdField.setPromptText("ID du colis");

        TextField destinationField = new TextField();
        destinationField.setPromptText("Destination du colis");

        Button addColisBtn = new Button("Ajouter le Colis");
        addColisBtn.setOnAction(e -> {
            String id = colisIdField.getText().trim();
            String destination = destinationField.getText().trim();
            if (!id.isEmpty() && !destination.isEmpty()) {
                enregistrerColis(new Colis(id, destination));
                colisIdField.clear();
                destinationField.clear();
            } else {
                showAlert("Erreur", "Veuillez entrer un ID et une destination valides !");
            }
        });

        // Sections pour les états des colis
        root.getChildren().addAll(
                createSection("En attente", enAttenteList),
                createSection("En transit", enTransitList),
                createSection("Livrés", livresList),
                new HBox(10, colisIdField, destinationField, addColisBtn)
        );

        // Bouton pour lancer la simulation
        Button simulateBtn = new Button("Lancer la Simulation");
        simulateBtn.setOnAction(e -> lancerSimulation());

        root.getChildren().add(simulateBtn);

        // Configuration de la scène
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("État des Colis");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSection(String title, ListView<String> listView) {
        Label label = new Label(title);
        VBox section = new VBox(5, label, listView);
        section.setStyle("-fx-padding: 10; -fx-border-color: gray; -fx-border-width: 1;");
        return section;
    }

    private void lancerSimulation() {
        Thread simulationThread = new Thread(() -> {
            try {
                for (String item : enAttenteList.getItems()) {
                    System.out.println("Traitement de l'élément : " + item);
                    // Extraire l'ID du colis à partir de la chaîne
                    String[] parts = item.split(": ");
                    if (parts.length < 2) {
                        System.err.println("Format incorrect pour : " + item);
                        continue; // Passe au prochain élément
                    }
                    String id = parts[1].split(",")[0].trim(); // Extraction plus robuste
                    mettreEnTransit(id);
                    Thread.sleep(1000); // Simule le délai de transit
                    livrerColis(id);
                    Thread.sleep(1000); // Simule un délai après la livraison
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        simulationThread.start();
    }


    private void enregistrerColis(Colis colis) {
        systemeLivraison.enregistrerColis(colis);
        Platform.runLater(() -> {
            enAttenteList.getItems().add("Colis ID: " + colis.getId() + ", Destination: " + colis.getDestination());
            colisEtat.put(colis.getId(), "En attente");
        });
    }

    private void mettreEnTransit(String id) {
        String etat = colisEtat.get(id);
        if (etat != null && etat.equals("En attente")) { // Vérifie que l'état n'est pas null
            Platform.runLater(() -> {
                enAttenteList.getItems().removeIf(item -> item.contains(id));
                enTransitList.getItems().add("Colis ID: " + id);
                colisEtat.put(id, "En transit");
            });
        }
    }


    private void livrerColis(String id) {
        String etat = colisEtat.get(id);
        if (etat != null && etat.equals("En transit")) { // Vérifie que l'état n'est pas null
            Platform.runLater(() -> {
                enTransitList.getItems().removeIf(item -> item.contains(id));
                livresList.getItems().add("Colis ID: " + id);
                colisEtat.put(id, "Livré");
            });
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
