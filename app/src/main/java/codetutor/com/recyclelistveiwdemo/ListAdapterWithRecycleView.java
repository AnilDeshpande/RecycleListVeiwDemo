package codetutor.com.recyclelistveiwdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by anildeshpande on 8/19/17.
 */

public class ListAdapterWithRecycleView extends RecyclerView.Adapter<ListAdapterWithRecycleView.PersonViewHolder> {

    private static final String TAG = ListAdapterWithRecycleView.class.getSimpleName();

    public interface PersonModifier{
        public void onPersonSelected(int position);
        public void onPersonDeleted(int position);
    }

    private List<Person> personList;
    private Context context;

    private PersonModifier personModifier;

    public ListAdapterWithRecycleView(Context context,List<Person> people){
        this.personList = people;
        this.context=context;
    }

    public void setPersonModifier(PersonModifier personModifier){
        this.personModifier = personModifier;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_person_row_item,parent,false);
        final PersonViewHolder personViewHolder=new PersonViewHolder(view);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
            int position=personViewHolder.getAdapterPosition();
            Toast.makeText(context,"Item at position "+position+" deleted",Toast.LENGTH_SHORT).show();
            personList.remove(position);
            notifyDataSetChanged();
            if(personModifier!=null){personModifier.onPersonDeleted(position);}
            return true;
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(personModifier!=null){
                personModifier.onPersonSelected(personViewHolder.getAdapterPosition());
            }
            }
        });
        Log.i(TAG,"onCreateViewHolder invoked");
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, int position) {
        final Person person=personList.get(position);
        holder.textViewName.setText(person.getName());
        holder.textViewLastName.setText(person.getLastName());
        holder.textViewNationality.setText(person.getNationality());
        holder.textViewGender.setText(person.getGender()== Person.GENDER.MALE?"Male":"Female");
        holder.textViewGender.setEnabled(true);
        holder.textViewGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Person.GENDER  gender= personList.get(holder.getAdapterPosition()).getGender();
            if(gender == Person.GENDER.MALE){
                personList.get(holder.getAdapterPosition()).setGender(Person.GENDER.FEMALE);
            }else if(gender == Person.GENDER.FEMALE){
                personList.get(holder.getAdapterPosition()).setGender(Person.GENDER.MALE);
            }
            notifyItemChanged(holder.getAdapterPosition());
            }
        });
        Log.i(TAG,"onBindViewHolder invoked: "+position);
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
