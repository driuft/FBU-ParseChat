package com.example.fbu_parsechat;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Message.class);

        // Use for monitoring Parse network traffic
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // any network interceptors must be added with the Configuration Builder given this syntax
        builder.networkInterceptors().add(httpLoggingInterceptor);

        // set applicationId and server based on the values in the Back4App settings.
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("IT9CFEu9H3rj1FHLMckJVUfExqCKZ3k4CWzpCj6L") // Replaced `myAppId`
                .clientKey("tWEHG16AVN7KpuePR9NCjf7lLTzItE7DUmFeaLvB")  // set explicitly unless clientKey is explicitly configured on Parse server
                .clientBuilder(builder) // this builder comes from the OkHttpClient.
                .server("https://parseapi.back4app.com/").build()); // Replace https://my-parse-app-url.herokuapp.com/parse
    }
}