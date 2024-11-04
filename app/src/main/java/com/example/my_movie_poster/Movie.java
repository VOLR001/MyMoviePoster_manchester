package com.example.my_movie_poster;

public class Movie {
    private int imageResourceId;
    private String movieName;
    private String rating;
    private String authors;
    private String description;
    private boolean selected;
    //create variables needed to display

    /**
     *Constructor for each movie within the recyclerview
     * @param imageResourceId The id of each image displayed.
     * @param movieName         name of each movie shown
     * @param rating            Rating of each movie displayed.
     * @param authors           Directors who wrote the movies I made up
     * @param description       Breif analysis of the movie
     */
    public Movie(int imageResourceId, String movieName, String rating, String authors, String description) {
        this.imageResourceId = imageResourceId;
        this.movieName = movieName;
        this.rating = rating;
        this.authors = authors;
        this.description = description;
        this.selected = false;
        //check Movie values and also if its selected in list
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getRating() {
        return rating;
    }

    public String getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}