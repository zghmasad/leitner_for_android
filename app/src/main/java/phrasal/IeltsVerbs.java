package phrasal;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import databasehelper.TestAdapterIE;
import dictionary.DictionaryActivity;
import yobinet.ir.ielts.R;
import litnerdb.LinterDB;
import litnerdb.LitnerClass;
import other.Utils;

public class IeltsVerbs extends Activity {
    static TextToSpeech myTTS;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ielts_words);



        Bundle b = getIntent().getExtras();
        String alphabet="";
        if(b != null) alphabet=b.getString("keyword");
        final float scale = getResources().getDisplayMetrics().density;
        TableLayout rootLL = findViewById(R.id.linear_layout);


        /*WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        LinearLayout.LayoutParams searchbar = new LinearLayout.LayoutParams(width, height/10);
        LinearLayout.LayoutParams scrollview = new LinearLayout.LayoutParams(width, 85*height);
        ScrollView sv=findViewById(R.id.buttomScroll);
        sv.setLayoutParams(scrollview);
        LinearLayout topSearch=findViewById(R.id.topSearch);
        topSearch.setLayoutParams(searchbar);*/



        ImageView btnTTS=findViewById(R.id.buttonTTS);
        btnTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ev=findViewById(R.id.et);
                //speakWords();

                myTTS = new TextToSpeech(IeltsVerbs.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if(i != TextToSpeech.ERROR) {
                            myTTS.setLanguage(Locale.US);
                        }
                    }
                });
                //myTTS.setLanguage(Locale.US);
                myTTS.speak(ev.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        ImageView btnClean=findViewById(R.id.buttonClean);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ev=findViewById(R.id.searchEditText);
                ev.setText("");
            }
        });
        TestAdapterIE mDbHelper = new TestAdapterIE(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        //String str = "SELECT * FROM tbl_5000ielts where keyword like '"+alphabet+"%'";
        ArrayList<String> list=new ArrayList<String>();
        //Cursor testCursor = mDbHelper.getCursor(str);
        Cursor testCursor=new Cursor() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public int getPosition() {
                return 0;
            }

            @Override
            public boolean move(int offset) {
                return false;
            }

            @Override
            public boolean moveToPosition(int position) {
                return false;
            }

            @Override
            public boolean moveToFirst() {
                return false;
            }

            @Override
            public boolean moveToLast() {
                return false;
            }

            @Override
            public boolean moveToNext() {
                return false;
            }

            @Override
            public boolean moveToPrevious() {
                return false;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean isBeforeFirst() {
                return false;
            }

            @Override
            public boolean isAfterLast() {
                return false;
            }

            @Override
            public int getColumnIndex(String columnName) {
                return 0;
            }

            @Override
            public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
                return 0;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return null;
            }

            @Override
            public String[] getColumnNames() {
                return new String[0];
            }

            @Override
            public int getColumnCount() {
                return 0;
            }

            @Override
            public byte[] getBlob(int columnIndex) {
                return new byte[0];
            }

            @Override
            public String getString(int columnIndex) {
                return null;
            }

            @Override
            public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {

            }

            @Override
            public short getShort(int columnIndex) {
                return 0;
            }

            @Override
            public int getInt(int columnIndex) {
                return 0;
            }

            @Override
            public long getLong(int columnIndex) {
                return 0;
            }

            @Override
            public float getFloat(int columnIndex) {
                return 0;
            }

            @Override
            public double getDouble(int columnIndex) {
                return 0;
            }

            @Override
            public int getType(int columnIndex) {
                return 0;
            }

            @Override
            public boolean isNull(int columnIndex) {
                return false;
            }

            @Override
            public void deactivate() {

            }

            @Override
            public boolean requery() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void registerContentObserver(ContentObserver observer) {

            }

            @Override
            public void unregisterContentObserver(ContentObserver observer) {

            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void setNotificationUri(ContentResolver cr, Uri uri) {

            }

            @Override
            public Uri getNotificationUri() {
                return null;
            }

            @Override
            public boolean getWantsAllOnMoveCalls() {
                return false;
            }

            @Override
            public void setExtras(Bundle extras) {

            }

            @Override
            public Bundle getExtras() {
                return null;
            }

            @Override
            public Bundle respond(Bundle extras) {
                return null;
            }
        };




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
                        Intent i=new Intent(IeltsVerbs.this,PhrasalActivity.class);
                        i.putExtras(bdl);
                        startActivity(i);
                    }
                });

                rootLL.addView(linearLayout);
            } while (testCursor.moveToNext());
        }

        EditText ev=findViewById(R.id.searchEditText);
        Utils.prepareEdit(ev,"irsans.ttf", 15);

        final String finalAlphabet = alphabet;
        ev.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence INPUT, int i, int i1, int i2) {

                String charSequence = INPUT.toString().toLowerCase();
                TableLayout linear_layout = findViewById(R.id.linear_layout);

                linear_layout.removeAllViews();

                ArrayList<String> list = new ArrayList<>();
                TestAdapterIE mDbHelper = new TestAdapterIE(IeltsVerbs.this);
                mDbHelper.createDatabase();
                mDbHelper.open();
                if(charSequence.length()<=0)
                    charSequence= finalAlphabet;
                String str = "SELECT * FROM tbl_phrasalverb where keyword like \""+charSequence+"%\"";
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

                        final TextView textView1 = new TextView(IeltsVerbs.this);
                        Utils.prepareTextView(textView1, "irsans.ttf", Color.BLACK, -1);
                        textView1.setText(s);
                        textView1.setHeight((int) (40 * scale + 0.5f));
                        textView1.setGravity(Gravity.CENTER);


                        View tempView2 = new View(IeltsVerbs.this);
                        tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
                        tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                        TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 12);
                        //params.setMargins(5, 5, 5, 5);

                        textView1.setLayoutParams(params);


                        textView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bdl=new Bundle();
                                bdl.putString("keyword",s);
                                Intent i=new Intent(IeltsVerbs.this,PhrasalActivity.class);
                                i.putExtras(bdl);
                                startActivity(i);
                            }
                        });
                        linear_layout.addView(textView1);
                        linear_layout.addView(tempView2);
                        //linear_layout.addView(ll);
                    }}

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //EditText ev=findViewById(R.id.searchEditText);
        ev.setText(alphabet);
    }
}
