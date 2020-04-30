package essay;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
import java.util.Locale;
import java.util.Objects;

import databasehelper.TestAdapter;
import databasehelper.TestAdapterEs;
import databasehelper.TestAdapterIE;
import dictionary.DictionaryActivity;
import yobinet.ir.ielts.R;
import litnerdb.LinterDB;
import litnerdb.LitnerClass;
import other.Utils;

public class EssayActivity extends AppCompatActivity {

    static TextToSpeech myTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);

        Bundle b = getIntent().getExtras();
        int id=0;
        if(b != null) id=b.getInt("id");

        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR) {
                    myTTS.setLanguage(Locale.US);
                }
            }
        });
        //Toast.makeText(this,word+word.length(),Toast.LENGTH_LONG).show();

        TestAdapterEs mDbHelper = new TestAdapterEs(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        String str = "SELECT * FROM Essays where essay_ID='"+id+"'";
        Essay essay;
        Cursor tempCursor=mDbHelper.getCursor(str);
        if(tempCursor.moveToFirst())
            do{

                essay=new Essay(tempCursor.getInt(0),tempCursor.getInt(1),tempCursor.getString(2),tempCursor.getString(3),tempCursor.getString(4));


            }while (tempCursor.moveToNext());
        else
            return;
        //final Essay essay=mDbHelper.getTestData(id);
        int i;
        SpannableString questionSS = new SpannableString(essay.essay_question);
        String[] questionSplitWord=essay.essay_question.split(" ");

                i=0;
                for (final String splitWord : questionSplitWord) {
                    //i++;
                    final Essay finalEssay = essay;
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                            TextView tv = (TextView) textView;
                            // TODO add check if tv.getText() instanceof Spanned
                            Spanned s = (Spanned) tv.getText();
                            int start = s.getSpanStart(this);
                            int end = s.getSpanEnd(this);
                            String str= finalEssay.essay_question.substring(start,end);
                            TestAdapter mDbHelper = new TestAdapter(EssayActivity.this);
                            mDbHelper.createDatabase();
                            mDbHelper.open();

                            String testdata = mDbHelper.getTestData(str);

                            mDbHelper.close();
                            final Dialog md=new Dialog(EssayActivity.this);
                            md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            md.setContentView(R.layout.dialog);
                            md.setCancelable(true);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                            Objects.requireNonNull(md.getWindow()).setLayout(width, height);

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
                                    LinterDB db=new LinterDB(EssayActivity.this);
                                    Calendar cl= Calendar.getInstance();
                                    int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                                    int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                                    db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                                    db.close();
                                    Toast.makeText(EssayActivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
                                    md.dismiss();
                                }
                            });

                                Button playSound=md.findViewById(R.id.playbutton);
                                playSound.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String test=englishTextView.getText().toString();
                                        myTTS.speak(test, TextToSpeech.QUEUE_FLUSH, null);
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
                    i=essay.essay_question.indexOf(trimed,i);
                    questionSS.setSpan(clickableSpan, i, i + trimed.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }

                TextView textView = findViewById(R.id.essay_question_textview);
                questionSS.setSpan(new ForegroundColorSpan(Color.BLACK), 0, essay.essay_question.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(questionSS);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setHighlightColor(Color.TRANSPARENT);


                SpannableString essaySS = new SpannableString(essay.essay);
                String[] essaySplits=essay.essay.split(" ");

                i=0;
                for (final String splitWord : essaySplits) {
                    //i++;
                    final Essay finalEssay1 = essay;
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                            TextView tv = (TextView) textView;
                            // TODO add check if tv.getText() instanceof Spanned
                            Spanned s = (Spanned) tv.getText();
                            int start = s.getSpanStart(this);
                            int end = s.getSpanEnd(this);
                            String str= finalEssay1.essay.substring(start,end);
                            //str=Utils.fTrim(Utils.rTrim(splitWord));;


                            TestAdapter mDbHelper = new TestAdapter(EssayActivity.this);
                            mDbHelper.createDatabase();
                            mDbHelper.open();

                            String testdata = mDbHelper.getTestData(str);

                            mDbHelper.close();
                            final Dialog md=new Dialog(EssayActivity.this);
                            md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            md.setContentView(R.layout.dialog);
                            md.setCancelable(true);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                            Objects.requireNonNull(md.getWindow()).setLayout(width, height);

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
                                    LinterDB db=new LinterDB(EssayActivity.this);
                                    Calendar cl= Calendar.getInstance();
                                    int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                                    int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                                    db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                                    db.close();
                                    Toast.makeText(EssayActivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
                                    md.dismiss();
                                }
                            });


                                Button playSound=md.findViewById(R.id.playbutton);
                                playSound.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String test=englishTextView.getText().toString();
                                        myTTS.speak(test, TextToSpeech.QUEUE_FLUSH, null);
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
                    i=essay.essay.indexOf(trimed,i);
                    essaySS.setSpan(clickableSpan, i, i + trimed.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }

                TextView textView3 = findViewById(R.id.essay_textview);
                essaySS.setSpan(new ForegroundColorSpan(Color.BLACK), 0, essay.essay.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView3.setText(essaySS);
                textView3.setMovementMethod(LinkMovementMethod.getInstance());
                textView3.setHighlightColor(Color.TRANSPARENT);

        SpannableString feedbakSS = new SpannableString(essay.feedbak);
        String[] feedbakSplits=essay.feedbak.split(" ");

        i=0;
        for (final String splitWord : feedbakSplits) {
            //i++;
            final Essay finalEssay2 = essay;
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    TextView tv = (TextView) textView;
                    // TODO add check if tv.getText() instanceof Spanned
                    Spanned s = (Spanned) tv.getText();
                    int start = s.getSpanStart(this);
                    int end = s.getSpanEnd(this);
                    String str= finalEssay2.feedbak.substring(start,end);
                    //str=Utils.fTrim(Utils.rTrim(splitWord));;


                    TestAdapter mDbHelper = new TestAdapter(EssayActivity.this);
                    mDbHelper.createDatabase();
                    mDbHelper.open();

                    String testdata = mDbHelper.getTestData(str);

                    mDbHelper.close();
                    final Dialog md=new Dialog(EssayActivity.this);
                    md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    md.setContentView(R.layout.dialog);
                    md.setCancelable(true);

                    int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                    int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                    Objects.requireNonNull(md.getWindow()).setLayout(width, height);

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
                            LinterDB db=new LinterDB(EssayActivity.this);
                            Calendar cl= Calendar.getInstance();
                            int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                            int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                            db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                            db.close();
                            Toast.makeText(EssayActivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
                            md.dismiss();
                        }
                    });
                        Button playSound=md.findViewById(R.id.playbutton);
                        playSound.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String test=englishTextView.getText().toString();
                                myTTS.speak(test, TextToSpeech.QUEUE_FLUSH, null);
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
            i=essay.feedbak.indexOf(trimed,i);
            feedbakSS.setSpan(clickableSpan, i, i + trimed.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        TextView textView4 = findViewById(R.id.essay_feedbak);
        feedbakSS.setSpan(new ForegroundColorSpan(Color.BLACK), 0, essay.feedbak.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView4.setText(feedbakSS);
        textView4.setMovementMethod(LinkMovementMethod.getInstance());
        textView4.setHighlightColor(Color.TRANSPARENT);


    }

}
