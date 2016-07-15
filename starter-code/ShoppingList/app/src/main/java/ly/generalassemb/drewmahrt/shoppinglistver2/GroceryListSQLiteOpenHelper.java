package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Qube on 7/13/16.
 */
public class GroceryListSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    public static String DATABASE_NAME = "SHOPPING_DB";
    public static String GROCERY_LIST_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_GROCERY_ID = "_id";
    public static final String COL_GROCERY_ITEM_NAME = "ITEM_NAME";
    public static final String COL_GROCERY_DESCRIPTION = "DESCRIPTION";
    public static final String COL_GROCERY_PRICE = "PRICE";
    public static final String COL_GROCERY_TYPE = "TYPE";

    public static final String[] GROCERY_COLUMNS =
            {COL_GROCERY_ID, COL_GROCERY_ITEM_NAME, COL_GROCERY_DESCRIPTION, COL_GROCERY_PRICE,
                    COL_GROCERY_TYPE};

    private static final String CREATE_GROCERY_LIST_TABLE =
            "CREATE TABLE " + GROCERY_LIST_TABLE_NAME + "(" +
                    COL_GROCERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_GROCERY_ITEM_NAME + " TEXT, " +
                    COL_GROCERY_DESCRIPTION + " TEXT, " +
                    COL_GROCERY_PRICE + " TEXT, " +
                    COL_GROCERY_TYPE + " TEXT )";

    private static GroceryListSQLiteOpenHelper instance;

    public static GroceryListSQLiteOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new GroceryListSQLiteOpenHelper(context);
        }
        return instance;
    }

    public GroceryListSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GROCERY_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + GROCERY_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public Cursor getGroceryList() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GROCERY_LIST_TABLE_NAME, // a. table
                GROCERY_COLUMNS, // b. column
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }
}
