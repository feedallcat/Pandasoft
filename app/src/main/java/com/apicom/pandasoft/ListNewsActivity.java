package com.apicom.pandasoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.apicom.pandasoft.adapter.NewsAdapter;
import com.apicom.pandasoft.model.NewsList;
import com.apicom.pandasoft.model.NewsModel;
import com.apicom.pandasoft.network.PandasoftService;
import com.apicom.pandasoft.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        init();
    }

    private void init(){
        PandasoftService service = RetrofitClientInstance.getRetrofitInstance().create(PandasoftService.class);
        Call<NewsList> call = service.getNewsList();

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if(response.body() != null){
                    if(response.body().getStatus() == 200){
                        generateDataList(response.body());
                    }else{
                        toastMessage("Error: " + response.body().getStatus());
                    }
                }else{
                    toastMessage("Error: body is null");
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                toastMessage("Error");
            }
        });
    }

    private void generateDataList(NewsList body){
        recyclerView = findViewById(R.id.reclerView);

        adapter = new NewsAdapter(this, body.getData(), new NewsAdapter.OnNewsClickListener() {
            @Override
            public void onClick(NewsModel item) {
                toSingleNews(item);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListNewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void toSingleNews(NewsModel newsModel){
        Intent intent = new Intent(this,SingleNewsActivity.class);
        intent.putExtra("news",newsModel);
        startActivity(intent);
    }

    private void toastMessage(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
