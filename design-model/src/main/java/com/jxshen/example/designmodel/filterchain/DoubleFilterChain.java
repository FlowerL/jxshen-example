package com.jxshen.example.designmodel.filterchain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DoubleFilterChain {

    public static void main(String[] args) {
        
    }
    
    public static class Request {
        String reqStr;
    }
    
    public static class Response {
        String respStr;
    }
    
    public static interface Filter {
        String doFilter(Request req, Response resp, FilterChain chain);
    }
    
    public static class HtmlFilter implements Filter {
        @Override
        public String doFilter(Request req, Response resp, FilterChain chain) {
            
            return null;
        }
    }
    
    public static class FilterChain {
        private final List<Filter> filters = new ArrayList<Filter>();
        // when call filter on chain, remark the index of filter
        private final AtomicInteger index = new AtomicInteger(0);
        private final AtomicBoolean isInBound = new AtomicBoolean(true);
        
        public FilterChain addFilter(Filter filter) {
            filters.add(filter);
            return this;
        }
        
        public void doFilter(Request req, Response resp) {
            int currentIndex = index.get();
            if (currentIndex == filters.size() ) {
                if (index.compareAndSet(currentIndex, currentIndex-1)) {
                    isInBound.set(false);
                }
            }
            // get current filter
            Filter f = filters.get(index.get());
            if (isInBound.get() == true) {
                index.getAndIncrement();
            } else {
                index.getAndDecrement();
            }
            
            f.doFilter(req, resp, this);
        }
    }
}
