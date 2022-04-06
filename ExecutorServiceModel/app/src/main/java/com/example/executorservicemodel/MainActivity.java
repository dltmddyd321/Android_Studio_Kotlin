package com.example.executorservicemodel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    Handler handler;

    String test = "Init";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(Looper.getMainLooper());

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        clickBtnThread(button);

    }

    private void clickBtnThread(Button button) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        //doInBackground
                        Log.d("doInBackground", "Now!");

//                        //1번 방법
//                        runOnUiThread(new Runnable() {
//                            @SuppressLint("SetTextI18n")
//                            @Override
//                            public void run() {
//                                //postExecute
//                                Log.d("postExecute", "Now!");
//                                isTrue = true;
//
//                                checkBoolean();
//                            }
//                        });

                        //2번 방법
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("postExecute", "Now!");
                                test = "Back";

                                Message message = new Message();
                                Bundle bundle = new Bundle();

                                bundle.putString("TRUE", test);
                                message.setData(bundle);

                                handler.sendMessage(message);

                                checkBoolean();
                            }
                        });
                    }
                });
                Log.d("Normal", "Now!");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkBoolean() {
        textView.setText(test);
    }
}

/*
class UpdateUserNewTask {

    private var interceptor = HttpLoggingInterceptor()

    private val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
    .connectTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .build()

    private val gson =
        GsonBuilder()
            .setLenient()
            .create()

    fun postApi(): UpdateUserApi = Retrofit.Builder()
        .baseUrl(ServerStatus.API_URL_PRFIX)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(UpdateUserApi::class.java)
}

interface UpdateUserApi {
    @Multipart
    @POST("api/users/setName")
    fun updateRepos(
        @Part("name") name: String,
        @Part("lang") lang: String,
        @Part file : MultipartBody.Part
    ) : Call<UpdateUser>
}

data class UpdateUser(
    val err : Int,
    val msg : String,
    val imgO : String,
    val imgT : String
)
 */