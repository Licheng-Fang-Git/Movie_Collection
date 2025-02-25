import java.util.ArrayList;

/**
 * Class that represents a single Movie object
 */
public class Movie {
    private String title;
    private String cast;
    private String director;
    private String tagline;
    private String keywords;
    private String overview;
    private int runtime;
    private String genres;
    private double userRating;
    private int year;
    private int revenue;

    public Movie(String title, String cast, String director, String tagline,
                 String keywords, String overview, int runtime, String genres,
                 double userRating, int year, int revenue) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.tagline = tagline;
        this.keywords = keywords;
        this.overview = overview;
        this.runtime = runtime;
        this.genres = genres;
        this.userRating = userRating;
        this.year = year;
        this.revenue = revenue;
    }

    public String getTitle() {
        return title;
    }

    public String getStringCast() {
        return cast;

    }

    public String[] getCast() {
        int countMembers = 0;
        for (int i = 0; i < cast.length(); i++) {
            if (cast.substring(i, i + 1).equals("|")) {
                countMembers += 1;
            }
        }
        String[] tempCast = new String[countMembers];
        tempCast = cast.split("\\|");

        return tempCast;

    }

    public String getDirector() {
        return director;
    }

    public String getTagline() {
        return tagline;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getOverview() {
        return overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getGenres() {
        return genres;
    }

    public String[] getGenreList() {

        int countMembers = 0;
        for (int i = 0; i < genres.length(); i++)
        {
            if (genres.substring(i, i + 1).equals("|")) {
                countMembers += 1;
            }
        }

        String[] tempGenre = new String[countMembers];
        tempGenre = genres.split("\\|");

        return tempGenre;
    }


    public double getUserRating()
    {
        return userRating;
    }

    public int getYear()
    {
        return year;
    }

    public int getRevenue()
    {
        return revenue;
    }

    public String toString()
    {
        return "Title: " + title + ", Tagline: " + tagline;
    }
}