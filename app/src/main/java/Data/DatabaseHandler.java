package Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Utils.Utils;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context,) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION,);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
