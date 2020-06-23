import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * класс, конвертирующий файл в коллекцию
 */
public class Convertator {
    static Logger logger = Logger.getLogger("ConvertatingLogger");
    private MovieList collection = new MovieList();
    private Gson gson = new Gson();

    /**
     * @param jsonPath путь до файла
     * @return коллекцию объектов класса Movie
     */
    public MovieList toCollection(String jsonPath) {
        File jsonFile = new File(jsonPath);

        if (!jsonFile.exists()) {
            logger.severe("File does not exist");
            System.exit(0);
        }

        if (!jsonFile.isFile()) {
            logger.severe("This is not a file");
            System.exit(0);
        }

        if (!jsonFile.canRead()) {
            logger.severe("File in unreadeble");
            System.exit(0);
        }

        if (!jsonPath.endsWith(".json")) {
            logger.severe("This is not a json-file");
            System.exit(0);
        }

        NullPointerChecker np = new NullPointerChecker();
        WrongFieldChecker wf = new WrongFieldChecker();
        int counter = 0;

        String data = new String();

        Type collectionType = new TypeToken<MovieList>() {
        }.getType();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(jsonPath), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                data = data + line;
            }

            MovieList newMovie = gson.fromJson(data.toString(), collectionType);

            for (Movie m : newMovie) {
                if (!collection.contains(m)) {
                    np.checkEverything(m);
                    wf.checkEverything(m);
                    collection.add(m);
                }
            }
        } catch (JsonSyntaxException e) {
            logger.severe("JSON syntax error. Program will stop automatically");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            logger.severe("Error occurred while reading this file");
            System.exit(0);
        } catch (NullPointerException e) {
            logger.severe("NULL");
            System.exit(0);
        }


        counter = collection.size();
        logger.severe("JSON loaded successfully. " + counter + " new Movies were added to the List");

        MovieList newCollection = collection.stream().sorted(Comparator.comparing(Movie::getName)).collect(Collectors.toCollection(MovieList::new));
        newCollection.setAuthDateTime(LocalDateTime.now());
        return newCollection;
    }
}
