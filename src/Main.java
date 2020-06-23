import com.google.gson.Gson;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger("MainLogger");
    private static Server server;
    private static MovieList movieList;
    private static Clear clear = new Clear();
    private static Gson gson = new Gson();

    public static void main(String[] args) {

        Command command;
        server = new Server();

        if (System.getenv("JSON_ENV") != null) {
        } else {
            logger.severe("Path was not found");
            System.exit(0);
        }

        movieList = server.loadCollection(System.getenv("JSON_ENV"));

        Terminal t = new Terminal(movieList, server);
        t.start();
    }
}