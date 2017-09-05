package codetutor.com.recyclelistveiwdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anildeshpande on 8/19/17.
 */

public class ListAdapterWithRecycleView extends RecyclerView.Adapter<ListAdapterWithRecycleView.PersonViewHolder> {

    private static final String TAG = ListAdapterWithRecycleView.class.getSimpleName();

    private List<Person> personList;
    private Context context;

    public ListAdapterWithRecycleView(Context context,List<Person> people){
        this.personList = people;
        this.context=context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_person_row_item,parent,false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, int position) {
        final Person person=personList.get(position);

        holder.textViewName.setText(person.getName());
        holder.textViewLastName.setText(person.getLastName());
        holder.textViewNationality.setText(person.getNationality());
        holder.textViewGender.setText(person.getGender()== Person.GENDER.MALE?"Male":"Female");
        holder.textViewNationality.setEnabled(true);

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewLastName;
        public TextView textViewGender;
        public TextView textViewNationality;

        public PersonViewHolder(View view){
            super(view);
            textViewName = (TextView)view.findViewById(R.id.textViewName);
            textViewLastName=(TextView)view.findViewById(R.id.textViewLastName);
            textViewGender = (TextView)view.findViewById(R.id.textViewGender);
            textViewNationality = (TextView)view.findViewById(R.id.textViewNationality);
        }
    }
}
