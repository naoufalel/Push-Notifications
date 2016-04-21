package com.naoufalel.notify;

import com.naoufalel.notify.model.NotificationCustom;
import com.naoufalel.notify.model.NotificationListWrapper;
import com.naoufalel.notify.model.NotifyMe;
import com.naoufalel.notify.view.NotificationChooserLayout;
import com.naoufalel.notify.view.RootController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Created by naouf on 2/14/2016.
 */
public class RemindersApp extends Application{


    private Stage primaryStage;
    private BorderPane rootLayout;
    private NotifyMe notifyMe;

    private ObservableList<NotificationCustom> notificationCustoms = FXCollections.observableArrayList();

    public ObservableList<NotificationCustom> getNotificationCustoms() {
        return notificationCustoms;
    }

    private Collection<Integer> counters;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {launch(args);    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        loadRemindersFromFile(getReminderFilePath());
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Reminders by Naoufal EL BANTLI");
        this.primaryStage.getIcons().add(new Image(RemindersApp.class.getResourceAsStream("images/clock.png")));

        this.primaryStage.setResizable(false);

        initRootLayout();

        showRemindersOverview();

//            try{
//                counters = new ArrayList<>();
//                notificationCustoms.forEach((n)->{
//                    counters.add(n.getDurationInSeconds());
//                });
//                for(int counter : counters){
//                    notifyMe=new NotifyMe(this,notificationCustoms,counter);
//                }
//            }catch (Exception r){
//                System.err.println("Mooowe\n"+r+"\n");r.printStackTrace();
//            }





    }

    /*
    * Showing root Layout with menu bar
    *
    * */
    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RemindersApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootController controller = loader.getController();
            controller.setRemindersApp(this);

            primaryStage.show();

        }catch (IOException e){
            System.err.println("Couldn't open root fxml.\n"+e);
        }
    }

    public void showRemindersOverview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RemindersApp.class.getResource("view/NotificationChooserLayout.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            rootLayout.setCenter(anchorPane);

            NotificationChooserLayout controller = loader.getController();
            controller.setRemindersApp(this);

        }catch (IOException e){
            System.err.println("Couldn't open reminders fxml.\n"+e);
        }
    }

    public File getReminderFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(RemindersApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        }
        return null;
    }

    public void setReminderFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(RemindersApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

        } else {
            prefs.remove("filePath");

        }
    }



    public void loadRemindersFromFile(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(NotificationListWrapper.class);

            Unmarshaller un = context.createUnmarshaller();
            NotificationListWrapper wrapper = (NotificationListWrapper) un.unmarshal(file);

            notificationCustoms.clear();
            notificationCustoms.addAll(wrapper.getNotificationCustoms());

            setReminderFilePath(file);
        }catch (Exception e){
            System.err.println(e);
        }
    }

    public void savePersonDataToFile(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(NotificationListWrapper.class);

            Marshaller m = context.createMarshaller();

            NotificationListWrapper wrapper = new NotificationListWrapper();
            wrapper.setNotificationCustoms(notificationCustoms);

            m.marshal(wrapper,file);

            setReminderFilePath(file);

        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Couldn't load data");
            alert.setContentText("Couldn't load data from file :\n" + file.getPath());
            alert.showAndWait();
        }
    }
}
