import java.util.Scanner;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        randomTest();
    }

    /** 手动测试 */
    private static void diyTest() {
        int n;
        int [] e;
        int c;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the num of objects");
        n = scanner.nextInt();
        e = new int[n+1];


        for(int i=1; i<=n; i++) {
            System.out.println("Input the size of the No" + i + ". object, still need " + (n-i+1) + " objects");
            e[i] = scanner.nextInt();
        }

        System.out.println("Input the size of the boxes");
        c = scanner.nextInt();


        test(e, c);
    }


    /** 产生随机数测试 */
    private static void randomTest() {
        int n;
        int [] e;
        int[] c_choice = {100, 500, 1000, 5000};
        int c;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the num of objects (2~200000)");
        n = scanner.nextInt();
        if(n<2 || n>200000) {
            System.out.println("Bad Input !!");
            return;
        }

        System.out.println("select the size of boxes (0/1/2/3 -- 100/500/1000/5000)");
        c = c_choice[scanner.nextInt()];


        e = new int[n+1];


        for(int i=1; i<=n; i++) {
            e[i] = (int)(Math.random() * (c-2)) + 1;
        }

        test(e, c);
    }


    private static void test(int[] e, int c) {
        /** 不同算法所用的时间 */
        long bfTime, ffTime, bfdTime, ffdTime;
        /** 计时节点 */
        long time_node1, time_node2;

        System.out.println("Test begins\n");

        time_node1 = System.currentTimeMillis();
        int BF_result = BF.BestFit(e, c);
        time_node2 = System.currentTimeMillis();
        bfTime = time_node2 - time_node1;

        time_node1 = System.currentTimeMillis();
        int FF_result = FF.FirstFit(e, c);
        time_node2 = System.currentTimeMillis();
        ffTime = time_node2 - time_node1;

        System.out.println("BF-result:" + BF_result + " --use time: " + bfTime + "ms");
        System.out.println("FF-result:" + FF_result + " --use time: " + ffTime + "ms");
    }
}
