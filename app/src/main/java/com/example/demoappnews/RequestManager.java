package com.example.demoappnews;

import android.content.Context;
import android.widget.Toast;

import com.example.demoappnews.Models.NewApiRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getNewHeadlines(OnFetchDataListener listener, String category, String query){
        CallNewApi callNewApi = retrofit.create(CallNewApi.class);
        Call<NewApiRespone> call = callNewApi.callHeadlines("us",category,query,context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<NewApiRespone>() {
                @Override
                public void onResponse(Call<NewApiRespone> call, Response<NewApiRespone> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error! Please again", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticles(),response.message());
                }

                @Override
                public void onFailure(Call<NewApiRespone> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        }

        catch (Exception e){
            e.printStackTrace();
        }

    }



    public interface CallNewApi{
        @GET("top-headlines")
        Call<NewApiRespone> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key
        );

    }
}
