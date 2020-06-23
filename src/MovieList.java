import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieList extends ArrayList<Movie> {
    public LocalDateTime authDateTime;

    public LocalDateTime getAuthDateTime() {

        return this.authDateTime;

    }

    public void setAuthDateTime(LocalDateTime authDateTime) {

        this.authDateTime = authDateTime;

    }
}
