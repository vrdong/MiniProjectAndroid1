package com.example.hp.miniproject1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class dialog_listview extends AppCompatActivity {
    public Intent data = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_listview);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        final University university =
                (University) bundle.getSerializable("university");

        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvsdt = (TextView) findViewById(R.id.tvSDT);
        TextView tvweb = (TextView) findViewById(R.id.tvWeb);
        Button btnsdt = (Button) findViewById(R.id.btnCall);
        Button btnweb = (Button) findViewById(R.id.btnWeb);
        Button btnfind = (Button) findViewById(R.id.btnFindUni);

        tvName.setText(university.getTen());
        tvsdt.setText(university.getPhone());
        tvweb.setText(university.getWebsite());

        btnsdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + university.getPhone()));
                startActivity(intent);
            }
        });

        btnweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = university.getWebsite();
                if (!url.startsWith("https://") && !url.startsWith("http://")){
                    url = "http://" + url;
                }
                Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(openUrlIntent);
            }
        });

        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.putExtra("destination", university.getTen());
                setResult(1,data);
                finish();
            }
        });
    }
}
