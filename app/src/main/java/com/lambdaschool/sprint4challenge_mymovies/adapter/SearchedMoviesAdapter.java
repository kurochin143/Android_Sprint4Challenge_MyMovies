package com.lambdaschool.sprint4challenge_mymovies.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lambdaschool.sprint4challenge_mymovies.R;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;
import com.lambdaschool.sprint4challenge_mymovies.dao.FavoriteMovieDao;
import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;

import java.util.ArrayList;

public class SearchedMoviesAdapter extends RecyclerView.Adapter<SearchedMoviesAdapter.ViewHolder> {

    private ArrayList<MovieOverview> searchedMovies = new ArrayList<>();
    private ArrayList<FavoriteMovie> favoriteMovies = new ArrayList<>();
    private OnAddFavoriteMovieListener onAddFavoriteMovieListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_searched_movie, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final MovieOverview searchedMovie = searchedMovies.get(i);

        boolean isFavorite = false;
        for (FavoriteMovie favoriteMovie : favoriteMovies) {
            if (searchedMovie.getId() == favoriteMovie.getId()) {
                isFavorite = true;
                break;
            }
        }

        if (isFavorite) {
            viewHolder.itemView.setBackgroundColor(Color.argb(100, 255, 255, 0));
        } else {
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
        }

        viewHolder.titleTextView.setText(searchedMovie.getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddFavoriteMovieListener != null) {
                    onAddFavoriteMovieListener.addFavoriteMovie(searchedMovie);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchedMovies.size();
    }

    public void setSearchedMovies(@NonNull ArrayList<MovieOverview> searchedMovies) {
        this.searchedMovies = searchedMovies;
        notifyDataSetChanged();
    }

    public void setFavoriteMovies(@NonNull ArrayList<FavoriteMovie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
        notifyDataSetChanged();
    }

    public void setOnAddFavoriteMovieListener(OnAddFavoriteMovieListener l) {
        onAddFavoriteMovieListener = l;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.item_searched_movie_text_title);
        }

        private TextView titleTextView;
    }

    public interface OnAddFavoriteMovieListener {
        void addFavoriteMovie(MovieOverview movieOverview);
    }
}
