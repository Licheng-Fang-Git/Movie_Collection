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
        Deque<SimpleMovie> dq = new LinkedList<>();
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        ArrayList<SimpleMovie> kevinMovies = MovieDatabaseBuilder.getMovieDB("src/output.txt");

        String searchName = search;

        ArrayList<SimpleMovie> link = new ArrayList<>();
        ArrayList<SimpleMovie> specificActor = new ArrayList<>();
        HashSet<SimpleMovie> s = new HashSet<>();

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getActors().contains(searchName)) {
                specificActor.add(movies.get(i));
                dq.add(movies.get(i));

            }
        }
        getActors.add(searchName);

        for (int i  = 0; i < specificActor.size(); i++) {
            if (specificActor.get(i).getActors().contains("Kevin Bacon")) {
                link.add(specificActor.get(i));
                getActors.add("Kevin Bacon");
                return link;
            }
        }

        while (!dq.isEmpty()) {
            SimpleMovie checkMovie = dq.pollFirst();

            if (s.contains(checkMovie)) {
                continue;
            }
            s.add(checkMovie);

            if (checkMovie.getActors().contains("Kevin Bacon")) {
                getActors.add("Kevin Bacon");
                link.add(checkMovie);
                dq.clear();
                return link;
            }

            ArrayList<String> actors = checkMovie.getActors();

            SimpleMovie other = actorInOtherMoive(movies, s, actors, search);

            if (s.contains(other)) {
                continue;
            }

            if (!(other == null)) {
                if(other.getActors().contains("Kevin Bacon")) {
                    link.add(checkMovie);
                    dq.addFirst(other);

                }
                else{
                    dq.addLast(other);
                }


            }
        }

        getActors.add("Kevin Bacon");
        return link;
    }

    public ArrayList<String> getGetActors() {
        return getActors;
    }

    public SimpleMovie actorInOtherMoive(ArrayList<SimpleMovie> movies, HashSet<SimpleMovie> s, ArrayList<String> actors, String search){
        ArrayList<SimpleMovie> moreThanOne =  new ArrayList<>();
        for (String actor : actors) {
            if (actor.equals(search)) {
                continue;
            }
            if (getActors.contains(actor)) {
                continue;
            }

            if (actor.contains("[")) {
                int bracket = actor.indexOf("[");
                actor = actor.substring(bracket + 1);
            }
            if (actor.contains("]")) {
                int bracket = actor.indexOf("]");
                actor = actor.substring(0, bracket);
            }

            for (int i = 0; i < movies.size(); i++) {
                if (s.contains(movies.get(i))) {
                    continue;
                }
                if (movies.get(i).getActors().contains(actor) && movies.get(i).getActors().contains("Kevin Bacon")) {
                    getActors.add(actor);
                    return movies.get(i);
                }
            }
        }

        for (String actor : actors) {
            if (actor.equals(search)) {
                continue;
            }

            if (getActors.contains(actor)) {
                continue;
            }

            if (actor.contains("[")) {
                int bracket = actor.indexOf("[");
                actor = actor.substring(bracket + 1);
            }
            if (actor.contains("]")) {
                int bracket = actor.indexOf("]");
                actor = actor.substring(0, bracket);
            }

            for (int i = 0; i < movies.size(); i++) {
                if (s.contains(movies.get(i))) {
                    continue;
                }
                if (movies.get(i).getActors().contains(actor)) {
                    getActors.add(actor);
                    return movies.get(i);
                }
            }
        }

        return null;
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
