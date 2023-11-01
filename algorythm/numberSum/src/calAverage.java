/*
* 과목수N을 입력받고
* 과목수 만큼 점수를 기입하고 점수중에서 최대값을M이라고 한다
* 그 점수들을 합하고 100을 곱한 후 M으로 나누고 그 값을 다시 과목수 만큼 나눈다.
* ex) N = 3 / a=40 b=80 c=60이면 M은 80
* (40+80+60)*100/80/3 = 75
* */

import java.util.Scanner;

public class calAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //과목수
        //int[] arr = new int[n];
        //int[] arr = {1,2,3} //배열선언과 동시에 초기화 (데이터 건수가 정해졌을시 사용)
        long sum = 0;
        long max = 0;
        float avg;
/*        for(int i=0; i < n; i++) {
            arr[i] = sc.nextInt(); //과목점수입력
            sum += arr[i];
        }*/
        for(int i=0; i < n; i++) {
            int temp = sc.nextInt();
            if(temp > max) {
                max = temp;
            }
            sum += temp;
        }
        System.out.println("sum : " + sum);
        System.out.println("avg : " + (sum*100.0)/max/n); //.0을 붙이면 계산할때 자동으로 double형계산

    }
}
