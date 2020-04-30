package irregular;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
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
import yobinet.ir.ielts.R;
import ieltsword.IeltsWords;
import ieltsword.WordActivity2;
import litnerdb.LinterDB;
import litnerdb.LitnerClass;
import other.Utils;

public class IrregularVerbsAcrivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.irregular_verbs_acrivity);


        TestAdapterIE mDbHelper = new TestAdapterIE(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        String str = "SELECT * FROM tbl_irregular_verb";
        Cursor testCursor = mDbHelper.getCursor(str);
        //final float scale = getResources().getDisplayMetrics().density;
        LinearLayout rootLL = findViewById(R.id.irregularVerbsLinearLayout);



        if (testCursor.moveToFirst()) {
            do {
                final String simple=testCursor.getString(0);
                final String past=testCursor.getString(1);
                final String past_participle=testCursor.getString(2);

                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,9);
                lp.setMargins(10,10,10,10);

                linearLayout.setLayoutParams(lp);

                LinearLayout.LayoutParams lpSimple=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,3);
                //LinearLayout.LayoutParams lpPast=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,3);
                //LinearLayout.LayoutParams lpPP=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,3);




                SpannableString defSS = new SpannableString(simple);
                String[] defSplits=simple.split(" ");

                int i=0;
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
                            String str=simple.substring(start,end);



                            TestAdapter mDbHelper = new TestAdapter(IrregularVerbsAcrivity.this);
                            mDbHelper.createDatabase();
                            mDbHelper.open();

                            String testdata = mDbHelper.getTestData(str);

                            mDbHelper.close();
                            final Dialog md=new Dialog(IrregularVerbsAcrivity.this);
                            md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            md.setContentView(R.layout.dialog);
                            md.setCancelable(true);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                            md.getWindow().setLayout(width, height);

                            final EditText persianEditText=md.findViewById(R.id.persian);
                            final TextView englishTextView=md.findViewById(R.id.english);
                            persianEditText.setText(Html.fromHtml(testdata.replace("~","")));
                            englishTextView.setText(str);
                            Utils.prepareEdit(persianEditText,"irsans.ttf",-1);
                            Utils.prepareTextView(englishTextView,"irsans.ttf",getResources().getColor(R.color.whiteColor),-1);

                            Button okbutton=md.findViewById(R.id.okbutton);
                            okbutton.setText(R.string.addtoleitner);
                            okbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LinterDB db=new LinterDB(IrregularVerbsAcrivity.this);
                                    Calendar cl= Calendar.getInstance();
                                    int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                                    int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                                    db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                                    db.close();
                                    Toast.makeText(IrregularVerbsAcrivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
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
                    i=simple.indexOf(trimed,i);
                    defSS.setSpan(clickableSpan, i, i + trimed.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }

                //TextView textView3 = findViewById(R.id.exampleTV);








                final TextView tvSimple = new TextView(this);
                tvSimple.setGravity(Gravity.CENTER);
                tvSimple.setLayoutParams(lpSimple);
                tvSimple.setPadding(10,10,10,10);
                defSS.setSpan(new ForegroundColorSpan(Color.WHITE), 0, simple.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvSimple.setText(defSS);
                tvSimple.setMovementMethod(LinkMovementMethod.getInstance());
                tvSimple.setHighlightColor(Color.TRANSPARENT);


                //LinearLayout childLinearLayout = new LinearLayout(this);
                //childLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                //childLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (30 * scale + 0.5f)));
                //childLinearLayout.setGravity(Gravity.CENTER);



                SpannableString defSS2 = new SpannableString(past);
                String[] defSplits2=past.split(" ");

                i=0;
                for (final String splitWord : defSplits2) {
                    //i++;
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                            TextView tv = (TextView) textView;
                            // TODO add check if tv.getText() instanceof Spanned
                            Spanned s = (Spanned) tv.getText();
                            int start = s.getSpanStart(this);
                            int end = s.getSpanEnd(this);
                            String str=past.substring(start,end);



                            TestAdapter mDbHelper = new TestAdapter(IrregularVerbsAcrivity.this);
                            mDbHelper.createDatabase();
                            mDbHelper.open();

                            String testdata = mDbHelper.getTestData(str);

                            mDbHelper.close();
                            final Dialog md=new Dialog(IrregularVerbsAcrivity.this);
                            md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            md.setContentView(R.layout.dialog);
                            md.setCancelable(true);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                            md.getWindow().setLayout(width, height);

                            final EditText persianEditText=md.findViewById(R.id.persian);
                            final TextView englishTextView=md.findViewById(R.id.english);
                            persianEditText.setText(Html.fromHtml(testdata.replace("~","")));
                            englishTextView.setText(str);
                            Utils.prepareEdit(persianEditText,"irsans.ttf",-1);
                            Utils.prepareTextView(englishTextView,"irsans.ttf",getResources().getColor(R.color.whiteColor),-1);

                            Button okbutton=md.findViewById(R.id.okbutton);
                            okbutton.setText(R.string.addtoleitner);
                            okbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LinterDB db=new LinterDB(IrregularVerbsAcrivity.this);
                                    Calendar cl= Calendar.getInstance();
                                    int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                                    int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                                    db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                                    db.close();
                                    Toast.makeText(IrregularVerbsAcrivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
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
                    i=past.indexOf(trimed,i);
                    defSS2.setSpan(clickableSpan, i, i + trimed.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }

                //TextView textView3 = findViewById(R.id.exampleTV);








                final TextView tvPast = new TextView(this);
                tvPast.setGravity(Gravity.CENTER);
                tvPast.setLayoutParams(lpSimple);
                tvPast.setPadding(10,10,10,10);
                defSS2.setSpan(new ForegroundColorSpan(Color.WHITE), 0, past.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvPast.setText(defSS2);
                tvPast.setMovementMethod(LinkMovementMethod.getInstance());
                tvPast.setHighlightColor(Color.TRANSPARENT);


                //LinearLayout childLinearLayout = new LinearLayout(this);
                //childLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                //childLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (30 * scale + 0.5f)));
                //childLinearLayout.setGravity(Gravity.CENTER);






                SpannableString defSS3 = new SpannableString(past_participle);
                String[] defSplits3=past_participle.split(" ");

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
                            String str=past_participle.substring(start,end);



                            TestAdapter mDbHelper = new TestAdapter(IrregularVerbsAcrivity.this);
                            mDbHelper.createDatabase();
                            mDbHelper.open();

                            String testdata = mDbHelper.getTestData(str);

                            mDbHelper.close();
                            final Dialog md=new Dialog(IrregularVerbsAcrivity.this);
                            md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            md.setContentView(R.layout.dialog);
                            md.setCancelable(true);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                            md.getWindow().setLayout(width, height);

                            final EditText persianEditText=md.findViewById(R.id.persian);
                            final TextView englishTextView=md.findViewById(R.id.english);
                            persianEditText.setText(Html.fromHtml(testdata.replace("~","")));
                            englishTextView.setText(str);
                            Utils.prepareEdit(persianEditText,"irsans.ttf",-1);
                            Utils.prepareTextView(englishTextView,"irsans.ttf",getResources().getColor(R.color.whiteColor),-1);

                            Button okbutton=md.findViewById(R.id.okbutton);
                            okbutton.setText(R.string.addtoleitner);
                            okbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LinterDB db=new LinterDB(IrregularVerbsAcrivity.this);
                                    Calendar cl= Calendar.getInstance();
                                    int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                                    int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                                    db.addLitner(new LitnerClass(Minutes,Days,englishTextView.getText().toString(),persianEditText.getText().toString(),0));
                                    db.close();
                                    Toast.makeText(IrregularVerbsAcrivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
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
                    i=past_participle.indexOf(trimed,i);
                    defSS3.setSpan(clickableSpan, i, i + trimed.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }

                //TextView textView3 = findViewById(R.id.exampleTV);








                final TextView tvPP = new TextView(this);
                tvPP.setGravity(Gravity.CENTER);
                tvPP.setLayoutParams(lpSimple);
                tvPP.setPadding(10,10,10,10);
                defSS3.setSpan(new ForegroundColorSpan(Color.WHITE), 0, past_participle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvPP.setText(defSS3);
                tvPP.setMovementMethod(LinkMovementMethod.getInstance());
                tvPP.setHighlightColor(Color.TRANSPARENT);


                //LinearLayout childLinearLayout = new LinearLayout(this);
                //childLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                //childLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (30 * scale + 0.5f)));
                //childLinearLayout.setGravity(Gravity.CENTER);


                linearLayout.addView(tvSimple);
                linearLayout.addView(tvPast);
                linearLayout.addView(tvPP);
                rootLL.addView(linearLayout);
            } while (testCursor.moveToNext());
    }
    }
}
