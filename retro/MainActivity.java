package com.example.retro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView=findViewById(R.id.liste);
        final ArrayList<Model> arrayList=new ArrayList<>();
        textView=findViewById(R.id.isim);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Gelenler> call= apiInterface.getMovieDetails();
        Log.d("gelenler","birşey oldu");
        call.enqueue(new Callback<Gelenler>() {
            @Override
            public void onResponse(Call<Gelenler> call, Response<Gelenler> response) {
                Log.d("gelenler",response.toString()); //code=200 çalıştı
                Log.d("gelenler", String.valueOf(response.body()));
                Log.d("gelenler", String.valueOf(response.body().getTracks().getTrack().get(1).getName())); //null
                Log.d("gelenler", String.valueOf(response.body().getTracks().getTrack().size()));
                int i=0;
                for(i=0;i<response.body().getTracks().getTrack().size();i++){
                    arrayList.add(new Model(response.body().getTracks().getTrack().get(i).getName(),
                            response.body().getTracks().getTrack().get(i).getArtist().getName(),
                            response.body().getTracks().getTrack().get(i).getAttr().getRank()));
                    Log.d("gelenler2",response.body().getTracks().getTrack().get(2).getArtist().getName());
                }
                adaptor adaptor= new adaptor(MainActivity.this,arrayList);
                Log.d("gelenler",arrayList.get(1).getIsim());
                recyclerView.setAdapter(adaptor);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<Gelenler> call, Throwable t) {
                Log.d("gelenler",t.getMessage());
                textView.setText(t.getMessage());
            }
        });
    }
}
