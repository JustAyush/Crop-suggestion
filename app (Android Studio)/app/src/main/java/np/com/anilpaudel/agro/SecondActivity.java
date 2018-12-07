package np.com.anilpaudel.agro;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       // TextView txtProduct = (TextView) findViewById(R.id.textview);

       // Intent i = getIntent();
        // getting attached intent data
        //String product = i.getStringExtra("team");
        // displaying selected product name
       // txtProduct.setText(product);
       // String[] teams = getResources().getStringArray(R.array.teams);
        // Binding resources Array to ListAdapter
    //    ArrayAdapter adapter1= new ArrayAdapter<String>(this, R.layout.list_item, R.id.textview, teams);
        //     setListAdapter(adapter1);

     /*   lv = (ListView) findViewById(R.id.list);

        lv.setAdapter(adapter1);
        // listening to single list item on click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String team = ((TextView) view).getText().toString();

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                // sending data to new activity
                i.putExtra("team", team);
                startActivity(i);

            }
        });
*/


    }
}
