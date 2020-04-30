package leitner;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import yobinet.ir.ielts.R;
import litnerdb.LinterDB;
import litnerdb.LitnerClass;
import other.Utils;

/**
 * Created by 4li on 3/19/2018.
 */

public class Box1back extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box6);
        final TextToSpeech myTTS=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR) {
                    //myTTS.setLanguage(Locale.US);
                }
            }
        });

        final LinterDB db=new LinterDB(this);


        LinearLayout rootLL=(LinearLayout)findViewById(R.id.rootLL);
        final float scale = getResources().getDisplayMetrics().density;


        List<LitnerClass> tempList6=db.getBoxLitners(0,false);
        Calendar cl= Calendar.getInstance();
        int daysNow=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
        int minsNow=cl.get(Calendar.HOUR_OF_DAY)*60+cl.get(Calendar.MINUTE);
        for(final LitnerClass lc:tempList6){
            if(!((lc.Days+1<daysNow)||(lc.Minutes<minsNow && lc.Days+1==daysNow))){



            LinearLayout linearLayout=new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int) (71 * scale + 0.5f)));

            TextView tv1=new TextView(this);

            tv1.setHeight((int) (40 * scale + 0.5f));
            tv1.setWidth(getResources().getDisplayMetrics().widthPixels);
            tv1.setText(lc.English);
            tv1.setGravity(Gravity.CENTER);

            LinearLayout childLinearLayout=new LinearLayout(this);
            childLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            childLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int) (30 * scale + 0.5f)));
            childLinearLayout.setGravity(Gravity.CENTER);

            ImageView soundImage=new ImageView(this);
            soundImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            soundImage.setImageResource(R.drawable.ic_play);
            soundImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTTS.speak(lc.English, TextToSpeech.QUEUE_FLUSH, null);
                }
            });

            ImageView deleteImage=new ImageView(this);
            deleteImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            deleteImage.setImageResource(R.drawable.ic_delete);
            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog md=new Dialog(Box1back.this);
                    md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    md.setContentView(R.layout.yesnodialog);
                    md.setCancelable(true);

                    int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                    int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                    md.getWindow().setLayout(width, height);

                    TextView t=md.findViewById(R.id.title);
                    Utils.prepareTextView(t,"irsans.ttf", Color.WHITE,16);
                    t.setText("اخطار!");

                    TextView alertTextView=md.findViewById(R.id.alert);
                    Utils.prepareTextView(alertTextView,"irsans.ttf", Color.BLACK,16);
                    alertTextView.setText("آیا از حذف این فیش مطمئن هستید؟");


                    Button yesbutton=md.findViewById(R.id.yesbutton);
                    yesbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LinterDB db=new LinterDB(Box1back.this);
                            db.deleteLeitner(lc.English);
                            Toast.makeText(Box1back.this,lc.English+" حذف گردید",Toast.LENGTH_LONG).show();
                            md.dismiss();
                        }
                    });

                    Button nobutton=md.findViewById(R.id.nobutton);
                    nobutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            md.dismiss();
                        }
                    });

                    md.show();
                    //
                }
            });

            ImageView editImage=new ImageView(this);
            editImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            editImage.setImageResource(R.drawable.ic_action_name);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog md=new Dialog(Box1back.this);
                    md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    md.setContentView(R.layout.dialog);
                    md.setCancelable(true);

                    int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                    int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                    md.getWindow().setLayout(width, height);

                    TextView t=md.findViewById(R.id.english);
                    Utils.prepareTextView(t,"irsans.ttf", Color.WHITE,16);
                    t.setText(lc.English);

                    final EditText editView=md.findViewById(R.id.persian);
                    editView.setText(lc.Persian);
                    Utils.prepareEdit(editView,"irsans.ttf",16);

                    Button okbutton=md.findViewById(R.id.okbutton);
                    okbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LinterDB db=new LinterDB(Box1back.this);
                            //Toast.makeText(getContext(),"ویرایش انجام شد",Toast.LENGTH_LONG).show();
                            db.updateTranslation(lc.English,editView.getText().toString());
                            lc.Persian=editView.getText().toString();
                            db.close();
                            md.dismiss();
                        }
                    });

                    md.show();
                }
            });

            childLinearLayout.addView(soundImage);
            childLinearLayout.addView(deleteImage);
            childLinearLayout.addView(editImage);

            View tempView2=new View(this);
            tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int)scale));
            tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            linearLayout.addView(tv1);
            linearLayout.addView(childLinearLayout);
            linearLayout.addView(tempView2);

            rootLL.addView(linearLayout);
        }}






    }
}
