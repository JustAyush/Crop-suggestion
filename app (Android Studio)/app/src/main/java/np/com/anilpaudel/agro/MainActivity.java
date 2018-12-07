package np.com.anilpaudel.agro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        Button myButton= (Button)findViewById(R.id.button4);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SecondActivity.class);
                // sending data to new activity
                i.putExtra("team", "nepal");
                startActivity(i);
            }
        });

       setupToolbar();

        DataModel[] drawerItem = new DataModel[12];

        drawerItem[0] = new DataModel(R.drawable.crops, "Crops");
        drawerItem[1] = new DataModel(R.drawable.agro, "Agro");
        drawerItem[2] = new DataModel(R.drawable.add_product, "Add");
        drawerItem[3] = new DataModel(R.drawable.request_product, "Request");
        drawerItem[4] = new DataModel(R.drawable.my_activity, "Activity");
        drawerItem[5] = new DataModel(R.drawable.readings, "Readings");
        drawerItem[6] = new DataModel(R.drawable.message, "Message");
        drawerItem[7] = new DataModel(R.drawable.notifications, "Notifications");
        drawerItem[8] = new DataModel(R.drawable.market_prices, "Market Prices");
        drawerItem[9] = new DataModel(R.drawable.market_analysis, "Market Analysis");
        drawerItem[10] = new DataModel(R.drawable.about, "About");
        drawerItem[11] = new DataModel(R.drawable.log_out, "Log Out");
//        drawerItem[14] = new DataModel(R.drawable.readings5, "Readings");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                Intent crop = new Intent(getApplicationContext(),crops.class);
                // sending data to new activity
                crop.putExtra("team", "nepal");
                startActivity(crop);
                break;
            case 1:
                Intent agr = new Intent(getApplicationContext(),agro.class);
                // sending data to new activity
                agr.putExtra("team", "nepal");
                startActivity(agr);
                fragment = new FixturesFragment();
                break;
            case 2:
                Intent add_produc = new Intent(getApplicationContext(),add_product.class);
                // sending data to new activity
                add_produc.putExtra("team", "nepal");
                startActivity(add_produc);
                // fragment = new TableFragment();
                break;
            case 3:
                Intent request_produc = new Intent(getApplicationContext(),request_product.class);
                // sending data to new activity
                request_produc.putExtra("team", "nepal");
                startActivity(request_produc);
                // fragment = new TableFragment();
                break;
            case 4:
                Intent my_activit = new Intent(getApplicationContext(),my_activity.class);
                // sending data to new activity
                my_activit.putExtra("team", "nepal");
                startActivity(my_activit);
                // fragment = new TableFragment();
                break;
            case 5:
                Intent reading = new Intent(getApplicationContext(),sign_up.class);
                // sending data to new activity
                reading.putExtra("team", "nepal");
                startActivity(reading);
                //fragment = new TableFragment();
                break;
            case 6:
                Intent messag = new Intent(getApplicationContext(),login.class);
                // sending data to new activity
                messag.putExtra("team", "nepal");
                startActivity(messag);
                // fragment = new TableFragment();
                break;
            case 7:
                Intent notification = new Intent(getApplicationContext(),notifications.class);
                // sending data to new activity
                notification.putExtra("team", "nepal");
                startActivity(notification);
                //fragment = new TableFragment();
                break;
            case 8:
                Intent market_pric = new Intent(getApplicationContext(),market_prices.class);
                // sending data to new activity
                market_pric.putExtra("team", "nepal");
                startActivity(market_pric);
                //fragment = new TableFragment();
                break;
            case 9:
                Intent market_analysi = new Intent(getApplicationContext(),market_analysis.class);
                // sending data to new activity
                market_analysi.putExtra("team", "nepal");
                startActivity(market_analysi);
                //fragment = new TableFragment();
                break;
            case 10:
                Intent abou = new Intent(getApplicationContext(),about.class);
                // sending data to new activity
                abou.putExtra("team", "nepal");
                startActivity(abou);
                //fragment = new TableFragment();
                break;
            case 11:
                Intent logi = new Intent(getApplicationContext(),login.class);
                // sending data to new activity
                logi.putExtra("team", "nepal");

                SharedPreferences pref = getApplicationContext().getSharedPreferences("user_details", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.apply(); // commit changes
                startActivity(logi);
                //fragment = new TableFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }
}