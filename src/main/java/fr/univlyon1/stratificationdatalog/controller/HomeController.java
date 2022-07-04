package fr.univlyon1.stratificationdatalog.controller;

import fr.univlyon1.stratificationdatalog.StratificationApplication;
import fr.univlyon1.stratificationdatalog.models.Stratums;
import fr.univlyon1.stratificationdatalog.parser.Parser;
import fr.univlyon1.stratificationdatalog.parser.Tokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

import static fr.univlyon1.stratificationdatalog.models.Rule.setRules;

public class HomeController {
    public static String directoryFile;

    @FXML private Button btnAbout;
    @FXML private Button btnStratifier;
    @FXML private Button btnClose;
    @FXML private Label nomFic;
    @FXML private Button btnFileChooser;
    File f;
    @FXML void chooseFile(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt","*.dl"));
        f = fc.showOpenDialog(null);

        nomFic.setText(f.getName());
    }

    @FXML void handleStratifier(ActionEvent event) throws IOException {
        try {
            FileReader reader = new FileReader(f);

            BufferedReader bufferedReader = new BufferedReader(reader);
            Tokenizer streamTokenizer = new Tokenizer(bufferedReader);
            Parser parser = new Parser();
            parser.parseProgramRules(streamTokenizer);
            setRules(parser.getRules());
            new ResultController();
            writeInFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        FXMLLoader fxmlLoader = new FXMLLoader(StratificationApplication.class.getResource("result-view.fxml"));
        Parent root = fxmlLoader.load();
        ResultController resultController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Stratification Result");
        stage.setScene(new Scene(root));
        resultController.showResultOnStage();
        stage.show();



    }

    @FXML void handleAbout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StratificationApplication.class.getResource("about-view.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setTitle("About");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML void handleClose(ActionEvent event) {
        Stage oldStage = (Stage) btnClose.getScene().getWindow();
        oldStage.close();
    }


    public void writeInFile() {
        try {
            String desktopPath = System.getProperty("user.home") + "/Documents";
            directoryFile = desktopPath.replace("\\", "/");
            System.out.print(desktopPath.replace("\\", "/"));
            FileWriter myWriter = new FileWriter (desktopPath+"/stratified_"+f.getName()+".txt");
            ResultController resultController = new ResultController();
            myWriter.write(resultController.showPartitions().toString());
            myWriter.write(Stratums.showStratificationTab().toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
