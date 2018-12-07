package np.com.anilpaudel.agro;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class readings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readings);


        Button button= findViewById(R.id.button_send);
        final TextView view_response=findViewById(R.id.view_response);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(MainActivity.this,temp+moisture+humidity, Toast.LENGTH_SHORT).show();
                final RequestQueue requestQueue= Volley.newRequestQueue(readings.this);
                StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.100.26:80/bodyAndroid", new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        view_response.setTextColor(Color.GREEN);
                        view_response.setText("jay hanuman"+response);
                        view_response.setMovementMethod(new ScrollingMovementMethod());




                       try {

                                JSONObject result = new JSONObject(response);
                                String temperature = result.getString("temperature");
                                String humidity = result.getString("humidity");
                                String moisture = result.getString("moisture");
                                Toast.makeText(getApplicationContext(), temperature+humidity+moisture, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        view_response.setText("Something went wrong...."+error.toString());
                        error.printStackTrace();
                        requestQueue.stop();

                    }
                })
                {
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError
                    {
                        Map<String, String> params = new HashMap<String, String>();
                      /*  params.put("apple","ball");
                        params.put("contact_no",moisture);
                        params.put("id_user",humidity);
                        */

                        return  params;

                    }
                }
                        ;
                requestQueue.add(stringRequest);
            }
        });





    }
}
