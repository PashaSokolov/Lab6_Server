/**
 * реализация команды clear
 */

public class Clear implements Comand {

    private String msg;


    @Override

    public void execute(MovieList movieList) {

        if (!movieList.isEmpty()) {

            movieList.clear();

            msg = "Collection was cleared successfully";

        } else {

            msg = "Collection is empty";

        }

    }


    @Override

    public String getMessage() {

        return this.msg;

    }

}