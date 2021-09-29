package edu.escuelaing.arep;
import edu.escuelaing.arep.server.Serverhttp;

import java.io.IOException;

import static spark.Spark.*;


public class roundApp {

    public static void main( String[] args ) throws IOException {
        port(getPort());
        staticFileLocation("/static");
        Serverhttp serverhttp = new Serverhttp();
        get("/", (req, res) -> {
            res.redirect("static/index.html");
            return null;
        });
        get("/taller", (req, res) -> {
            res.status(200);
            res.type("application/json");
            String response = serverhttp.getMessage();
            return response;
        });
        post("/taller", (req, res) -> {
            if(serverhttp.getUrl()==""){
                serverhttp.setUrl("http://"+(req.url().split("//")[1]).split(":")[0]);
            }
            serverhttp.postMessage(req.body());
            return "";
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
