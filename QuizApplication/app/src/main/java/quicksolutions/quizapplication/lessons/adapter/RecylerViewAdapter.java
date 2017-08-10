package quicksolutions.quizapplication.lessons.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import quicksolutions.quizapplication.R;
import quicksolutions.quizapplication.TestSelectionActivity;
import quicksolutions.quizapplication.lessons.activity.LessonsActivity;
import quicksolutions.quizapplication.lessons.activity.LessonsDetailActivity;

/**
 * Created by zeeshan on 8/6/2017.
 */
public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> implements Filterable {

    ArrayList<String> list;
    ArrayList<String> mFilteredList;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;
    Intent intent;
    LessonsActivity parent;

    public RecylerViewAdapter(Context context1,ArrayList<String> list,LessonsActivity parent){

        this.parent  = parent;
        this.list = list;
        this.mFilteredList = list;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout container;
        public TextView textView;

        public ViewHolder(View v){

            super(v);

            container = (LinearLayout)v.findViewById(R.id.container);
            textView = (TextView)v.findViewById(R.id.subject_textview);
        }
    }

    @Override
    public RecylerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.recylerview_items,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){

        holder.textView.setText(mFilteredList.get(position));

        holder.container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(context,LessonsDetailActivity.class);
                intent.putExtra("name",mFilteredList.get(position));
                context.startActivity(intent);

            }
        });
        
    }

    @Override
    public int getItemCount(){

        return mFilteredList.size();
    }



    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = list;
                } else {

                    ArrayList<String> filteredList = new ArrayList<>();

                    for (String s : list) {

                        if (s.toLowerCase().contains(charString) ) {

                            filteredList.add(s);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}