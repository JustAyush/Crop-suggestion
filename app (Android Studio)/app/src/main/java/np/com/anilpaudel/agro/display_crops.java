package np.com.anilpaudel.agro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class display_crops extends AppCompatActivity {
    ImageView cropsImage;
    TextView crops_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_crops);
         Intent i = getIntent();
         //getting attached intent data
        String crops = i.getStringExtra("crops");
        cropsImage=(ImageView) findViewById(R.id.crops_image);
        crops_name=(TextView) findViewById(R.id.crops_name);
        switch (crops)
        {
            case "Maize":
            {
                cropsImage.setImageResource(R.drawable.maize);
                crops_name.setText("MAIZE");
                break;
            }
            case "Millet":
            {
                cropsImage.setImageResource(R.drawable.millet);
                crops_name.setText("MILLET");
                break;
            }
            case "Wheat":
            {
                cropsImage.setImageResource(R.drawable.wheat);
                crops_name.setText("WHEAT");
                break;
            }
            case "Sugarcane":
          {
            cropsImage.setImageResource(R.drawable.sugarcane);
            crops_name.setText("SUGARCANE");
              break;
        }
            case "Rice":
        {
            cropsImage.setImageResource(R.drawable.rice);
            crops_name.setText("RICE");
            break;
        }
            case "Tomato":
            {
                cropsImage.setImageResource(R.drawable.tomato);
                crops_name.setText("TOMATO");
                break;
            }
            case "Potato":
            {
                cropsImage.setImageResource(R.drawable.potato);
                crops_name.setText("POTATO");
                break;
            }
            case "Corn":
            {
                cropsImage.setImageResource(R.drawable.maize);
                crops_name.setText("CORN");
                break;
            }
            default :
            {
                cropsImage.setImageResource(R.drawable.crops);
                crops_name.setText("CROPS");
                break;
            }
        }
    }
}