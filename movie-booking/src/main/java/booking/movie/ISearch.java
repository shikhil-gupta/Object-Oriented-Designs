package booking.movie;

import java.util.List;

public interface ISearch {

    List<Movie> searchMoviewbyName(String searchName);
    List<Movie> searchMoviewByCharacter(String characterName);

}
