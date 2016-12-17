package prbm15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets. For example,
 * given array S = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */

class Pair<L, R> {

    private final L left;
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair pairo = (Pair) o;
        return this.left.equals(pairo.getLeft()) && this.right.equals(pairo.getRight());
    }

}

public class SumThree {
    public List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> retSet = new HashSet<List<Integer>>();
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                countMap.put(num, countMap.get(num) + 1);
            } else {
                countMap.put(num, 1);
            }
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int a = nums[i];
                int b = nums[j];
                if (a == 0 && b == 0) {
                    if (countMap.containsKey(0) && countMap.get(0) >= 3) {
                        List<Integer> l = new ArrayList<Integer>();
                        l.add(0);
                        l.add(0);
                        l.add(0);
                        retSet.add(l);
                    }
                    continue;
                }
                int c = 0 - a - b;
                if ((c == a || c == b)) {
                    if (countMap.containsKey(c) && countMap.get(c) >= 2) {
                        List<Integer> l = new ArrayList<Integer>();
                        l.add(a);
                        l.add(b);
                        l.add(c);
                        Collections.sort(l);
                        retSet.add(l);
                    }
                    continue;
                }
                if (countMap.containsKey(c) && countMap.get(c) >= 1) {
                    List<Integer> l = new ArrayList<Integer>();
                    l.add(a);
                    l.add(b);
                    l.add(c);
                    Collections.sort(l);
                    retSet.add(l);
                }
            }
        }
        List<List<Integer>> retList = new ArrayList<List<Integer>>();
        retList.addAll(retSet);
        return retList;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> retSet = new HashSet<List<Integer>>();
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                countMap.put(num, countMap.get(num) + 1);
            } else {
                countMap.put(num, 1);
            }
        }

        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        while (r >= l + 1) {
            int a = nums[l];
            int b = nums[r];
            int c = 0 - a - b;
            if (c < a) {
                r--;
            } else if (c > b) {
                l++;
            } else {
                if (a == 0 && b == 0) {
                    if (countMap.containsKey(0) && countMap.get(0) >= 3) {
                        List<Integer> tl = new ArrayList<Integer>();
                        tl.add(0);
                        tl.add(0);
                        tl.add(0);
                        retSet.add(tl);
                    }
                    r--;
                    continue;
                }
                if ((c == a || c == b)) {
                    if (countMap.containsKey(c) && countMap.get(c) >= 2) {
                        List<Integer> tl = new ArrayList<Integer>();
                        tl.add(a);
                        tl.add(b);
                        tl.add(c);
                        Collections.sort(tl);
                        retSet.add(tl);
                    }
                    if (c == a) {
                        r--;
                    } else {
                        // c==b
                        l++;
                    }
                    continue;
                }
                if (countMap.containsKey(c) && countMap.get(c) >= 1) {
                    List<Integer> tl = new ArrayList<Integer>();
                    tl.add(a);
                    tl.add(b);
                    tl.add(c);
                    Collections.sort(tl);
                    retSet.add(tl);
                }
            }
        }
        List<List<Integer>> retList = new ArrayList<List<Integer>>();
        retList.addAll(retSet);
        return retList;
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> retSet = new HashSet<List<Integer>>();
        // val->List(pos)
        Map<Integer, List<List<Integer>>> posMap = new TreeMap<Integer, List<List<Integer>>>();
        for (int i = 0; i < nums.length; i++) {
            if(posMap.containsKey(nums[i])){
                posMap.get(nums[i]).add(Arrays.asList(i));
            }else {
                List<List<Integer>> posList=new ArrayList<List<Integer>>();
                posList.add(Arrays.asList(i));
                posMap.put(nums[i], posList);
            }
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (posMap.containsKey(sum)) {
                    posMap.get(sum).add(Arrays.asList(i, j));
                } else {
                    List<List<Integer>> posList = new ArrayList<List<Integer>>();
                    posList.add(Arrays.asList(i, j));
                    posMap.put(sum, posList);
                }
            }
        }
        // System.out.println(posMap);
        Integer[] slist = new Integer[posMap.keySet().size()];
        posMap.keySet().toArray(slist);
        int i = 0;
        int j = slist.length - 1;
        while (true) {
            int sum = slist[i] + slist[j];
            if (sum == 0) {
                List<List<Integer>> posListi = posMap.get(slist[i]);
                List<List<Integer>> posListj = posMap.get(slist[j]);
                for (int ii = 0; ii < posListi.size(); ii++) {
                    for (int jj = 0; jj < posListj.size(); jj++) {
                        List<Integer> lii = posListi.get(ii);
                        List<Integer> ljj = posListj.get(jj);
                        if (lii.size() == 1 && ljj.size() == 1 || lii.size() == 2 && ljj.size() == 2
                                || lii.containsAll(ljj) || ljj.containsAll(lii)) {
                            // not qualified
                            continue;
                        }
                        List<Integer> tl = new ArrayList<Integer>();
                        for (Integer idxi : lii) {
                            tl.add(nums[idxi]);
                        }
                        for (Integer idxj : ljj) {
                            tl.add(nums[idxj]);
                        }
                        Collections.sort(tl);
                        retSet.add(tl);
                    }
                }
            }
            if (i == j) {
                break;
            }
            // add minus i, j
            if (sum > 0) {
                j--;
            } else {
                i++;
            }
        }
        List<List<Integer>> retList = new ArrayList<List<Integer>>();
        retList.addAll(retSet);
        return retList;
    }

    public List<List<Integer>> threeSum4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> retSet = new HashSet<List<Integer>>();
        // val->List(pos)
        Map<Integer, List<Integer>> posMap1 = new TreeMap<Integer, List<Integer>>();
        Map<Integer, List<Pair<Integer, Integer>>> posMap2 = new TreeMap<Integer, List<Pair<Integer, Integer>>>();
        for (int i = 0; i < nums.length; i++) {
            if (posMap1.containsKey(nums[i])) {
                posMap1.get(nums[i]).add(i);
            } else {
                List<Integer> posList = new ArrayList<Integer>();
                posList.add(i);
                posMap1.put(nums[i], posList);
            }
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (posMap2.containsKey(sum)) {
                    posMap2.get(sum).add(new Pair<Integer, Integer>(i, j));
                } else {
                    List<Pair<Integer, Integer>> posList = new ArrayList<Pair<Integer, Integer>>();
                    posList.add(new Pair<Integer, Integer>(i, j));
                    posMap2.put(sum, posList);
                }
            }
        }
        // System.out.println(posMap1);
        // System.out.println(posMap2);
        if (posMap2.size() == 0) {
            return new ArrayList<List<Integer>>();
        }
        Integer[] slist1 = new Integer[posMap1.keySet().size()];
        posMap1.keySet().toArray(slist1);
        Integer[] slist2 = new Integer[posMap2.keySet().size()];
        posMap2.keySet().toArray(slist2);
        int i = 0;
        int j = slist2.length - 1;
        while (true) {
            int sum = slist1[i] + slist2[j];
            if (sum == 0) {
                List<Integer> posListi = posMap1.get(slist1[i]);
                List<Pair<Integer, Integer>> posListj = posMap2.get(slist2[j]);
                for (int ii = 0; ii < posListi.size(); ii++) {
                    for (int jj = 0; jj < posListj.size(); jj++) {
                        Integer lii = posListi.get(ii);
                        Pair<Integer, Integer> ljj = posListj.get(jj);
                        if (lii == ljj.getLeft() || lii == ljj.getRight()) {
                            // not qualified
                            continue;
                        }
                        List<Integer> tl = new ArrayList<Integer>();
                        tl.add(nums[lii]);
                        tl.add(nums[ljj.getLeft()]);
                        tl.add(nums[ljj.getRight()]);
                        Collections.sort(tl);
                        retSet.add(tl);
                    }
                }
            }
            // add minus i, j
            if (sum > 0) {
                j--;
            } else {
                i++;
            }
            if (i >= slist1.length || j < 0) {
                break;
            }
        }
        List<List<Integer>> retList = new ArrayList<List<Integer>>();
        retList.addAll(retSet);
        return retList;
    }

    // wrong
    public List<List<Integer>> threeSum5(int[] nums) {
        Set<List<Integer>> retSet = new HashSet<List<Integer>>();
        Map<Integer, Integer> cntMap = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (!cntMap.containsKey(num)) {
                cntMap.put(num, 1);
            } else {
                cntMap.put(num, cntMap.get(num) + 1);
            }
        }
        System.out.println(cntMap);

        Integer[] slist = new Integer[cntMap.keySet().size()];
        cntMap.keySet().toArray(slist);
        int i = 0;
        int j = slist.length - 1;
        while (true) {
            int tempa = slist[i];
            int tempb = slist[j];
            int sum = tempa + tempb;
            if (i == j && cntMap.get(slist[i]) <= 1) {
                break;
            }
            int tempc = -sum;
            if (cntMap.containsKey(tempc) && (tempa == tempb && tempb == tempc && cntMap.get(tempa) >= 3
                    || tempa != tempc && tempb != tempc || tempa == tempc && tempb != tempc && cntMap.get(tempa) >= 2
                    || tempb == tempc && tempa != tempc && cntMap.get(tempb) >= 2)) {
                List<Integer> tl = Arrays.asList(tempa, tempb, tempc);
                Collections.sort(tl);
                retSet.add(tl);
            }
            if (i == j) {
                break;
            }
            // add minus i, j
            if (sum > 0) {
                j--;
            } else {
                i++;
            }
        }
        List<List<Integer>> retList = new ArrayList<List<Integer>>();
        retList.addAll(retSet);
        return retList;
    }

    public List<List<Integer>> threeSum6(int[] nums) {
        Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!cntMap.containsKey(num)) {
                cntMap.put(num, 1);
            } else {
                cntMap.put(num, cntMap.get(num) + 1);
            }
        }
        // System.out.println(cntMap);
        Set<List<Integer>> retSet = getThree(cntMap, null, null);
        List<List<Integer>> retList = new ArrayList<List<Integer>>();
        retList.addAll(retSet);
        return retList;
    }

    public Set<List<Integer>> getThree(Map<Integer, Integer> cntMap, Integer first, Integer second) {
        Set<List<Integer>> retSet = new HashSet<List<Integer>>();
        if (first != null && second != null) {
            if (first == second && cntMap.get(first) < 2) {
                return retSet;
            }
            int third = -first - second;
            if (!cntMap.containsKey(third)) {
                return retSet;
            }
            if (third == first && cntMap.get(first) < 2) {
                return retSet;
            }
            if (third == second && cntMap.get(second) < 2) {
                return retSet;
            }
            if (third == first && third == second && cntMap.get(first) < 3) {
                return retSet;
            }
            List<Integer> tl = Arrays.asList(first, second, third);
            Collections.sort(tl);
            retSet.add(tl);
            return retSet;
        }
        if (first != null) {
            for (Integer s : cntMap.keySet()) {
                retSet.addAll(getThree(cntMap, first, s));
            }
            return retSet;
        }
        for (Integer f : cntMap.keySet()) {
            retSet.addAll(getThree(cntMap, f, null));
        }
        return retSet;
    }

    public static void main(String[] args) {
        SumThree s = new SumThree();
        int[] input0 = new int[] {};
        int[] input1 = new int[] { 5, -11, -7, -2, 4, 9, 4, 4, -5, 12, 12, -14, -5, 3, -3, -2, -6, 3, 3, -9, 4, -13, 6,
                2, 11, 12, 10, -14, -15, 11, 0, 5, 8, 0, 10, -11, -6, -1, 0, 4, -4, -3, 5, -2, -15, 9, 11, -13, -2, -8,
                -7, 9, -6, 7, -11, 12, 4, 14, 6, -4, 3, -9, -14, -12, -2, 3, -8, 7, -13, 7, -12, -9, 11, 0, 4, 12, -6,
                -7, 14, -1, 0, 14, -6, 1, 6, -2, -9, -4, -11, 12, -1, -1, 10, -7, -6, -7, 11, 1, -15, 6, -15, -12, 12,
                12, 3, 1, 9, 12, 9, 0, -11, -14, -1 };
        int[] input2 = new int[] { 0 };
        int[] input3 = new int[] { 0, -4, -1, -4, -2, -3, 2 };
        System.out.println(s.threeSum2(input0));
        System.out.println(s.threeSum6(input0));
    }
}
