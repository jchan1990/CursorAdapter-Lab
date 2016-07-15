package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        listView = (ListView) findViewById(R.id.shopping_list_view);

        final Cursor cursor = GroceryListSQLiteOpenHelper.getInstance(this).getGroceryList();

        CursorAdapter adapter = new CursorAdapter(MainActivity.this,
                cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context)
                        .inflate(R.layout.grocery_list_item, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
                TextView priceTextView = (TextView) view.findViewById(R.id.priceTextView);
                TextView typeTextView = (TextView) view.findViewById(R.id.typeTextView);
                TextView descTextView = (TextView) view.findViewById(R.id.descTextView);

//                  Code elegance
//                int colIndex = cursor.getColumnIndex(GroceryListSQLiteOpenHelper.COL_GROCERY_ITEM_NAME);
//                String name = cursor.getString(colIndex);
                nameTextView.setText(cursor.getString(cursor
                        .getColumnIndex(GroceryListSQLiteOpenHelper.COL_GROCERY_ITEM_NAME)));

//                price is a String dataype assigned to a String from the column index
//                COL_GROCERY_PRICE in the GroceryListSQLiteOpenHelper(Singleton case)
//                then we set the price to the priceTextView
                String price = cursor.getString(cursor
                        .getColumnIndex(GroceryListSQLiteOpenHelper.COL_GROCERY_PRICE));
                priceTextView.setText(price);

//                Same thing we did with price
                String type = cursor.getString(cursor
                        .getColumnIndex(GroceryListSQLiteOpenHelper.COL_GROCERY_TYPE));
                typeTextView.setText(type);

//                Same thing we did with price
                String desc = cursor.getString(cursor
                        .getColumnIndex(GroceryListSQLiteOpenHelper.COL_GROCERY_DESCRIPTION));
                descTextView.setText(desc);

            }
        };
        listView.setAdapter(adapter);
    }

}
