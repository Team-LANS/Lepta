package com.teamlans.lepta.service.user;

import com.teamlans.lepta.service.exceptions.LeptaServiceException;

/**
 * The Credentials class wraps user name and password entered in an instance of SignUpForm.
 */
public final class Credentials {

  private String name;
  private String password;

  public Credentials(String name, String password) throws LeptaServiceException {
    if (name == null || password == null) {
      throw new LeptaServiceException("Invalid user data.");
    }
    this.name = name;
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Credentials)) return false;

    Credentials that = (Credentials) o;

    return name.equals(that.name);

  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }
}
