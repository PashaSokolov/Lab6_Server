import java.util.Collections;

public class AddIfMax implements BigCommand {
    private String msg;

    @Override
    public void execute(MovieList movieList, Movie movie) {
        if (movie != null) {
            if (movieList.isEmpty()) {
                movieList.add(movie);
                msg = "Element successfully added to the collection";
            } else {
                if (movie.compareTo(Collections.max(movieList)) > 0) {
                    movieList.add(movie);
                    msg = ("City was successfully added to the collection");
                } else {
                    msg = ("City wasn't added to the collection");
                }
            }
        } else {
            msg = "Element won't be added to the collection.";
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}