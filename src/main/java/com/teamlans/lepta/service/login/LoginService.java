package com.teamlans.lepta.service.login;

import com.teamlans.lepta.service.exceptions.LeptaLoginException;

public class LoginService {

  public void authenticate(String login, String password) throws LeptaLoginException {

    if ("user".equals(login) && "querty".equals(password)) {
      loadProtectedResources();
      return;
    }

    throw new LeptaLoginException("Login failed!");

  }

  private void loadProtectedResources() {
    // TODO: show MainWindow
  }

}
