package app.helpers;

import io.jsonwebtoken.Jwts;

import java.util.HashMap;
import java.util.Map;

public class JwtHelper {

    public Map<String, String> generateToken(String username) {
        Map<String, String> model = new HashMap<>();

        String jwtToken = Jwts.builder()
                .claim("username", username)
                .compact();
        model.put("response", jwtToken);
        return model;
    }
}
