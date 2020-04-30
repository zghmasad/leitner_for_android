package idioms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import yobinet.ir.ielts.R;

public class IdiomAlphabetList extends AppCompatActivity {

    public class Alphabet{
        public String alphabet;
        public int count;
        public int id;

        Alphabet(String iaphabet,int iindex,int iid){
            alphabet=iaphabet;
            count=iindex;
            id=iid;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idiomalphabetlist);
        final float scale = getResources().getDisplayMetrics().density;
        LinearLayout rootLL = findViewById(R.id.idiomAlphabetLinearLayout);
        TextView tv1;

        View tempView2 = new View(this);
        tempView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) scale));
        tempView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        final ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();

        Alphabet temp=new Alphabet("Actions - Behaviour",132,1);
        alphabets.add(temp);
        temp=new Alphabet("Age",8,2);
        alphabets.add(temp);
        temp=new Alphabet("Agreements - Arrangments",10,3);
        alphabets.add(temp);
        temp=new Alphabet("Ambition - Determination",39,4);
        alphabets.add(temp);
        temp=new Alphabet("Anger - Annoyance",69,5);
        alphabets.add(temp);
        temp=new Alphabet("Animals - Birds - Fish",113,6);
        alphabets.add(temp);
        temp=new Alphabet("Anxiety - Power",28,7);
        alphabets.add(temp);
        temp=new Alphabet("Arguments - Disagreements",41,8);
        alphabets.add(temp);
        temp=new Alphabet("Authority - Power",34,9);
        alphabets.add(temp);
        temp=new Alphabet("Beauty - Appearance",13,10);
        alphabets.add(temp);
        temp=new Alphabet("Body",189,11);
        alphabets.add(temp);
        temp=new Alphabet("Business - Work",107,12);
        alphabets.add(temp);
        temp=new Alphabet("Choices - Options",24,13);
        alphabets.add(temp);
        temp=new Alphabet("Clothes",24,14);
        alphabets.add(temp);
        temp=new Alphabet("Colours",39,15);
        alphabets.add(temp);
        temp=new Alphabet("Communication",12,16);
        alphabets.add(temp);
        temp=new Alphabet("Comparisons - Similarity",61,17);
        alphabets.add(temp);
        temp=new Alphabet("Consequences - Effects",23,18);
        alphabets.add(temp);
        temp=new Alphabet("Descriptions people",79,19);
        alphabets.add(temp);
        temp=new Alphabet("Descriptions places - things",70,20);
        alphabets.add(temp);
        temp=new Alphabet("Efficiency - Competence",28,21);
        alphabets.add(temp);
        temp=new Alphabet("Employment - Jobs",22,22);
        alphabets.add(temp);
        temp=new Alphabet("Enthusiasm - Motivation",11,23);
        alphabets.add(temp);
        temp=new Alphabet("Feelings - Emotions",51,24);
        alphabets.add(temp);
        temp=new Alphabet("Food - Drink",49,25);
        alphabets.add(temp);
        temp=new Alphabet("Frankness - sincerity",11,26);
        alphabets.add(temp);

        temp=new Alphabet("Fun - Enjoyment",19,27);
        alphabets.add(temp);
        temp=new Alphabet("Happiness - Sadness",20,28);
        alphabets.add(temp);
        temp=new Alphabet("Health - Fitness",36,29);
        alphabets.add(temp);
        temp=new Alphabet("Hesitation - Indecision",16,30);
        alphabets.add(temp);
        temp=new Alphabet("Honesty - Dishonesty",37,31);
        alphabets.add(temp);
        temp=new Alphabet("House - Fruniture",27,32);
        alphabets.add(temp);
        temp=new Alphabet("Intelligence-Understanding",57,33);
        alphabets.add(temp);
        temp=new Alphabet("Law - Order",26,34);
        alphabets.add(temp);
        temp=new Alphabet("Lifestyle",12,35);
        alphabets.add(temp);
        temp=new Alphabet("Luck - Opportunity",17,36);
        alphabets.add(temp);
        temp=new Alphabet("Madness - Insanity",12,37);
        alphabets.add(temp);
        temp=new Alphabet("Memory - Remembering",13,38);
        alphabets.add(temp);
        temp=new Alphabet("Mistakes - Errors",8,39);
        alphabets.add(temp);
        temp=new Alphabet("Money - Finance",55,40);
        alphabets.add(temp);
        temp=new Alphabet("Music",9,41);
        alphabets.add(temp);
        temp=new Alphabet("Negotiations",15,42);
        alphabets.add(temp);
        temp=new Alphabet("Numbers",40,43);
        alphabets.add(temp);
        temp=new Alphabet("Plants - Flowers - Trees",6,44);
        alphabets.add(temp);
        temp=new Alphabet("Politness",6,45);
        alphabets.add(temp);
        temp=new Alphabet("Problems - Difficulties",56,46);
        alphabets.add(temp);
        temp=new Alphabet("Relationships",16,47);
        alphabets.add(temp);
        temp=new Alphabet("Safety - Danger",27,48);
        alphabets.add(temp);
        temp=new Alphabet("Secrets - Discretion",17,49);
        alphabets.add(temp);
        temp=new Alphabet("Shopping",5,50);
        alphabets.add(temp);
        temp=new Alphabet("Sleep - Tiredness",7,51);
        alphabets.add(temp);
        temp=new Alphabet("Speed - Rapidity",15,52);
        alphabets.add(temp);
        temp=new Alphabet("Sports",13,53);
        alphabets.add(temp);
        temp=new Alphabet("Success - Failure",72,54);
        alphabets.add(temp);
        temp=new Alphabet("Surprise - Disbelief",15,55);
        alphabets.add(temp);
        temp=new Alphabet("Thoughts - Ideas",20,56);
        alphabets.add(temp);
        temp=new Alphabet("Time",31,57);
        alphabets.add(temp);
        temp=new Alphabet("Travel - Transport",15,58);
        alphabets.add(temp);
        temp=new Alphabet("Violence",3,59);
        alphabets.add(temp);
        temp=new Alphabet("Weather",13,60);
        alphabets.add(temp);



        //LinearLayout.LayoutParams tvlp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        for(int index=0;index<60;index++){
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
                    bdl.putInt("id",alphabets.get(finalIndex).id);
                    Intent i=new Intent(IdiomAlphabetList.this,Idioms.class);
                    i.putExtras(bdl);
                    startActivity(i);
                }
            });

            rootLL.addView(linearLayout);
        }


    }
}
