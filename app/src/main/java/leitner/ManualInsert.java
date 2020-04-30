package leitner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import litnerdb.LinterDB;
import litnerdb.LitnerClass;

import yobinet.ir.ielts.R;

public class ManualInsert extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manudalinsert);

        final EditText perTv=findViewById(R.id.persian);
        final EditText enTv=findViewById(R.id.english);
        final EditText boxTv=findViewById(R.id.box);

        Button btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et=findViewById(R.id.year);

                final int daysNow;
                final int minsNow;
                if(et.getText().toString().isEmpty())
                {
                    Calendar cl= Calendar.getInstance();
                    daysNow=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                    minsNow=cl.get(Calendar.HOUR_OF_DAY)*60+cl.get(Calendar.MINUTE);
                }
                else
                {
                    Calendar cl= Calendar.getInstance();
                    cl.set(Calendar.YEAR, Integer.parseInt(et.getText().toString()));
                    EditText tvDay=findViewById(R.id.day);
                    cl.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tvDay.getText().toString()));
                    EditText tvMon=findViewById(R.id.mon);
                    cl.set(Calendar.MONTH,Integer.parseInt(tvMon.getText().toString()));

                    daysNow=cl.get(Calendar.DAY_OF_YEAR)+(cl.get(Calendar.YEAR))*365;
                    minsNow=cl.get(Calendar.HOUR_OF_DAY)*60+cl.get(Calendar.MINUTE);
                }
                LinterDB db=new LinterDB(ManualInsert.this);

                String per=perTv.getText().toString();
                String en=enTv.getText().toString();
                int box=Integer.parseInt(boxTv.getText().toString());
                db.addLitner(new LitnerClass(minsNow,daysNow,en,per,box));
                Toast.makeText(ManualInsert.this,"به جعبه لایتنر اضافه شد",Toast.LENGTH_LONG).show();

            }
        });
    }
}
