import java.util.Objects;

public class Update implements BigCommand {
    private String msg;

    @Override
    public void execute(MovieList movieList, Movie movie) {
        if (movie == null) {
            msg = "Arguments are either null or filled up incorrectly";
        }
    }

    public void execute(MovieList movieList, long id, Movie movie) {
        if (movie == null && !Objects.isNull(id)) {
            msg = "Movie is null";
        } else if (Objects.isNull(id)) {
            msg = "ID is null";
        } else {
            int prevSize = movieList.size();
            movieList.removeIf(city1 -> (city1.getId() == id));
            if (prevSize != movieList.size()) {
                movie.setId(id);
                movieList.add(movie);
                msg = "Movie " + id + " was updated successfully";
            } else {
                msg = "Needed movie wasn't found";
            }
        }
    }

    @Override
    public String getMessage() {
        return msg;
    }
}