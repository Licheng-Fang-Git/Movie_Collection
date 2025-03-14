import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;


    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }


    public ArrayList<Movie> getMovies()
    {
        return movies;
    }


    public void menu()
    {
        String menuOption = "";


        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");


        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();


            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }


    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }


    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();


        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();


        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();


        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();


            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResultsTitles(results);


        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();


            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;


            System.out.println("" + choiceNum + ". " + title);
        }


        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");


        int choice = scanner.nextInt();
        scanner.nextLine();


        Movie selectedMovie = results.get(choice - 1);


        displayMovieInfo(selectedMovie);


        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }


    private void sortResultsTitles(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getStringCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void sortCastResults(ArrayList<String> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            String temp = listToSort.get(j);

            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(listToSort.get(possibleIndex - 1)) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void searchCast()
    {
        System.out.print("Enter a cast search term: ");
        String searchTerm = scanner.nextLine();
        searchTerm = searchTerm.toLowerCase();

        ArrayList<String> allCast = new ArrayList<String>();

        for (int i = 0; i < movies.size(); i++){
            String[] members = movies.get(i).getCast();
            for(int j = 0; j < members.length; j++){
                if(!(allCast.contains(members[j]))){
                    allCast.add(members[j]);
                }
            }
        }

        ArrayList<String> castMembers = new ArrayList<String>();

        for (int i = 0; i < allCast.size(); i++){
            if (allCast.get(i).toLowerCase().contains(searchTerm)){
                castMembers.add(allCast.get(i));
            }
        }
        sortCastResults(castMembers);

        // now, display them all to the user
        for (int i = 0; i < castMembers.size(); i++)
        {
            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + castMembers.get(i));
        }
        System.out.println("Which actor/actress would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        searchTerm = castMembers.get(choice - 1);

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++) {
            String[] movieCast = movies.get(i).getCast();
            for (String member : movieCast) {
                if (member.equals(searchTerm)) {
                    //add the Movie objest to the results list
                    results.add(movies.get(i));
                }
            }
        }

        sortResultsTitles(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;


            System.out.println("" + choiceNum + ". " + title);
        }


        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");


        choice = scanner.nextInt();
        scanner.nextLine();


        Movie selectedMovie = results.get(choice - 1);


        displayMovieInfo(selectedMovie);


        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String searchTerm = scanner.nextLine();


        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();


        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();


        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keyword = movies.get(i).getKeywords();
            keyword = keyword.toLowerCase();

            if (keyword.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }


        // sort the results by title
        sortResultsTitles(results);


        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;


            System.out.println("" + choiceNum + ". " + title );
        }


        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");


        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }


    private void listGenres()
    {
        ArrayList<String> allGenres = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++){
            for (int j = 0; j < movies.get(i).getGenreList().length; j++){
                if(!(allGenres.contains( movies.get(i).getGenreList()[j]))){
                    allGenres.add(movies.get(i).getGenreList()[j]) ;
                }
            }
        }
        System.out.println(allGenres);

        for (int i = 0; i < allGenres.size(); i++)
        {
            String genre = allGenres.get(i);


            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;


            System.out.println("" + choiceNum + ". " + genre );
        }

        System.out.println("Which Genre would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedGenre = allGenres.get(choice - 1);
        selectedGenre = selectedGenre.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieGenre = movies.get(i).getGenres();
            movieGenre = movieGenre.toLowerCase();

            if (movieGenre.contains(selectedGenre))
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }
        // sort the results by title
        sortResultsTitles(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title= results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title );
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int number = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(number - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }


    private void listHighestRated()
    {
        ArrayList<Movie> highestRated = new ArrayList<>();
        double rating = 10;
        while (highestRated.size() < 50){
            for (int i = 0; i < movies.size(); i++){
                if(!(highestRated.contains(movies.get(i)))) {
                    if (movies.get(i).getUserRating() >= rating) {
                        if (highestRated.size() < 50) {
                            highestRated.add(movies.get(i));
                        }
                    }
                }
            }
            rating -= 0.1;
        }

        for(int i = 0; i < highestRated.size(); i++){

            String title = highestRated.get(i).getTitle();
            String rated = String.valueOf(highestRated.get(i).getUserRating());

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + ":" + rated );

        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int number = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = highestRated.get(number - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }

    private void listHighestRevenue()
    {
        ArrayList<Movie> highestRevenue = new ArrayList<>();

       // 2 3
        for (int i = 0; i < movies.size(); i++) {
            highestRevenue.add(movies.get(i));
            for (int j = 0; j < highestRevenue.size(); j++){
                if (movies.get(i).getRevenue() > movies.get(j).getRevenue()){
                    highestRevenue.set(i, movies.get(j));
                    highestRevenue.set(j, movies.get(i));
                }
            }
        }

        for(int i = 0; i < 50; i++){

            String title = highestRevenue.get(i).getTitle();
            String revenue = String.valueOf(highestRevenue.get(i).getRevenue());


            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + ": $" + revenue );

        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int number = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = highestRevenue.get(number - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }


    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();


            movies = new ArrayList<Movie>();


            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");


                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);


                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}
