public class RemoveIndex implements BigCommand {

    private String msg;

    @Override
    public void execute(MovieList movieList, Movie movie) {
        msg = "Invalid argument";
    }

    public void execute(MovieList movieList, int indexToRemove) {
        if (Integer.valueOf(indexToRemove) != null) {
            try {
                int prevSize = movieList.size();

                if (!movieList.isEmpty()) {
                    if(movieList.get(indexToRemove) == null){
                        msg = "Nothing was removed";
                    }else{
                        movieList.remove(indexToRemove);
                        msg = ("Movie" + indexToRemove + "was succesfully removed");
                    }
                }else{
                    msg = "Collection is empty";
                }
            } catch (NumberFormatException e) {
                msg = ("ID should be a number from 0 to " + Long.MAX_VALUE);
            }catch (IndexOutOfBoundsException e){
                msg = ("Max ID is: " + (movieList.size() -1));
            }
        }
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
