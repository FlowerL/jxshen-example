package com.jxshen.example.jdk.list;

import java.util.*;

public class ListForEachTest {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2)
                list.remove(integer);
        }

//        List<String> arrayList = new ArrayList<>();
//        arrayList.add("1");
//        arrayList.add("2");
//        arrayList.add("3");
//        arrayList.add("4");
//        try {
//            for (String str : arrayList) {
//                if (str.equalsIgnoreCase("2")) {
//                    arrayList.remove(str);
//                }
//            }
//        } catch (ConcurrentModificationException e) {
//            e.printStackTrace();
//        }
//
//        List<Integer> linkedList = new LinkedList<>();
//        linkedList.add(1);
//        linkedList.add(2);
//        linkedList.add(3);
//        try {
//            for (Integer integer : linkedList) {
//                if (integer == 2) {
//                    linkedList.remove(integer);
//                }
//            }
//        } catch (ConcurrentModificationException e) {
//            e.printStackTrace();
//        }
    }
}
