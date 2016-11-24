

//*********************************************************************
//* *
//* CIS611 Fall Session 2016 Niraj Shah Aman Arora*
//* *
//* Program Assignment PP04 *
//* *
//* Class Description *
//* * Creates the structure to store information about the songs 
//* *
//* Date Created 11/15/2016
//* *
//* Saved in: PP04.java *
//* *
//*********************************************************************
public class Song {
	
	private int rank;
	private String writer;
	private String producer;
	private String releaseDate;
	private String url;
	private String description;
	
	static int remainingObjects=50;

	public Song(int rank, String writer, String producer, String releaseDate,
			String url, String description) {
		super();
		this.rank = rank;
		this.writer = writer;
		this.producer = producer;
		this.releaseDate = releaseDate;
		this.url = url;
		this.description = description;
		remainingObjects--;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * @return the writer
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}

	/**
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * @param producer the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Song [rank=" + rank + ", writer=" + writer + ", producer="
				+ producer + ", releaseDate=" + releaseDate + ", url=" + url
				+ ", description=" + description + "]";
	}
	
	
	
}
