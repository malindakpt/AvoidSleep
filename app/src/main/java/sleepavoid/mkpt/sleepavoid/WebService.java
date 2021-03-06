package sleepavoid.mkpt.sleepavoid;


import android.app.DownloadManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WebService {
    private MainActivity mainActivity;
    public WebService(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    public void sendRequest(){

        RequestQueue queue = Volley.newRequestQueue(mainActivity.getApplicationContext());
        String url = "http://photographydiary.lk/qBox/get.php?updated="+QuestioinManager.getLastUpdatedTime();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if(response.length()>3){
                            QuestioinManager.loadQues2(response);
                            Toast.makeText(mainActivity.getApplicationContext(), "Upgrading the DB...",Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainActivity.getApplicationContext(), "Error while upgrading the DB..",Toast.LENGTH_SHORT).show();
            }
        });


// Access the RequestQueue through your singleton class.
       // MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        queue.add(stringRequest);
    }
}
