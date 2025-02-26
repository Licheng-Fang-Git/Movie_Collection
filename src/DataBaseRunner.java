import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseRunner {
    public static void main(String[] args) {

        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        MovieDatabaseBuilder m = new MovieDatabaseBuilder();
        Scanner s = new Scanner(System.in);

        System.out.println("Which person do you want to link Kevin Bacon to?");
        System.out.print("Enter name: ");
        String name = s.nextLine();
        System.out.print("Enter a number: ");
        int count  = Integer.parseInt(s.nextLine());
        ArrayList<SimpleMovie> linking = m.menu(name, count);
        s.close();

        for (SimpleMovie movie : linking) {
            System.out.print(name + "--->");
            System.out.println(movie.getTitle() + ": Actors " + movie.getActors());
        }
        System.out.println("Number of movies: " + movies.size() );
    }
}