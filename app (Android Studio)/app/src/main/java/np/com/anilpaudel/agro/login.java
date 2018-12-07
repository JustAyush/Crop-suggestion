package np.com.anilpaudel.agro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        SharedPreferences prefer = getApplicationContext().getSharedPreferences("user_details", 0); // 0 - for private mode
        try {
            String login_status = prefer.getString("p_l_login_status", null); // getting String
            if (login_status.equals("1")) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        }
        catch (NullPointerException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        final EditText edit_user=(EditText) findViewById(R.id.edit_user);
        final EditText edit_password=(EditText) findViewById(R.id.edit_password);
        final String server_url=getString(R.string.server_ip)+"login.php" ;
//        final String server_url="http://192.168.0.7/agro/login.php" ;
        Toast.makeText(getApplicationContext(),server_url,Toast.LENGTH_SHORT).show();
    //    final TextView view_login_response=findViewById(R.id.view_login_response);
       // view_login_response.setTextColor(Color.RED);
        final Button button_login= findViewById(R.id.button_login);
        final Button button_sign_up=findViewById(R.id.button_sign_up);
        final RequestQueue requestQueue= Volley.newRequestQueue(login.this);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent inte = new Intent(getApplicationContext(), sign_up.class);
                                                  startActivity(inte);
                                              }
                                          });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_id=edit_user.getText().toString();
                final String password=edit_password.getText().toString();
                final RequestQueue requestQueue= Volley.newRequestQueue(login.this);
                StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  view_login_response.setText("jay hanuman"+response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        try {
                            Toast.makeText(login.this,"hello",Toast.LENGTH_SHORT).show();
                            JSONObject mainObject = new JSONObject(response);
                            Toast.makeText(login.this,"whatsup",Toast.LENGTH_SHORT).show();

                            String  log_error_code = mainObject.getString("error_code");
                            if(log_error_code.equals("0")) {

                                String log_name=mainObject.getString("full_name");

                                Toast.makeText(login.this,"inside if",Toast.LENGTH_SHORT).show();
                                String log_email = mainObject.getString("email");
                                String log_password = mainObject.getString("password");
                                String log_contact_no = mainObject.getString("contact_no");
                                String log_token = mainObject.getString("login_token");
                                String log_occupation = mainObject.getString("occupation");
                                String log_age = mainObject.getString("age");
                                String log_gender = mainObject.getString("gender");
                                String log_address = mainObject.getString("address");
                                String log_address_name = mainObject.getString("address_name");
                                String log_id=mainObject.getString("id");
                                Toast.makeText(getApplicationContext(), "log are" + log_password + log_email + log_contact_no + log_occupation+ log_age + log_gender + log_address, Toast.LENGTH_LONG).show();
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("user_details", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("p_l_email", log_email);
                                editor.putString("p_l_full_name", log_name);
                                editor.putString("p_l_login_token", log_token);
                                editor.putString("p_l_occupation", log_occupation);
                                editor.putString("p_l_contact_no", log_contact_no);
                                editor.putString("p_l_address", log_address);
                                editor.putString("p_l_age", log_age);
                                editor.putString("p_l_gender", log_gender);
                                editor.putString("p_l_address_name", log_address_name);
                                editor.putString("p_l_id", log_id);
                                editor.putString("p_l_password", log_password);
                                editor.putString("p_l_login_status", "1");
                                editor.commit();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Error message",Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        requestQueue.stop();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // view_login_response.setText("Something went wrong...."+error.getMessage());
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
                        params.put("user",user_id);
                        params.put("password",password);
                        //  params.put("json_array",value);
                        return  params;

                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }
}
