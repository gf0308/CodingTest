package study.bj_10158_ant;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 백준 알고리즘 문제 - 10158 : 개미
 * (https://www.acmicpc.net/problem/10158)
 *
 * [문제]
 * 가로 길이가 w이고 세로 길이가 h인 2차원 격자 공간이 있다. 이 격자는 아래 그림처럼 왼쪽 아래가 (0,0)이고 오른쪽 위가 (w,h)이다.
 * 이 공간 안의 좌표 (p,q)에 개미 한 마리가 놓여있다. 개미는 오른쪽 위 45도 방향으로 일정한 속력으로 움직이기 시작한다.
 * 처음에 (p,q)에서 출발한 개미는 1시간 후에는 (p+1,q+1)로 옮겨간다.
 * 단, 이 속력으로 움직이다가 경계면에 부딪치면 같은 속력으로 반사되어 움직인다.
 *
 * 위 그림은 6×4 격자에서 처음에 (4,1)에서 출발한 개미가 움직인 길을 보여주고 있다.
 * 처음에 (4,1)에 있는 개미는 2시간 후에 (6,3)에 있으며 8시간 후에 (0,1)에 있다.
 * 만일 그 개미가 처음에 (5,3)에 있었다면 매 시간마다 (6,4), (5,3), (4,2), (3,1)로 움직인다.
 *
 * 여러분은 크기 w×h인 격자 공간에서 처음에 (p,q)에서 출발하는 개미의 t시간 후의 위치 (x,y)를 계산하여 출력해야 한다.
 * 개미는 절대 지치지 않고 같은 속력으로 이동한다고 가정한다.
 * 문제에서 w와 h는 자연수이며 범위는 2 ≤ w,h ≤ 40,000이다.
 * 그리고 개미의 초기 위치 p와 q도 자연수이며 범위는 각각 0 < p < w과 0 < q < h이다.
 * 그리고 계산할 시간 t의 범위는 1 ≤ t ≤ 200,000,000이다.
 *
 * */
public class Main2 {
    public static void main(String[] args) {
        Main2 main = new Main2();
        main.solution();
    }

    public void solution() {
        int width = 0;
        int height = 0;
        int p = 0;
        int q = 0;
        int t = 0;
        // 사용자로부터 콘솔을 통해 입력값을 받는다
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("가로값 w, 세로값 h를 입력해주세요(2 ≤ w,h ≤ 40,000)");
            String widthSpaceHeight = sc.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(widthSpaceHeight);         // split() 으로도 가능하나 속도(성능)면에서 StringTokenizer가 더 좋음; but 복잡한식이나 정확한 구분이 중요할 때는 split사용이 적절함.

            if(!stringTokenizer.hasMoreTokens()) {
                System.out.println("값을 제대로 입력해주세요.");
                continue;
            }
            int tokenCnt = stringTokenizer.countTokens();
            if(tokenCnt != 2) {
                System.out.println("2개의 값을 입력해주세요. 값 사이는 공백을 두고 입력해주세요.");
                continue;
            }

            width = Integer.parseInt(stringTokenizer.nextToken());
            height = Integer.parseInt(stringTokenizer.nextToken());
        } while( !((width >= 2 && width <= 40000) && (height >= 2 && height <= 40000)) );

        do {
            System.out.println("개미의 초기 위치 p,q를 입력해주세요(0 < p < w, 0 < q < h)");
            String position = sc.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(position);

            if(!stringTokenizer.hasMoreTokens()) {
                System.out.println("값을 제대로 입력해주세요.");
                continue;
            }
            int tokenCnt = stringTokenizer.countTokens();
            if(tokenCnt != 2) {
                System.out.println("2개의 값을 입력해주세요. 값 사이는 공백을 두고 입력해주세요.");
                continue;
            }

            p = Integer.parseInt(stringTokenizer.nextToken());
            q = Integer.parseInt(stringTokenizer.nextToken());
        } while( !((p > 0 && p < width) && (q >= 0 && q <= height)) );

        do {
            System.out.println("개미가 움직일 시간 t를 입력해주세요(1 ≤ t ≤ 200,000,000)");
            String time = sc.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(time);
            if(!stringTokenizer.hasMoreTokens()) {
                System.out.println("값을 제대로 입력해주세요.");
                continue;
            }
            int tokenCnt = stringTokenizer.countTokens();
            if(tokenCnt != 1) {
                System.out.println("1개의 값을 입력해주세요.");
                continue;
            }
            t = Integer.parseInt(time);
        } while( !(t >= 1 && t < 200000000) );

        // 구한 값들 정리해서 세팅
        int[] widthHeight = new int[2];
        widthHeight[0] = width;
        widthHeight[1] = height;

        int[] position = new int[2];
        position[0] = p;
        position[1] = q;

        int time = t;

        int result[] = this.calculate(widthHeight, position, time);

        System.out.print(result[0] + " " + result[1]);
    }

    private int[] calculate(int[] widthHeight, int[] position, int time) {
        int p = position[0];
        int q = position[1];
        // 증감 방향 플래그: true - 상승 방향, false - 감소 방향
        boolean upDownFlgOfP = true;
        boolean upDownFlgOfQ = true;

        for(int i = 1; i <= time; i++) {
            // 증감 처리
            p = (upDownFlgOfP) ? p + 1 : p - 1 ;
            q = (upDownFlgOfQ) ? q + 1 : q - 1 ;
            // 증감플래그 전환할지 체크
            if(p == widthHeight[0]) upDownFlgOfP = false;
            if(p == 0) upDownFlgOfP = true;
            if(q == widthHeight[1]) upDownFlgOfQ = false;
            if(q == 0) upDownFlgOfQ = true;
        }

        position[0] = p;
        position[1] = q;

        return position;
    }

}

