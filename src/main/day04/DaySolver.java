package main.day04;

import java.util.*;

import main.AbstractSolver;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    // input 168630-718098
    @Override
    public void solvePart1() {
        List<Integer> res = reduceInputRange(168630, 718098);
        System.out.println(res.size());
    }

    @Override
    public void solvePart2() {
        List<Integer> res = reduceInputRange(168630, 718098);
        int resCnt = 0;

outer:  for (Integer val : res) {
            char[] digits = String.valueOf(val).toCharArray();
            int followedCnt = 1;
            char before = digits[0];
            for (int i = 1; i < digits.length; ++i) {
                char curr = digits[i];
                if (curr == before) {
                    followedCnt++;
                } else {
                    if (followedCnt == 2) {
                        resCnt++;
                        continue outer;
                    } else {
                        followedCnt = 1;
                    }
                }
                before = curr;
            }
            if (followedCnt == 2) {
                resCnt++;
            }
        }
        System.out.println(resCnt);
    }

    private List<Integer> reduceInputRange(int min, int max) {
        List<Integer> res = new ArrayList<>();
        for (int curr = min; curr < max; ++curr) {
            char[] digits = String.valueOf(curr).toCharArray();
            boolean matchCriteria1 = true;
            boolean matchCriteria2 = false;
            for (int i = 0; i < digits.length - 1; ++i) {
                // must be increasing
                if (digits[i] > digits[i + 1]) {
                    matchCriteria1 = false;
                    break;
                }
                // must have at least 2 adjacent digits
                if (digits[i] == digits[i + 1]) {
                    matchCriteria2 = true;
                }
            }
            if (matchCriteria1 && matchCriteria2) {
                res.add(curr);
            }
        }
        return res;
    }

}
