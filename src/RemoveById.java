public class RemoveById implements BigCommand {
    private String msg;

    @Override
    public void execute(MovieList movieList, Movie movie) {
        msg = "Invalid argument";
    }

    public void execute(MovieList movieList, Long idToRemove) {
        if (idToRemove != null) {
            try {
                int prevSize = movieList.size();

                if (!movieList.isEmpty()) {
                    movieList.removeIf(movie -> (movie.getId() == idToRemove));

                    if (movieList.size() != prevSize) {
                        msg = ("Movie with id " + idToRemove + " was successfully removed");
                    } else {
                        msg = ("Nothing was removed");
                    }
                } else {
                    msg = ("Collection is empty");
                }
            } catch (NumberFormatException e) {
                msg = ("ID should be a number from 0 to " + Long.MAX_VALUE);
            }
        } else {
            msg = "ID is null";
        }
    }

    @Override
    public String getMessage() {
        return msg;
    }
}

