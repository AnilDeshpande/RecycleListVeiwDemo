package codetutor.com.recyclelistveiwdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    AppUtility appUtility;

    ArrayAdapter<String> namesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appUtility=AppUtility.getAppUtility(getApplicationContext());

        listView=(ListView)findViewById(R.id.listView);
        namesAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,appUtility.getNames());
        listView.setAdapter(namesAdapter);


    }
}
