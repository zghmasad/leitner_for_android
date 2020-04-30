package litnerdb;

/**
 * Created by 4li on 8/22/2017.
 */

public class LitnerClass {
    public int Minutes;
    public int Days;
    public int Id;
    public int Box;
    public String English;
    public String Persian;
    public LitnerClass(){}
    public LitnerClass(int minutes,int days,String english,String persian,int box){
        Minutes=minutes;
        Days=days;
        Box=box;
        English=english;
        Persian=persian;
    }
    public LitnerClass(int id,int minutes,int days,String english,String persian,int box){
        Id=id;
        Minutes=minutes;
        Days=days;
        Box=box;
        English=english;
        Persian=persian;
    }
}
