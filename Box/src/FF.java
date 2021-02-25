/** First Fit*/
public class FF {
    /**
     *
     * @param e 物品序列
     * @param c 箱子的容量
     *
     * @return 最终所用箱子的数量
     */
    public static int FirstFit(int[] e, int c) {
        /** 使用箱子的数量 */
        int count = 0;

        /** 使用的物品存于1~n号 */
        int n = e.length-1;

        /** 初始化箱子 */
        int [] box = new int[n+1];
        for(int i=0; i<=n; i++)
            box[i] = c;

        WinnerTree w = new WinnerTree(box);

        /** 模拟1-n号物品放入箱子的过程 */
        for(int i=1; i<=n; i++) {
            /** 先查左子树 */
            int p = 2;
            while (p<n) {
                int win = w.t[p];

                if(box[win] < e[i]) {
                    p++; //移到右子树上
                }

                p *= 2;
            }

            /** 最终确定的箱子 */
            int b;
            p /= 2;

            if(p<n) {
                b =  w.t[p];

                if(b>1 && box[b-1]>=e[i]) {
                    b--;
                }
            }
            /** 出现一种特殊情况 即此时p仍在外部节点*/
            else {
                b = w.t[p/2];
            }

            /** 还没被用过 */
            if(box[b] == c) {
                count++;
            }
            box[b] -= e[i];

            //重赛
            w.replay(b);
        }

        return count;

    }
}
