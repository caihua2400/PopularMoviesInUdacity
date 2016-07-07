package hua.popularmoviesinudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_frame);
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(R.id.detailContainer,new DetailFragment()).commit();
        }




    }
    public static class DetailFragment extends Fragment{
        public DetailFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.movie_detail_fragment,container,false);
            Intent intent=getActivity().getIntent();
            if(intent!=null&&intent.hasExtra("singleMovie")){
                movie m= (movie) intent.getSerializableExtra("singleMovie");
                

            }

            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

}
