package com.example.akapil.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akapil.myapplication.Activities.DetailsActivity;
import com.example.akapil.myapplication.Models.Book;
import com.example.akapil.myapplication.R;

import java.util.ArrayList;

/**
 * Created by akapil on 3/15/2017.
 */

public class CustomAdapter extends BaseAdapter {


    private static LayoutInflater inflater = null;
    Context context;
    ArrayList<Book> rowItems = new ArrayList<Book>();


    public CustomAdapter(Context context, ArrayList<Book> books) {
        this.rowItems = books;
        this.context = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class Holder {
        TextView bookName;
        ImageView bookImage;
        TextView bookPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        final int pos = position;
        View rowView;

        rowView = inflater.inflate(R.layout.book_item_component, null);
        holder.bookImage = (ImageView) rowView.findViewById(R.id.book_image);
        holder.bookName = (TextView) rowView.findViewById(R.id.book_name);
        holder.bookPrice = (TextView) rowView.findViewById(R.id.book_price);

        holder.bookName.setText(rowItems.get(position).getBookName());
        holder.bookPrice.setText(Integer.toString(rowItems.get(position).getBookPrice()));
        holder.bookImage.setImageResource(R.drawable.download);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), DetailsActivity.class);
                i.putExtra("BookName", rowItems.get(pos).getBookName());
                i.putExtra("BookPrice", rowItems.get(pos).getBookPrice());
                i.putExtra("BookDetails", rowItems.get(pos).getBookDetails());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        return rowView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
