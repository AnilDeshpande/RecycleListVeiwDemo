package codetutor.com.recyclelistveiwdemo;

import android.content.Context;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

/**
 * Created by anildeshpande on 8/14/17.
 */

public class ListAdapterPeople extends BaseAdapter {

    private static final String TAG = ListAdapterPeople.class.getSimpleName();

    List<Person> people;
    Context context;
    LayoutInflater layoutInflater;
    Set<View> views;

    public ListAdapterPeople(Context context,List<Person> people){
        this.context=context;
        this.people=people;
        this.views= new android.support.v4.util.ArraySet<View>();
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
        PersonViewHolder personViewHolder;

        if(view==null){
            layoutInflater = LayoutInflater.from(this.context);

            view=layoutInflater.inflate(R.layout.layout_person_row_item,null);
            personViewHolder=new PersonViewHolder();

            personViewHolder.textViewName = (TextView)view.findViewById(R.id.textViewName);
            personViewHolder.textViewLastName=(TextView)view.findViewById(R.id.textViewLastName);
            personViewHolder.textViewGender = (TextView)view.findViewById(R.id.textViewGender);
            personViewHolder.textViewNationality = (TextView)view.findViewById(R.id.textViewNationality);

            view.setTag(personViewHolder);
        }else{
            personViewHolder = (PersonViewHolder)view.getTag();
        }

        final Person person = people.get(i);

        personViewHolder.textViewName.setText(person.getName());
        personViewHolder.textViewLastName.setText(person.getLastName());
        personViewHolder.textViewNationality.setText(person.getNationality());
        personViewHolder.textViewGender.setText((person.getGender()== Person.GENDER.MALE? "Male":"Female"));

        views.add(view);

        Log.i(TAG,"Index: "+i+" : "+view+", Set Size: "+views.size());

        return view;
    }

    private static class PersonViewHolder{
        public TextView textViewName;
        public TextView textViewLastName;
        public TextView textViewGender;
        public TextView textViewNationality;
    }
}
