package com.tarapus.soundbycloud;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGridAdapter extends BaseAdapter {

    private Context context;
    private String[] filenames;
    LayoutInflater inflater;

    public CustomGridAdapter(Context context, String[] filenames) {
        this.context = context;
        this.filenames = filenames;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return filenames.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return filenames[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.grid_row,null);
        }
        TextView vidName = (TextView)convertView.findViewById(R.id.filepath);
        vidName.setText(filenames[position]);

        ImageView thumb = (ImageView)convertView.findViewById(R.id.thumbnail);

        Bitmap bmThumb;
        String path = Environment.getExternalStorageDirectory().toString()+"/Movies";
        bmThumb = ThumbnailUtils.createVideoThumbnail(path + filenames[position] + ".mp4", 0);
        thumb.setImageBitmap(bmThumb);
        return convertView;

    }

}
