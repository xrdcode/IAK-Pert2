package com.xrdcode.pertemuan2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xrdcode.pertemuan2.R;
import com.xrdcode.pertemuan2.helper.MovieHelper;
import com.xrdcode.pertemuan2.model.Movies;

import java.util.List;

/**
 * Created by reysd on 06/08/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterHolder> {

    private Context mContext;
    private List<Movies.Result> movieList;

    public Adapter(Context mContext, List<Movies.Result> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public Adapter.AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.list_movies_item, parent, false);
        AdapterHolder adapterHolder = new AdapterHolder(rowView);
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(Adapter.AdapterHolder holder, int position) {
        holder.movieTitle.setText(movieList.get(position).title);
        holder.movieOverview.setText(movieList.get(position).overview);
        Picasso.with(mContext).load(MovieHelper.MOVIE_POSTER_URL + movieList.get(position).poster_path);
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {

        TextView movieTitle, movieOverview;
        ImageView movieImg;

        public AdapterHolder(View itemView) {
            super(itemView);
            movieTitle = (TextView) itemView.findViewById(R.id.title);
            movieOverview = (TextView) itemView.findViewById(R.id.overview);
            movieImg = (ImageView) itemView.findViewById(R.id.movieImg);
        }
    }
}
