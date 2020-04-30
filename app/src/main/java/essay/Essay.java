package essay;

public class Essay {
    public int id;
    public String essay_question;
    public String feedbak;
    public String essay;
    public int band;
    public Essay(int iid,int iband,String iessayquestion,String iessay,String ifeedbak){
        id=iid;
        band=iband;
        feedbak=ifeedbak;
        essay=iessay;
        essay_question=iessayquestion;
    }
}
