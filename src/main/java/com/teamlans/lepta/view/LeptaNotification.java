package com.teamlans.lepta.view;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

/**
 * LeptaNotification shows already perfectly styled notifications.
 */
public class LeptaNotification extends Notification {

  public LeptaNotification(String description) {
    super("", description); // set description, not caption!
  }

  public LeptaNotification(String caption, String description) {
    super(caption, description);
  }


  // for severe errors
  public static void showError(String caption, String description) {
    final Notification error = new Notification(caption, description);
    error.setPosition(Position.MIDDLE_CENTER);
    error.setStyleName(ValoTheme.NOTIFICATION_ERROR + " " + ValoTheme.NOTIFICATION_CLOSABLE);
    error.setDelayMsec(-1);
    error.show(Page.getCurrent());
  }

  public static void showError(String description) {
    // no caption
    LeptaNotification.showError("", description);
  }


  // for warnings and small errors, e.g. 'login failed'
  public static void showWarning(String caption, String description) {
    final Notification warning = new Notification(caption, description);
    warning.setPosition(Position.BOTTOM_CENTER);
    warning.setStyleName(ValoTheme.NOTIFICATION_ERROR + " " + ValoTheme.NOTIFICATION_CLOSABLE);
    warning.setDelayMsec(3000);
    warning.show(Page.getCurrent());
  }

  public static void showWarning(String description) {
    // no caption
    LeptaNotification.showWarning("", description);
  }


  // for everything else
  public static void show(String caption, String description) {
    final Notification notification = new Notification(caption, description);
    notification.setPosition(Position.TOP_RIGHT);
    notification.setStyleName(ValoTheme.NOTIFICATION_TRAY + " " + ValoTheme.NOTIFICATION_CLOSABLE +
        " " + ValoTheme.NOTIFICATION_SMALL);
    notification.setDelayMsec(3000);
    notification.show(Page.getCurrent());
  }

  public static void show(String description) {
    // no caption
    LeptaNotification.show("", description);
  }

}
