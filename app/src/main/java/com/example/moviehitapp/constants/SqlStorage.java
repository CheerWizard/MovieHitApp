package com.example.moviehitapp.constants;

public final class SqlStorage {
    //db
    public static final class Databases {
        public static final String MOVIE_DB = "db_movie";
    }
    //versions
    public static final class Version {
        public static final int V1 = 1;
    }
    //tables
    public static final class Entities {

    }
    //columns for tables
    public static final class Columns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_MOVIEID = "movieid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_USERRATING = "userrating";
        public static final String COLUMN_POSTER_PATH = "posterpath";
        public static final String COLUMN_PLOT_SYNOPSIS = "overview";
    }
}
