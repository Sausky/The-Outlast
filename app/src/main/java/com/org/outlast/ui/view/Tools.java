package com.org.outlast.ui.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;
import com.org.outlast.component.listener.OnDryerClickedListener;
import com.org.outlast.component.listener.OnExbookClickedListener;
import com.org.outlast.component.listener.OnRingClickedListener;
import com.org.outlast.component.listener.OnSDriverClickedListener;
import com.org.outlast.component.listener.OnShovelClickedListener;

import java.util.ArrayList;
import java.util.HashSet;

public class Tools extends Activity {
    private static HashSet<String> tools=new HashSet<String>();
    private ImageView dryerImage;
    private ImageView ringImage;
    private ImageView shovelImage;
    private ImageView exerciceBookImage;
    private ImageView screwdriverImage;
    private String[] allTools;
    private ImageView[] images;
    private View.OnClickListener[] listeners;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        dryerImage=(ImageView)findViewById(R.id.dryer);
        ringImage=(ImageView)findViewById(R.id.ring);
        shovelImage=(ImageView)findViewById(R.id.shovel);
        exerciceBookImage=(ImageView)findViewById(R.id.exercisebook);
        screwdriverImage=(ImageView)findViewById(R.id.screwdriver);
        allTools=new String[]{this.getString(R.string.dryer),this.getString(R.string.ring),this.getString(R.string.shovel),
                this.getString(R.string.screwdriver),this.getString(R.string.exercise_book)};
        images=new ImageView[]{dryerImage,ringImage,shovelImage,screwdriverImage,exerciceBookImage};
        listeners=new View.OnClickListener[]{new OnDryerClickedListener(this),new OnRingClickedListener(this),new OnShovelClickedListener(this),
                new OnSDriverClickedListener(this),new OnExbookClickedListener(this)};

        for (int i=0;i<allTools.length;i++){
            if (!tools.contains(allTools[i])){
                images[i].setVisibility(ImageView.INVISIBLE);
            }
            else{
                images[i].setOnClickListener(listeners[i]);
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tools, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static void addOneTool(String tool){
          tools.add(tool);
    }
}
