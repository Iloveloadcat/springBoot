//슈도코드
/*
* N값 입력받기
* 길이 N의 숫자를 입력받아 string 변수 sNum에 저장
* sNum을 다시 char[]형 변수 cNum에 저장
* int형 변수 sum 선언하기
* for(cNum 갈아만큼 반복)
* {
*  배열의 각 자릿값을 정수형으로 변환하여 sum에 더하여 누적하기
* }
* sum출력하기
* */

import java.util.Scanner;

public class sumNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //5
        String sNum = sc.next(); //54321
        char[] cNum = sNum.toCharArray();

        int sum = 0;

        for(int i = 0; i< cNum.length; i++) {
            /*
            * 아스키코드를 이해하고있어야 함
            * 문자열 '1'과 숫자 1의 코드값 차이는 49 이므로
            * '1'-48 또는  '1'-0 으로 연산하면 문자열을 숫자형으로 변환 가능
            * */
            sum += cNum[i] - '0';
        }
        System.out.println("sum :  " + sum);
    }
}