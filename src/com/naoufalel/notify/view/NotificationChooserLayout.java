package com.naoufalel.notify.view;

import com.naoufalel.notify.RemindersApp;
import com.naoufalel.notify.model.NotificationCustom;
import com.naoufalel.notify.util.SecondsToStandard;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * Created by naouf on 2/14/2016.
 */
public class NotificationChooserLayout {

    @FXML
    private TableView<NotificationCustom> notificationTable;
    @FXML
    private TableColumn<NotificationCustom, String> titleColumn;
    @FXML
    private TableColumn<NotificationCustom, String> messageColumn;
    @FXML
    private TableColumn<NotificationCustom, String> occurenceColumn;

    @FXML
    private TextField titleField;
    @FXML
    private TextField messageField;
    @FXML
    private TextField hoursField;
    @FXML
    private TextField minutesField;
    @FXML
    private TextField secondsField;


    private RemindersApp remindersApp;
    private boolean okClicked = false;
    private NotificationCustom ntc;

    public NotificationChooserLayout() {

    }

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        messageColumn.setCellValueFactory(cellData -> cellData.getValue().messageProperty());
        occurenceColumn.setCellValueFactory(cellData -> cellData.getValue().durationToShowInTableProperty());

        titleField.addEventHandler(
                DragEvent.DRAG_OVER,
                event -> {
                    if (event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.COPY);
                    }
                    event.consume();
                });
        titleField.addEventHandler(
                DragEvent.DRAG_DROPPED,
                event -> {
                    Dragboard dragboard = event.getDragboard();
                    if (event.getTransferMode() == TransferMode.COPY &&
                            dragboard.hasString()) {
                        titleField.setText(dragboard.getString());
                        event.setDropCompleted(true);
                    }
                    event.consume();
                });
    }

    public void setRemindersApp(RemindersApp remindersApp) {
        this.remindersApp = remindersApp;
        notificationTable.setItems(remindersApp.getNotificationCustoms());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOkBtn() {
        if (isInputValid()) {

            ntc = new NotificationCustom(titleField.getText(), messageField.getText());
//            ntc.setTitle(titleField.getText());
//            ntc.setMessage(messageField.getText());

            int result = 0;
            String s = "";
            try {
                int hours = Integer.parseInt(hoursField.getText());
                int mins = Integer.parseInt(minutesField.getText());
                int secs = Integer.parseInt(secondsField.getText());
                result = hours * 3600 + mins * 60 + secs;
                s = SecondsToStandard.convertSecToString(result);
            } catch (Exception e) {
                System.err.println("Naoufal is the wow\n" + e);
            }

            ntc.setDurationInSeconds(result);
            ntc.setDurationToShowInTable(s);
            okClicked = true;

            remindersApp.getNotificationCustoms().add(ntc);

        }
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "No valid title !\n";
        }
        if (messageField.getText() == null || messageField.getText().length() == 0) {
            errorMessage += "No valid message!\n";
        }
        if (hoursField.getText() == null || minutesField.getText() == null || secondsField.getText() == null) {
            errorMessage += "No valid timer!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                int h = Integer.parseInt(hoursField.getText());
                int m = Integer.parseInt(minutesField.getText());
                int s = Integer.parseInt(secondsField.getText());
                if ((h * 3600 + m * 60 + s) == 0) {
                    errorMessage += "No valid timer!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid timer (must be an number)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(remindersApp.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);


            alert.showAndWait();

            return false;
        }
    }


    @FXML
    private void handleCancel() {
        titleField.setText(null);
        messageField.setText(null);
        hoursField.setText("00");
        minutesField.setText("00");
        secondsField.setText("00");
    }

    @FXML
    private void handleRemove() {
        int selectedIndex = notificationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            notificationTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(remindersApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Reminder Selected");
            alert.setContentText("Please select a reminder in the table");

            alert.showAndWait();
        }
    }


    @FXML
    private void nev() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.initOwner(remindersApp.getPrimaryStage());
        a.setTitle("mok");
        a.setHeaderText("No Reminder Selected");
        a.setContentText("Please select a reminder in the table");

    }
}
