package com.example.demoappnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoappnews.Models.NewHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    NewHeadlines newHeadlines;
    TextView tvDetailAuthor, tvDetailContent, tvDetailTime, tvDetailTitle, tvDetail;
    ImageView ivDetailNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        newHeadlines = (NewHeadlines) getIntent().getSerializableExtra("data");

        mapping();

        tvDetailTitle.setText(newHeadlines.getTitle());
        tvDetailAuthor.setText(newHeadlines.getAuthor());
        tvDetailTime.setText(newHeadlines.getPublishedAt());
        tvDetail.setText(newHeadlines.getDescription());
        tvDetailContent.setText(newHeadlines.getContent());
        Picasso.get().load(newHeadlines.getUrlToImage()).into(ivDetailNews);
    }

    private void mapping() {
        tvDetailAuthor = findViewById(R.id.tvDetailAuthor);
        tvDetailContent = findViewById(R.id.tvDetailContent);
        tvDetailTime = findViewById(R.id.tvDetailTime);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetail = findViewById(R.id.tvDetail);
        ivDetailNews = findViewById(R.id.ivDetailNews);
    }
}