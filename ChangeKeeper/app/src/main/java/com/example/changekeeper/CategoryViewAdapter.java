package com.example.changekeeper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.ViewHolder>{
    private static final String TAG = "L";
    private ArrayList<String> infoList;
    private String[] info;
    private Context mContext;



    public CategoryViewAdapter(ArrayList<String> infoList,String[] info, Context context) throws NoSuchFieldException {
        this.infoList = infoList;
        this.mContext = context;
        this.info = info;
        Log.i(TAG,"BOOOOOOI" + this.mContext.getClass().toString());
        mContext.getClass().getField("info");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.i(TAG,"onBindViewHolder: called");

        viewHolder.category.setText(infoList.get(position));

        Log.i(TAG,infoList.get(position));
        switch (infoList.get(position)){
            case "Non-Specified":
                viewHolder.image.setImageResource(R.drawable.ic_other);
                break;

            case "Add a new category...":
                viewHolder.category.setTextColor(Color.parseColor("#2ecc71"));
                viewHolder.image.setImageResource(R.drawable.ic_addnew);
                break;
            default:
                viewHolder.image.setImageResource(R.drawable.ic_usercat);
                break;
        }


        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,infoList.get(position));
                if(!infoList.get(position).equals("Add a new category...")){
                    Intent intent = new Intent(view.getContext(), RegExpenseScreen.class);
                    Log.i(TAG,"BOIBOIB" + view.getContext().getClass().toString());
                    String[] message = info;
                    message[3] = infoList.get(position);
                    intent.putExtra(CategoryScreen.EXTRA_MESSAGE, message);
                    mContext.startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return this.infoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView category;
        ImageView image;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.categoryName);
            image = itemView.findViewById(R.id.imageView3);
            parentLayout = itemView.findViewById(R.id.infoTableItem);
        }
    }
}
