package idioms;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import databasehelper.TestAdapter;
import databasehelper.TestAdapterIE;
import dictionary.DictionaryActivity;
import yobinet.ir.ielts.R;
import litnerdb.LinterDB;
import litnerdb.LitnerClass;
import other.Utils;

public class IdiomActivity extends AppCompatActivity {


    public static String english;
    public static String persian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        String word="";
        if(b != null) word=b.getString("keyword");
        TextView wordTextView=findViewById(R.id.EnglishWord);
        Utils.prepareTextView(wordTextView,"irsans.ttf",getResources().getColor(R.color.blackColor),-1);
        wordTextView.setText(word);

        //Toast.makeText(this,word+word.length(),Toast.LENGTH_LONG).show();

        TestAdapterIE mDbHelper = new TestAdapterIE(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        String str = "SELECT * FROM tbl_idiom where keyword=\""+word+"\"";
        Cursor testCursor = mDbHelper.getCursor(str);

        if (testCursor.moveToFirst()) {
            do {
                //final String partsOfSpeech=testCursor.getString(2);
                int i;
                final String definition=testCursor.getString(2);
                final String example=testCursor.getString(3);
                final String translation=testCursor.getString(4);
                String charSequence=word;
                if (charSequence.charAt(charSequence.length() - 1) == ' ') {
                    charSequence = charSequence.substring(0, charSequence.length() - 1);
                }


                SpannableString defSS = new SpannableString(definition);
                String[] defSplits=definition.split(" ");

                i=0;
                for (final String splitWord : defSplits) {
                    //i++;
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                            TextView tv = (TextView) textView;
                            // TODO add check if tv.getText() instanceof Spanned
                            Spanned s = (Spanned) tv.getText();
                            int start = s.getSpanStart(this);
                            int end = s.getSpanEnd(this);
                            String str=definition.substring(start,end);



                            TestAdapter mDbHelper = new TestAdapter(IdiomActivity.this);
                            mDbHelper.createDatabase();
                            mDbHelper.open();

                            String testdata = mDbHelper.getTestData(str);

                            mDbHelper.close();
                            final Dialog md=new Dialog(IdiomActivity.this);
                            md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            md.setContentView(R.layout.dialog);
                            md.setCancelable(true);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                            md.getWindow().setLayout(width, height);

                            final EditText persianEditText=md.findViewById(R.id.persian);
                            final TextView englishTextView=md.findViewById(R.id.english);
                            persianEditText.setText(Html.fromHtml(testdata.replace("~","")).toString());
                            englishTextView.setText(str);
                            Utils.prepareEdit(persianEditText,"irsans.ttf",-1);
                            Utils.prepareTextView(englishTextView,"irsans.ttf",getResources().getColor(R.color.whiteColor),-1);

                            Button okbutton=md.findViewById(R.id.okbutton);
                            okbutton.setText(R.string.addtoleitner);
                            okbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LinterDB db=new LinterDB(IdiomActivity.this);
                                    Calendar cl= Calendar.getInstance();
                                    int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                                    int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                                    db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                                    db.close();
                                    Toast.makeText(IdiomActivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
                                    md.dismiss();
                                }
                            });

                            md.show();
                        }
                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setUnderlineText(false);
                        }
                    };
                    String trimed=Utils.fTrim(Utils.rTrim(splitWord));
                    i=definition.indexOf(trimed,i);
                    defSS.setSpan(clickableSpan, i, i + trimed.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }

                TextView textView = findViewById(R.id.definisionTV);
                defSS.setSpan(new ForegroundColorSpan(Color.BLACK), 0, definition.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(defSS);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setHighlightColor(Color.TRANSPARENT);


                SpannableString defSS3 = new SpannableString(example);
                String[] defSplits3=example.split(" ");

                i=0;
                for (final String splitWord : defSplits3) {
                    //i++;
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                            TextView tv = (TextView) textView;
                            // TODO add check if tv.getText() instanceof Spanned
                            Spanned s = (Spanned) tv.getText();
                            int start = s.getSpanStart(this);
                            int end = s.getSpanEnd(this);
                            String str=example.substring(start,end);
                            //str=Utils.fTrim(Utils.rTrim(splitWord));;


                            TestAdapter mDbHelper = new TestAdapter(IdiomActivity.this);
                            mDbHelper.createDatabase();
                            mDbHelper.open();

                            String testdata = mDbHelper.getTestData(str);

                            mDbHelper.close();
                            final Dialog md=new Dialog(IdiomActivity.this);
                            md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            md.setContentView(R.layout.dialog);
                            md.setCancelable(true);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                            md.getWindow().setLayout(width, height);

                            final EditText persianEditText=md.findViewById(R.id.persian);
                            final TextView englishTextView=md.findViewById(R.id.english);
                            persianEditText.setText(Html.fromHtml(testdata.replace("~","")).toString());
                            str=Utils.fTrim(Utils.rTrim(str));;
                            englishTextView.setText(str);
                            Utils.prepareEdit(persianEditText,"irsans.ttf",-1);
                            Utils.prepareTextView(englishTextView,"irsans.ttf",getResources().getColor(R.color.whiteColor),-1);

                            Button okbutton=md.findViewById(R.id.okbutton);
                            okbutton.setText(R.string.addtoleitner);
                            okbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LinterDB db=new LinterDB(IdiomActivity.this);
                                    Calendar cl= Calendar.getInstance();
                                    int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                                    int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                                    db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                                    db.close();
                                    Toast.makeText(IdiomActivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
                                    md.dismiss();
                                }
                            });

                            md.show();
                        }
                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setUnderlineText(false);
                        }
                    };
                    String trimed=Utils.fTrim(Utils.rTrim(splitWord));
                    i=example.indexOf(trimed,i);
                    defSS3.setSpan(clickableSpan, i, i + trimed.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }

                TextView textView3 = findViewById(R.id.exampleTV);
                defSS3.setSpan(new ForegroundColorSpan(Color.BLACK), 0, example.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView3.setText(defSS3);
                textView3.setMovementMethod(LinkMovementMethod.getInstance());
                textView3.setHighlightColor(Color.TRANSPARENT);


                TextView textView4 = findViewById(R.id.translationTV);
                textView4.setText(translation);
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20,5,20,0);
                textView4.setLayoutParams(lp);
                textView4.setMovementMethod(LinkMovementMethod.getInstance());
                textView4.setHighlightColor(Color.TRANSPARENT);
                Utils.prepareTextView(textView4,"irsans.ttf",getResources().getColor(R.color.blackColor),-1);
                english=charSequence;
                persian=translation;


                //SearchView sv=findViewById(R.id.searchView);
            } while (testCursor.moveToNext());


        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinterDB db=new LinterDB(IdiomActivity.this);
                Calendar cl= Calendar.getInstance();
                int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                db.addLitner(new LitnerClass(Minutes,Days,english,persian,0));
                Toast.makeText(IdiomActivity.this,"به جعبه لایتنر اضافه شد",Toast.LENGTH_LONG).show();
            }
        });
    }

}
