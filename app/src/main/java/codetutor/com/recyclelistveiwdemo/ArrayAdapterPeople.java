package codetutor.com.recyclelistveiwdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

/**
 * Created by anildeshpande on 11/7/17.
 */

public class ArrayAdapterPeople extends ArrayAdapter<Person> {

    private static final String TAG = ListAdapterPeople.class.getSimpleName();

    List<Person> people;
    Context context;
    LayoutInflater layoutInflater;
    Set<View> viewSet;

    public ArrayAdapterPeople(Context context,List<Person> people){
        super(context,R.layout.layout_person_row_item,people);
        this.context=context;
        this.people=people;
        this.viewSet = new android.support.v4.util.ArraySet<View>();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Person getItem(int i) {
        return people.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ArrayAdapterPeople.PersonViewHolder personViewHolder;

        if(view==null){
            layoutInflater = LayoutInflater.from(this.context);

            view=layoutInflater.inflate(R.layout.layout_person_row_item,null);
            personViewHolder=new ArrayAdapterPeople.PersonViewHolder();

            personViewHolder.textViewName = (TextView)view.findViewById(R.id.textViewName);
            personViewHolder.textViewLastName=(TextView)view.findViewById(R.id.textViewLastName);
            personViewHolder.textViewGender = (TextView)view.findViewById(R.id.textViewGender);
            personViewHolder.textViewNationality = (TextView)view.findViewById(R.id.textViewNationality);
            personViewHolder.dragHandle = (TextView)view.findViewById(R.id.dragHandle);

            view.setTag(personViewHolder);
        }else{
            personViewHolder = (ArrayAdapterPeople.PersonViewHolder)view.getTag();
        }

        final Person person = people.get(i);

        if(person.isDraggable()){
            person.setDraggable(true);
            personViewHolder.dragHandle.setVisibility(View.VISIBLE);
        }else {
            person.setDraggable(false);
            personViewHolder.dragHandle.setVisibility(View.GONE);
        }

        personViewHolder.textViewName.setText(person.getName());
        personViewHolder.textViewLastName.setText(person.getLastName());
        personViewHolder.textViewNationality.setText(person.getNationality());
        personViewHolder.textViewGender.setText((person.getGender()== Person.GENDER.MALE? "Male":"Female"));

        viewSet.add(view);

        Log.i(TAG,"Index: "+i+" : "+view+", Set Size: "+ viewSet.size());

        return view;
    }

    private static class PersonViewHolder{
        public TextView textViewName;
        public TextView textViewLastName;
        public TextView textViewGender;
        public TextView textViewNationality;
        public  TextView dragHandle;
    }
}
