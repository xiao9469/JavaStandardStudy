package com.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();
        Collections.addAll(list,13,17,15,8,14,15,19,7,8,9);
        int result = max_profit_dc(list);
        System.out.println("问题的求解为:"+result);
    }
    public static int max_profit_dc(List prices){
        int len_prices ;
        int min;
        int maxProfit_left;
        int maxProfit_right;
        int maxProfit_left_right;
        int result;
        List<Integer> prices_left = new LinkedList<Integer>();
        List<Integer> prices_right = new LinkedList<Integer>();
        List<Integer> resultList = new LinkedList<Integer>();

        len_prices = prices.size();
        if(len_prices <= 1){
            return 0;
        }

        min = len_prices / 2;
        prices_left = prices.subList(0, min);
        prices_right = prices.subList(min,len_prices);
        maxProfit_left = max_profit_dc(prices_left);
        maxProfit_right = max_profit_dc(prices_right);
        maxProfit_left_right = Collections.max(prices_right) - Collections.min(prices_left);

        Collections.addAll(resultList,maxProfit_left,maxProfit_right,maxProfit_left_right);
        result = Collections.max(resultList);
        return result;
    }

}
