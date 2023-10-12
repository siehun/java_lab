package new05;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * javaLabclass
 *
 * @author yuehaitao
 * @date 2023/10/11
 */
public class javaLab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList<Integer> li = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            li.add(i);
        }
        int time = sc.nextInt();
        for(int i = 0; i < time; i++) {
            int inte = sc.nextInt();
            int index = li.indexOf(inte);
            int step = sc.nextInt();
            li.remove(index);
            li.add(index+step,inte);
        }
        for(int ele: li) {
            System.out.print(ele+" ");
        }
    }
}
