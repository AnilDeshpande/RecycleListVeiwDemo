package codetutor.com.recyclelistveiwdemo;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DragSortListView listView;
    AppUtility appUtility;

    ArrayAdapter listAdapterPeople;

    private DragSortListView.DropListener onDrop;

    private DragSortListView.RemoveListener onRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        listView=(DragSortListView)findViewById(R.id.listView);

        appUtility=AppUtility.getAppUtility(getApplicationContext());
        listAdapterPeople=new ArrayAdapterPeople(this,appUtility.getDraggablePeople());

        onDrop = new DragSortListView.DropListener()
        {
            @Override
            public void drop(int from, int to)
            {
                if (from != to)
                {
                    if(((Person)listAdapterPeople.getItem(to)).isDraggable() && ((Person)listAdapterPeople.getItem(from)).isDraggable()){
                        Person item = (Person) listAdapterPeople.getItem(from);
                        Log.i(TAG,from+ ", "+to+" : "+item);
                        listAdapterPeople.remove(item);
                        listAdapterPeople.insert(item, to);
                    }else{
                        Snackbar.make(findViewById(android.R.id.content),"Can't do this operation",Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        };

        listView.setAdapter(listAdapterPeople);

        listView.setDragEnabled(true);
        listView.setDropListener(onDrop);
       // listView.setRemoveListener(onRemove);

        DragSortController controller = new DragSortController(listView);
        controller.setDragHandleId(R.id.dragHandle);
        //controller.setClickRemoveId(R.id.);
        controller.setRemoveEnabled(false);
        controller.setSortEnabled(true);
        controller.setDragInitMode(1);
        //controller.setRemoveMode(removeMode);

        listView.setFloatViewManager(controller);
        listView.setOnTouchListener(controller);
        listView.setDragEnabled(true);


    }
}
