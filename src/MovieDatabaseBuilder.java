import javax.swing.plaf.IconUIResource;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class MovieDatabaseBuilder {

    private Scanner scanner;
    private ArrayList<Movie> priority;
    private ArrayList<String> getActors = new ArrayList<>();

    public MovieDatabaseBuilder() {
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
        } catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return movies;
    }

    public void kevinBaconFile(ArrayList<SimpleMovie> movies) {

        try {
            File f = new File("src/output.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);

            for (SimpleMovie n : movies) {
                if (n.getActors().contains("Kevin Bacon")) {
                    fw.write(n.getTitle() + "--- " + n.getActors() + "\n");
                }
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Writing file failed");
            System.out.println(ioe);
        }

    }

    public ArrayList<SimpleMovie> menu(String search, int count) {
        Deque<SimpleMovie> dq = new ArrayDeque<SimpleMovie>();
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        ArrayList<SimpleMovie> kevinMovies = MovieDatabaseBuilder.getMovieDB("src/output.txt");

        String searchName = search;

        ArrayList<SimpleMovie> link = new ArrayList<>();
        ArrayList<SimpleMovie> specificActor = new ArrayList<>();
        Set<SimpleMovie> s = new HashSet<>();

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getActors().get(0).contains(searchName)) {
                specificActor.add(movies.get(i));
                dq.add(movies.get(i));

            }
        }
        getActors.add(searchName);

        while (!dq.isEmpty()) {
            SimpleMovie checkMovie = dq.pop();
            if (s.contains(checkMovie)) {
                continue;
            }
            s.add(checkMovie);

            ArrayList<String> actors =  checkMovie.getActors();

            for (String actor : actors) {

                if (actor.contains("Kevin Bacon")) {
                    continue;
                }

                if (actor.contains(search)) {
                    continue;
                }

                if (getActors.contains(actor)) {
                    continue;
                }

                if(actor.contains("[")){
                    int bracket = actor.indexOf("[");
                    actor = actor.substring(bracket+1);
                }
                if(actor.contains("]")){
                    int bracket = actor.indexOf("]");
                    actor = actor.substring(0,bracket);
                }

                for (int i = 0; i < kevinMovies.size(); i++) {
                    if (s.contains(kevinMovies.get(i))) {
                        continue;
                    }

                    if (kevinMovies.get(i).getActors().get(0).contains(actor)) {
//                        System.out.println(actor);
//                        System.out.println(kevinMovies);
//                        System.out.println(kevinMovies.get(i));
                        link.add(checkMovie);
                        getActors.add(actor);
                        link.add(kevinMovies.get(i));
                        s.add(kevinMovies.get(i));
                    }

                    else{
                        for(int j = 0; j < movies.size(); j++){
                            if (s.contains(movies.get(i))){
                                continue;
                            }
                            if (!(getActors.contains(actor))) {
                                if (movies.get(i).getActors().get(0).contains(actor)) {
                                    dq.add(movies.get(i));
                                    link.add(movies.get(i));
                                    s.add(movies.get(i));
                                }
                            }
                        }
                    }
                }
            }
        }
        getActors.add("Kevin Bacon");
        if (link.size() == 0) {
            if(specificActor.size() != 0) {
                link.add(specificActor.get(0));
            }
        }


        return link;
    }

    public ArrayList<String> getGetActors() {
        return getActors;
    }

    public String mostAppear(){
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        int max = 0;
        int count = 0;
        String actor = "";
        String match = "";
        for (int i = 0; i < movies.size(); i++){
            String[] actors = movies.get(i).getActors().get(0).split(", ");
            for (String actored : actors) {
                if(actored.contains("Kevin Bacon")){
                    continue;
                }
                if(actored.contains("Kyra Sedgwick")){
                    continue;
                }
                if(actored.contains("Oliver Platt")){
                    continue;
                }
                if(actored.contains("Jr.")){
                    continue;
                }

                count = 0;
                for (int j = i; j < movies.size(); j++) {
                    if( movies.get(j).getActors().get(0).contains(actored)){
                        count += 1;
                    }
                }
                if (count > max){
                    max = count;
                    actor = actored;
                }
            }
        }
        System.out.println(actor);
        return actor;
    }


}
