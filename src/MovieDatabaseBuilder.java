import javax.swing.plaf.IconUIResource;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
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

    public void kevinBaconFile(ArrayList<SimpleMovie> movies){
        ArrayList<SimpleMovie> kevinBaconMovie = new ArrayList<SimpleMovie>();

        try {
            File f = new File("src/output.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);

            for (SimpleMovie n : movies) {
                if (n.getActors().contains("Kevin Bacon")) {
                    fw.write(n.getTitle() + "--- " + n.getActors() + "\n" );
                }
            }
            fw.close();
        }
        catch (IOException ioe) {
            System.out.println("Writing file failed");
            System.out.println(ioe);
        }

    }

    public ArrayList<SimpleMovie> menu(String search, int count){
        Deque<SimpleMovie> dq = new ArrayDeque<SimpleMovie>();
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/output.txt");

        String searchName = search;

        ArrayList<SimpleMovie> link = new ArrayList<>();
        Set<SimpleMovie> s = new HashSet<>();

        int number  = 0;
        ArrayList<String> allActors = new ArrayList<>();

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getActors().get(0).contains(searchName)) {
                link.add(movies.get(i));
                dq.add(movies.get(i));
                allActors.add(" " + searchName);
            }
            i = movies.size();
        }


        while (!dq.isEmpty()){

            SimpleMovie checkMovie = dq.pop();
            if (s.contains(checkMovie)){
                continue;
            }
            s.add(checkMovie);
            String[] actors = checkMovie.getActors().get(0).split(",");

            for (String actor : actors){
                if(actor.equals(" Kevin Bacon") || actor.equals(" Kevin Bacon]") || actor.equals(" [Kevin Bacon")  ){
                    continue;
                }

                if(actor.equals(" " + searchName)){
                    continue;
                }

                for (int i = 0; i < movies.size(); i++){
                    if (s.contains(movies.get(i))){
                        continue;
                    }
                    // contains the actor in another movie
                    if (movies.get(i).getActors().get(0).contains(actor)){
                        for (String linkup : allActors) {

                            System.out.println("true");
                            System.out.println(actor);
                            System.out.println(movies.get(i));
                            allActors.add(actor);
                            link.add(movies.get(i));
                            dq.add(movies.get(i));
                        }
                    }
                }
            }

        }

        return link;

    }


}