package program.com.ba;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by faith on 9/13/15.
 */
public class NomineeDBHelper  extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="NOMINEEINFO.DB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY="CREATE TABLE "+ NomineeData.NewNomineeInfo.TABLE_NAME+
            "("+ NomineeData.NewNomineeInfo.NOMINEE_NAME+" TEXT,"+
            NomineeData.NewNomineeInfo.NOMINEE_OCCUPATION+" TEXT,"+
            NomineeData.NewNomineeInfo.NOMINEE_REGNO+" TEXT,"+
            NomineeData.NewNomineeInfo.NOMINEE_WORKPLACE+" TEXT,"+
            NomineeData.NewNomineeInfo.NOMINEE_COUNTY+" TEXT);";



    public NomineeDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("DATABASE OPERATIONS", "DATABASE CREATED/ OPENED ...");



    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.d("DATABASE OPERATIONS", "TABLE CREATED...");

    }
    public void addInformation(String name, String occupation, String regno, String workplace, String county, SQLiteDatabase db){
        ContentValues contentValues=new ContentValues();
        contentValues.put(NomineeData.NewNomineeInfo.NOMINEE_NAME,name);
        contentValues.put(NomineeData.NewNomineeInfo.NOMINEE_OCCUPATION,occupation);
        contentValues.put(NomineeData.NewNomineeInfo.NOMINEE_REGNO,regno);
        contentValues.put(NomineeData.NewNomineeInfo.NOMINEE_WORKPLACE,workplace);
        contentValues.put(NomineeData.NewNomineeInfo.NOMINEE_COUNTY,county);
        db.insert(NomineeData.NewNomineeInfo.TABLE_NAME, null, contentValues);
        Log.d("DATABASE OPERATIONS", "one row inserted...");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//DROPS OLDER TABLE
        db.execSQL("drop table if it exists" + NomineeData.NewNomineeInfo.TABLE_NAME);
        //creates table again
        onCreate(db);
    }
}
