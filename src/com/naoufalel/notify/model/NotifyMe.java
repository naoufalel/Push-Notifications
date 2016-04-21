package com.naoufalel.notify.model;

import com.naoufalel.notify.RemindersApp;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.TimerTask;
import javax.swing.Timer;

/**
 * Created by naouf on 2/17/2016.
 */
public class NotifyMe {

    private RemindersApp remindersApp;
    private ObservableList<NotificationCustom> notificationCustom;
    Timer timer;
    int counter=2000;


    public NotifyMe(RemindersApp remindersApp, ObservableList<NotificationCustom> notificationCustom,int count) {
        this.remindersApp = remindersApp;
        this.notificationCustom=notificationCustom;
        this.counter = count;
        if(notificationCustom==null){
            System.err.println("dfs");
        }else {

            try {

                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        counter--;
                        if (counter == -1) {
                            System.out.println("fuck");
                            counter = count;
                            actionPerformed(e);
                        }
                    }
                });
                timer.start();
            }catch (Exception re){
                System.err.println("Miwe "+re);
            }
        }

    }

    class NotifySchedule extends TimerTask {
        public void run() {

            try {
                for (NotificationCustom n : notificationCustom) {
                  //counters.add(n.getDurationInSeconds());
                }
                //for (NotificationCustom n : remindersApp.getNotificationCustoms()){
                    System.out.println("Lynda");
                    //timer.cancel();
                //}

            } catch (Exception e) {
                System.err.println("naoufaaaal"+e);
            }

        }
    }

    private void startReminding() {


        try {
//            //timer = new Timer();
//            for (int counter : counters) {
//
//                //timer.schedule(new NotifySchedule(), counter);
//            }
        } catch (Exception e) {
            System.err.println("This is shit !!\n" + e);
        }

    }

}
