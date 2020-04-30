package phrasal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import yobinet.ir.ielts.R;

public class VerbsAlphabetList extends AppCompatActivity {

    public class Alphabet{
        public String alphabet;
        public int count;

        Alphabet(String iaphabet,int iindex){
            alphabet=iaphabet;
            count=iindex;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ieltsalphabetlist);
        final float scale = getResources().getDisplayMetrics().density;
        LinearLayout rootLL = findViewById(R.id.ieltsAlphabetLinearLayout);
        TextView tv1;

        View tempView2 = new View(this);
        tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) scale));
        tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        final ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();

        Alphabet temp=new Alphabet("a",39);
        alphabets.add(temp);
        temp=new Alphabet("b",284);
        alphabets.add(temp);
        temp=new Alphabet("c",332);
        alphabets.add(temp);
        temp=new Alphabet("d",131);
        alphabets.add(temp);
        temp=new Alphabet("e",24);
        alphabets.add(temp);
        temp=new Alphabet("f",176);
        alphabets.add(temp);
        temp=new Alphabet("g",429);
        alphabets.add(temp);
        temp=new Alphabet("h",136);
        alphabets.add(temp);
        temp=new Alphabet("i",2);
        alphabets.add(temp);
        temp=new Alphabet("j",18);
        alphabets.add(temp);
        temp=new Alphabet("k",70);
        alphabets.add(temp);
        temp=new Alphabet("l",135);
        alphabets.add(temp);
        temp=new Alphabet("m",122);
        alphabets.add(temp);
        temp=new Alphabet("n",20);
        alphabets.add(temp);
        temp=new Alphabet("o",11);
        alphabets.add(temp);
        temp=new Alphabet("p",271);
        alphabets.add(temp);
        temp=new Alphabet("q",6);
        alphabets.add(temp);
        temp=new Alphabet("r",130);
        alphabets.add(temp);
        temp=new Alphabet("s",487);
        alphabets.add(temp);
        temp=new Alphabet("t",236);
        alphabets.add(temp);
        temp=new Alphabet("u",6);
        alphabets.add(temp);
        temp=new Alphabet("v",5);
        alphabets.add(temp);
        temp=new Alphabet("w",122);
        alphabets.add(temp);
        temp=new Alphabet("x",0);
        alphabets.add(temp);
        temp=new Alphabet("y",3);
        alphabets.add(temp);
        temp=new Alphabet("z",17);
        alphabets.add(temp);

        //LinearLayout.LayoutParams tvlp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        for(int index=0;index<26;index++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));




            tv1 = new TextView(this);
            tv1.setHeight((int) (40 * scale + 0.5f));
            tv1.setWidth(getResources().getDisplayMetrics().widthPixels);
            tv1.setText(alphabets.get(index).alphabet+" ("+ alphabets.get(index).count +")");
            tv1.setGravity(Gravity.CENTER);



            tempView2 = new View(this);
            tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
            tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            linearLayout.addView(tv1);
            linearLayout.addView(tempView2);

            final int finalIndex = index;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bdl=new Bundle();
                    bdl.putString("keyword",alphabets.get(finalIndex).alphabet);
                    Intent i=new Intent(VerbsAlphabetList.this,IeltsVerbs.class);
                    i.putExtras(bdl);
                    startActivity(i);
                }
            });

            rootLL.addView(linearLayout);
        }


    }
}
