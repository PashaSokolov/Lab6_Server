import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * реализация команды save
 */

public class Save implements Comand {
    static Logger logger = Logger.getLogger("SaveLogger");
    Gson gson = new Gson();
    private String msg;

    @Override
    public void execute(MovieList movieList) {
        FileOutputStream FileOutput = null;
        String path = System.getenv("JSON_ENV");
        String strCollection = new String();

        while (!path.endsWith(System.getProperty("file.separator"))) {
            path = path.replaceAll(".$", "");
        }
        path = path + "SavedCollection.json";

        try {

            strCollection = gson.toJson(movieList);


            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                FileOutput = new FileOutputStream(file);
                FileOutput.write(strCollection.getBytes());
                msg = ("Collection saved successfully");
                logger.info("Collection saved successfully");
            } else if (!file.canRead() || !file.canWrite()) {
                msg = ("File is unreachable");
                logger.severe("File is unreachable");
            } else {
                FileOutput = new FileOutputStream(file);
                FileOutput.write(strCollection.getBytes());
                msg = ("Collection saved successfully");
                logger.info("Collection saved successfully");
            }
        } catch (IOException e) {
            msg = ("Error occurred");
            logger.warning("Error occurred");
        } catch (OutOfMemoryError e) {
            msg = ("Collection is too big, out of memory");
            logger.warning("Collection is too big, out of memory");
        } catch (SecurityException e) {
            msg = ("Impossible to create file in this directory");
            logger.warning("Impossible to create file in this directory");
        } finally {
            try {
                if (FileOutput != null) {
                    FileOutput.close();
                }
            } catch (IOException e) {
                msg = ("Error while closing");
                logger.warning("Error while closing");
            }
        }

    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}