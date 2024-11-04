package com.example.my_movie_poster;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private final List<Movie> movieList;
    private final Context context;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public List<Movie> getMovieList() {//return just the current list of Movies
        return movieList;
    }

    /**
     * Constructor for the ImageAdapter
     *
     * @param movieList The list of movies to be displayed in the RecyclerView.
     * @param context   Used for layouts and storage for later
     * @param listener  The listener for handling item click events.
     */
    public ImageAdapter(List<Movie> movieList, Context context, OnItemClickListener listener) {
        this.movieList = movieList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ImageViewHolder(view, listener);
    }


    /**
     * Binds the movie data to the view holder for display in the RecyclerView
     *
     * @param holder   The ViewHolder will hold the view for the current movie
     * @param position The position of the movie in the list
     */
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        //create and disply movies
        Movie currentMovie = movieList.get(position);
        holder.imageView.setImageResource(currentMovie.getImageResourceId());
        holder.titleView.setText(currentMovie.getMovieName());
        //set text and images for movie
        holder.ratingView.setText("Rating--> " + currentMovie.getRating());
        holder.authorView.setText("Author(s)--> " + currentMovie.getAuthors());
        holder.descriptionView.setText(currentMovie.getDescription());

        //visually indicate selection
        holder.itemView.setAlpha(currentMovie.isSelected() ? 0.5f : 1.0f);
        Log.d("ImageAdapter", "Binding movie: " + currentMovie.getMovieName());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView titleView, ratingView, authorView, descriptionView;
        OnItemClickListener listener;


        /**
         * Constructor for the ImageViewHolder
         *
         * @param itemView The view for a single item in the RecyclerView
         * @param listener The listener for handling click events on the item
         */
        public ImageViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            titleView = itemView.findViewById(R.id.movie_name);
            ratingView = itemView.findViewById(R.id.movie_rating);
            authorView = itemView.findViewById(R.id.movie_author);
            descriptionView = itemView.findViewById(R.id.movie_description);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}