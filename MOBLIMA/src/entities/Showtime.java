package entities;

import java.time.LocalDateTime;
import java.util.*;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.*;

public class Showtime extends Cinema{

    private int showtimeID;
    private int movieID;
    private LocalDateTime dateTime;

    //Unique movieID to specify the movie being shown for that showtime.
    private String movieTitle;
    /**
     * Movie Format of the specific showtime.
     */
    private MovieType movieReso;
    /**
     * Cinema object with access to a seating plan. Cinema held is the cinema at which the movie is showing.
     */
    private Cinema cinema;
    /**
     * Name of the cineplex where the movie is showing at this specific showtime.
     */
    private String cineplexName;
    /**
     * Status of the Showtime, whether its AVAILABLE, or SELLING_FAST etc.
     */
    private CinemaStatus cinemaStatus;
    private int[][] seats;

    public Showtime(String name, String location, int numCinemas, int cinemaID, int showtimeID){
        super(name, location, numCinemas, cinemaID);
        this.showtimeID = showtimeID;
        
        try {
            
            Path path = Paths.get("data\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+getShowtimeID()+".csv");
            // System.out.println(path.toAbsolutePath().toString());

            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            List<String[]> r = csvReader.readAll(); //Read File

            int[][] seats = new int[5][10];

            for (int i = 0; i < 5; i++){
                for(int j = 0; j < 10; j++){

                    seats[i][j] = Integer.valueOf(r.get(i)[j]); //Copying individual lines into seats array

                }
            }

            this.seats = seats;
            
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

    public void showSeats(){ //Printer method for seats
        System.out.println(super.getName()+", hall "+(super.getCinemaID()+1)+", movie " + getShowtimeID());
        
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){

                System.out.print(seats[i][j]);

            }
            System.out.println("");
        }

        System.out.println("");
    }


    //Methods


    /* Updates the status of the cinema depending on proportion of seats filled.
    public void updateCinemaStatus() {
    	double percentageFilled = (double) getCinema().getOccupiedSeatsNo() / (double) getCinema().getTotalSeatNo();
    	if (percentageFilled <= 0.50) {
    		setCinemaStatus(CinemaStatus.AVAILABLE);
    	}
    	else if (percentageFilled < 1) {
    		setCinemaStatus(CinemaStatus.SELLING_FAST);
    	}
    	else {
    		setCinemaStatus(CinemaStatus.SOLD_OUT);
    	}
    }
    */


    //Getters

    public Integer getShowtimeID() {return showtimeID;}
    public Integer getMovieID() {return movieID;}
    public LocalDateTime getDateTime() {return dateTime;}
    public String getMovieTitle() {return movieTitle;}
    public MovieType getMovieFormat() {return movieReso;}
    public Cinema getCinema() {return cinema;}
    public String getCineplexName() {return cineplexName;}
    public CinemaStatus getCinemaStatus() {return cinemaStatus;}

    //Setters

    public void setShowtimeID(Integer showtimeID) {this.showtimeID = showtimeID;}
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setMovieID(Integer movieID) {this.movieID = movieID;}
    public void setMovieFormat(MovieType movieReso) {this.movieReso = movieReso;}
    public void setCinema(Cinema cinema) {this.cinema = cinema;}
    public void setCineplexName(String cineplexName) {this.cineplexName = cineplexName;}
    public void setCinemaStatus(CinemaStatus cinemaStatus) {this.cinemaStatus = cinemaStatus;}

};
