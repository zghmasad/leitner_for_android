package leitner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import litnerdb.LinterDB;
import litnerdb.LitnerClass;

import yobinet.ir.ielts.R;

/**
 * Created by 4li on 1/14/2018.
 */

public class LeitnerTab extends AppCompatActivity {
    class item{
        int id;
        String en;
    }
    static int box;
    static List<item> items;

    private FragmentPagerAdapter createCalendarAdaptor() {return new FragmentPagerAdapter(
            getSupportFragmentManager()) {
        @Override
        public int getCount() {
            return items.size()>100?100:items.size();
        }

        @Override
        public Fragment getItem(int position) {
            LeitnerFragment fragment= new LeitnerFragment();
            Bundle args = new Bundle();
            item temp=items.get(position);
            args.putString("en", temp.en);
            args.putInt("box", box);
            args.putInt("id", temp.id);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemPosition(Object object){
            return PagerAdapter.POSITION_NONE;
        }


    };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leitner_tab);
        items=new ArrayList<>();
        //box=0;
        Intent myIntent = getIntent(); // gets the previously created intent
        box = myIntent.getIntExtra("BOX",-1);
        LinterDB db=new LinterDB(this);

        Calendar cl=Calendar.getInstance();
        int daysNow=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
        int minsNow=cl.get(Calendar.HOUR_OF_DAY)*60+cl.get(Calendar.MINUTE);
        List<LitnerClass> tempLS;
        if(box!=-1)
        {
            tempLS=db.getBoxLitners(box,false);

            for(LitnerClass lc:tempLS){
                if((lc.Days+(Math.pow(2,box))<daysNow)||(lc.Minutes<minsNow && lc.Days+(Math.pow(2,box))==daysNow)){
                    item item1=new item();
                    item1.en=lc.English;
                    item1.id=lc.Id;
                    items.add(item1);
                }
            }
            //Toast.makeText(this,items.size()+"",Toast.LENGTH_LONG).show();
            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            viewPager.setAdapter(createCalendarAdaptor());
            viewPager.setOffscreenPageLimit(items.size()>100?100:items.size());

        }




    }
}
