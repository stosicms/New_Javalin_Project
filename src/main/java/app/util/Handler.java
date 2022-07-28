package app.util;

import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Handler {
    void handle (@NotNull Context var1) throws Exception;
}
