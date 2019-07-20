package com.apicom.pandasoft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apicom.pandasoft.model.JsonBody;
import com.apicom.pandasoft.model.NewsLike;
import com.apicom.pandasoft.model.NewsModel;
import com.apicom.pandasoft.network.PandasoftService;
import com.apicom.pandasoft.network.RetrofitClientInstance;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleNewsActivity extends AppCompatActivity {

    private NewsModel newsModel;

    private ImageView imgNews;
    private TextView detailNews;
    private Button likeNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);

        init();
    }

    private void init(){
        newsModel = (NewsModel)getIntent().getSerializableExtra("news");
        imgNews = findViewById(R.id.imgNews);
        detailNews = findViewById(R.id.detailNews);
        likeNews = findViewById(R.id.likeNews);

        detailNews.setText(newsModel.getDetail());

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load(newsModel.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imgNews);

        likeNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PandasoftService service = RetrofitClientInstance.getRetrofitInstance().create(PandasoftService.class);
                NewsLike newsLike = new NewsLike(newsModel.getId());
                Call<JsonBody> call = service.likeNews();

                call.enqueue(new Callback<JsonBody>() {
                    @Override
                    public void onResponse(Call<JsonBody> call, Response<JsonBody> response) {
                        if(response.body() != null){
                            if(response.body().getStatus() == 200){
                                toastMessage(response.body().getMessage());
                            }else{
                                toastMessage("Error: " + response.body().getStatus());
                            }
                        }else{
                            toastMessage("Error: body is null");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonBody> call, Throwable t) {
                        toastMessage("error");
                    }
                });
            }
        });
    }

    private void toastMessage(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
