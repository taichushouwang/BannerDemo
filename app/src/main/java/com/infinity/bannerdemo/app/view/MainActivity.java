/*
 * FileName: MainActivity.java
 *
 * Description:
 *
 * Author: Infinity
 *
 * Email: 309212292@qq.com
 *
 * Ver 1.0, 2018-05-22, create file.
 */

package com.infinity.bannerdemo.app.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.infinity.bannerdemo.app.R;
import com.infinity.bannerdemo.app.utils.Constants;
import com.infinity.bannerdemo.app.utils.ImageUtils.ImageItem;
import com.infinity.bannerdemo.app.presenter.ImageAdapter;

import java.util.ArrayList;

import static com.infinity.bannerdemo.app.utils.Constants.IMAGE_ID;
import static com.infinity.bannerdemo.app.utils.ImageUtils.sArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGridView();
    }

    private void initGridView() {
        mGridView = findViewById(R.id.grid_view);
        initArrayList(sArrayList);
        mAdapter = new ImageAdapter(MainActivity.this);
        mAdapter.setImageList(sArrayList);
        mGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jumpToWallpaperBrowseActivity(mAdapter.getItem(position));
            }
        });
        mGridView.setAdapter(mAdapter);
    }

    private void jumpToWallpaperBrowseActivity(ImageItem imageItem) {
        Intent intent = new Intent(this, ImagePreviewActivity.class);
        intent.putExtra(IMAGE_ID, imageItem.getImageId());
        startActivity(intent);
    }

    private void initArrayList(ArrayList<ImageItem> arrayList) {
        arrayList.clear();
        for (int i = 1; i <= Constants.IMAGE_COUNT; i++) {
            int imageId = getResources().getIdentifier("image_" + i, "drawable", getPackageName());
            arrayList.add(new ImageItem(imageId, Uri.parse("res://" + getPackageName() + "/" + imageId)));
        }
    }
}
