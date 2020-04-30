package yobinet.ir.ielts;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;

import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;


import dictionary.DictionaryActivity;
import essay.EssayBand;
import idioms.IdiomAlphabetList;
import ieltsword.IeltsAlphabetList;
import irregular.IrregularVerbsAcrivity;
import leitner.LeitnerActivity;
import other.Utils;
import phrasal.VerbsAlphabetList;

import static litnerdb.LinterDB.DATABASE_NAME;

public class MainActivity extends AppCompatActivity
{

    private InterstitialAd mInterstitialAd;

    static int function=0;
    //1 dictionary        2 Leitner         3 ieltsWords          4 irregularVerbs

    //private TextView mLevelTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button dictionaryBtn = findViewById(R.id.dictionaryButton);
        Utils.prepareButton(dictionaryBtn,"irsans.ttf", 13);
        //dictionaryBtn.setEnabled(false);
        dictionaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function=1;
                showInterstitial();
                //

            }
        });

        Button leitnerBtn = findViewById(R.id.leitnerButton);
        Utils.prepareButton(leitnerBtn,"irsans.ttf", 13);
        leitnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function=2;
                showInterstitial();
                //

            }
        });

        Button ieltsWordsBtn = findViewById(R.id.ieltsWordsButton);
        Utils.prepareButton(ieltsWordsBtn,"irsans.ttf", 13);
        //ieltsWordsBtn.setEnabled(false);
        ieltsWordsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function=3;
                showInterstitial();
                //

            }
        });

        Button irregularVerbsButton = findViewById(R.id.irregularVerbsButton);
        Utils.prepareButton(irregularVerbsButton,"irsans.ttf", 13);
        //irregularVerbsButton.setEnabled(false);
        irregularVerbsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function=4;
                showInterstitial();
                //

            }
        });

        Button idiomBtn = findViewById(R.id.idiomsButton);
        Utils.prepareButton(idiomBtn,"irsans.ttf", 13);
        idiomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function=5;
                showInterstitial();
            }
        });

        Button essayBtn = findViewById(R.id.essayButton);
        Utils.prepareButton(essayBtn,"irsans.ttf", 13);
        essayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function=6;
                showInterstitial();
            }
        });

        Button phrasalBtn = findViewById(R.id.phrasalButton);
        Utils.prepareButton(phrasalBtn,"irsans.ttf", 13);
        phrasalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function=7;
                showInterstitial();
            }
        });

        mInterstitialAd = newInterstitialAd();
        loadInterstitial();


    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                /*dictionaryBtn.setEnabled(true);
                leitnerBtn.setEnabled(true);
                ieltsWordsBtn.setEnabled(true);
                irregularVerbsButton.setEnabled(true);*/
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                /*dictionaryBtn.setEnabled(true);
                leitnerBtn.setEnabled(true);
                ieltsWordsBtn.setEnabled(true);
                irregularVerbsButton.setEnabled(true);*/
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            //Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        /*dictionaryBtn.setEnabled(false);
        leitnerBtn.setEnabled(false);
        ieltsWordsBtn.setEnabled(false);
        irregularVerbsButton.setEnabled(false);*/
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        //mLevelTextView.setText("Level " + (++mLevel));
        switch (function){
            case 1:startActivity(new Intent(getApplicationContext(), DictionaryActivity.class));break;
            case 2:startActivity(new Intent(getApplicationContext(), LeitnerActivity.class));break;
            case 3:startActivity(new Intent(getApplicationContext(), IeltsAlphabetList.class));break;
            case 4:startActivity(new Intent(getApplicationContext(), IrregularVerbsAcrivity.class));break;
            case 5:startActivity(new Intent(getApplicationContext(), IdiomAlphabetList.class));break;
            case 6:startActivity(new Intent(getApplicationContext(), EssayBand.class));break;
            case 7:startActivity(new Intent(getApplicationContext(), VerbsAlphabetList.class));break;
        }
        //if(function==1)startActivity(new Intent(getApplicationContext(), YouTubePlayerFragmentActivity.class));
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }
    //
    private void shareApplication() {

        //GenericFileProvider temp=new GenericFileProvider();
        ApplicationInfo app = getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("*/*");

        // Append file and send Intent
        File originalApk = new File(filePath);

        try {
            //Make new directory in new location
            File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            //Open share dialog
            Uri apkURI = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + "yobinet.ir.ielts", tempFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            startActivity(Intent.createChooser(intent, "Share app via"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.action_share)
            shareApplication();
        else if (id == R.id.action_backup) {
            exportDB(this);
            return true;
        }else if (id == R.id.action_restore) {
            importDB(this);
            return true;
        }

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }
    public static void importDB(Context context) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            if (sd.canWrite()) {
                File backupDB = context.getDatabasePath(DATABASE_NAME);
                String backupDBPath = String.format("%s.bak", DATABASE_NAME);
                File currentDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

                Toast.makeText(context,"بازیابی انجام شد",Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(context,"عدم وجود مجوز",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportDB(Context context) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            //File data = Environment.getDataDirectory();


            if (sd.canWrite()) {
                Toast.makeText(context,"در حال انجام پشتیبان گیری",Toast.LENGTH_LONG).show();
                String backupDBPath = String.format("%s.bak", DATABASE_NAME);
                File currentDB = context.getDatabasePath(DATABASE_NAME);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(context,"پشتیبان گیری انجام شد",Toast.LENGTH_LONG).show();
                //MyApplication.toastSomething(context, "Backup Successful!");
            }
            else{
                Toast.makeText(context,"عدم وجود مجوز",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
