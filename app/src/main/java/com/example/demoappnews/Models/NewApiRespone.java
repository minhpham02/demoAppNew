package com.example.demoappnews.Models;

import java.io.Serializable;
import java.util.List;

public class NewApiRespone implements Serializable {
    String status;
    String totalResults;
    List<NewHeadlines> articles;

    public NewApiRespone(String status, String totalResults, List<NewHeadlines> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewHeadlines> getArticles() {
        return articles;
    }

    public void setArticles(List<NewHeadlines> articles) {
        this.articles = articles;
    }
}
