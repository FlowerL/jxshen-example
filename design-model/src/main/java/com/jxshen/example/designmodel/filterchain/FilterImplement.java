package com.jxshen.example.designmodel.filterchain;

/**
 * reference from: http://www.flyne.org/article/693<br>
 * func to instead of html remark and filter sensitive words
 */
public class FilterImplement {

    public static void main(String[] args) {
        
    }
    
    public static interface Filter {
        String doFilter(String str);
    }
    
    public static class HtmlFilter implements Filter {
        @Override
        public String doFilter(String str) {
            String r = str;
            r = r.replace("<", "&lt;").replace(">", "&gt;");
            return r;
        }
    }
    
    public static class SensitiveFilter implements Filter {
        @Override
        public String doFilter(String str) {
            String r = str;
            r = r.replace("sensitive", "");
            return r;
        }
    }
    
    public static class MsgProcessor {
        private String msg;
        private Filter[] filters = {new HtmlFilter(), new SensitiveFilter()};

        public MsgProcessor(String msg) {
            super();
            this.msg = msg;
        }
        
        public String process() {
            String r = msg;
            for (Filter filter : filters) {
                r = filter.doFilter(r);
            }
            return r;
        }
    }
}
