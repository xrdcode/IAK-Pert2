package com.xrdcode.pertemuan2.model;

import java.util.List;

/**
 * Created by reysd on 06/08/2017.
 */

public class Movies
{
    public int page;
    public int total_results;
    public int total_pages;
    public List<Result> results;

    public class Result
    {
        public int vote_count;
        public int id;
        public boolean video;
        public double vote_average;
        public String title;
        public double popularity;
        public String poster_path;
        public String original_language;
        public String original_title;
        public List<Integer> genre_ids;
        public String backdrop_path;
        public boolean adult;
        public String overview;
        public String release_date;
    }

}
