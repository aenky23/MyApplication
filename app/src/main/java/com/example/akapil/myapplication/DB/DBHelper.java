package com.example.akapil.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.akapil.myapplication.Models.Book;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    String DB_PATH = null;

    private static DBHelper mInstance;

    private static String DB_NAME = "commerce.sqlite";
    public static String TABLE_BOOK_LIST = "TableBookList";
    public static String TABLE_CART = "Cart";
    public static String FIELD_NAME = "Name";
    public static String FIELD_DETAILS = "Description";
    public static String FIELD_PRICE = "Price";
    public static String FIELD_ID = "_Id";
    private SQLiteDatabase myDataBase;
    private Context myContext = null;

    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */
    private DBHelper(Context context) {

        super(context, DB_NAME, null, 1);

        this.myContext = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    private void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {

            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;

            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);

        } catch (SQLiteException e) {

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

//	public boolean deleteDB() {
//		try {
//			mInstance = null;
//			return SQLiteDatabase.deleteDatabase(new File(DB_PATH + DB_NAME));
//		} catch (Exception ex) {
//			return false;
//		}
//	}

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        InputStream myInput = myContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        String myPath = DB_PATH + DB_NAME;

        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    /**
     * Get default instance of the class to keep it a singleton
     *
     * @param context the application context
     */
    public static DBHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBHelper(context);
            initDB();
        }
        return mInstance;
    }

    private static void initDB() {

        try {

            mInstance.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");
        }

        try {
            mInstance.openDataBase();

        } catch (SQLException sqle) {

            throw sqle;
        }
    }

/*
    public List<String> getBookNames() {
        List<String> bookNames = new ArrayList<String>();

        Cursor cursor = null;

        try {

            cursor = myDataBase.rawQuery("Select DISTINCT " + FIELD_NAME
                    + " from " + TABLE_BOOK_LIST, null);

            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                do {
                    bookNames.add(cursor.getString(cursor
                            .getColumnIndex(FIELD_NAME)));
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return bookNames;
    }
*/


    public List<String> getBookNamesFromCart() {
        List<String> bookNames = new ArrayList<String>();

        Cursor cursor = null;

        try {

            cursor = myDataBase.rawQuery("Select DISTINCT " + FIELD_NAME
                    + " from " + TABLE_CART, null);

            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                do {
                    bookNames.add(cursor.getString(cursor
                            .getColumnIndex(FIELD_NAME)));
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return bookNames;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        Cursor cursor = null;

        try {
            cursor = myDataBase.rawQuery("Select * from " + TABLE_BOOK_LIST, null);
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                do {
                    Book book = new Book();
                    book.setBookName(cursor.getString(cursor
                            .getColumnIndex(FIELD_NAME)));
                    book.setBookPrice(cursor.getInt(cursor
                            .getColumnIndex(FIELD_PRICE)));
                    book.setBookDetails(cursor.getString(cursor
                            .getColumnIndex(FIELD_DETAILS)));
                    books.add(book);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return books;
    }

    public Book getBook(String bookName) {

        Book myBook = new Book();
        Cursor c = null;

        String query = "Select * from " + TABLE_BOOK_LIST + " where " + FIELD_NAME + "=?";
        c = myDataBase.rawQuery(query, new String[]{bookName});


        //c.moveToFirst();

        if (c.moveToFirst()) {

            myBook.setBookName(c.getString(c.getColumnIndex(FIELD_NAME)));
            myBook.setBookPrice(c.getInt(c.getColumnIndex(FIELD_PRICE)));
            myBook.setBookDetails(c.getString(c.getColumnIndex(FIELD_DETAILS)));
        }
        return myBook;
    }

    public Book getMyBook(String name) {

        Book myBoo = new Book();
        Cursor cursor = null;

        try {
            String query = "Select * from "
                    + TABLE_BOOK_LIST + " where " + FIELD_NAME + "=?";

            cursor = myDataBase.rawQuery(query, new String[]{name});
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                myBoo.setBookName(cursor.getString(cursor.getColumnIndex(FIELD_NAME)));
                myBoo.setBookPrice(cursor.getInt(cursor.getColumnIndex(FIELD_PRICE)));
                myBoo.setBookDetails(cursor.getString(cursor.getColumnIndex(FIELD_DETAILS)));
            }
        } catch (Exception ex) {
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return myBoo;
    }
//
//	public Object getDetailsById(int id, String levelTableName) {
//
//		String dialogue = "Select * from " + levelTableName + " where "
//				+ FIELD_ID + " = ?";
//		// Log.v("", dialogue);
//		Cursor c = myDataBase.rawQuery(dialogue,
//				new String[] { Integer.toString(id) });
//
//		Question que = new Question();
//
//		if (c.getCount() > 0) {
//			c.moveToFirst();
//			que.setId(c.getInt(c.getColumnIndex("_Id")));
//			que.setAnswer(c.getInt(c.getColumnIndex("Solution")));
//			que.setDialogue(c.getString(c.getColumnIndex("Dialogue")));
//			que.setOption1(c.getString(c.getColumnIndex("Option1")));
//			que.setOption2(c.getString(c.getColumnIndex("Option2")));
//			que.setOption3(c.getString(c.getColumnIndex("Option3")));
//			que.setOption4(c.getString(c.getColumnIndex("Option4")));
//			que.setAudio(c.getString(c.getColumnIndex("Audio")));
//
//			int x = c.getInt(c.getColumnIndex("Answered"));
//			if (x == 0) {
//				que.setAnswered(false);
//			} else {
//				que.setAnswered(true);
//			}
//
//			int i = c.getInt(c.getColumnIndex("Option1Answered"));
//			if (i == 0) {
//				que.setOption1Answered(false);
//			} else {
//				que.setOption1Answered(true);
//			}
//
//			int j = c.getInt(c.getColumnIndex("Option2Answered"));
//			if (j == 0) {
//				que.setOption2Answered(false);
//			} else {
//				que.setOption2Answered(true);
//			}
//
//			int k = c.getInt(c.getColumnIndex("Option3Answered"));
//			if (k == 0) {
//				que.setOption3Answered(false);
//			} else {
//				que.setOption3Answered(true);
//			}
//
//			int l = c.getInt(c.getColumnIndex("Option4Answered"));
//			if (l == 0) {
//				que.setOption4Answered(false);
//			} else {
//				que.setOption4Answered(true);
//			}
//
//			que.setAttempts(c.getInt(c.getColumnIndex("Attempts")));
//		}
//
//		return que;
//	}
//
//	public int getDialogId(String dialog, String tableName) {
//
//		int id = 0;
//
//		Cursor c = null;
//
//		String query = "Select " + FIELD_ID + " from " + tableName + " where "
//				+ FIELD_DIALOGUE + "=?";
//
//		c = myDataBase.rawQuery(query, new String[] { dialog });
//
//		if (c.getCount() > 0) {
//			c.moveToFirst();
//			id = c.getInt(c.getColumnIndex(FIELD_ID));
//
//		}
//
//		return id;
//	}
//
//	public Object getDialogues(String tableName) {
//
//		String dialogue = "Select * from " + tableName;
//
//		Cursor c = myDataBase.rawQuery(dialogue, null);
//
//		List<Question> queList = new ArrayList<Question>();
//
//		if (c.getCount() > 0) {
//			c.moveToFirst();
//
//			do {
//
//				Question que = new Question();
//				que.setId(c.getInt(c.getColumnIndex("_Id")));
//				que.setAnswer(c.getInt(c.getColumnIndex("Solution")));
//				que.setDialogue(c.getString(c.getColumnIndex("Dialogue")));
//				que.setOption1(c.getString(c.getColumnIndex("Option1")));
//				que.setOption2(c.getString(c.getColumnIndex("Option2")));
//				que.setOption3(c.getString(c.getColumnIndex("Option3")));
//				que.setOption4(c.getString(c.getColumnIndex("Option4")));
//				que.setTableName(c.getString(c.getColumnIndex("TableName")));
//				que.setAudio(c.getString(c.getColumnIndex("Audio")));
//				int x = c.getInt(c.getColumnIndex("Answered"));
//				if (x == 0) {
//					que.setAnswered(false);
//				} else {
//					que.setAnswered(true);
//				}
//
//				queList.add(que);
//
//			} while (c.moveToNext());
//		}
//
//		Questions ques = new Questions();
//		ques.setQueList(queList);
//		return ques;
//	}
////
//	public void updateAnswered(int id, Book book) {
//
//		ContentValues values = new ContentValues();
//		values.put(FIELD_NAME, book.getBookName());
//        values.put(FIELD_NAME, book.getBookName());
//        values.put(FIELD_NAME, book.getBookName());
//        values.put(FIELD_NAME, book.getBookName());
//
//		myDataBase.update(TABLE_BOOK_LIST, values, FIELD_ID + " =? ",
//				new String[] { (Integer.toString(id)) });
//	}

    public void addBookToCart(Book book) {

        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, book.getBookName()); // Contact Name
        values.put(FIELD_PRICE, book.getBookPrice()); // Contact Phone
        // Inserting Row
        myDataBase.insert(TABLE_CART, null, values);

    }

//
//	public int updateOptionAnswered(int id, String levelTableName, String option) {
//		// Log.v("", "op:" + option);
//		ContentValues values = new ContentValues();
//		values.put(option, 1);
//		// Log.v("", "values:" + values);
//
//		return myDataBase.update(levelTableName, values, FIELD_ID + " =? ",
//				new String[] { (Integer.toString(id)) });
//	}
//
//	public void updateAttempts(int id, String levelTableName, int attempts) {
//		// Log.v("", "op:" + option);
//		ContentValues values = new ContentValues();
//		values.put(FIELD_ATTEMPTS, attempts);
//		// Log.v("", "values:" + values);
//
//		myDataBase.update(levelTableName, values, FIELD_ID + " =? ",
//				new String[] { (Integer.toString(id)) });
//	}
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//	}

}