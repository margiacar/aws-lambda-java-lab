package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;

import java.io.FileWriter;
import java.util.Map;

// Handler value: example.Handler
public class Handler implements RequestHandler<Map<String, String>, String> {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @SneakyThrows
    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        try (FileWriter writer = new FileWriter("/tmp/file.txt")) {
            writer.write("content\n");
        }
        String response = "200 OK";
             // log execution details
             logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
             logger.log("CONTEXT: " + gson.toJson(context));
             // process event
             logger.log("EVENT: " + gson.toJson(event));
             logger.log("EVENT TYPE: " + event.getClass());
        return response;
    }
}