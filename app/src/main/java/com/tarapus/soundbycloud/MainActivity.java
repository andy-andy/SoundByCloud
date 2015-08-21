package com.tarapus.soundbycloud;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = Environment.getExternalStorageDirectory().toString()+"/Movies";
        File file = new File(path);
        File list[] = file.listFiles();
        String fileNames[] = new String[list.length];
        String[] displayNames = new String[list.length];
        final int[] fileType= new int[list.length];

        for(int i = 0; i < list.length; ++i) {
            fileNames[i] = list[i].toString();
            displayNames[i] = fileNames[i].substring(path.length() + 1, fileNames[i].length() - 4);

            if(fileNames[i].endsWith(".mp4"))
                fileType[i] = 0;
            else {
                fileType[i] = 1;
            }
        }

        GridView gridview = (GridView)findViewById(R.id.gridView);

        final CustomGridAdapter gridAdapter = new CustomGridAdapter(MainActivity.this, displayNames);
        gridview.setAdapter(gridAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String path1 = parent.getAdapter().getItem(position).toString();
                playVideo(path1, fileType[position]);

            }

        });
    }

    public void playVideo(String path1, int fileType){
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("path", path1);
        intent.putExtra("type", fileType);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
