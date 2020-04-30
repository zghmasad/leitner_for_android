package leitner;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import yobinet.ir.ielts.R;
import litnerdb.LinterDB;
import litnerdb.LitnerClass;
import other.Utils;

/**
 * Created by 4li on 1/14/2018.
 */

public class LeitnerFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v=inflater.inflate(R.layout.leitnerfragment, container, false);

        final TextToSpeech myTTS=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if(i != TextToSpeech.ERROR) {
                        //myTTS.setLanguage(Locale.US);
                    }
                }
            });

        Bundle args = getArguments();
        final String english = args.getString("en", "");
        //final int id = args.getInt("id", -1);
        final LinterDB db=new LinterDB(getContext());
        final String persian=db.getTranslation(english);
        final TextView tv=v.findViewById(R.id.text);
        final ImageView play2=v.findViewById(R.id.play2);
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTTS.speak(persian, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        Utils.prepareTextView(tv,"irsans.ttf",Color.BLACK,15);
        tv.setText(persian);
        final ImageView editButton=v.findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog md=new Dialog(getContext());
                md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                md.setContentView(R.layout.dialog);
                md.setCancelable(true);

                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                md.getWindow().setLayout(width, height);

                TextView t=md.findViewById(R.id.english);
                Utils.prepareTextView(t,"irsans.ttf", Color.WHITE,16);
                t.setText(english);

                final EditText editView=md.findViewById(R.id.persian);
                String persian=db.getTranslation(english);
                editView.setText(persian);
                Utils.prepareEdit(editView,"irsans.ttf",16);

                Button okbutton=md.findViewById(R.id.okbutton);
                okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LinterDB db=new LinterDB(getContext());
                        //Toast.makeText(getContext(),"ویرایش انجام شد",Toast.LENGTH_LONG).show();
                        tv.setText(editView.getText());
                        db.updateTranslation(english,editView.getText().toString());
                        db.close();
                        md.dismiss();
                    }
                });

                md.show();
            }
        });
        final int box = args.getInt("box", -1);



        final LinearLayout llinfo=v.findViewById(R.id.info);


        final LinearLayout LL=v.findViewById(R.id.ll);
        LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setVisibility(View.VISIBLE);
                llinfo.setVisibility(View.VISIBLE);
                play2.setVisibility(View.VISIBLE);
            }
        });

        ImageView iv=v.findViewById(R.id.delete);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog md=new Dialog(getContext());
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
                        LinterDB db=new LinterDB(v.getContext());
                        db.deleteLeitner(english);
                        LL.setVisibility(View.GONE);
                        Toast.makeText(v.getContext(),english+" حذف گردید",Toast.LENGTH_LONG).show();
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

        TextView tven=v.findViewById(R.id.texten);
        tven.setText(english);

        final Button btnLearned=v.findViewById(R.id.learned);
        final Button btnNotLearned=v.findViewById(R.id.notlearned);

        btnLearned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cl= Calendar.getInstance();
                int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                LinterDB db=new LinterDB(getContext());
                String persian=db.getTranslation(english);
                db.updateLeitner(new LitnerClass(Minutes,Days,english,persian,box+1));
                btnLearned.setVisibility(View.GONE);
                btnNotLearned.setVisibility(View.GONE);
            }
        });

        btnNotLearned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cl= Calendar.getInstance();
                int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                LinterDB db=new LinterDB(getContext());
                String persian=db.getTranslation(english);
                db.updateLeitner(new LitnerClass(Minutes,Days,english,persian,0));
                btnLearned.setVisibility(View.GONE);
                btnNotLearned.setVisibility(View.GONE);
            }
        });

        ImageView play=v.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTTS.speak(english, TextToSpeech.QUEUE_FLUSH, null);
            }
        });





        return v;
    }
}
