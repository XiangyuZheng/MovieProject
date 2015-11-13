
package movie.com.model;

public class FavoriteMovies {
    private int favoriteMovieId;
    private Users user;
    private Movies movie;

    public FavoriteMovies(int favoriteMovieId, Users user, Movies movie) {
        this.favoriteMovieId = favoriteMovieId;
        this.user = user;
        this.movie = movie;
    }

    public FavoriteMovies(int favoriteMovieId) {
        this.favoriteMovieId = favoriteMovieId;
    }

    public int getFavoriteMovieId() {
        return favoriteMovieId;
    }

    public void setFavoriteMovieId(int favoriteMovieId) {
        this.favoriteMovieId = favoriteMovieId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

}
