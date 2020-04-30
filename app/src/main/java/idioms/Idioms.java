package idioms;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Locale;

import databasehelper.TestAdapterIE;
import yobinet.ir.ielts.R;
import ieltsword.WordActivity2;
import other.Utils;

public class Idioms extends Activity {
    static TextToSpeech myTTS;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idioms);
        Bundle b = getIntent().getExtras();
        int id=0;
        if(b != null) id=b.getInt("id");
        final float scale = getResources().getDisplayMetrics().density;
        TableLayout rootLL = findViewById(R.id.linear_layout);


        TestAdapterIE mDbHelper = new TestAdapterIE(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        String str = "SELECT * FROM tbl_idiom where category_id=',"+id+",'";
        ArrayList<String> list=new ArrayList<String>();
        Cursor testCursor = mDbHelper.getCursor(str);






        if (testCursor.moveToFirst()) {
            do {
                final String word=testCursor.getString(1);
                list.add(word);
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                //linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (71 * scale + 0.5f)));

                TextView tv1 = new TextView(this);
                tv1.setHeight((int) (40 * scale + 0.5f));
                tv1.setWidth(getResources().getDisplayMetrics().widthPixels);
                tv1.setText(word);
                tv1.setGravity(Gravity.CENTER);
                Utils.prepareTextView(tv1,"irsans.ttf",getResources().getColor(R.color.blackColor),-1);

                View tempView2 = new View(this);
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);

                tempView2.setLayoutParams(lp);
                tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                linearLayout.addView(tv1);
                linearLayout.addView(tempView2);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bdl=new Bundle();
                        bdl.putString("keyword",word);
                        Intent i=new Intent(Idioms.this, IdiomActivity.class);
                        i.putExtras(bdl);
                        startActivity(i);
                    }
                });

                rootLL.addView(linearLayout);
            } while (testCursor.moveToNext());
        }

        EditText ev=findViewById(R.id.searchEditText);
        Utils.prepareEdit(ev,"irsans.ttf", 15);

        ev.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence INPUT, int i, int i1, int i2) {

                final String charSequence = INPUT.toString().toLowerCase();
                TableLayout linear_layout = findViewById(R.id.linear_layout);

                linear_layout.removeAllViews();

                ArrayList<String> list = new ArrayList<>();
                TestAdapterIE mDbHelper = new TestAdapterIE(Idioms.this);
                mDbHelper.createDatabase();
                mDbHelper.open();
                if(charSequence.length()<=0)
                    return;
                    //charSequence= alphabet;
                String str = "SELECT * FROM tbl_idiom where keyword like \""+charSequence+"%\"";
                Cursor testCursor = mDbHelper.getCursor(str);
                if (testCursor.moveToFirst()) {
                    do {
                        final String word=testCursor.getString(1);
                        list.add(word);
                    } while (testCursor.moveToNext());
                }
                final float scale = getResources().getDisplayMetrics().density;
                if(list!=null){
                    for (final String s : list
                            ) {

                        final TextView textView1 = new TextView(Idioms.this);
                        Utils.prepareTextView(textView1, "irsans.ttf", Color.BLACK, -1);
                        textView1.setText(s);
                        textView1.setHeight((int) (40 * scale + 0.5f));
                        textView1.setGravity(Gravity.CENTER);


                        View tempView2 = new View(Idioms.this);
                        tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
                        tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                        TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 12);
                        //params.setMargins(5, 5, 5, 5);

                        textView1.setLayoutParams(params);
                        linear_layout.addView(textView1);
                        linear_layout.addView(tempView2);
                        linear_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bdl=new Bundle();
                                bdl.putString("keyword",s);
                                Intent i=new Intent(Idioms.this,WordActivity2.class);
                                i.putExtras(bdl);
                                startActivity(i);
                            }
                        });
                    }}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
