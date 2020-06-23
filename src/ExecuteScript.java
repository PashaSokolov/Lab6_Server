import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript implements BigCommand {
    private Server server;
    private Terminal fileTerminal;
    private String msg = "Script executed";
    private Gson gson = new Gson();
    private NullPointerChecker np = new NullPointerChecker();
    private WrongFieldChecker wf = new WrongFieldChecker();

    public ExecuteScript(Terminal terminal, Server server) {
        this.server = server;
        this.fileTerminal = terminal;
    }

    @Override
    public void execute(MovieList movieList, Movie movie) {
        server.writeLine("Invalid argument");
    }

    public void execute(MovieList movieList, String fileName) {
        String path = System.getenv("JSON_ENV");
        while (!path.endsWith(System.getProperty("file.separator"))) {
            path = path.replaceAll(".$", "");
        }
        fileName = path + fileName;

        try {
            File script = new File(fileName);

            if (!script.exists()) {
                msg = ("File does not exist");
                throw new FileNotFoundException();
            }
            if (!script.isFile()) {
                msg = ("This is not a file");
                throw new FileNotFoundException();
            }
            if (!script.canRead()) {
                msg = ("File is unreachable");
                throw new FileNotFoundException();
            }

            if (!script.canWrite()) {
                msg = ("File is unreachable");
                throw new FileNotFoundException();
            }

            Scanner scanner = new Scanner(script);
            while (scanner.hasNext()) {
                String newLine = scanner.nextLine().trim();
                String[] arr = newLine.split(" ", 2);

                if (arr.length == 1) {
                    fileTerminal.startWithCommand(new Command(arr[0], null));
                } else {
                    switch (arr[0]) {
                        case "add":
                        case "add_if_max":
                            fileTerminal.startWithCommand(new Command(arr[0], makeMovieFromJSON(arr[1])));
                            break;
                        default:
                            fileTerminal.startWithCommand(new Command(arr[0], arr[1]));
                            break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            msg = (msg + (". Script was not executed"));
            return;
        } catch (OutOfMemoryError e) {
            msg = ("File is too big");
        }
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public Movie makeMovieFromJSON(String s) {
        try {
            Movie m = gson.fromJson(s, Movie.class);
            np.checkEverything(m, CheckParametr.WITHOUT_ASKING);
            wf.checkEverything(m, CheckParametr.WITHOUT_ASKING);
            return m;
        } catch (JsonSyntaxException e) {
            msg = ("JSON Syntax error. ");
        } catch (NumberFormatException e) {
            msg = ("Some number-fields have incorrect values. ");
        }
        return null;
    }
}