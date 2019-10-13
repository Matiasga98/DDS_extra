package dominio;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador {
    public static String hashear(String pass) {
        return Hashing.sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString();
    }
}
