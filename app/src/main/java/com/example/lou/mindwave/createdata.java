package com.example.lou.mindwave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;

public class createdata extends AppCompatActivity {
    EditText showUsername,showDate,showTime,showMaxattendtion,showAverattention,showMaxmeditation,showAvermeditation;
    String  username,date,time,maxattendtion,averattention,maxmeditation,avermeditation;
    RecordList recordList;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createdata);
        initSpring();
    }
    private void initSpring(){
        showUsername=(EditText) findViewById(R.id.showuserName);

        showTime=(EditText)findViewById(R.id.showTime);
        showMaxattendtion=(EditText)findViewById(R.id.showmaxAttention);
        showAverattention=(EditText)findViewById(R.id.showaverAttention);
        showMaxmeditation=(EditText)findViewById(R.id.showmaxMeditation);
        showAvermeditation=(EditText)findViewById(R.id.showaverMeditation);
    }
    public void sumbit(View view){
        recordList=new RecordList();
        username=showUsername.getText().toString();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        date=format.format(new java.util.Date());
        time=showTime.getText().toString();
        maxattendtion=showMaxattendtion.getText().toString();
        averattention=showAverattention.getText().toString();
        maxmeditation=showMaxmeditation.getText().toString();
        avermeditation=showAvermeditation.getText().toString();
        recordList.setUsername(username);
        recordList.setDate(date);
        recordList.setLevel(time);
        recordList.setMaxattention(Integer.valueOf(maxattendtion).intValue());
        recordList.setAverattention(Integer.valueOf(averattention).intValue());
        recordList.setMaxmeditation(Integer.valueOf(maxmeditation).intValue());
        recordList.setAvermeditation(Integer.valueOf(avermeditation).intValue());
        dbManager.add(recordList);
    }
    public void ok(View view){
        Intent intent=new Intent(createdata.this,showRecord.class);
        startActivity(intent);

    }
}
