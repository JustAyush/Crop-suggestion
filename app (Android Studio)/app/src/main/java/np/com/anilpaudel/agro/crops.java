//package np.com.anilpaudel.agro;
/*
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class crops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crops);
    }
}


*/


package np.com.anilpaudel.agro;

import android.content.Intent;
import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.Switch;
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

public class crops extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crops);
        final EditText temp_crops=(EditText) findViewById(R.id.temp_crops);
        final EditText moisture_crops=(EditText) findViewById(R.id.moisture_crops);
        final EditText humidity_crops=(EditText) findViewById(R.id.humidity_crops);
        final String server_node_mcu="http://www.protocal.me/agro/node_mcu.php";
        final String server_flask="http://www.protocal.me/agro/flask.php";
        //final TextView textView=findViewById(R.id.response_view);
        //final TextView textView2=findViewById(R.id.responseView2);
        //textView2.setTextColor(Color.RED);
        Button data_sensor= findViewById(R.id.data_sensor);
        final Button request_crops=findViewById(R.id.request_crops);
        final RequestQueue requestQueue= Volley.newRequestQueue(crops.this);
        data_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_node_mcu, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

              /*  view_response.setTextColor(Color.GREEN);
                view_response.setText("jay hanuman"+response);
                view_response.setMovementMethod(new ScrollingMovementMethod());
*/


                        try {

                            JSONObject result = new JSONObject(response);
                            String temperature = result.getString("temperature");
                            temp_crops.setText(temperature);
                            String humidity = result.getString("humidity");
                            humidity_crops.setText(humidity);
                            String moisture = result.getString("moisture");
                            moisture_crops.setText(moisture);
                            Toast.makeText(getApplicationContext(), temperature + humidity + moisture, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //view_response.setText("Something went wrong...."+error.toString());
                        error.printStackTrace();
                        requestQueue.stop();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                      /*  params.put("apple","ball");
                        params.put("contact_no",moisture);
                        params.put("id_user",humidity);
                        */

                        return params;

                    }
                };
                requestQueue.add(stringRequest);
            }
            });

/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String temp=spinner1.getSelectedItem().toString();
                final String moisture=spinner2.getSelectedItem().toString();
                final String humidity=spinner3.getSelectedItem().toString();
                Toast.makeText(crops.this,temp+moisture+humidity,Toast.LENGTH_SHORT).show();
                final RequestQueue requestQueue= Volley.newRequestQueue(crops.this);
                StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        textView.setTextColor(Color.GREEN);
                        textView.setText("jay hanuman"+response);
                        textView.setMovementMethod(new ScrollingMovementMethod());
/*
                       try {
                     //   Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();

                            JSONArray mainObject = new  JSONArray (response);
                            for(int n=0;n<mainObject.length();n++) {
                                JSONObject result = mainObject.getJSONObject(n);

                                JSONObject inner=result.getJSONObject("magic");
                                String blood = inner.getString("blood_group");
                                String contact = inner.getString("contact_no");
                                Toast.makeText(MainActivity.this, blood + contact, Toast.LENGTH_SHORT).show();



                       //         Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
                       //         JSONObject result=mainObject.getJSONObject(n);
                       //         String blood=result.getString("blood_group");
                        //        String contact=result.getString("contact_no");

                          //      Toast.makeText(MainActivity.this,blood+contact,Toast.LENGTH_SHORT).show();

                            }
                           Toast.makeText(MainActivity.this,"whatsup",Toast.LENGTH_SHORT).show();
                          // String  log_contact_no = mainObject.getString("contact_no");
/*
                            String  log_email = mainObject.getString("email");
                           String  log_password = mainObject.getString("password");
                           String  log_contact_no = mainObject.getString("contact_no");
                           String  log_token = mainObject.getString("token");
                           String  log_blood_group = mainObject.getString("blood_group");
                           String  log_age = mainObject.getString("age");
                           String  log_sex = mainObject.getString("sex");
                           String  log_address = mainObject.getString("address");
                           */
                        // textView.setText("here we go");
  /*
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        textView.setText(error.getMessage());
                        error.printStackTrace();
                        requestQueue.stop();

                    }
                })
                {
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError
                    {
                        Map <String, String> params = new HashMap<String, String>();
                        params.put("temperature",temp);
                        params.put("moisture",moisture);
                        params.put("humidity",humidity);
                        return  params;

                    }
                }
                        ;
                requestQueue.add(stringRequest);
            }
        });
*/
        request_crops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String temp=temp_crops.getText().toString();
                final String moisture=moisture_crops.getText().toString();
                final String humidity=humidity_crops.getText().toString();
                final RequestQueue requestQueue= Volley.newRequestQueue(crops.this);

                StringRequest stringRequest=new StringRequest(Request.Method.POST, server_flask, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responses) {
                    //    textView.setText("jay hanuman"+response);
                        Toast.makeText(getApplicationContext(),responses,Toast.LENGTH_SHORT).show();
                        switch (responses)
                        {
                            // sending data to new activity

                            case "Maize":
                            {

                                Intent inte = new Intent(getApplicationContext(), display_crops.class);
                                inte.putExtra("crops", "Maize");
                                startActivity(inte);
                                break;
                            }
                            case "Wheat":
                            {

                                Intent inte = new Intent(getApplicationContext(), display_crops.class);
                                inte.putExtra("crops", "Wheat");
                                startActivity(inte);
                                break;
                            }
                            case "Millet":
                            {

                                Intent inte = new Intent(getApplicationContext(), display_crops.class);
                                inte.putExtra("crops", "Millet");
                                startActivity(inte);
                                break;
                            }
                            case "Sugarcane":
                            {

                                Intent inte = new Intent(getApplicationContext(), display_crops.class);
                                inte.putExtra("crops", "Sugarcane");
                                startActivity(inte);
                                break;
                            }
                            case "Rice":
                            {

                                Intent inte = new Intent(getApplicationContext(), display_crops.class);
                                inte.putExtra("crops", "Rice");
                                startActivity(inte);
                                break;
                            }
                            case "Tomato":
                            {

                                Intent inte = new Intent(getApplicationContext(), display_crops.class);
                                inte.putExtra("crops", "Tomato");
                                startActivity(inte);
                                break;
                            }
                            case "Potato":
                            {

                                Intent inte = new Intent(getApplicationContext(), display_crops.class);
                                inte.putExtra("crops", "Potato");
                                startActivity(inte);
                                break;
                            }
                             default:
                            {
                                Toast.makeText(getApplicationContext(),"result didnot match",Toast.LENGTH_SHORT).show();
                                break;
                            }

                        }

                        requestQueue.stop();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("Something went wrong...."+error.getMessage());
                        error.printStackTrace();
                        requestQueue.stop();
                     //   temp_view.setText("Temperature");


                    }
                })
                {
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError
                    {
                        Map <String, String> params = new HashMap<String, String>();
                        params.put("temperature",temp);
                        params.put("humidity",moisture);
                        params.put("moisture",humidity);

                      //  params.put("json_array",value);
                        return  params;

                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }
}


