import java.time.format.DateTimeFormatter;


/**
 * реализация команды info
 */

public class Info implements Comand {

    private String msg;


    @Override

    public void execute(MovieList movieList) {

        if (movieList.isEmpty()) {

            msg = "Collection is empty";

        } else {

            msg = ("Amount of cities in storage: " + movieList.size() + "\n" +

                    "Date of initialization: " + movieList.getAuthDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n" +

                    "Collection type: " + movieList.getClass().getSimpleName());

        }

    }


    @Override

    public String getMessage() {

        return this.msg;

    }

}