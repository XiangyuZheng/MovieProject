package movie.com.tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import movie.com.dal.GenresDao;
import movie.com.dal.MoviesDao;
import movie.com.dal.UsersDao;
import movie.com.model.Genres;
import movie.com.model.Movies;
import movie.com.model.Users;

public class Inserter {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		Date date = new Date();
		UsersDao usersDao = UsersDao.getInstance();
		MoviesDao moviesDao = MoviesDao.getInstance();
	//	GenresDao genresDao = GenresDao.getInstance();
		
		
		// INSERT objects from our model.
		Users user1 = new Users("hx-test1", "test1", "test-email", "h", "x", date, "profile", "f");
		user1 = usersDao.create(user1);
		Users user2 = new Users("hx-test2", "test2", "test-email", "h", "x", date, "profile", "f");
		user2 = usersDao.create(user2);
		
		Movies movie1 = new Movies("movie-test1", 2015, "url", 3.0, "description");
		movie1 = moviesDao.create(movie1);
		Movies movie2 = new Movies("movie-test2", 2014, "url", 4.0, "description");
		movie2 = moviesDao.create(movie2);
		Movies movie3 = new Movies("movie-test3", 2013, "url", 5.0, "description");
		movie3 = moviesDao.create(movie3);
		Movies movie4 = new Movies("movie-test3", 1998, "url", 4.0, "a different movie");
		movie4 = moviesDao.create(movie4);
		
//		
//		Genres genre1 = new Genres(Genres.GenreType.Action);
//		genre1 = genresDao.create(genre1);
		
		
		
		//read
		Users u1 = usersDao.getUserFromUserId(1);
		System.out.format("Reading user: u:%s f:%s l:%s e:%s \n",
				u1.getUserName(), u1.getFirstName(), u1.getLastName(), u1.getEmail());
		
		Users u2 = usersDao.getUserFromUserName("hx-test2");
		System.out.format("Reading user: u:%s f:%s l:%s e:%s \n",
				u2.getUserName(), u2.getFirstName(), u2.getLastName(), u2.getEmail());
		
		usersDao.updateEmail(user1, "newEmail");
		System.out.format("Reading user: u:%s f:%s l:%s e:%s \n",
				u1.getUserName(), u1.getFirstName(), u1.getLastName(), u1.getEmail());
		
		
		
		Movies m1 = moviesDao.getMovieById(1);
		System.out.format("Reading movie: m:%s r:%f \n",
				m1.getTitle(), m1.getRating());
		List<Movies> mList1 = moviesDao.getMoviesByTitle("movie-test3");
		for(Movies m : mList1) {
			System.out.format("Looping movies: t:%s y:%d r:%f \n",
				m.getTitle(), m.getYear(), m.getRating());
		}
		
		
		
		
		
		
	}

}
