import java.util.Scanner;

public class project2 {
	public static void main(String[] args) {
		System.out.print("연산:");
		Scanner scanner = new Scanner(System.in);
		
		int num1 = scanner.nextInt();
		String s = scanner.next();
		int num2 = scanner.nextInt();
		int sum = 0;
		
		switch(s) {
		case "+":
			sum = num1 + num2;
			System.out.println(num1+s+num2+ "의 계산 결과는 "+sum);
			break;
		case "-":
			sum = num1 - num2;
			System.out.println(num1+s+num2+ "의 계산 결과는 "+sum);
			break;
		case "*":
			sum = num1 * num2;
			System.out.println(num1+s+num2+ "의 계산 결과는 "+sum);
			break;
		case "/":
			if (num2 == 0) {
				System.out.print("0으로 나눌 수 없습니다.");
				return;
			}
			System.out.println(num1+s+num2+ "의 계산 결과는 "+sum);
			sum = num1 / num2;
			break;
		default:
			System.out.println("연산자를 정확히 입력하세요.");
			
		}
		scanner.close();
	}
}