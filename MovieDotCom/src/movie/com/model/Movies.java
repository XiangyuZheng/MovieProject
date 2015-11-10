package movie.com.model;

public class Movies {
	
	protected int movieId;
	protected String title;
	protected int year;
	protected String imageURL;
	protected double rating;
	protected String description;
	
	
	public Movies(int movieId, String title, int year, String imageURL, double rating, String description) {
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.imageURL = imageURL;
		this.rating = rating;
		this.description = description;
	}

	public Movies(String title, int year, String imageURL, double rating, String description) {
		this.title = title;
		this.year = year;
		this.imageURL = imageURL;
		this.rating = rating;
		this.description = description;
	}


	
	public long getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	

	public String getTitle() {
		return title;
	}



	public void setName(String title) {
		this.title = title;
	}



	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getImageURL() {
		return imageURL;
	}


	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	

}
