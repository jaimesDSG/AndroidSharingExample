package com.demo.jdesousa.sharingexemple.Facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.demo.jdesousa.sharingexemple.R;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

/**
 * Created by j.desousa on 08/07/2014.
 */
public class FacebookActivity extends Activity {
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        // Code pour récupérer la hashKey => cette clé doit être fourni auprès de Facebook pour identifier le compte et permettre l'utilisation de l'application de dev
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.demo.jdesousa.sharingexemple",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("Facebook => hashkey:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        title = (TextView) findViewById(R.id.facebook_activity_title);

        // start Facebook Login
        Session.openActiveSession(this, true, new Session.StatusCallback() {

            // callback when session changes state
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                // Vérifie que la session est bien ouverte et qu'il n'y a pas eu de bug
                if (session.isOpened()) {
                    Log.e("Facebook", "Session open");
                    title.setText("Session open");
                    // Fait la requête pour récupérer mes informations.
                    Request.newMeRequest(session, new Request.GraphUserCallback() {

                        // callback after Graph API response with user object
                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (user != null) {
                                // Si la personne s'est bien connectée on peut recupérer les informations sur son compte à partir du USER
                                title.setText("Hello " + user.getName());
                            } else {
                                title.setText("Fail to connect");
                            }
                        }
                    }).executeAsync();
                } else {
                    title.setText("Fail to open the session : " + exception);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    // Permet de récupérer le session en cours suite à la tentative de login
    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession()
                .onActivityResult(this, requestCode, resultCode, data);
    }
}