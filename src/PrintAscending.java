public class PrintAscending implements Comand {
    private String msg;

    @Override
    public void execute(MovieList movieList) {
        msg = "";
        if (!movieList.isEmpty()) {
            for (Movie m : movieList) {
                if (m.getMpaaRating() == MpaaRating.G) {
                    msg = msg + m.toString() + "\n----//----\n";
                }
            }
            for (Movie m : movieList) {
                if (m.getMpaaRating() == MpaaRating.PG) {
                    msg = msg + m.toString() + "\n----//----\n";
                }
            }
            for (Movie m : movieList) {
                if (m.getMpaaRating() == MpaaRating.PG_13) {
                    msg = msg + m.toString() + "\n----//----\n";
                }
            }
            for (Movie m : movieList) {
                if (m.getMpaaRating() == MpaaRating.R) {
                    msg = msg + m.toString() + "\n----//----\n";
                }
            }
            for (Movie m : movieList) {
                if (m.getMpaaRating() == MpaaRating.NC_17) {
                    msg = msg + m.toString() + "\n----//----\n";
                }
            }
        } else {
            msg = ("Collection is empty");
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
