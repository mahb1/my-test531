package com.mahb.algorithm.greedy;

import java.util.*;

/**
 * @program: algorithm
 * @description: 贪心算法
 * @author: Mr.Mahongbin
 * @create: 2019-10-06 10:29
 **/
public class GreedtAlgorithm {

    public static void main(String[] args) {

        // 创建 广播电台
        Map<String, HashSet<String>> broadcasts = new HashMap<>();

        // 将各个 电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        // 存方所有 地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 存放选择的电台集合
        List<String> selects = new ArrayList<>();

        // 遍历过程中，存放遍历过的电台覆盖的 地区和当前还没有 覆盖的地区 的 交集
        HashSet<String> tempSet = new HashSet<>();

        // 保存一次遍历过程中，能够覆盖最大 未覆盖的确对应的 电台 key
        // 如果 MaxKey 不为 null, 直接加入到 select
        String maxKey = null;
        while( allAreas.size() != 0){ // 不为0 表示还没有覆盖 所有地区

            maxKey = null;

            for(String key : broadcasts.keySet()){
                tempSet.clear();

                // 当前 key 能够 覆盖的 地区
                HashSet<String> araes = broadcasts.get(key);
                tempSet.addAll(araes) ;
                tempSet.retainAll(allAreas) ; // 将 tempSet 和 retainAll 两个集合的交集 赋给 tempSet

                // 如果当前集合 包含的未覆盖地区的数量 比 maxKey 指向的集合 未覆盖的地区还要多；需要重置 maxKey ;
                /**
                 * tempSet.size() > broadcasts.get(maxKey).size() 体现 贪心算法的 特点,每次选择最优的
                 */
                if(tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }

            if(maxKey != null){     // 不为 null 加入到  selects
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey)); // 将 maxKey 指向的广播电台 覆盖的地区，从 allAreas 清除
            }
        }

        System.out.println(" 得到的选择结果：" + selects );





    }
}
