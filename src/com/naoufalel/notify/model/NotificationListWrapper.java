package com.naoufalel.notify.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by naouf on 2/14/2016.
 */
@XmlRootElement(name = "reminders")
public class NotificationListWrapper {
    private List<NotificationCustom> notificationCustoms;

    @XmlElement(name = "reminder")
    public List<NotificationCustom> getNotificationCustoms() {
        return notificationCustoms;
    }

    public void setNotificationCustoms(List<NotificationCustom> notificationCustoms) {
        this.notificationCustoms = notificationCustoms;
    }
}
