package other;

import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 4li on 1/15/2018.
 */

public class Utils {
    public static void prepareTextView(TextView textView, String fonturl, int color, float size) {
        Typeface typeface;
        typeface = Typeface.createFromAsset(textView.getContext().getAssets(), fonturl);
        textView.setTextColor(color);
        if(size>0)
            textView.setTextSize(size);
        else
            textView.setTextSize(13);
        textView.setTypeface(typeface);
    }

    public static void prepareEdit(EditText textView, String fonturl,float size) {
        Typeface typeface;
        typeface = Typeface.createFromAsset(textView.getContext().getAssets(), fonturl);
        if(size>0)
            textView.setTextSize(size);
        else
            textView.setTextSize(13);
        textView.setTypeface(typeface);
    }

    public static void prepareButton(Button button, String fonturl, float size) {
        Typeface typeface;
        typeface = Typeface.createFromAsset(button.getContext().getAssets(), fonturl);
        if(size>0)
            button.setTextSize(size);
        else
            button.setTextSize(13);
        button.setTypeface(typeface);
    }

    public static String rTrim(String str) {
        if (str != null && str.length() > 0 && (str.charAt(str.length() - 1) == ':'||
                str.charAt(str.length() - 1) == '"'||
                str.charAt(str.length() - 1) == '.'||
                str.charAt(str.length() - 1) == ','||
                str.charAt(str.length() - 1) == ';'||
                str.charAt(str.length() - 1) == '!'||
                str.charAt(str.length() - 1) == '?'||
                str.charAt(str.length() - 1) == '\''||
                str.charAt(str.length() - 1) == ')'
        )) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static String fTrim(String str) {
        if (str != null && str.length() > 0 && (str.charAt(0) == '('||
                str.charAt(0) == '"'||
                str.charAt(0) == '\''
        )) {
            str = str.substring(1, str.length());
        }
        return str;
    }
}
