package essay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import yobinet.ir.ielts.R;

public class EssayBand extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band);
        Button btn6=findViewById(R.id.band6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bdl=new Bundle();
                bdl.putInt("band",6);
                Intent i=new Intent(EssayBand.this, EssayAlphabetList.class);
                i.putExtras(bdl);
                startActivity(i);
            }
        });

        Button btn7=findViewById(R.id.band7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bdl=new Bundle();
                bdl.putInt("band",7);
                Intent i=new Intent(EssayBand.this, EssayAlphabetList.class);
                i.putExtras(bdl);
                startActivity(i);
            }
        });

        Button btn8=findViewById(R.id.band8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bdl=new Bundle();
                bdl.putInt("band",8);
                Intent i=new Intent(EssayBand.this, EssayAlphabetList.class);
                i.putExtras(bdl);
                startActivity(i);
            }
        });

    }

}
