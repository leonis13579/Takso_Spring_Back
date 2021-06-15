package ru.realityfamily.takso.Request;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.realityfamily.takso.Models.Rightech.RightechObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;

public class RightechConnection {
    private static RightechConnection Instance;
    public static RightechConnection getInstance() {
        if (Instance == null) {
            Instance = new RightechConnection();
        }
        return Instance;
    }

    private RightechConnection() {
        httpClient = new OkHttpClient.Builder();

        httpClient.interceptors().clear();
        httpClient.addInterceptor( chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI1ZmQwYTI4OThmMDljMGMwYzBlMjJlMzYiLCJzdWIiOiI1Zjg2YTM4MTQ5M2FmZjA5NzJkZmIwOGUiLCJncnAiOiI1Zjg2YTM4MTQ5M2FmZmMwYWZkZmIwOGQiLCJsaWMiOnRydWUsInVzZyI6ImFwaSIsImZ1bGwiOmZhbHNlLCJyaWdodHMiOjEuNSwiaWF0IjoxNjA3NTA4NjE3LCJleHAiOjE2MTAwNTMyMDB9.Pi5lo1IbLWGdjupAjW9BXk-Z40suNhbqSMBXDjdVjYY")
                    .build();
            return chain.proceed(request);
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.rightech.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        rightechController = retrofit.create(RightechController.class);
    }

    OkHttpClient.Builder httpClient;

    Retrofit retrofit;

    RightechController rightechController;

    public List<RightechObject> getObjects() {
        retrofit2.Response<List<RightechObject>> response = null;
        try {
            response = rightechController.getObjects().execute();
            List<RightechObject> rightechObjects  = response.body();
            return rightechObjects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RightechObject getObject(String objectId) {
        try {
            Response<RightechObject> response = rightechController.getObject(objectId).execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
