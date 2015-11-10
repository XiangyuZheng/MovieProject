package movie.com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import movie.com.model.Genres;

public class GenresDao {
	protected ConnectionManager connectionManager;
	private static GenresDao instance = null;
	
	protected GenresDao() {
		connectionManager = new ConnectionManager();
	}
	public static GenresDao getInstance() {
		if(instance == null) {
			instance = new GenresDao();
		}
		return instance;
	}

	
	public Genres create(Genres genre) throws SQLException {
		String insertGenre =
			"INSERT INTO Genres(GenreType) " +
			"VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertGenre,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, genre.getGenreType().name());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int genreId = -1;
			if(resultKey.next()) {
				genreId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			genre.setGenreId(genreId);
			return genre;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	
	/**
	 * Delete the Genre instance.
	 * This runs a DELETE statement.
	 */
	public Genres delete(Genres genre) throws SQLException {

		String deleteGenre = "DELETE FROM Genres WHERE genreId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGenre);
			deleteStmt.setInt(1, genre.getGenreId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}
