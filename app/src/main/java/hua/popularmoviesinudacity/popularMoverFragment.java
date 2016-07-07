package hua.popularmoviesinudacity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by caihua2300 on 04/07/2016.
 */
public class popularMoverFragment extends Fragment {
    ArrayList movieList;
    private movieAdaptor adaptor;
    public popularMoverFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onStart() {
        super.onStart();
    }
    private void updateMovie(){
        FetchMovieDataTask fetchMovieDataTask=new FetchMovieDataTask();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String order=sharedPreferences.getString(getString(R.string.pref_order_key),getString(R.string.default_order));
        fetchMovieDataTask.execute(order);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.popularmoviefragment,container,false);
        adaptor=new movieAdaptor(getActivity(),new ArrayList<movie>());
        GridView gridView=(GridView) rootView.findViewById(R.id.gridViewMain);
        gridView.setAdapter(adaptor);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_refresh,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_fresh){
            updateMovie();
        }
        else if(item.getItemId()==R.id.action_order){


        }
        return super.onOptionsItemSelected(item);
    }
    class FetchMovieDataTask extends AsyncTask<String,Void,String[][]>{
        private final String LOG_TAG=FetchMovieDataTask.class.getSimpleName();

        private String[][] getMovieDataByJson(String movieJsonStr)throws JSONException{
            final String baseMoviewPathUrl="http://image.tmdb.org/t/p/w185/";
            final String RESULT="results";
            final String POSTER_PATH="poster_path";
            final String ORIGINAL_TITLE="original_title";
            final String OVERVIEW="overview";
            final String VOTE_AVERAGE="vote_average";
            final String RELEASE_DATE="release_date";

            JSONObject Moviewdata=new JSONObject(movieJsonStr);
            JSONArray jsonArray=Moviewdata.getJSONArray(RESULT);
            String[][] movieCollection=new String[12][5];

            for(int i=0;i<12;i++){
                JSONObject singleMovie=jsonArray.getJSONObject(i);
                String path=singleMovie.getString(POSTER_PATH);

                String title=singleMovie.getString(ORIGINAL_TITLE);
                String overview=singleMovie.getString(OVERVIEW);
                String vote_average=singleMovie.getString(VOTE_AVERAGE);
                String release_date=singleMovie.getString(RELEASE_DATE);

                movieCollection[i][0]=baseMoviewPathUrl+path;
                movieCollection[i][1]=title;
                movieCollection[i][2]=overview;
                movieCollection[i][3]=vote_average;
                movieCollection[i][4]=release_date;



            }



            return movieCollection;
        }

        @Override
        protected String[][] doInBackground(String... params) {
            HttpURLConnection urlConnection=null;
            BufferedReader reader=null;
            String movieJsonStr=null;
            try{
                final String baseUrl="https://api.themoviedb.org/3/discover/movie?";
                final String api_key="5b225f3e1c035eb518d4398622376bc8";
                final String SORT_PARAM="sort_by";
                Uri uri=Uri.parse(baseUrl).buildUpon().appendQueryParameter(SORT_PARAM,params[0]).appendQueryParameter("api_key",api_key).build();
                URL url=new URL(uri.toString());
                Log.d(LOG_TAG,uri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                movieJsonStr=buffer.toString();
                Log.d(LOG_TAG,movieJsonStr);



            }catch(IOException e){
                return null;
            }finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("popularMovieFragment", "Error closing stream", e);
                    }
                }
            }
            try{
                return getMovieDataByJson(movieJsonStr);
            }catch(JSONException e){
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            return new String[0][];
        }

        @Override
        protected void onPostExecute(String[][] strings) {
            if(strings!=null){
                adaptor.clear();
                for(int i=0;i<strings.length;i++){

                        movie m=new movie(strings[i][0],strings[i][1],strings[i][2],strings[i][3],strings[i][4]);
                        adaptor.add(m);


                }

            }

            super.onPostExecute(strings);
        }
    }
}
