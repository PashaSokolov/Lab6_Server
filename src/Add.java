import java.util.logging.Logger;

/**
 * реализация команды add
 */
public class Add implements BigCommand {
    static Logger logger = Logger.getLogger("AddLogger");
    private String msg;

    @Override
    public void execute(MovieList movieList, Movie movie) {
        if (movie == null) {
            logger.warning("Original movie is null");
            msg = "Original movie is null";
        } else {
            try {
                int prevSize = movieList.size();

                if (!movieList.contains(movie)) {
                    movieList.add(movie);
                }
                if (prevSize != movieList.size()) {
                    logger.info("Element was successfully added to the collection");
                    msg = ("Element was successfully added to the collection.");
                } else {
                    logger.info("Collection already contains such element");
                    msg = ("Collection already contains such element");
                }
            } catch (NumberFormatException e) {
                logger.warning("Some number-fields have incorrect values");
                msg = ("Some number-fields have incorrect values. Try changing them");
            }
        }
    }

    @Override
    public String getMessage() {
        return msg;
    }
}