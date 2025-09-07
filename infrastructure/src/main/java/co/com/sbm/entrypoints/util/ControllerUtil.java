package co.com.sbm.entrypoints.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerUtil {

    public static String getToken(String bearerToken) {
        if (bearerToken == null || bearerToken.isEmpty()) {
            return null;
        }
        if (!bearerToken.startsWith("Bearer ")) {
            return  null;
        }

        bearerToken = bearerToken.substring(7);
        return bearerToken;
    }
}
