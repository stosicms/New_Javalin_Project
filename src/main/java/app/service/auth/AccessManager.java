package app.service.auth;

import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.Set;

public class AccessManager {
    JWTService jwtService = new JWTService();

    public void accessManager (Handler handler, Context context, Set<RouteRole> permitedRoles) throws Exception {

        if (permitedRoles.contains(Roles.ANYONE)) handler.handle(context);
        else if (permitedRoles.contains(Roles.USER)) {
            String token = context.cookie("codeDNA");

            if (token == null || jwtService.validateJWT(token).isEmpty())
                context.status(403).result("Please login");
            else handler.handle(context);
        }
        else context.status(400).result("Bad request");
    }
}
