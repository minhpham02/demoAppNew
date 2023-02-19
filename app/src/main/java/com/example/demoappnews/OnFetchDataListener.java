package com.example.demoappnews;

import com.example.demoappnews.Models.NewHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewApiRespone> {
    void onFetchData(List<NewHeadlines> list, String message);
    void onError(String message);

}
