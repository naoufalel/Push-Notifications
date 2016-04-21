package com.naoufalel.notify.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by naouf on 2/14/2016.
 */
public class NotificationCustom {
    private StringProperty title;
    private StringProperty message;
    private IntegerProperty durationInSeconds;
    private StringProperty durationToShowInTable;
    private IntegerProperty startTime;

    public NotificationCustom(){
        this(null,null);
    }

    public NotificationCustom(String title, String message){
        this.title = new SimpleStringProperty(title);
        this.message = new SimpleStringProperty(message);
        this.durationInSeconds = new SimpleIntegerProperty(2000);
        this.durationToShowInTable = new SimpleStringProperty("");
        this.startTime = new SimpleIntegerProperty(0);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }

    public int getDurationInSeconds() {
        return durationInSeconds.get();
    }

    public IntegerProperty durationInSecondsProperty() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds.set(durationInSeconds);
    }

    public String getDurationToShowInTable() {
        return durationToShowInTable.get();
    }

    public StringProperty durationToShowInTableProperty() {
        return durationToShowInTable;
    }

    public void setDurationToShowInTable(String durationToShowInTable) {
        this.durationToShowInTable.set(durationToShowInTable);
    }

    public int getStartTime() {
        return startTime.get();
    }

    public IntegerProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime.set(startTime);
    }
}
