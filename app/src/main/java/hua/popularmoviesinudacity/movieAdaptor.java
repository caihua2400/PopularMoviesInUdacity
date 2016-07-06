package hua.popularmoviesinudacity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by caihua2300 on 06/07/2016.
 */
public class movieAdaptor extends ArrayAdapter<movie> {
    public movieAdaptor(Context context, List<movie> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        movie m=getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.main_list_item,parent,false);

        }

        ImageView imageView=(ImageView) convertView.findViewById(R.id.main_image);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg ").into(imageView);
        //imageView.setImageResource(m.getImage());
        return convertView;
    }
}
