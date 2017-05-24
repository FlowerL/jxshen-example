package com.jxshen.example.designmodel.filterchain;

/**
 * reference from: http://www.flyne.org/article/693<br>
 * func to instead of html remark and filter sensitive words
 */
public class NoFilterDesign {

    public static void main(String[] args) {
        String msg = "sensitive word:), <script>";
        MsgProcessor mp = new MsgProcessor(msg);
        String r = mp.process();
        System.out.println(r);
    }
    
    public static class MsgProcessor {
        private String msg;

        public MsgProcessor(String msg) {
            super();
            this.msg = msg;
        }
        
        public String process() {
            String r = msg;
            // filter html remark in msg
            r = r.replace("<", "&lt;").replace(">", "&gt;");
            // filter sensitive words
            r = r.replace("sensitive", "");
            
            return r;
        }
    }
}
