import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseRunner {
    public static void main(String[] args) {

        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        MovieDatabaseBuilder m = new MovieDatabaseBuilder();
        m.kevinBaconFile(movies);
        Scanner s = new Scanner(System.in);

        System.out.println("Which person do you want to link Kevin Bacon to?");
        System.out.print("Enter name: ");
        String name = s.nextLine();
        System.out.print("Enter a number: ");
        int count  = Integer.parseInt(s.nextLine());
        ArrayList<SimpleMovie> linking = m.menu(name, count);
        ArrayList<String> actors = m.getGetActors();
        s.close();
        System.out.println(linking);
        for(int i = 0; i < linking.size(); i++){
            System.out.print(actors.get(i) + " --> ");
            System.out.print(linking.get(i).getTitle() + " --> ");
        }
        System.out.println(actors.getLast());
        System.out.println("Bacon number of " + linking.size());

    }
}