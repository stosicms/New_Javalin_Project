package app.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

public class JWTService {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JWTService (Algorithm algorithm, JWTVerifier verifier) {
        this.algorithm = algorithm;
        this.verifier = verifier;
    }

    public JWTService () {
        this.algorithm = Algorithm.HMAC256(Dotenv.load().get("JWT_SECRET"));
        this.verifier = JWT.require(this.algorithm).build();
    }

    public String generateJWT () {
        JWTCreator.Builder builder = JWT.create().withExpiresAt(
                Date.from(ZonedDateTime.now().plusMinutes(15).toInstant()));
        return builder.sign(algorithm);
    }

    public Optional<DecodedJWT> validateJWT (String token) {
        try {
            return Optional.of(verifier.verify(token));
        }
        catch (JWTVerificationException exception){
            return Optional.empty();
        }
    }
}
