package np.com.anilpaudel.agro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.HashMap;
import java.util.Map;

    public class request_product extends AppCompatActivity {
        EditText edit_name;
        EditText edit_quantity;
        EditText edit_date;
        EditText edit_price;
        EditText edit_description;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_request_product);
            final LatLng[] latlng = new LatLng[1];
            final double[] lat = new double[1];
            final double[] lng =new double[1] ;
            final EditText edit_name=(EditText)findViewById(R.id.edit_name);
            final EditText edit_quantity=(EditText)findViewById(R.id.edit_quantity);
            final EditText edit_date=(EditText)findViewById(R.id.edit_date);
            final EditText edit_price=(EditText)findViewById(R.id.edit_price);
            final EditText edit_description=(EditText)findViewById(R.id.edit_description);
            final String[] location_address={""};
            final String[] location_name={""};
            final String[] longitude = {""};
            final String[] latitude = {""};
            final String server_url=getString(R.string.server_ip)+"request_product.php" ;
            final Spinner spinner_category= findViewById(R.id.spinner_category);
            ArrayAdapter<CharSequence> adapter1 =ArrayAdapter.createFromResource(this,R.array.category_product,android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_category.setAdapter(adapter1);
            final Spinner spinner_unit= findViewById(R.id.spinner_unit);
            ArrayAdapter <CharSequence> adapter2 =ArrayAdapter.createFromResource(this,R.array.quantity,android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_unit.setAdapter(adapter2);
            final Spinner spinner_unit_price= findViewById(R.id.spinner_unit_price);
            ArrayAdapter <CharSequence> adapter3 =ArrayAdapter.createFromResource(this,R.array.unit,android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_unit_price.setAdapter(adapter3);
            //final TextView view_result=findViewById(R.id.view_result);spinner_unit
            Button add_button= findViewById(R.id.button_add);
            PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                    getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
            autocompleteFragment.setBoundsBias(new LatLngBounds(new LatLng(25.0,80.0),new LatLng(30.0,89.0)));
            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    // TODO: Get info about the selected place.
                    latlng[0] = place.getLatLng();
                    lat[0] =latlng[0].latitude;
                    lng[0]=latlng[0].longitude;
                    location_address[0]= (String) place.getAddress();
                    location_name[0]= (String) place.getName();
                    latitude[0] = String.valueOf(lat[0]);
                    longitude[0] =String.valueOf(lng[0]);
                    Toast.makeText(getApplicationContext(),"place is"+place.getName()+latitude[0],Toast.LENGTH_SHORT).show();
                    // Log.i(TAG, "Place: " + place.getName());
                }

                @Override
                public void onError(Status status) {
                    // TODO: Handle the error.
                    //    Log.i(TAG, "An error occurred: " + status);
                    Toast.makeText(getApplicationContext(),"place error is:"+status,Toast.LENGTH_SHORT).show();
                }
            });
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String category=spinner_category.getSelectedItem().toString();
                    final String unit=spinner_unit.getSelectedItem().toString();
                    final String unit_price=spinner_unit_price.getSelectedItem().toString();
                    final String item_name=edit_name.getText().toString();
                    final String item_quantity=edit_quantity.getText().toString();
                    final String date=edit_date.getText().toString();

                    final String description=edit_description.getText().toString();
                    final String price=edit_price.getText().toString();

                    final SharedPreferences pref = getApplicationContext().getSharedPreferences("user_details", 0); // 0 - for private mode
                    Toast.makeText(getApplicationContext(),category+unit+unit_price,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"latitude is:"+latitude[0]+"longitude is:"+longitude[0]+unit_price+
                                    location_name[0]+location_address[0],
                            Toast.LENGTH_SHORT).show();
                    final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),"here we go response is"+response,Toast.LENGTH_SHORT).show();

                            //view_result.setTextColor(Color.GREEN);
                            //view_result.setText("Jay Hanuman"+response);
                            //view_result.setMovementMethod(new ScrollingMovementMethod());
                            requestQueue.stop();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//<<<<<<< HEAD
                            //view_result.setText("Something went wrong....");
//=======
                            // view_result.setText("Something went wrong...."+error.getMessage());
//>>>>>>> 00e51ce7c38b17a7f7082fb4918eb1233d3e4f0e
                            error.printStackTrace();
                            requestQueue.stop();

                        }
                    })
                    {
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError
                        {
                            Map <String, String> params = new HashMap<String, String>();
                            params.put("item_categories",category);
                            params.put("quantity_unit",unit);
                            params.put("req_price",price);
                            params.put("req_price_unit",unit_price);
                            params.put("quantity",item_quantity);
                            params.put("item_name",item_name);
                            params.put("req_end_date",date);
                            params.put("req_address",location_address[0]);
                            params.put("req_address_name",location_name[0]);
                            params.put("req_address_latitude", latitude[0]);
                            params.put("req_address_longitude",longitude[0]);
                            params.put("req_description",description);
                            params.put("user_id",pref.getString("p_l_id", null)); // getting String);
                            params.put("req_user_name",pref.getString("p_l_full_name", null));
                            params.put("req_occupation",pref.getString("p_l_occupation", null));
                            params.put("req_email",pref.getString("p_l_email", null));
                            params.put("req_contact_no",pref.getString("p_l_contact_no", null));
                            params.put("login_token",pref.getString("p_l_login_token", null));
                            return  params;
                        }
                    }
                            ;
                    requestQueue.add(stringRequest);
                }
            });
        }
    }