
package movie.com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import movie.com.model.FavoriteMovies;
import movie.com.model.Movies;
import movie.com.model.Users;

public class FavoriteMoviesDao {

    protected ConnectionManager connectionManager;
    private static FavoriteMoviesDao instance = null;

    protected FavoriteMoviesDao() {
        connectionManager = new ConnectionManager();
    }

    public static FavoriteMoviesDao getInstance() {
        if (instance == null) {
            instance = new FavoriteMoviesDao();
        }
        return instance;
    }

    public FavoriteMovies create(FavoriteMovies favoriteMovies) throws SQLException {
        String insertFavoriteMovie = "INSERT INTO FavoriteMovies(UserId,UserId) VALUES(?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertFavoriteMovie);
            insertStmt.setInt(1, favoriteMovies.getUser().getUserId());
            insertStmt.setInt(2, favoriteMovies.getMovie().getMovieId());
            insertStmt.executeUpdate();
            return favoriteMovies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public FavoriteMovies getMovieGenresById(int favoriteMoviesId) throws SQLException {
        String selectFavoriteMovies = "SELECT FavoriteMovieId,UserId,MovieId"
                + " FROM FavoriteMovies WHERE FavoriteMovieId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectFavoriteMovies);
            selectStmt.setInt(1, favoriteMoviesId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            if (results.next()) {
                int resultFavoriteMoviesId = results.getInt("FavoriteMovieId");
                int userId = results.getInt("UserId");
                int movieId = results.getInt("MovieId");
                Users user = usersDao.getUserFromUserId(userId);
                Movies movie = moviesDao.getMovieById(movieId);
                FavoriteMovies favoriteMovies = new FavoriteMovies(resultFavoriteMoviesId, user,
                        movie);
                return favoriteMovies;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public FavoriteMovies delete(FavoriteMovies favoriteMovies) throws SQLException {
        String deleteFavoriteMovie = "DELETE FROM FavoriteMovies WHERE FavoriteMovieId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteFavoriteMovie);
            deleteStmt.setInt(1, favoriteMovies.getFavoriteMovieId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
