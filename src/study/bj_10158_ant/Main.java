package study.bj_10158_ant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)  throws IOException {
        Main main = new Main();
        main.solution();
    }

    public void solution() throws IOException {
        int width, height;
        int p, q, t;
        int[] widthHeight = new int[2];
        int[] positionArr = new int[2];
        // 사용자로부터 콘솔을 통해 입력값을 받는다
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String widthSpaceHeight = br.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(widthSpaceHeight);
        width = Integer.parseInt(stringTokenizer.nextToken());
        height = Integer.parseInt(stringTokenizer.nextToken());

        String position = br.readLine();
        StringTokenizer stringTokenizer2 = new StringTokenizer(position);
        p = Integer.parseInt(stringTokenizer2.nextToken());
        q = Integer.parseInt(stringTokenizer2.nextToken());

        t = Integer.parseInt(br.readLine());

        // 구한 값들 정리해서 세팅
        widthHeight[0] = width;
        widthHeight[1] = height;
        positionArr[0] = p;
        positionArr[1] = q;

        int result[] = calculate(widthHeight, positionArr, t);

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

