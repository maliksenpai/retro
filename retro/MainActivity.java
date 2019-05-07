package com.example.retro;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    public RecyclerView recyclerView;
    public ArrayList<Model> arrayList=new ArrayList<>();
    public int b=0;
    public int i=0;
    public String limit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.isim);
        api();
        final int pastVisiblesItems, visibleItemCount, totalItemCount;
        final RecyclerView recyclerView=findViewById(R.id.liste);
    }
    public void api(){
        final RecyclerView recyclerView=findViewById(R.id.liste);
        i=b;
        b=b+20;
        limit=String.valueOf(b);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        final Call<Gelenler> call= apiInterface.getMovieDetails(limit);
        Log.d("gelenler","birşey oldu");
        call.enqueue(new Callback<Gelenler>() {
            @Override
            public void onResponse(Call<Gelenler> call, Response<Gelenler> response) {
                Log.d("gelenler",response.toString()); //code=200 çalıştı
                Log.d("gelenler", String.valueOf(response.body()));
                Log.d("gelenler", String.valueOf(response.body().getTracks().getTrack().get(1).getName())); //null
                Log.d("gelenler", String.valueOf(response.body().getTracks().getTrack().size()));
                for(;i<response.body().getTracks().getTrack().size();i++){
                    arrayList.add(new Model(response.body().getTracks().getTrack().get(i).getName(),
                            response.body().getTracks().getTrack().get(i).getArtist().getName(),
                            response.body().getTracks().getTrack().get(i).getAttr().getRank()));
                    Log.d("gelenler2",response.body().getTracks().getTrack().get(2).getArtist().getName());
                }
                adaptor adaptor= new adaptor(MainActivity.this,arrayList);
                Log.d("gelenler3", String.valueOf(arrayList.size()));
                recyclerView.setAdapter(adaptor);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<Gelenler> call, Throwable t) {
                Log.d("gelenler",t.getMessage());
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView.canScrollVertically(2) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    api();
                }
            }
        });
    }
}
