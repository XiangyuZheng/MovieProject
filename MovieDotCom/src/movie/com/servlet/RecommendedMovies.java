
package movie.com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.com.dal.MoviesDao;
import movie.com.model.Movies;

@WebServlet("/recommendedmovies")
public class RecommendedMovies extends HttpServlet {

    private MoviesDao moviesDao = MoviesDao.getInstance();

    @Override
    public void init() throws ServletException {
        moviesDao = MoviesDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate UserName.
        if (req.getSession().getAttribute("userid") == null) {
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/home"));
            return;
        }
        int userId = (int) req.getSession().getAttribute("userid");

        // Retrieve Movies, and store in the request.
        List<Movies> movies = new ArrayList<Movies>();
        try {
            movies = moviesDao.getRecommendedMoviesByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/RecommendedMovies.jsp").forward(req, resp);
    }

}
