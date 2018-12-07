package np.com.anilpaudel.agro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
public class sign_up extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);//hello man
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
        final String server_url=getString(R.string.server_ip)+"sign_up.php";
        final Button sign_up_button= findViewById(R.id.button_sign_up);
        final EditText edit_name=(EditText)findViewById(R.id.edit_name);
        final EditText edit_email=(EditText)findViewById(R.id.edit_email);
        final EditText edit_phone_no=(EditText)findViewById(R.id.edit_phone);
        final EditText edit_age=(EditText)findViewById(R.id.edit_age);
        final EditText edit_occupation=(EditText)findViewById(R.id.edit_occupation);
        final EditText edit_password=(EditText)findViewById(R.id.edit_password);
        final String[] location_address={""};
        final String[] location_name={""};
        final String[] longitude = {""};
        final String[] latitude = {""};
        final LatLng[] latlng = new LatLng[1];
        final double[] lat = new double[1];
        final double[] lng =new double[1] ;
        final Spinner spinner_gender= findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter1 =ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapter1);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setBoundsBias(new LatLngBounds(new LatLng(25.0,80.0),new LatLng(30.0,89.0)));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                latlng[0] = place.getLatLng();
                lat[0] = latlng[0].latitude;
                lng[0] = latlng[0].longitude;
                location_address[0] = (String) place.getAddress();
                location_name[0] = (String) place.getName();
                latitude[0] = String.valueOf(lat[0]);
                longitude[0] = String.valueOf(lng[0]);
                Toast.makeText(getApplicationContext(), "place is" + place.getName() + latitude[0], Toast.LENGTH_SHORT).show();
                // Log.i(TAG, "Place: " + place.getName());
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //    Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(getApplicationContext(),"place error is:"+status,Toast.LENGTH_SHORT).show();
            }
        });

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=edit_name.getText().toString();
                final String age=edit_age.getText().toString();
                final String occupation=edit_occupation.getText().toString();
                final String gender=spinner_gender.getSelectedItem().toString();
                final String phone_no=edit_phone_no.getText().toString();
                final String email=edit_email.getText().toString();
                final String password=edit_password.getText().toString();

                //final TextView textView=findViewById(R.id.view_result);
            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();

                final RequestQueue requestQueue= Volley.newRequestQueue(sign_up.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //textView.setText(response);
                       // Toast.makeText(getApplicationContext(),server_url+response,Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        try {

                            Toast.makeText(getApplicationContext(),"toast no 1     ",Toast.LENGTH_SHORT).show();
                            JSONObject mainObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),"toast no one     ",Toast.LENGTH_SHORT).show();
                            String  log_error_code = mainObject.getString("error_code");
                            if(log_error_code.equals("0")) {
                                Toast.makeText(getApplicationContext(),"toast no 2     ",Toast.LENGTH_SHORT).show();
                                String log_name=mainObject.getString("full_name");
                                Toast.makeText(getApplicationContext(),"toast no 3     ",Toast.LENGTH_SHORT).show();
                              //  Toast.makeText(sign_up.this,"inside if",Toast.LENGTH_SHORT).show();
                                String log_email = mainObject.getString("email");
                                Toast.makeText(getApplicationContext(),"toast no 4     ",Toast.LENGTH_SHORT).show();
                                String log_password = mainObject.getString("password");
                                Toast.makeText(getApplicationContext(),"toast no 5     ",Toast.LENGTH_SHORT).show();
                                String log_contact_no = mainObject.getString("contact_no");
                                Toast.makeText(getApplicationContext(),"toast no 6     ",Toast.LENGTH_SHORT).show();
                                String log_token = mainObject.getString("login_token");
                                Toast.makeText(getApplicationContext(),"toast no 7     ",Toast.LENGTH_SHORT).show();
                                String log_occupation = mainObject.getString("occupation");
                                Toast.makeText(getApplicationContext(),"toast no 8     ",Toast.LENGTH_SHORT).show();
                                String log_age = mainObject.getString("age");
                                Toast.makeText(getApplicationContext(),"toast no 9     ",Toast.LENGTH_SHORT).show();
                                String log_gender = mainObject.getString("gender");
                                Toast.makeText(getApplicationContext(),"toast no 10     ",Toast.LENGTH_SHORT).show();
                                String log_address = mainObject.getString("address");
                                Toast.makeText(getApplicationContext(),"toast no 1     ",Toast.LENGTH_SHORT).show();
                                String log_address_name = mainObject.getString("address_name");
                                Toast.makeText(getApplicationContext(),"toast no 11     ",Toast.LENGTH_SHORT).show();
                                String log_id=mainObject.getString("id");
                                Toast.makeText(getApplicationContext(),"toast no 12    ",Toast.LENGTH_SHORT).show();
                                String comment=mainObject.getString("comment");
                                Toast.makeText(getApplicationContext(),"toast no 13    ",Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "log are"+comment + log_password + log_email + log_contact_no + log_occupation+ log_age + log_gender + log_address, Toast.LENGTH_LONG).show();
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
                                Toast.makeText(getApplicationContext(),"toast after preference     ",Toast.LENGTH_SHORT).show();
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
              /*  view_response.setTextColor(Color.GREEN);
                view_response.setText("jay hanuman"+response);
                view_response.setMovementMethod(new ScrollingMovementMethod());
*/
/*
                        try {

                            JSONObject result = new JSONObject(response);
                            String temperature = result.getString("temperature");
                            .setText(temperature);
                            String humidity = result.getString("humidity");
                            humidity_crops.setText(humidity);
                            String moisture = result.getString("moisture");
                            moisture_crops.setText(moisture);
                            Toast.makeText(getApplicationContext(), temperature + humidity + moisture, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
*/

                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //view_response.setText("Something went wrong...."+error.toString());
                        error.printStackTrace();
                       // TextView textView=findViewById(R.id.view_result);
                        //textView.setText(error.getMessage());
                        requestQueue.stop();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                       params.put("name",name);
                        params.put("email",email);
                        params.put("age",age);
                        params.put("password",password);
                        params.put("gender",gender);
                        params.put("phone_no",phone_no);
                        params.put("occupation",occupation);
                        params.put("latitude",latitude[0]);
                        params.put("longitude",longitude[0]);
                        params.put("address_name",location_name[0]);
                        params.put("address",location_address[0]);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}
//in the end