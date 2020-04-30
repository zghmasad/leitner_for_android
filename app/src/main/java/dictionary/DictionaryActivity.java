package dictionary;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import databasehelper.TestAdapter;
import yobinet.ir.ielts.R;
import ieltsword.WordActivity2;
import litnerdb.LinterDB;
import litnerdb.LitnerClass;
import other.Utils;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

/**
 * Created by 4li on 1/10/2018.
 */

public class DictionaryActivity extends Activity {
    static TextToSpeech myTTS;

    public static String getTranslation(String en,Context context){
        TestAdapter mDbHelper;
        mDbHelper = new TestAdapter(context);
        mDbHelper.createDatabase();
        String returnValue="";
        final String charSequence=en.toString().toLowerCase();
        if (charSequence.length() >=1) {
            mDbHelper.close();
            mDbHelper.open();

            String tableName = "";
            if ('a' == charSequence.charAt(0) || charSequence.charAt(0) == 'b') {
                    tableName = "e1";
                } else if ('c' == charSequence.charAt(0) || charSequence.charAt(0) == 'd') {
                    tableName = "e2";
                } else if ('e' <= charSequence.charAt(0) && charSequence.charAt(0) < 'h') {
                    tableName = "e3";
                } else if ('h' <= charSequence.charAt(0) && charSequence.charAt(0) < 'm') {
                    tableName = "e4";
                } else if ('m' <= charSequence.charAt(0) && charSequence.charAt(0) < 'q') {
                    tableName = "e5";
                } else if ('q' <= charSequence.charAt(0) && charSequence.charAt(0) < 't') {
                    tableName = "e6";
                } else if ('t' <= charSequence.charAt(0) && charSequence.charAt(0) <= 'z'){
                    tableName = "e7";
                } else if ('آ' == charSequence.charAt(0) || charSequence.charAt(0) == 'ب') {
                    tableName = "f1";
                } else if ('پ' == charSequence.charAt(0) || charSequence.charAt(0) == 'ت'|| charSequence.charAt(0) == 'ث'|| charSequence.charAt(0) == 'ج'|| charSequence.charAt(0) == 'چ'|| charSequence.charAt(0) == 'ح') {
                    tableName = "f2";
                } else if (charSequence.charAt(0) == 'خ'|| charSequence.charAt(0) == 'د'|| charSequence.charAt(0) == 'ذ'|| charSequence.charAt(0) == 'ر'|| charSequence.charAt(0) == 'ز'|| charSequence.charAt(0) == 'ژ') {
                    tableName = "f3";
                } else if ('س' == charSequence.charAt(0) || charSequence.charAt(0) == 'ش'|| charSequence.charAt(0) == 'ص'|| charSequence.charAt(0) == 'ض'|| charSequence.charAt(0) == 'ط'|| charSequence.charAt(0) == 'ظ'|| charSequence.charAt(0) == 'ع'|| charSequence.charAt(0) == 'غ'|| charSequence.charAt(0) == 'ف'|| charSequence.charAt(0) == 'ق') {
                    tableName = "f4";
                } else if ('ک' == charSequence.charAt(0) || charSequence.charAt(0) == 'گ'|| charSequence.charAt(0) == 'ل'|| charSequence.charAt(0) == 'م') {
                    tableName = "f5";
                } else if ('ن' == charSequence.charAt(0) || charSequence.charAt(0) == 'و'|| charSequence.charAt(0) == 'ه'|| charSequence.charAt(0) == 'ی') {
                    tableName = "f6";
                }else return returnValue;
                    String str = "SELECT * FROM " + tableName + " WHERE en=\"" + charSequence +"\"";
                    Cursor testCursor = mDbHelper.getCursor(str);
                    if (testCursor.moveToFirst()) {
                        do {
                            returnValue=testCursor.getString(2).replace("~","");

                        } while (testCursor.moveToNext());

                        mDbHelper.close();
                    }
                }
                return returnValue;

            }

    public static ArrayList<String> getTranslations(String en, Context context,int limit){
        ArrayList<String> list=new ArrayList<String>();
        TestAdapter mDbHelper;
        mDbHelper = new TestAdapter(context);
        mDbHelper.createDatabase();
        final String charSequence=en.toString().toLowerCase();
        if (charSequence.length() >=1) {
            mDbHelper.close();
            mDbHelper.open();

            String tableName = "";
            if ('a' == charSequence.charAt(0) || charSequence.charAt(0) == 'b') {
                tableName = "e1";
            } else if ('c' == charSequence.charAt(0) || charSequence.charAt(0) == 'd') {
                tableName = "e2";
            } else if ('e' <= charSequence.charAt(0) && charSequence.charAt(0) < 'h') {
                tableName = "e3";
            } else if ('h' <= charSequence.charAt(0) && charSequence.charAt(0) < 'm') {
                tableName = "e4";
            } else if ('m' <= charSequence.charAt(0) && charSequence.charAt(0) < 'q') {
                tableName = "e5";
            } else if ('q' <= charSequence.charAt(0) && charSequence.charAt(0) < 't') {
                tableName = "e6";
            } else if ('t' <= charSequence.charAt(0) && charSequence.charAt(0) <= 'z'){
                tableName = "e7";
            } else if ('آ' == charSequence.charAt(0) || charSequence.charAt(0) == 'ب') {
                tableName = "f1";
            } else if ('پ' == charSequence.charAt(0) || charSequence.charAt(0) == 'ت'|| charSequence.charAt(0) == 'ث'|| charSequence.charAt(0) == 'ج'|| charSequence.charAt(0) == 'چ'|| charSequence.charAt(0) == 'ح') {
                tableName = "f2";
            } else if (charSequence.charAt(0) == 'خ'|| charSequence.charAt(0) == 'د'|| charSequence.charAt(0) == 'ذ'|| charSequence.charAt(0) == 'ر'|| charSequence.charAt(0) == 'ز'|| charSequence.charAt(0) == 'ژ') {
                tableName = "f3";
            } else if ('س' == charSequence.charAt(0) || charSequence.charAt(0) == 'ش'|| charSequence.charAt(0) == 'ص'|| charSequence.charAt(0) == 'ض'|| charSequence.charAt(0) == 'ط'|| charSequence.charAt(0) == 'ظ'|| charSequence.charAt(0) == 'ع'|| charSequence.charAt(0) == 'غ'|| charSequence.charAt(0) == 'ف'|| charSequence.charAt(0) == 'ق') {
                tableName = "f4";
            } else if ('ک' == charSequence.charAt(0) || charSequence.charAt(0) == 'گ'|| charSequence.charAt(0) == 'ل'|| charSequence.charAt(0) == 'م') {
                tableName = "f5";
            } else if ('ن' == charSequence.charAt(0) || charSequence.charAt(0) == 'و'|| charSequence.charAt(0) == 'ه'|| charSequence.charAt(0) == 'ی') {
                tableName = "f6";
            }else return null;
            String str = "SELECT * FROM " + tableName + " WHERE en like\"" + charSequence +"%\" LIMIT 0,"+limit;
            Cursor testCursor = mDbHelper.getCursor(str);
            if (testCursor.moveToFirst()) {
                do {
                    list.add(testCursor.getString(1).replace("~",""));

                } while (testCursor.moveToNext());

                mDbHelper.close();
            }
        }
        return list;

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dic_activity);



        //if(myTTS==null)
            myTTS = new TextToSpeech(DictionaryActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if(i != TextToSpeech.ERROR) {
                        myTTS.setLanguage(Locale.US);
                    }
                }
            });


        ImageView btnTTS=findViewById(R.id.buttonTTS);
        btnTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ev=findViewById(R.id.et);
                //speakWords();

                //myTTS.setLanguage(Locale.US);
                myTTS.speak(ev.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        ImageView btnClean=findViewById(R.id.buttonClean);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ev=findViewById(R.id.et);
                ev.setText("");
            }
        });



        EditText ev=findViewById(R.id.et);
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

                ArrayList<String> list = getTranslations(charSequence, DictionaryActivity.this,20);
                final float scale = getResources().getDisplayMetrics().density;
                if(list!=null){

                for (final String s : list
                        ) {

                    final TextView textView1 = new TextView(DictionaryActivity.this);
                    Utils.prepareTextView(textView1, "irsans.ttf", Color.BLACK, -1);
                    textView1.setText(s);
                    textView1.setHeight((int) (40 * scale + 0.5f));
                    textView1.setGravity(Gravity.CENTER);
                    textView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(DictionaryActivity.this,getTranslation(s,DictionaryActivity.this),Toast.LENGTH_LONG).show();
                            final Dialog md=new Dialog(DictionaryActivity.this);
                            md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            md.setContentView(R.layout.dialog);
                            md.setCancelable(true);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                            md.getWindow().setLayout(width, height);

                            final EditText persianEditText=md.findViewById(R.id.persian);
                            final TextView englishTextView=md.findViewById(R.id.english);
                            String tranlation=getTranslation(s,DictionaryActivity.this);
                            persianEditText.setText(Html.fromHtml(tranlation).toString());
                            englishTextView.setText(s);
                            Utils.prepareEdit(persianEditText,"irsans.ttf",getResources().getColor(R.color.blackColor));
                            Utils.prepareTextView(englishTextView,"irsans.ttf",getResources().getColor(R.color.whiteColor),-1);

                            Button okbutton=md.findViewById(R.id.okbutton);
                            okbutton.setText(R.string.addtoleitner);
                            okbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LinterDB db=new LinterDB(DictionaryActivity.this);
                                    Calendar cl= Calendar.getInstance();
                                    int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                                    int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                                    db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                                    db.close();
                                    Toast.makeText(DictionaryActivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
                                    md.dismiss();
                                }
                            });

                            md.show();
                        }
                    });

                    View tempView2 = new View(DictionaryActivity.this);
                    tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
                    tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 12);
                    //params.setMargins(5, 5, 5, 5);

                    textView1.setLayoutParams(params);
                    linear_layout.addView(textView1);
                    linear_layout.addView(tempView2);
                }}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        if (!(clipboard.hasPrimaryClip())) {



        } else if (!(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {


        } else {
            ev=findViewById(R.id.et);
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            if(ev!=null && item!=null && !item.getText().toString().isEmpty())
                ev.setText(item.getText().toString());

        }

    }


    @Override
    protected void onDestroy() {
        if(myTTS != null) {

            myTTS.stop();
            myTTS.shutdown();
            //Log.d(TAG, "TTS Destroyed");
        }
        super.onDestroy();
    }
}
