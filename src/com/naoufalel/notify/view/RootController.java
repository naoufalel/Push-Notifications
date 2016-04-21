package com.naoufalel.notify.view;

import com.naoufalel.notify.RemindersApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by naouf on 2/14/2016.
 */
public class RootController {

    private RemindersApp remindersApp;

    public void setRemindersApp(RemindersApp remindersApp) {
        this.remindersApp = remindersApp;
    }

    public RootController(){

    }

    @FXML
    public void initialize(){

    }

    @FXML
    public void handleSave(){
        File remindersFile = remindersApp.getReminderFilePath();
        if (remindersFile != null) {
            remindersApp.savePersonDataToFile(remindersFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(remindersApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            remindersApp.savePersonDataToFile(file);
        }
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reminders");
        alert.setHeaderText("About");
        alert.setContentText("Author: Naoufal EL BANTLI\nWebsite: http://linkedin.com/in/nelbantli");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
