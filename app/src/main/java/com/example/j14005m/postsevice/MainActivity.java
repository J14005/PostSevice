package com.example.j14005m.postsevice;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements LocationListener {
    Button b1;
    double[] lat = {0.0};
    double[] lng = {0.0};
    LocationManager mLocationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsynHttp post = new AsynHttp("name",lat[0],lng[0]);
                post.execute();
            }
        });
        locationStart();
    }


    @Override
    protected void onResume() {
        if (mLocationManager != null) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }

        super.onPause();
    }


    //GPS�X�^�[�g
    private void locationStart() {
        Log.d("debug", "locationStart()");
        //LocationManager�C���X�^���X����
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        final boolean gpsEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            //GPS�ݒ肷��悤�ɑ���
            Intent settingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingIntent);
            Log.d("debug", "gpsEnable, startActivity");
        } else {
            Log.d("debug", "gpsEnabled");
        }
    }


        @Override
        public void onLocationChanged(Location location) {
            //�ܓx�̕\��
            lat[0] = location.getLatitude();
            lng[0] = location.getLongitude();
            Log.d("Debug", String.valueOf(lat[0]));
            Log.d("Debug", String.valueOf(lng[0]));

        }








    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
