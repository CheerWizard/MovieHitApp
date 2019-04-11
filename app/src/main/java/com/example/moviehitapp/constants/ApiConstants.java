package com.example.moviehitapp.constants;

/**This looks not convenient as url data are not public for outside
 * Java can't find file settings.properties where I have placed all those urls and api keys
 * which any consumers could rewrite in production
 *
 * That's really issue as for changing url of some webservice ,
 * you should go to Java class and change it there.
 *
 * In other words , other clients must know Java code to understand how to change
 * this data.*/
public final class ApiConstants {
    //todo How to read settings.properties file in Dagger2 runtime (throws FileNotFoundException)
    public static final String url = "http://api.themoviedb.org/3/";
    public static final String key = "0158bec8a1eaa020bc08bf9bb7ea5577";
}
