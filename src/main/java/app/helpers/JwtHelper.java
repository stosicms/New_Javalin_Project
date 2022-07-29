package app.helpers;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JwtHelper {
    // Key is hardcoded here for simplicity.
// Ideally this will get loaded from env configuration/secret vault
    String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
            SignatureAlgorithm.HS256.getJcaName());

    public Map<String, String> generateToken(String userId) {
        Map<String, String> model = new HashMap<>();
        System.out.println(userId);
        String jwtToken = Jwts.builder()
                .claim("userId", userId)
                .signWith(hmacKey)
                .compact();
        model.put("response", jwtToken);
        return model;
    }

    public Jwt<JwsHeader, Claims> parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token);
    }


}
