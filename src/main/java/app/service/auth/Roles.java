package app.service.auth;

import io.javalin.core.security.RouteRole;

public enum Roles implements RouteRole {
    ANYONE,
    USER,
    ADMINISTRATOR,
}
