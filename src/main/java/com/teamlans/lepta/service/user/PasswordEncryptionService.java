package com.teamlans.lepta.service.user;

import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Encrypts passwords using PBKDF2 and validates login attempts. Obviously not the most up-to-date
 * approach, but maybe the best solution in this case.
 */
@Service
public final class PasswordEncryptionService {

  public boolean isValid(String attemptedPassword, User user)
      throws LeptaServiceException {
    byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, user.getSalt());
    return Arrays.equals(user.getPassword(), encryptedAttemptedPassword);
  }

  public byte[] getEncryptedPassword(String password, byte[] salt)
      throws LeptaServiceException {
    String algorithm = "PBKDF2WithHmacSHA1";
    // Suggested at http://blog.jerryorr.com/2012/05/secure-password-storage-lots-of-donts.html
    // because SHA-1 generates 160 bit hashes
    int derivedKeyLength = 160;
    int iterations = 20000;
    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
    try {
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
      return keyFactory.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new LeptaServiceException("Could not encrypt password", e);
    }
  }

  public byte[] generateSalt() throws LeptaServiceException {
    try {
      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
      byte[] salt = new byte[8];
      random.nextBytes(salt);
      return salt;
    } catch (NoSuchAlgorithmException e) {
      throw new LeptaServiceException("Could not generate salt", e);
    }
  }

}
