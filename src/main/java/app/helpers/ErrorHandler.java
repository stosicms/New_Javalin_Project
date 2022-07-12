package app.helpers;

import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import io.javalin.http.UnauthorizedResponse;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {

    public void httpError(HttpResponseException e, Context ctx) {
        Map<String, String> model = new HashMap<>();
        model.put("error", e.getMessage());
        ctx.status(e.getStatus()).json(model);
    }
}
