package essay;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import databasehelper.DataBaseHelperEs;
import databasehelper.TestAdapterEs;
import databasehelper.TestAdapterIE;
import yobinet.ir.ielts.R;

public class EssayAlphabetList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.essayalphabetlist);
        final float scale = getResources().getDisplayMetrics().density;
        LinearLayout rootLL = findViewById(R.id.essayAlphabetLinearLayout);
        TextView tv1;

        View tempView2 = new View(this);
        tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) scale));
        tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        Bundle b = getIntent().getExtras();
        int band=0;
        if(b != null) band=b.getInt("band");

        Essay essay;

        //final ArrayList<Essay> list=temp.getEssayBandList(band);

        /*DataBaseHelperEs mDbHelper = new DataBaseHelperEs(this);
        mDbHelper.createDataBase();
        mDbHelper.open();
        String str = "SELECT * FROM Essays where essay_ID='"+id+"'";
        ArrayList<String> list=new ArrayList<String>();
        Cursor testCursor = mDbHelper.getCursor(str);*/

        TestAdapterEs mDbHelper = new TestAdapterEs(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        String str = "SELECT * FROM Essays where band_ID="+band;
        Cursor testCursor = mDbHelper.getCursor(str);

        int index=1;
        int id=-1;
        if(testCursor.moveToFirst())
            do{

                id=testCursor.getInt(0);

                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                tv1 = new TextView(this);
                tv1.setHeight((int) (40 * scale + 0.5f));
                tv1.setWidth(getResources().getDisplayMetrics().widthPixels);
                tv1.setText("Essay "+" ("+ (++index) +")");
                tv1.setGravity(Gravity.CENTER);



                tempView2 = new View(this);
                tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
                tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                linearLayout.addView(tv1);
                linearLayout.addView(tempView2);
                final int finalIndex=id;
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bdl=new Bundle();
                        bdl.putInt("id",finalIndex);
                        Intent i=new Intent(EssayAlphabetList.this,EssayActivity.class);
                        i.putExtras(bdl);
                        startActivity(i);
                    }
                });

                rootLL.addView(linearLayout);
            }while (testCursor.moveToNext());

    }
}
