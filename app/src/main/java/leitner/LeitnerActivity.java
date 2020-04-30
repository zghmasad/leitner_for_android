package leitner;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by 4li on 1/10/2018.
 */

public class LeitnerActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leitner_layout);
        final LinterDB db=new LinterDB(this);

        Calendar cl= Calendar.getInstance();

        int daysNow=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
        int minsNow=cl.get(Calendar.HOUR_OF_DAY)*60+cl.get(Calendar.MINUTE);
        //if(cl.get(Calendar.AM)==0)
        final boolean[] ready={false,false,false,false,false};
        int r=0;
        int n=0;
        List<LitnerClass> tempList=db.getBoxLitners(0,false);
        for(LitnerClass lc:tempList){
            if((lc.Days+1<daysNow)||(lc.Minutes<minsNow && lc.Days+1==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[0]=true;
        TextView tvr=findViewById(R.id.box1_ready);
        Utils.prepareTextView(tvr,"irsansb.ttf", Color.WHITE,14);
        tvr.setText(r+"");
        TextView tvn=findViewById(R.id.box1_notready);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn.setText(n+"");


        r=0;
        n=0;
        List<LitnerClass> tempList1=db.getBoxLitners(1,false);
        for(LitnerClass lc:tempList1){
            if((lc.Days+2<daysNow)||(lc.Minutes<minsNow && lc.Days+2==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[1]=true;
        tvr=findViewById(R.id.box2_ready);
        Utils.prepareTextView(tvr,"irsansb.ttf", Color.WHITE,14);
        tvr.setText(r+"");
        tvn=findViewById(R.id.box2_notready);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn.setText(n+"");


        r=0;
        n=0;
        List<LitnerClass> tempList2=db.getBoxLitners(2,false);
        for(LitnerClass lc:tempList2){
            if((lc.Days+4<daysNow)||(lc.Minutes<minsNow && lc.Days+4==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[2]=true;
        tvr=findViewById(R.id.box3_ready);
        Utils.prepareTextView(tvr,"irsansb.ttf", Color.WHITE,14);
        tvr.setText(r+"");
        tvn=findViewById(R.id.box3_notready);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn.setText(n+"");


        r=0;
        n=0;
        List<LitnerClass> tempList3=db.getBoxLitners(3,false);
        for(LitnerClass lc:tempList3){
            if((lc.Days+8<daysNow)||(lc.Minutes<minsNow && lc.Days+8==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[3]=true;
        tvr=findViewById(R.id.box4_ready);
        Utils.prepareTextView(tvr,"irsansb.ttf", Color.WHITE,14);
        tvr.setText(r+"");
        tvn=findViewById(R.id.box4_notready);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn.setText(n+"");


        r=0;
        n=0;
        List<LitnerClass> tempList4=db.getBoxLitners(4,false);
        for(LitnerClass lc:tempList4){
            if((lc.Days+14<daysNow)||(lc.Minutes<minsNow && lc.Days+14==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[4]=true;
        tvr=findViewById(R.id.box5_ready);
        Utils.prepareTextView(tvr,"irsansb.ttf", Color.WHITE,14);
        tvr.setText(r+"");
        tvn=findViewById(R.id.box5_notready);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn.setText(n+"");

        LinearLayout ll=findViewById(R.id.box1);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[0]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",0);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent test=new Intent(LeitnerActivity.this,Box1back.class);
                test.putExtra("BOX",0);
                startActivity(test);
                return false;
            }
        });

        LinearLayout ll1=findViewById(R.id.box2);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[1]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",1);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout ll2=findViewById(R.id.box3);
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[2]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",2);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout ll3=findViewById(R.id.box4);
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[3]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",3);
                    startActivity(test);
                }
                else
                        Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout ll4=findViewById(R.id.box5);
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[4]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",4);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        tvn=findViewById(R.id.box1_title);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.box2_title);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.box3_title);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.box4_title);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.box5_title);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.box6_title);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.readyTitle1);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.neadyTitle1);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.readyTitle2);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.neadyTitle2);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.readyTitle3);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.neadyTitle3);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.readyTitle4);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.neadyTitle4);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.readyTitle5);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.neadyTitle5);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.readyTitle6);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        tvn=findViewById(R.id.box6_ready);
        Utils.prepareTextView(tvn,"irsansb.ttf", Color.WHITE,14);
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final LinterDB db=new LinterDB(this);

        Calendar cl= Calendar.getInstance();
        //String sql="SELECT * FROM litnerTable WHERE box=1";

        int daysNow=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
        int minsNow=cl.get(Calendar.HOUR_OF_DAY)*60+cl.get(Calendar.MINUTE);
        final boolean[] ready={false,false,false,false,false,false};
        int r=0;
        int n=0;
        List<LitnerClass> tempList=db.getBoxLitners(0,false);
        for(LitnerClass lc:tempList){
            if((lc.Days+(Math.pow(2,0))<daysNow)||(lc.Minutes<minsNow && lc.Days+(Math.pow(2,0))==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[0]=true;
        TextView tvr=findViewById(R.id.box1_ready);
        tvr.setText(r+"");
        TextView tvn=findViewById(R.id.box1_notready);
        tvn.setText(n+"");


        r=0;
        n=0;
        List<LitnerClass> tempList1=db.getBoxLitners(1,false);
        for(LitnerClass lc:tempList1){
            if((lc.Days+(Math.pow(2,1))<daysNow)||(lc.Minutes<minsNow && lc.Days+(Math.pow(2,1))==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[1]=true;
        tvr=findViewById(R.id.box2_ready);
        tvr.setText(r+"");
        tvn=findViewById(R.id.box2_notready);
        tvn.setText(n+"");


        r=0;
        n=0;
        List<LitnerClass> tempList2=db.getBoxLitners(2,false);
        for(LitnerClass lc:tempList2){
            if((lc.Days+(Math.pow(2,2))<daysNow)||(lc.Minutes<minsNow && lc.Days+(Math.pow(2,2))==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[2]=true;
        tvr=findViewById(R.id.box3_ready);
        tvr.setText(r+"");
        tvn=findViewById(R.id.box3_notready);
        tvn.setText(n+"");


        r=0;
        n=0;
        List<LitnerClass> tempList3=db.getBoxLitners(3,false);
        for(LitnerClass lc:tempList3){
            if((lc.Days+(Math.pow(2,3))<daysNow)||(lc.Minutes<minsNow && lc.Days+(Math.pow(2,3))==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[3]=true;
        tvr=findViewById(R.id.box4_ready);
        tvr.setText(r+"");
        tvn=findViewById(R.id.box4_notready);
        tvn.setText(n+"");


        r=0;
        n=0;
        List<LitnerClass> tempList4=db.getBoxLitners(4,false);
        for(LitnerClass lc:tempList4){
            if((lc.Days+(Math.pow(2,4))<daysNow)||(lc.Minutes<minsNow && lc.Days+(Math.pow(2,4))==daysNow)){
                r++;
            }
            else
                n++;
        }
        if(r>0)
            ready[4]=true;
        tvr=findViewById(R.id.box5_ready);
        tvr.setText(r+"");
        tvn=findViewById(R.id.box5_notready);
        tvn.setText(n+"");

        r=0;
        n=0;
        List<LitnerClass> tempList5=db.getBoxLitners(5,true);
        r=tempList5.size();
        if(r>0)
            ready[5]=true;
        tvr=findViewById(R.id.box6_ready);
        tvr.setText(r+"");

        LinearLayout ll=findViewById(R.id.box1);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[0]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",0);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout ll1=findViewById(R.id.box2);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[1]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",1);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout ll2=findViewById(R.id.box3);
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[2]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",2);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout ll3=findViewById(R.id.box4);
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[3]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",3);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout ll4=findViewById(R.id.box5);
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[4]){
                    Intent test=new Intent(LeitnerActivity.this,LeitnerTab.class);
                    test.putExtra("BOX",4);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی آماده یادگیری نمی باشد",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout ll5=findViewById(R.id.box6);
        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready[5]){
                    Intent test=new Intent(LeitnerActivity.this,Box6.class);
                    test.putExtra("BOX",5);
                    startActivity(test);
                }
                else
                    Toast.makeText(LeitnerActivity.this,"هیچ فیشی فراگرفته نشده است",Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog md=new Dialog(LeitnerActivity.this);
                md.requestWindowFeature(Window.FEATURE_NO_TITLE);
                md.setContentView(R.layout.dialog2);
                md.setCancelable(true);

                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                md.getWindow().setLayout(width, height);

                final EditText editView2=md.findViewById(R.id.persian);
                final EditText editView=md.findViewById(R.id.english);
                Utils.prepareEdit(editView,"irsans.ttf",16);
                Utils.prepareEdit(editView2,"irsans.ttf",16);

                Button okbutton=md.findViewById(R.id.okbutton);
                okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LinterDB db=new LinterDB(LeitnerActivity.this);
                        Calendar cl= Calendar.getInstance();
                        int Minutes=cl.get(Calendar.MINUTE)+cl.get(Calendar.HOUR_OF_DAY)*60;
                        int Days=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                        db.addLitner(new LitnerClass(Minutes,Days,editView.getText().toString(),editView2.getText().toString(),0));
                        db.close();
                        Toast.makeText(LeitnerActivity.this,"فیش افزوده شد",Toast.LENGTH_LONG).show();
                        md.dismiss();
                    }
                });

                md.show();
            }
        });

        db.close();
    }
}
