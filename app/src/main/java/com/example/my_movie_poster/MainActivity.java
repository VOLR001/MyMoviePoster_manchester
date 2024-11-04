package com.example.my_movie_poster;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {
    //values to store movies, watchlist button and the image adapter for the posters
    private MaterialButton watchlistButton;
    private List<Movie> movieList;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //toolbar setup at the top for easy access
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("COLE MANCHESTER"); //Toolbar with my name
        toolbar.setTitleTextColor(Color.parseColor("#FF416AD3"));
        setSupportActionBar(toolbar);   //set colors, title and then make drawable

        //create our 'movies' to be used
        int[] imageResources = {
                R.drawable.image2, R.drawable.img3, R.drawable.img4,
                R.drawable.img5, R.drawable.img1, R.drawable.img6,
                R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10
        };

        //load drawable resources dynamically
        String[] movieTitles = {
                "Legendary Movie", "Batman 2", "Scream 3",
                "Karate Man 4", "Transformers 100", "Starwars 500",
                "Good Movie Choice", "Ice Box", "Animal 2", "Pulp Fiction"
        };

        String[] movieRatings = {
                "5/5", "4/5", "3/5", "5/5", "4/5",
                "5/5", "2/5", "3/5", "4/5", "5/5"
        };

        String[] movieDirectors = {
                "Director David", "Director Anna", "Director Stevie",
                "Director Cass", "Director Bandit", "Director Tyler",
                "Director Belle", "Director Ian", "Director Abraham", "Director Dot"
        };

        String[] movieDescriptions = {
                "A scary thriller", "Its just batman 2",
                "Another scary one I guess", "Action movie with good music",
                "Did they really need another Transformers movie", "Yes they will continue to make more starwars probably",
                "A mysterious murder mystery", "A movie about the life of a refrigerator",
                "An animal documentary, the prequel", "A solid choice all around"
        };

        //create movie list
        movieList = new ArrayList<>(); // Use class-level variable

        //loop to add movies dynamically
        for (int i = 0; i < imageResources.length; i++) {
            movieList.add(new Movie(
                    imageResources[i],
                    movieTitles[i],
                    movieRatings[i],
                    movieDirectors[i],
                    movieDescriptions[i]
            ));
        }

        // set the adapter up with current movies
        adapter = new ImageAdapter(movieList, this, this);
        recyclerView.setAdapter(adapter);

        //watchlist Button setup
        watchlistButton = findViewById(R.id.watchlist_button);
        watchlistButton.setText("Your WatchList"); // user  watchlist starts invisible until added to
        watchlistButton.setVisibility(View.GONE);

        // event listener for the Watchlist Button in case of additions
        watchlistButton.setOnClickListener(v -> {
            Toast.makeText(this, "Watchlist updated!", Toast.LENGTH_SHORT).show();
        });
    }


    /**
     * Callback method for item click events in the RecyclerView
     * This method updates the selection state of the clicked movie
     * a Toast message to provide feedback about the selection.
     *
     * @param position The position of the clicked item in the RecyclerView
     */
    @Override
    public void onItemClick(int position) {
        Movie selectedMovie = movieList.get(position);
        selectedMovie.setSelected(!selectedMovie.isSelected());
        adapter.notifyItemChanged(position);

        //update Watchlist Button visibility based on selection
        boolean anySelected = false;
        for (Movie movie : movieList) {
            if (movie.isSelected()) {
                anySelected = true;
                break;
            }
        }
        watchlistButton.setVisibility(anySelected ? View.VISIBLE : View.GONE);

        //display Toast for selection feedback letting us know if the selected is removed or added
        Toast.makeText(this, selectedMovie.isSelected() ? "Added to Watchlist" : "Removed from Watchlist", Toast.LENGTH_SHORT).show();
    }
}