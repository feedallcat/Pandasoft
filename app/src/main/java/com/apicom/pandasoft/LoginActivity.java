package com.apicom.pandasoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apicom.pandasoft.model.JsonBody;
import com.apicom.pandasoft.model.UserLogin;
import com.apicom.pandasoft.network.PandasoftService;
import com.apicom.pandasoft.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PandasoftService service = RetrofitClientInstance.getRetrofitInstance().create(PandasoftService.class);
                UserLogin user = new UserLogin(username.getText().toString() ,password.getText().toString());
                Call<JsonBody> call = service.login(user);

                call.enqueue(new Callback<JsonBody>() {
                    @Override
                    public void onResponse(Call<JsonBody> call, Response<JsonBody> response) {
                        if(response.body() != null){
                            if(response.body().getStatus() == 200){
                                toListNews();
                            }else{
                                toastMessage("Error: " + response.body().getStatus());
                            }
                        }else{
                            toastMessage("Error: body is null");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonBody> call, Throwable t) {
                        toastMessage("Error");
                    }
                });

            }
        });
    }

    private void toListNews(){
        Intent intent = new Intent(this,ListNewsActivity.class);
        startActivity(intent);
    }

    private void toastMessage(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
