package xin.lz1998.autosms;

public class MyClass {
    static String msgFormat;
    static String msgVar;
    static void setInfo(String msgf,String msgv){
        msgFormat=msgf;
        msgVar=msgv;
    }
    static public Tel[] getTel(){
        Tel[] t=new Tel[1000];
        String[] lines=msgVar.split("\n");
        String[] var_name=lines[0].split("@@@");
        for(int i=1;i<lines.length;i++){
            StringBuffer tmp=new StringBuffer(msgFormat);
            String[] vars=lines[i].split("@@@");
            t[i-1]=new Tel();
            t[i-1].phoneNum=vars[0];
            for(int j=0;j<vars.length;j++){
                int startPos=tmp.indexOf("{"+var_name[j]+"}");
                if(startPos>0)
                    tmp.replace(startPos,startPos+var_name[j].length()+2,vars[j]);
            }
            t[i-1].msg=tmp.toString();
        }
        return t;
    }

}
