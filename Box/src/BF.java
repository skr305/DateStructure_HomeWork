import java.util.TreeMap;
import java.util.regex.Pattern;

/** Best Fit*/
public class BF {

    public static int BestFit(int[] e, int c) {

        int count = 0;
        int n = e.length-1;

        // 二叉搜索树
        TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();

        for(int i=1; i<=n; i++) {
            /** 最小的比e[i]大的物品 */
            Integer box_key = t.ceilingKey(e[i]);
            /** 目标箱子在被使用后的剩余空间 */
            int remain = 0;

            if(box_key == null) {
                remain = c-e[i];
                count++;

            } else {
                remain = box_key-e[i];
                /** 减少原箱子key的数量 */
                int num = t.get(box_key);
                if(num == 1) {
                    t.remove(box_key);
                } else {
                    t.put(box_key, num-1);
                }
            }


            /** 容量为remain的箱子的数量 */
            Integer remain_num = t.get(remain);
            /** 没有这个容量的箱子 */
            if(remain_num == null) {
                t.put(remain, 1);
            } else {
                t.put(remain, remain_num+1);
            }

        }

        return count;

    }

}
