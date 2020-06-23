/**
 * реализация команды show
 */
public class Show implements Comand {
    private String msg;


    @Override
    public void execute(MovieList movieList) {
        msg = "";
        if (movieList.isEmpty()) {
            msg = ("Collection is empty");
        } else {
            for (Movie c : movieList) {
                msg = msg + (c.toString() + "\n----------//----------\n");
            }
            msg = msg.substring(0, msg.length() - 2);
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

}