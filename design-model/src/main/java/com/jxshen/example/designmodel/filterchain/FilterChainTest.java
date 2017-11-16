package com.jxshen.example.designmodel.filterchain;

import java.util.concurrent.atomic.AtomicInteger;

public class FilterChainTest {

    public static void main(String[] args) {
        FilterChain fc = new FilterChainImpl();
        fc.doFilter(fc);
    }

    private interface Filter {
        void doFilter(Filter chain);
    }

    private interface FilterChain extends Filter {}

    private static class FilterChainImpl implements FilterChain {

        private AtomicInteger index = new AtomicInteger(0);
        private Filter[] filters = {new FilterImpl1(), new FilterImpl2()};

        @Override
        public void doFilter(Filter chain) {
            Integer curIndex = index.getAndIncrement();
            if (curIndex < filters.length) {
                filters[curIndex].doFilter(this);
            }
            else {
                index.set(0);
            }
        }
    }

    private static abstract class AbstractFilter implements Filter {
        @Override
        public void doFilter(Filter chain) {
            System.out.println(this.getClass().getSimpleName());
            chain.doFilter(chain);
        }
    }

    private static class FilterImpl1 extends AbstractFilter{}
    private static class FilterImpl2 extends AbstractFilter{}
}
