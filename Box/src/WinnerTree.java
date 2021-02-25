public class WinnerTree {

    /**赢者树的节点数量*/
    int n;
    int LowExt;
    int offset;

    int [] t;
    int [] e;

    WinnerTree(int[] e) {
        this.e = e;

        n = e.length-1;
        /** 1~n节点代表第1~n场比赛 */
        this.t = new int[n];

        int i, s;

        /** 计算出重要参数LowExt 和 offset*/
        for(s=1; 2*s<=n-1; s+=s);

        LowExt = 2*(n-s);
        offset = 2*s-1;

        /** 先对底层节点进行比赛 */
        for(i=2; i<=LowExt; i+=2) {
            int p = (offset+i)/2;
            play(p, i-1, i);
        }

        /** 当n为奇数的时候 会有一场内部节点与外部节点的比赛 */
        if(n % 2 != 0) {
            play(n/2, t[n-1], LowExt+1);
            i = LowExt + 3;
        } else {
            i = LowExt + 2;
        }


        /** 从左至右依次完成剩下的比赛 */
        for(; i<n; i+=2) {
            play((i-LowExt+n-1)/2, i-1, i);
        }
    }

    /** 重赛
     * @param i 被针对重赛的节点
     */
    public void replay(int i) {
        if(i<0 || i>n) {
            System.out.println("Bad Input in WinnerTree::replay(i)");
        }

        int p, lc, rc;

        /** 先确认第一场比赛的选手节点 */
         //如果此节点在最低层
        if(i <= LowExt) {
            p = (offset + i) / 2;
            lc = 2*p - offset;
            rc = lc + 1;
        } else {
            p = (i-LowExt+n-1)/2;
            /** 看看是否是和内部节点的比赛(特殊情况)*/
            if(2*p == n-1) {
                lc = t[2*p];
                rc = i;
            } else {
                lc = 2*p -n + 1 + LowExt;
                rc = lc + 1;
            }
        }


        t[p] = winner(lc, rc);

        /** 完成剩余的比赛 */
        p /= 2;
        for(;p>=1; p/=2) {
            if(2*p+1==n) {
                t[p] = winner(t[2*p], n);
            } else {
                t[p] = winner(t[2*p], t[2*p+1]);
            }

        }
    }

    private void play(int p, int lc, int rc) {
        t[p] = winner(lc, rc);

        /** 如果此节点是右节点 那么此时它左边的比赛都结束了*/
        while (p>1 && p%2 != 0) {
            t[p/2] = winner(t[p-1], t[p]);
            p /= 2;
        }
    }


    private int winner(int lc, int rc) {
        if(e[lc] >= e[rc]) {
            return lc;
        } else {
            return rc;
        }
    }

}
