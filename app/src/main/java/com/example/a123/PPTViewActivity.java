package com.example.a123;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a123.pptviewer.R;
import com.itsrts.pptviewer.PPTViewer;

public class PPTViewActivity extends AppCompatActivity {

    PPTViewer pptViewer;
    private TextView TextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pptview);
        pptViewer = (PPTViewer) findViewById(R.id.pptViewer);
        TextView=(TextView)findViewById(R.id.textview);
        String path = Environment.getExternalStorageDirectory().getPath()
                + "/download/game.pptx";
        pptViewer.setNext_img(R.drawable.next).setPrev_img(R.drawable.prev)
                .setSettings_img(R.drawable.settings)
                .setZoomin_img(R.drawable.zoomin)
                .setZoomout_img(R.drawable.zoomout);
        try {
            pptViewer.loadPPT(this, path);

        } catch (Exception e) {
            TextView.setText(e.toString());
        }
    }
}