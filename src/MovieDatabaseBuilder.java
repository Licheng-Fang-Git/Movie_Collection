import javax.swing.plaf.IconUIResource;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;


public class MovieDatabaseBuilder {

    private Scanner scanner;
    private ArrayList<Movie> priority;

    public MovieDatabaseBuilder(){
        scanner = new Scanner(System.in);
    }

    public static ArrayList<SimpleMovie> getMovieDB(String fileName) {
        ArrayList<SimpleMovie> movies = new ArrayList<SimpleMovie>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("---");
                if (data.length > 1) {
                    SimpleMovie s = new SimpleMovie(data[0], data[1]);
                    movies.add(s);
                }

            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return movies;
    }

    public ArrayList<SimpleMovie> menu(String search, int count){
        Deque<SimpleMovie> dq = new ArrayDeque<SimpleMovie>();
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");

        String searchName = search;

        ArrayList<SimpleMovie> link = new ArrayList<>();
        Set<SimpleMovie> s = new HashSet<>();
        String kevin = "Kevin Bacon";
        int number  = 0;

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getActors().contains(kevin)) {
                s.add(movies.get(i));
                dq.add(movies.get(i));
                link.add(movies.get(i));
                i = movies.size();
            }
        }

        while (!dq.isEmpty() && number <= count){
            SimpleMovie checkMovie = dq.pop();
            if (s.contains(checkMovie)){
                continue;
            }
            s.add(checkMovie);
            for (int i = 0; i < movies.size(); i++) {
                if (checkMovie.getActors().contains(kevin) && checkMovie.getActors().contains(searchName)){
                    link.add(checkMovie);
                    dq.add(checkMovie);
                }
            }


            number += 1;
        }

        return link;

    }


}