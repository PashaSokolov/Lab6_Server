public class RemoveFirst implements Comand {
    private String msg;

    @Override
    public void execute(MovieList movieList) {
        if (movieList.isEmpty()) {
            msg = "Collection is empty";
        } else {
            movieList.remove(0);
            msg = "Movie was successfully removed";
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
