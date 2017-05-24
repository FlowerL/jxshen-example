package com.jxshen.example.designmodel.filterchain;

import java.util.ArrayList;
import java.util.List;

/**
 * reference from: http://www.flyne.org/article/693<br>
 * func to instead of html remark and filter sensitive words
 */
public class FilterChainImplement {

    public static void main(String[] args) {
        
    }
    
    public static interface Filter {
        String doFilter(String str);
    }
    
    public static class FilterChain implements Filter {
        private List<Filter> filters = new ArrayList<Filter>();
        
        public FilterChain addFilter(Filter filter) {
            filters.add(filter);
            return this;
        }

        @Override
        public String doFilter(String str) {
            String r = str;
            for (Filter filter : filters) {
                r = filter.doFilter(r);
            }
            return r;
        }
    }
    
    public static class MsgProcessor {
        private String msg;
        private FilterChain filterChain = new FilterChain();
        public MsgProcessor(String msg, FilterChain filterChain) {
            super();
            this.msg = msg;
            this.filterChain = filterChain;
        }
        
        public String process() {
            return filterChain.doFilter(msg);
        }
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
}
