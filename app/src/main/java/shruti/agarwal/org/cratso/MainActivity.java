package shruti.agarwal.org.cratso;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    List<String> name;
    List<String> user_id;
    List<String> photo;
    List<String> rank;
    OkHttpClient client;
    final String url = "https://us-central1-cratso-171712.cloudfunctions.net/cratso_internship/leaderboard?pointer=10&offset=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=(RecyclerView)findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        name= new ArrayList<String>();
        user_id= new ArrayList<String>();
        photo= new ArrayList<String>();
        rank= new ArrayList<String>();
        client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Network Connection Failed", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Toast.makeText(getApplicationContext(),response.body().string(), Toast.LENGTH_LONG).show();
                            String str = response.body().string();
                            JSONObject json = new JSONObject(str);
                            JSONArray array = json.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                String n = jsonObject.getString("name");
                                name.add(n);
                                String id = jsonObject.getString("user_id");
                                user_id.add(id);
                                String pic = jsonObject.getString("profile_pic");
                                photo.add(pic);
                                String r = jsonObject.getString("rank");
                                rank.add(r);
                            }

                            //Toast.makeText(getApplicationContext(), name.get(2), Toast.LENGTH_LONG).show();
                            rv.setAdapter(new MyRecyclerAdapter(getApplicationContext(), name, user_id, photo, rank));

                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "Exception Raised: " + e.toString(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Exception Raised: " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });











    }


}

