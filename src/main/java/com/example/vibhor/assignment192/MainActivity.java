package com.example.vibhor.assignment192;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

//MainActivity class which is extending AppCompatActivity and implementing HandleJsonListener
public class MainActivity extends AppCompatActivity implements HandleJsonListener {

    //API url
    private String URL = "http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";

    //creating listview object
    ListView mList;

    //creating arrayList to store data in list form
    ArrayList<Data> arrayList;

    //CustomAdapter object to show list in customized form
    CustomAdapter customAdapter;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting reference of listview with its ID
        mList = findViewById(R.id.list);

        //initializing arrayList
        arrayList = new ArrayList<>();

        //creating customAdapter object to show arrayList
        customAdapter = new CustomAdapter(MainActivity.this,arrayList);

        //setting customAdapter to listview
        mList.setAdapter(customAdapter);

        //checking if device is connected to internet or not
        if(isIntenetConnected())
        {
            //creating interface object
            NetworkRequestTask networkRequestTask = new NetworkRequestTask(MainActivity.this,URL,this);

            //executing the interface
            networkRequestTask.execute();
        }
        else
        {
            //displaying toast message
            Toast.makeText(MainActivity.this,"No Intenet connection found",Toast.LENGTH_SHORT).show();
        }
    }

    //checking connection status of the device
    private boolean isIntenetConnected()
    {
        //initialising status of the device whether is connected to internet or not
        boolean isConnected=false;

        //creating connectivityManager object to check connection status
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        //getting network information
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //if connected to internet then changing its value
        if(networkInfo!=null)
        {
            isConnected = true;
        }

        //returning connection status
        return isConnected;
    }

    //method to handle the json parsing
    @Override
    public void updateData(String json)
    {
        try {
            //creating parent Json object
            JSONObject parentObject = new JSONObject(json);

            //getting array of Json objects in name results
            JSONArray array = parentObject.getJSONArray("results");

            //initialising name, votes and id of each data
            String name=null,votes=null,ids=null;

            for(int i=0;i<array.length();i++)
            {
                //creating child Json object to access child array
                JSONObject childObject = array.getJSONObject(i);

                //storing information from their key name
                name = childObject.getString("original_name");
                votes = childObject.getString("vote_count");
                ids = childObject.getString("id");

                //creating data object to store information in one data object
                Data data = new Data();
                 data.setName("  "+name);
                 data.setVotes("  Votes :"+votes);
                 data.setId("  Id :"+ids);

                 //adding data object to arrayList
                arrayList.add(data);
            }

            //refreshing the arrayList
            customAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
