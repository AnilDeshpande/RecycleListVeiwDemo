package codetutor.com.recyclelistveiwdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anildeshpande on 8/14/17.
 */

public class ListAdapterPeople extends BaseAdapter {

    private static final String TAG = ListAdapterPeople.class.getSimpleName();

    List<Person> people;
    Context context;
    LayoutInflater layoutInflater;

    public ListAdapterPeople(Context context,List<Person> people){
        this.context=context;
        this.people=people;
    }


    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Object getItem(int i) {
        return people.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        layoutInflater = LayoutInflater.from(this.context);

        final Person person = people.get(i);

        view=layoutInflater.inflate(R.layout.layout_person_row_item,null);
        ((TextView)view.findViewById(R.id.textViewName)).setText(person.getName());
        ((TextView)view.findViewById(R.id.textViewLastName)).setText(person.getLastName());
        ((TextView)view.findViewById(R.id.textViewGender)).setText((person.getGender()== Person.GENDER.MALE? "Male":"Female"));
        ((TextView)view.findViewById(R.id.textViewNationality)).setText(person.getNationality());

        Log.i(TAG,"Index: "+i+" : "+view);
        return view;
    }
}
