/*
* 인덱스 1  2  3  4  5
* 배열A  5  4  3  2  1
* 배열합S 5  9 12 14 15
* 합 배열 공식 => S[i] = S[i-1] + A[i]
* 만약 구간 i~j가 주어졌다면 구간합은 S[j] - s[i-1]
* 질의1(1,3) : S[3] - s[0] => 12 - 0 = 12
* 질의2(2,4) : S[4] - S[1] => 14 - 5 = 9
* 질의3(5,5) : S[5] - S[4] => 15 - 14 = 1
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class guganSum {
    public static void main(String[] args) throws IOException {
        //받는 입력값이 많을때는 BufferdReader를 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer 문자열을 우리가 지정한 구분자로 문자열을 쪼개주는 클래스
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine()); //입력데이터 한 줄을 통채로 받아 공백을 기준으로 분리하여 저장하겠다.
        int suNo = Integer.parseInt(stringTokenizer.nextToken()); //수의 개수
        int quizNo = Integer.parseInt(stringTokenizer.nextToken()); //질의개수
        long[] S = new long[suNo + 1]; //배열의 0번째 인덱스를 무시하고 질의를 바로 받아서 처리하고자 +1을 함

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i=1; i <= suNo; i++) {
            S[i] = S[i-1]+Integer.parseInt(stringTokenizer.nextToken());
        }

        for(int j=0; j < quizNo; j++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println("S[b] :" + S[b]);
            System.out.println("S[a-1] :" + S[a-1]);
            System.out.println(S[b] - S[a-1]);
        }
    }
}
