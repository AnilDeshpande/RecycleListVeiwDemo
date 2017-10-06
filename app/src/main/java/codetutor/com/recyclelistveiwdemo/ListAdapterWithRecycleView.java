package codetutor.com.recyclelistveiwdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by anildeshpande on 8/19/17.
 */

public class ListAdapterWithRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = ListAdapterWithRecycleView.class.getSimpleName();

    public interface PersonModifier{
        public void onPersonSelected(int position);
        public void onPersonDeleted(int position);
    }

    private List<Object> catalogue;
    private Context context;

    private PersonModifier personModifier;

    public ListAdapterWithRecycleView(Context context,List<Object> catalogue){
        this.catalogue = catalogue;
        this.context=context;
    }

    public void setPersonModifier(PersonModifier personModifier){
        this.personModifier = personModifier;
    }

    @Override
    public int getItemViewType(int position) {
        if(catalogue.get(position) instanceof Advertisement){
            return R.layout.layout_advertisement_row_item;
        } else if (catalogue.get(position) instanceof Person) {
            return R.layout.layout_person_row_item;
        }else{
            return -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder;
        View view;
        switch (viewType){
            case R.layout.layout_advertisement_row_item:
                view= LayoutInflater.from(context).inflate(R.layout.layout_advertisement_row_item,parent,false);
                holder=new AdvertisementHolder(view);
                break;

            case R.layout.layout_person_row_item:
                view= LayoutInflater.from(context).inflate(R.layout.layout_person_row_item,parent,false);
                holder=new PersonViewHolder(view);
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int position=holder.getAdapterPosition();
                        Toast.makeText(context,"Item at position "+position+" deleted",Toast.LENGTH_SHORT).show();
                        catalogue.remove(position);
                        notifyDataSetChanged();
                        if(personModifier!=null){personModifier.onPersonDeleted(position);}
                        return true;
                    }
                });
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(personModifier!=null){
                            personModifier.onPersonSelected(holder.getAdapterPosition());
                        }
                    }
                });
                break;
            default:
                view= LayoutInflater.from(context).inflate(R.layout.layout_advertisement_row_item,parent,false);
                holder=new AdvertisementHolder(view);
                break;

        }

        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PersonViewHolder){
            final Person person= (Person) catalogue.get(position);
            ((PersonViewHolder)holder).textViewName.setText(person.getName());
            ((PersonViewHolder)holder).textViewLastName.setText(person.getLastName());
            ((PersonViewHolder)holder).textViewNationality.setText(person.getNationality());
            ((PersonViewHolder)holder).textViewGender.setText(person.getGender()== Person.GENDER.MALE?"Male":"Female");
            ((PersonViewHolder)holder).textViewGender.setEnabled(true);
            ((PersonViewHolder)holder).textViewGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Person.GENDER  gender= ((Person)catalogue.get(holder.getAdapterPosition())).getGender();
                    if(gender == Person.GENDER.MALE){
                        ((Person)catalogue.get(holder.getAdapterPosition())).setGender(Person.GENDER.FEMALE);
                    }else if(gender == Person.GENDER.FEMALE){
                        ((Person)catalogue.get(holder.getAdapterPosition())).setGender(Person.GENDER.MALE);
                    }
                    notifyItemChanged(holder.getAdapterPosition());
                }
            });
        }else if(holder instanceof AdvertisementHolder){
            ((AdvertisementHolder)holder).textViewAdvertMessage.setText(((Advertisement)catalogue.get(position)).getAdMessage());
            ((AdvertisementHolder)holder).textViewAdvertMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Toast.makeText(context,"Advertisement not available",Toast.LENGTH_LONG).show();
                }
            });
        }

        Log.i(TAG,"onBindViewHolder invoked: "+position);
    }

    @Override
    public int getItemCount() {
        return catalogue.size();
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

    class AdvertisementHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewAdvertisementBanner;
        public TextView textViewAdvertMessage;

        public AdvertisementHolder(View view){
            super(view);
            imageViewAdvertisementBanner = (ImageView)view.findViewById(R.id.imageViewAdvertisementBanner);
            textViewAdvertMessage = (TextView)view.findViewById(R.id.textViewAdvertMessage);
        }
    }
}
