package com.demo.jdesousa.sharingexemple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.demo.jdesousa.sharingexemple.Facebook.FacebookActivity;
import com.demo.jdesousa.sharingexemple.Twitter.TwitterActivity;
import com.demo.jdesousa.sharingexemple.google.GoogleActivity;

/**
 * Created by j.desousa on 08/07/2014.
 */
public class MainActivity extends Activity {

    private Button twitterButton;
    private Button facebookButton;
    private Button shareButton;
    private Button mBtnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twitterButton = (Button) findViewById(R.id.main_activity_twitter_button);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TwitterActivity.class);
                startActivity(intent);
            }
        });

        facebookButton = (Button) findViewById(R.id.main_activity_facebook_button);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FacebookActivity.class);
                startActivity(intent);
            }
        });

        shareButton = (Button) findViewById(R.id.main_activity_all_share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cet intent permet de lancer le partage par le biais d'autres applications
                // Twitter, Facebook, mail, un peu près tout
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                // Pour un texte
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "test ?!");

                // Pour une image
//                shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(new File(filePath)));
//                shareIntent.setType("image/png");
                startActivity(Intent.createChooser(shareIntent, "PARTAGER"));
            }
        });

        mBtnGoogle = (Button)findViewById(R.id.main_activity_google_button);
        mBtnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GoogleActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
