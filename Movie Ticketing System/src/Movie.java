/*Author : Niraj Shah Aman Arora
Description : Movie*/
public class Movie implements java.io.Serializable{

	
	//list of shows
	private int movieID;
	private String movieName;
	private int movieRank;
	
	
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public Movie(String movieName, int movieRank) {
		this.movieName = movieName;
		this.movieRank = movieRank;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getMovieRank() {
		return movieRank;
	}
	public void setMovieRank(int movieRank) {
		this.movieRank = movieRank;
	}
	@Override
	public String toString() {
		return "\n Movie [movieID=" + movieID + ", movieName=" + movieName + ", movieRank=" + movieRank + "]";
	}
	
	
	
}
