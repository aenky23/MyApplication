package com.example.akapil.myapplication.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akapil.myapplication.DB.DBHelper;
import com.example.akapil.myapplication.Models.Book;
import com.example.akapil.myapplication.R;

import org.w3c.dom.Text;

/**
 * Created by akapil on 3/14/2017.
 */

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_bookName;
    TextView tv_bookDetails;
    ImageView iv_bookImage;

    Book book;
    Button addToCart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initUI();
        fetchData();
        addToCart.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void fetchData() {

        tv_bookName.setText(getIntent().getStringExtra("BookName"));
        tv_bookDetails.setText(getIntent().getStringExtra("BookDetails"));
        book = DBHelper.getInstance(getApplicationContext()).getBook(getIntent().getStringExtra("BookName"));
        //  Log.v("","asd"+ book.getBookDetails());
        iv_bookImage.setImageResource(R.drawable.download);

    }

    public void initUI() {
        tv_bookName = (TextView) findViewById(R.id.book_name_details);
        tv_bookDetails = (TextView) findViewById(R.id.book_details);
        iv_bookImage = (ImageView) findViewById(R.id.book_image_details);
        addToCart = (Button) findViewById(R.id.addToCart);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addToCart:

                Toast.makeText(getApplicationContext(), "book added to cart!", Toast.LENGTH_SHORT).show();
                DBHelper.getInstance(getApplicationContext()).addBookToCart(book);
                break;
        }
    }
}
