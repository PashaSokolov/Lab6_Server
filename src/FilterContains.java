public class FilterContains implements BigCommand {

    private String msg;

    @Override

    public void execute(MovieList movieList, Movie movie) {

        msg = "Invalid argument";

    }



    public void execute(MovieList movieList, String subName){

        boolean contains = false;

        if (!movieList.isEmpty()){

            msg = "";

            for (Movie m : movieList){

                if(m.getName().contains(subName)){

                    contains = true;

                    msg = msg + (m.toString()) + "\n----//----\n";

                }

            }



            if (!contains){

                msg = ("Nothing was found");

            }else {

                msg = msg.substring(0, msg.length() - 1);

            }

        }else {

            msg = ("Collection is empty");

        }

    }



    @Override

    public String getMessage() {

        return msg;

    }

}