package com.example.afanasenko.mypictures;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private InstagramApp mApp;
    private Button btnConnect, btnViewInfo, btnGetAllImages;
    private LinearLayout llAfterLoginView;

    private HashMap<String, String> userInfoHashmap = new HashMap<String, String>();
    private ArrayList<String> userMedia=new ArrayList<String>();
    private ArrayList<String> userLinks=new ArrayList<String>();

    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == InstagramApp.WHAT_FINALIZE) {
                userInfoHashmap = mApp.getUserInfo();
                userMedia = mApp.getUserMedia();
                userLinks = mApp.getUserLinks();
            }
            else if (msg.what == InstagramApp.WHAT_FINALIZE) {
                Toast.makeText(MainActivity.this, "Check your network.",
                Toast.LENGTH_SHORT).show();
            }
        return false;
        }

        });

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidgetReference();

        mApp = new InstagramApp(this, ApplicationData.CLIENT_ID,
        ApplicationData.CLIENT_SECRET, ApplicationData.CALLBACK_URL);
        mApp.setListener(new InstagramApp.OAuthAuthenticationListener() {

        @Override
        public void onSuccess() {
        // tvSummary.setText("Connected as " + mApp.getUserName());
            btnConnect.setText("Disconnect");
            llAfterLoginView.setVisibility(View.VISIBLE);
            userInfoHashmap = mApp.getUserInfo();
            userMedia = mApp.getUserMedia();
            userLinks = mApp.getUserLinks();
        }

        @Override
            public void onFail(String error) {
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
                         }
        );
        setWidgetReference();
//          bindEventHandlers();

        if (mApp.hasAccessToken()) {
            btnConnect.setText("Disconnect");
            llAfterLoginView.setVisibility(View.VISIBLE);
            mApp.fetchUserInfo();
            mApp.fetchUserMedia();
        }

        }


private void setWidgetReference() {
        llAfterLoginView = (LinearLayout) findViewById(R.id.llAfterLoginView);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnViewInfo = (Button) findViewById(R.id.btnViewInfo);
        btnGetAllImages = (Button) findViewById(R.id.btnGetAllImages);
        }

// OAuthAuthenticationListener listener ;

/*@Override
public void onClick(View v) {
        if (v == btnConnect) {
        connectOrDisconnectUser();
        } else if (v == btnViewInfo) {
        displayInfoDialogView();
        } else if (v == btnGetAllImages) {
        //startActivity(new Intent(MainActivity.this, AllMediaFiles.class)
        //.putExtra("userInfo", userInfoHashmap));
        }
        }
*/
private void connectOrDisconnectUser() {
        if (mApp.hasAccessToken()) {
final AlertDialog.Builder builder = new AlertDialog.Builder(
        MainActivity.this);
        builder.setMessage("Disconnect from Instagram?")
        .setCancelable(false)
        .setPositiveButton("Yes",
        new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dialog,
        int id) {
        mApp.resetAccessToken();
// btnConnect.setVisibility(View.VISIBLE);
        llAfterLoginView.setVisibility(View.GONE);
        btnConnect.setText("Connect");
// tvSummary.setText("Not connected");
        }
        })
        .setNegativeButton("No",
        new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dialog,
        int id) {
        dialog.cancel();
        }
        });
final AlertDialog alert = builder.create();
        alert.show();
        } else {
        mApp.authorize();
        }
        }

private void displayInfoDialogView() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
        MainActivity.this);
        alertDialog.setTitle("Profile Info");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.profile_view, null);
        alertDialog.setView(view);

        ImageView ivProfile = (ImageView) view.findViewById(R.id.ivProfileImage);
        TextView tvName = (TextView) view.findViewById(R.id.tvUserName);
        TextView tvNoOfFollwers = (TextView) view.findViewById(R.id.tvNoOfFollowers);
        TextView tvNoOfFollowing = (TextView) view.findViewById(R.id.tvNoOfFollowing);
        TextView tvNoOfMedia = (TextView) view.findViewById(R.id.tvNoOfMedia);

        new ImageLoader(MainActivity.this).DisplayImage(userInfoHashmap.get(InstagramAppTags.TAG_PROFILE_PICTURE.toString()), ivProfile);

        tvName.setText(userInfoHashmap.get(InstagramAppTags.TAG_USERNAME.toString()));
        tvNoOfFollowing.setText(userInfoHashmap.get(InstagramAppTags.TAG_FOLLOWS.toString()));
        tvNoOfFollwers.setText(userInfoHashmap.get(InstagramAppTags.TAG_FOLLOWED_BY.toString()));
        tvNoOfMedia.setText(userInfoHashmap.get(InstagramAppTags.TAG_MEDIA.toString()));

        alertDialog.create().show();
        }

    public void onClick_btnViewInfo(View view) {
        displayInfoDialogView();
    }

    public void onClick_btnConnect(View view) {
        connectOrDisconnectUser();
    }


    public void onClick_btnGetImages(View view) {
        Intent i=new Intent(this,Media.class);
        i.putStringArrayListExtra("USER_MEDIA", userMedia);
        i.putStringArrayListExtra("USER_LINKS", userLinks);
        //i.putExtra("USER_MEDIA",userMedia);
        startActivity(i);
    }
}
