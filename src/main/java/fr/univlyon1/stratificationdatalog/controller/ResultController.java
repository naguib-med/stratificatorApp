package fr.univlyon1.stratificationdatalog.controller;

import fr.univlyon1.stratificationdatalog.models.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

import static fr.univlyon1.stratificationdatalog.controller.HomeController.directoryFile;
import static fr.univlyon1.stratificationdatalog.models.Stratums.showStratificationTab;


public class ResultController implements Initializable{
    @FXML
    private VBox resultContainer;

    Map<Integer, ArrayList<Rule>> allPartition;

    Map<String, Integer> stratums = new HashMap<>();

    public ResultController() {
        this.allPartition = new HashMap<>();
        Stratums stratums = new Stratums(this.stratums);
        stratums.stratification();
        getAllPartition();
        System.out.println(showPartitions());
        System.out.println(showStratificationTab());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation du controller");

    }

    public void getAllPartition() {
        System.out.println("\n---------- Partitions ----------\n");
        for (int i = 1; i <= Collections.max(stratums.values()); i++) {
            for (Map.Entry<String, Integer> val :  stratums.entrySet()) {
                if (val.getValue().equals(i)) {
                    ArrayList<Rule> rules = new ArrayList<>();
                    if (allPartition.containsKey(i)) {
                        rules = allPartition.get(i);
                    }
                    rules.addAll(Rule.getRuleByHead(val.getKey()));
                    allPartition.put(i, rules);
                }

            }

        }

    }


    public StringBuilder showPartitions() {
        StringBuilder stringBuilder = new StringBuilder();
        allPartition.forEach((key, values) -> {
            stringBuilder.append("P").append(key.toString());
            stringBuilder.append(" = { ");

            for (Rule value : values) {
                stringBuilder.append('\n');
                stringBuilder.append("\t\t");
                stringBuilder.append(value);
            }
            stringBuilder.append('\n');
            stringBuilder.append("}");
            stringBuilder.append("\n\n");
        });
        return stringBuilder;
    }

    public void showResultOnStage() {
        Label label = new Label("------------------------------------Stratification---------------------------------------------");
        Label partition = new Label("--------------------------------- Partitions -----------------------------------------------");
        Label direc = new Label("------------------------------------- Directory of the resultat file------------------------------");
        Label l = new Label();
        Label l2 = new Label();
        Label directory = new Label();
        l.setText(showPartitions().toString());
        l2.setText(showStratificationTab().toString());
        directory.setText(directoryFile);
        resultContainer.getChildren().addAll(label,l2, partition, l,direc, directory);

    }


}
