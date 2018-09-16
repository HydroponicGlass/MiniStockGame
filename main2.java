
// ---------- 기업정보 ----------
/* 
 * 0. 헬로닉스 : 전자, 초기값 83000, 가중치 우상향, 변동률 중
 * 1. 셀트리오 : 바이오, 초기값 218000, 가중치 우상향, 변동률 최상
 * 2. 세이버 : 소프트웨어, 초기값 805000, 가중치 보합, 변동률 최상
 * 3. kd금융 : 금융, 초기값 56000, 가중치 보합, 변동률 하
 * 4. 아산건설 : 건설, 초기값 36000, 가중치 우하향, 변동률 하
 * 5. Korean Telecom : 통신, 초기값 29300, 가중치 보합, 변동률 상
 * 6. 뉴월드 : 유통, 초기값 259000, 가중치 우하향, 변동률 상
 */
// ---------------------------



import java.util.Scanner;
import java.util.Random;

public class main2 { 
	// ---------------- 턴마다 저장되는 값 -----------------
	// 변동 주식 저장값
	public static int save_company(int company, double[] percent_ran_array, int ran_array_number) {
		company += company*percent_ran_array[ran_array_number]/100;
		return company; 
	}
	// 더해진 현금 저장값
	public static int plus_account(int account, int company, int tran_number) {
		account +=company*tran_number;
		return account; 
	}
	// 감소된 보유 주식 수 저장값
	public static int minus_stock(int stock_number, int tran_number) {
		stock_number -= tran_number;
		return stock_number;  
	}
	// 감소된 현금 저장 값
	public static int minus_account(int account, int company, int tran_number) {
		account -=company*tran_number;
		return account; 
	}
	// 증가된 보유 주식 수 저장 값
	public static int plus_stock(int stock_number, int tran_number) {
		stock_number += tran_number;
		return stock_number; 
	}
	
	//--------------- 게임 엔딩 조건 ------------------
	public static boolean ending(int a, int b) {
		if(a < 0) {
			System.out.println("GAME OVER");
			System.out.println("최종 평가금액 : " + a);
			return false;
		}
		if(a >= 10000000) {
			System.out.println("VICTORY");
			System.out.println("최종 평가금액 : " + a);
			return false;
		}
		if(b < -1000000) {
			System.out.println("GAME OVER");
			System.out.println("최종현금 : " + b);
			return false;
		}
		return true;
	}
	public static void main(String args[]) {
// ------------- 변경 가능 값 -----------	
		int turn_interval=3; // 이벤트 간격
		int strike_interval = 5; // 현금 감소 간격
		int sum = 0; // 평가금액
		int strike = 10000; // 차감금액
		//int strike_sum = 0; // 차감금액 초기값, 이제는 안씀
		int account = 500000; // 현금(초기자본금)
		int company_0 = 83000; // 헬로닉스 초기값
		int company_1 = 218000; // 셀트리오 초기값
		int company_2 = 805000; // 세이버 초기값
		int company_3 = 56000; // kd금융 초기값
		int company_4 = 36000; // 아산건설 초기값
		int company_5 = 29300; // Korean Telecom 초기값
		int company_6 = 259000; // 뉴월드 초기값
//------------------------------------------
		
		int stock_0, stock_1, stock_2, stock_3, stock_4, stock_5, stock_6; // 보유주식, 초기값 0
		stock_0 = stock_1 =  stock_2 =  stock_3 = stock_4 = stock_5 = stock_6 = 0;
		boolean tf = true; // 코드 전체 반복문
		int turn =1;	 // 턴 수
		int k=7; // 회사 수
		int print_ran_array_number =0; // 주식 변동률 지정 랜덤 변수(출력용)
		int company_ran_array[] = new int[k]; // 왜 만들었더라
		// ------- 회사 가중치 선언 --------
		double print_percent_ran_array0[] = new double[20]; // 헬로닉스
		double print_percent_ran_array1[] = new double[20]; // 셀트리오
		double print_percent_ran_array2[] = new double[20]; // 세이버
		double print_percent_ran_array3[] = new double[20]; // kd금융
		double print_percent_ran_array4[] = new double[20]; // 아산건설
		double print_percent_ran_array5[] = new double[20]; // Korean Telecom
		double print_percent_ran_array6[] = new double[20]; // 뉴월드
		// -------------------------------------
		System.out.println("***** 진행방법 ****************************************************************************************");
		System.out.println("1. 각 턴마다 주식을 사고 팔 수 있습니다.");
		System.out.println("2. 주가는 턴마다 변동하며, 3턴에 한번씩 이벤트에 해당하는 주식은 크게 변동할 수 있습니다.");
		System.out.println("3. 평가금액은 현금 + 각 종목 보유주식 수 X 주식가치 입니다.");
		System.out.println("4. 50만원의 자본금으로 1000만원의 평가금액을 달성하면 승리합니다.");
		System.out.println("5. 백만원 초과의 빛(현금)이 생기거나, 평가금액이 0원 미만이 되면 패배합니다. ");
		System.out.println("6. 5번째 턴에 현금 만원이 감소하며, 감소량은 턴이 5번 돌아올 때마다 두배씩 증가합니다.");
		System.out.println("*********************************************************************************************************");
		System.out.println("\t");
		System.out.println("* 시작하시려면 start 를 입력하세요 *");

		boolean tf_start = true; // 시작 반복문, start 입력 전까지 무한 반복
		while(tf_start) {
			Scanner start = new Scanner(System.in);
			String start_string = start.next();
			if(start_string.equals("start")) {
			tf_start = false;
			}
		}
		while(tf) {
			boolean tf2 = true; // 거래 반복문
			Random ran_news = new Random();
			int ran_news_number = ran_news.nextInt(31); // 뉴스 지정 랜덤 변수
			int ran_report_number = ran_news.nextInt(7); // 증권사 리포트 지정 랜덤 변수
			int ran_array_number = ran_news.nextInt(20); // 회사 가중치 지정 랜덤 변수
			double percent_ran_array0[], percent_ran_array1[], percent_ran_array2[],percent_ran_array3[], percent_ran_array4[], percent_ran_array5[], percent_ran_array6[];
			// ------------- 회사 기본 가중치(주식 증감률) 할당 --------------
			percent_ran_array0 = new double[] {-10, -10, -5, -5, -5, -5, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 10, 10, 10};
			percent_ran_array1 = new double[] {-15, -10, -5, -5, -5, -5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 10, 10, 10, 15};
			percent_ran_array2 = new double[] {-15, -15, -10, -10, -5, -5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 5, 10, 10, 15, 15};
			percent_ran_array3 = new double[] {-5, -5, -5, -5, -5, -5, -5, -5, -5, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5};
			percent_ran_array4 = new double[] {-10, -10, -10, -10, -5, -5, -5, -5, -5, 0, 0, 0, 0, 5, 5, 5, 5, 10, 10, 10};
			percent_ran_array5 = new double[] {-10, -10, -10, -5, -5, -5, -5, -5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 10, 10, 10};
			percent_ran_array6 = new double[] {-10, -10, -10, -5, -5, -5, 5, 5, 5, 0, 0, 0, 0, 0, 5, 5, 5, 10, 10, 10};
			// -------------------------------------------------
			System.out.println("\t");
			// -------  n턴마다 현금 차감 ---------
			if(turn%strike_interval == 0) {
				//strike_sum +=strike;
				//account -= strike_sum;
				account -= strike;
				System.out.println("----------------------------------------------------------------------------" );
				System.out.println("현재 " + turn +"턴, 현금에서 " + strike + "가 차감되었습니다." );
				System.out.println("--------------------------------------------------------------------------- ." );
				strike = strike*2;
			}
			//-------------------------------
			System.out.println("\t");
			
			while(tf2) { /* 거래 반복문 */
			//	sum = sum_fx(account, stock_0, company_0);
				// 평가금액
				sum = account + stock_0*company_0 + stock_1*company_1 + stock_2*company_2 + stock_3*company_3 + stock_4*company_4 + stock_5*company_5 + stock_6*company_6;
				System.out.println(" * 보유 현금 / 평가금액 *");
				System.out.println(" l " + account + " / " + sum + " l ");
				System.out.println(" * 보유 주식 * ");
				System.out.print("ㅣ헬로닉스 " + stock_0 +"주 ㅣ");
				System.out.print("ㅣ셀트리오 " + stock_1 +"주 ㅣ");
				System.out.print("ㅣ세이버 " + stock_2 +"주 ㅣ");
				System.out.print("ㅣkd금융 " + stock_3 +"주 ㅣ");
				System.out.print("ㅣ아산건설 " + stock_4 +"주 ㅣ");
				System.out.print("ㅣKorean Telecom " + stock_5 +"주 ㅣ");
				System.out.println("ㅣ뉴월드 " + stock_6 +"주 ㅣ");
				System.out.println(" * 종목 현재가 (1주당 가격) * ");
				if(percent_ran_array0[print_ran_array_number] > 0) {
					System.out.print("ㅣ헬로닉스 " + company_0+"원 " + print_percent_ran_array0[print_ran_array_number] +"% ▲ ㅣ");
				} else if(print_percent_ran_array0[print_ran_array_number] < 0) {
					System.out.print("ㅣ헬로닉스 " + company_0+"원 " + print_percent_ran_array0[print_ran_array_number] +"% ▼ ㅣ");
				} else {
					System.out.print("ㅣ헬로닉스 " + company_0+"원 " + print_percent_ran_array0[print_ran_array_number] +"% ● ㅣ");
				}
				if(print_percent_ran_array1[print_ran_array_number] > 0) {
					System.out.print("ㅣ셀트리오 " + company_1+"원 " + print_percent_ran_array1[print_ran_array_number] +"% ▲ ㅣ");
				} else if(print_percent_ran_array1[print_ran_array_number] < 0) {
					System.out.print("ㅣ셀트리오 " + company_1+"원 " + print_percent_ran_array1[print_ran_array_number] +"% ▼ ㅣ");
				} else {
					System.out.print("ㅣ셀트리오 " + company_1+"원 " + print_percent_ran_array1[print_ran_array_number] +"% ● ㅣ");
				}
				if(print_percent_ran_array2[print_ran_array_number] > 0) {
					System.out.print("ㅣ세이버 " + company_2+"원 " + print_percent_ran_array2[print_ran_array_number] +"% ▲ ㅣ");
				} else if(print_percent_ran_array2[print_ran_array_number] < 0) {
					System.out.print("ㅣ세이버 " + company_2+"원 " + print_percent_ran_array2[print_ran_array_number] +"% ▼ ㅣ");
				} else {
					System.out.print("ㅣ세이버 " + company_2+"원 " + print_percent_ran_array2[print_ran_array_number] +"% ● ㅣ");
				}
				if(print_percent_ran_array3[print_ran_array_number] > 0) {
					System.out.println("ㅣkd금융 " + company_3+"원 " + print_percent_ran_array3[print_ran_array_number] +"% ▲ ㅣ");
				} else if(print_percent_ran_array3[print_ran_array_number] < 0) {
					System.out.println("ㅣkd금융 " + company_3+"원 " + print_percent_ran_array3[print_ran_array_number] +"% ▼ ㅣ");
				} else {
					System.out.println("ㅣkd금융 " + company_3+"원 " + print_percent_ran_array3[print_ran_array_number] +"% ● ㅣ");
				}
				if(print_percent_ran_array4[print_ran_array_number] > 0) {
					System.out.print("ㅣ아산건설 " + company_4+"원 " + print_percent_ran_array4[print_ran_array_number] +"% ▲ ㅣ");
				} else if(print_percent_ran_array4[print_ran_array_number] < 0) {
					System.out.print("ㅣ아산건설 " + company_4+"원 " + print_percent_ran_array4[print_ran_array_number] +"% ▼ ㅣ");
				} else {
					System.out.print("ㅣ아산건설 " + company_4+"원 " + print_percent_ran_array4[print_ran_array_number] +"% ● ㅣ");
				}
				if(print_percent_ran_array5[print_ran_array_number] > 0) {
					System.out.print("ㅣKorean Telecom " + company_5+"원 " + print_percent_ran_array5[print_ran_array_number] +"% ▲ ㅣ");
				} else if(print_percent_ran_array5[print_ran_array_number] < 0) {
					System.out.print("ㅣKorean Telecom " + company_5+"원 " + print_percent_ran_array5[print_ran_array_number] +"% ▼ ㅣ");
				} else {
					System.out.print("ㅣKorean Telecom " + company_5+"원 " + print_percent_ran_array5[print_ran_array_number] +"% ● ㅣ");
				}
				if(print_percent_ran_array6[print_ran_array_number] > 0) {
					System.out.println("ㅣ뉴월드 " + company_6+"원 " + print_percent_ran_array6[print_ran_array_number] +"% ▲ ㅣ");
				} else if(print_percent_ran_array6[print_ran_array_number] < 0) {
					System.out.println("ㅣ뉴월드 " + company_6+"원 " + print_percent_ran_array6[print_ran_array_number] +"% ▼ ㅣ");
				} else {
					System.out.println("ㅣ뉴월드 " + company_6+"원 " + print_percent_ran_array6[print_ran_array_number] +"% ● ㅣ");
				}
				// -----------------  뉴스에 따른 회사 가중치 변경 ----------------
				if(turn%turn_interval == 0) {
					System.out.println("----------------------------------------------------------------------------");
					switch (ran_news_number) {
						case 0:
							percent_ran_array0 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							System.out.println("[고조선일보] 헬로닉스, 올해 실적 호조");
							break;
						case 1:
							percent_ran_array6 = new double[] {-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-5};
							System.out.println("[고조선일보] 뉴월드, 프렌차이즈 갑질 드러나");
							break;
						case 2:
							percent_ran_array3 = new double[] {10,10,10,10,10,10,15,15,15,15,15,15,15,15,15,25,25,25,25,25};
							System.out.println("[고조선일보] kd금융, 올해의 브랜드 대상");
							break;
						case 3:
							percent_ran_array4 = new double[] {10,10,10,10,10,10,15,15,15,15,15,15,20,20,20,20,20,20,20,20};
							System.out.println("[고조선일보] 전체가구 44.5% 무주택자...5채 이상 소유자 11만명 육박");
							break;
						case 4:
							percent_ran_array4 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							System.out.println("[고조선일보] 정부, 임대주택 예산 작년대비 50%상승");
							break;
						case 5:
							percent_ran_array1 = new double[] {-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5};
							System.out.println("[고조선일보] 사성바이오로직스, 떠오르는 신성");
							break;
						case 6:
							percent_ran_array2 = new double[] {-30,-30,-30,-25,-25,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5};
							System.out.println("[고조선일보] 세이버, 5년간 탈세...1조 5천억 세금 추징");
							break;
						case 7:
							percent_ran_array2 = new double[] {20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20};
							System.out.println("[고조선일보] 세이버, 보안 사업 수주");
							break;
						case 8:
							percent_ran_array1 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5,-5};
							percent_ran_array4 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5,-5};
							System.out.println("[고조선일보] 유가 급등...원자재비용 상승");
							break;
						case 9:
							percent_ran_array0 = new double[] {-30,-30,-30,-30,-30,-30,-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10};
							percent_ran_array1 = new double[] {-30,-30,-30,-30,-30,-30,-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10};
							percent_ran_array4 = new double[] {-30,-30,-30,-30,-30,-30,-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10};
							percent_ran_array6 = new double[] {-30,-30,-30,-30,-30,-30,-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10};
							System.out.println("[고조선일보] 북, ICBM 개발 완료 주장");
							break;
						case 10:
							percent_ran_array1 = new double[] {5,5,5,5,5,10,10,10,10,10,10,10,10,10,10,25,25,25,25,25};
							percent_ran_array4 = new double[] {5,5,5,5,5,10,10,10,10,10,10,10,10,10,10,25,25,25,25,25};
							percent_ran_array6 = new double[] {5,5,5,5,5,10,10,10,10,10,10,10,10,10,10,25,25,25,25,25};
							System.out.println("[고조선일보] 갈수록 심해지는 미세먼지... 환경부 문책 이어져");
							break;
						case 11:
							percent_ran_array6 = new double[] {-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-10,-10,-5,-5,-5};
							System.out.println("[고조선일보] 전자담배 세금 인상에 논란... '얼마까지 오를까'");
							break;
						case 12:
							percent_ran_array3 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5,-5};
							percent_ran_array6 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5,-5};
							System.out.println("[고조선일보] 지갑 열지 않는 소비자들");
							break;
						case 13:
							percent_ran_array0 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							System.out.println("[고조선일보] 코스피 사상 최고가 경신");
							break;
						case 14:
							percent_ran_array1 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-5,5,20,20,20,20,25,25,25,25,25};
							percent_ran_array2 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-5,5,20,20,20,20,25,25,25,25,25};
							percent_ran_array6 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-5,5,20,20,20,20,25,25,25,25,25};
							System.out.println("[고조선일보] 한중FTA, 재협상 기간 다가와");
							break;
						case 15:
							percent_ran_array0 = new double[] {-15,-15,-15,-10,-10,-10,-10,-5,-5,-5,5,5,5,10,10,10,10,15,15,15};
							percent_ran_array1 = new double[] {-15,-15,-15,-10,-10,-10,-10,-5,-5,-5,5,5,5,10,10,10,10,15,15,15};
							percent_ran_array3 = new double[] {-15,-15,-15,-10,-10,-10,-10,-5,-5,-5,5,5,5,10,10,10,10,15,15,15};
							percent_ran_array6 = new double[] {-15,-15,-15,-10,-10,-10,-10,-5,-5,-5,5,5,5,10,10,10,10,15,15,15};

							System.out.println("[고조선일보] 북, 60일째 도발 중지... 추가도발은 시간문제");
							break;
						case 16:
							percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
							percent_ran_array2 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
							percent_ran_array5 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
							System.out.println("[고조선일보] 대한민국, 4차산업 후발주자...그러나");
							break;
						case 17:
							percent_ran_array0 = new double[] {-30,-30,-30,-30,-30,-30,-20,-20,-20,-20,20,20,20,20,30,30,30,30,30,30};
							System.out.println("[고조선일보] 헬로닉스, 반도체 공장 증설");
							break;
						case 18:
							percent_ran_array1 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-5,5,20,20,20,20,25,25,25,25,25};
							System.out.println("[고조선일보] BUY.L의 감기약 B 특허 만료");
							break;
						case 19:
							percent_ran_array5 = new double[] {-10,-10,-10,-10,-10,-10,-5,-5,-5,-5,5,5,5,5,10,10,10,10,10,10};
							System.out.println("[고조선일보] 배우 A양, Korean Telecom 둘째아들과 열애설");
							break;
						case 20:
							percent_ran_array5 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
							System.out.println("[고조선일보] Korean Telecom, South Korean Telecom 인수 시작");
							break;
						case 21:
							System.out.println("[고조선일보] 전년대비 배추값 하락, 산지 200원에 거래 ");
							break;
						case 22:
							System.out.println("[고조선일보] 헬로닉스 광고모델 김구례, 임신 4개월째");
							break;
						case 23:
							percent_ran_array0 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array1 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array2 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array3 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array4 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array5 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array6 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							System.out.println("[한강을 사랑하는 모임] 정부 증권거래세 개정 검토할듯");
							break;
						case 24:
							percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array1 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array2 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array3 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array4 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array5 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array6 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							System.out.println("[한강을 사랑하는 모임] 내년 GDP 성장률 감소 확실한가요");
							break;
						case 25:
							percent_ran_array0 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array1 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array2 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array3 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array4 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array5 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array6 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							System.out.println("[한강을 사랑하는 모임] 정부가 가상화폐 과세한다네요");
							break;
						case 26:
							percent_ran_array0 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array1 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array2 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array3 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array4 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array5 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array6 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							System.out.println("[한강을 사랑하는 모임] 코스피 외인, 기관 대량 매수한다는 소문입니다.");
							break;
						case 27:
							switch (ran_report_number) {
							case 0:
								percent_ran_array0 = new double[] {-30,-30,-30,-25,-25,-20,-20,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10};
								break;
							case 1:
								percent_ran_array0 = new double[] {-30,-30,-30,-25,-25,-20,-20,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10};
								break;
							case 2:
								percent_ran_array0 = new double[] {-30,-30,-30,-25,-25,-20,-20,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10};
								break;
							case 3:
								percent_ran_array0 = new double[] {-30,-30,-30,-25,-25,-20,-20,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10};
								break;
							case 4:
								percent_ran_array0 = new double[] {-30,-30,-30,-25,-25,-20,-20,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10};
								break;
							case 5:
								percent_ran_array0 = new double[] {-30,-30,-30,-25,-25,-20,-20,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10};
								break;
							case 6:
								percent_ran_array0 = new double[] {-30,-30,-30,-25,-25,-20,-20,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10};
								break;
							}
							System.out.println("[kd증권] 대장주 : 분기 실적 리포트");
							break;
						case 28:
							switch(ran_report_number) {
							case 0:
								percent_ran_array0 = new double[] {10,10,15,15,15,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30};
								break;
							case 1:
								percent_ran_array0 = new double[] {10,10,15,15,15,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30};
								break;
							case 2:
								percent_ran_array0 = new double[] {10,10,15,15,15,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30};
								break;
							case 3:
								percent_ran_array0 = new double[] {10,10,15,15,15,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30};
								break;
							case 4:
								percent_ran_array0 = new double[] {10,10,15,15,15,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30};
								break;
							case 5:
								percent_ran_array0 = new double[] {10,10,15,15,15,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30};
								break;
							case 6:
								percent_ran_array0 = new double[] {10,10,15,15,15,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30};
								break;
							}
							System.out.println("[kd증권] 4차산업 수혜주 목록");
							break;
						case 29:
							switch (ran_report_number) {
							case 0:
								percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
								break;
							case 1:
								percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
								break;
							case 2:
								percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
								break;
							case 3:
								percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
								break;
							case 4:
								percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
								break;
							case 5:
								percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
								break;
							case 6:
								percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
								break;
							}
							System.out.println("[kd증권] 이달의 테마주 종합");
							break;
						case 30:
							switch (ran_report_number) {
							case 0:
								percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
								break;
							case 1:
								percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
								break;
							case 2:
								percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
								break;
							case 3:
								percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
								break;
							case 4:
								percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
								break;
							case 5:
								percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
								break;
							case 6:
								percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
								break;	
							}
							System.out.println("[kd증권] 업종간 분석 리포트");
							break;
							
					} // switch end
					System.out.println("----------------------------------------------------------------------------");		
				} //if end
				System.out.println("\t");
				System.out.println("* 주식을 구매하시려면 'buy', 판매하시려면 'sell', 턴을 넘기시려면 'skip'을 입력하세요* ");
				Scanner scan = new Scanner(System.in);
				String tran = scan.next();
				
				if(tran.equals("buy")) { /* 주식 구매 반복문 */
					System.out.println("\t");
					System.out.println("* 구매하실 종목의 코드와 주식 수를 입력하세요 *");
					System.out.println("* ㅣ헬로닉스 aㅣㅣ셀트리오 bㅣㅣ세이버 cㅣㅣkd금융 dㅣㅣ아산건설 eㅣㅣKorean Telecom fㅣㅣ뉴월드 gㅣ *");
					System.out.println("* 헬로닉스 15주를 구매하는 예 : a 15 *");
					Scanner scan2 = new Scanner(System.in);
					String company = scan.next();
					int tran_number = scan.nextInt();
					if(company.equals("a")) {
						account = minus_account(account, company_0, tran_number); // 현금 감소
						stock_0 = plus_stock(stock_0, tran_number); // 보유주식 증가
					}
					if(company.equals("b")) {
						account = minus_account(account, company_1, tran_number); // 현금 감소
						stock_1 = plus_stock(stock_1, tran_number); // 보유주식 증가
					}
					if(company.equals("c")) {
						account = minus_account(account, company_2, tran_number); // 현금 감소
						stock_2 = plus_stock(stock_2, tran_number); // 보유주식 증가
					}
					if(company.equals("d")) {
						account = minus_account(account, company_3, tran_number); // 현금 감소
						stock_3 = plus_stock(stock_3, tran_number); // 보유주식 증가
					}
					if(company.equals("e")) {
						account = minus_account(account, company_4, tran_number); // 현금 감소
						stock_4 = plus_stock(stock_4, tran_number); // 보유주식 증가
					}
					if(company.equals("f")) {
						account = minus_account(account, company_5, tran_number); // 현금 감소
						stock_5 = plus_stock(stock_5, tran_number); // 보유주식 증가
					}
					if(company.equals("g")) {
						account = minus_account(account, company_6, tran_number); // 현금 감소
						stock_6 = plus_stock(stock_6, tran_number); // 보유주식 증가
					}
				}
		
				if(tran.equals("sell")) { /* 주식 판매 반복문 */
					System.out.println("\t");
					System.out.println("* 판매하실 종목의 코드와 주식 수를 입력하세요 *");
					System.out.println("* ㅣ헬로닉스 aㅣㅣ셀트리오 bㅣㅣ세이버 cㅣㅣkd금융 dㅣㅣ아산건설 eㅣㅣKorean Telecom fㅣㅣ뉴월드 gㅣ *");
					System.out.println("* 헬로닉스 15주를 판매하는 예 : a 15 *");
					Scanner scan2 = new Scanner(System.in);
					String company = scan.next();
					int tran_number = scan.nextInt();
					if(company.equals("a")) {
						account = plus_account(account, company_0, tran_number); // 현금 증가
						stock_0 = minus_stock(stock_0, tran_number); // 보유주식 감소
					}
					if(company.equals("b")) {
						account = plus_account(account, company_1, tran_number); // 현금 증가
						stock_1 = minus_stock(stock_1, tran_number); // 보유주식 감소
					}
					if(company.equals("c")) {
						account = plus_account(account, company_2, tran_number); // 현금 증가
						stock_2 = minus_stock(stock_2, tran_number); // 보유주식 감소
					}
					if(company.equals("d")) {
						account = plus_account(account, company_3, tran_number); // 현금 증가
						stock_3 = minus_stock(stock_3, tran_number); // 보유주식 감소
					}
					if(company.equals("e")) {
						account = plus_account(account, company_4, tran_number); // 현금 증가
						stock_4 = minus_stock(stock_4, tran_number); // 보유주식 감소
					}
					if(company.equals("f")) {
						account = plus_account(account, company_5, tran_number); // 현금 증가
						stock_5 = minus_stock(stock_5, tran_number); // 보유주식 감소
					}
					if(company.equals("g")) {
						account = plus_account(account, company_6, tran_number); // 현금 증가
						stock_6 = minus_stock(stock_6, tran_number); // 보유주식 감소
					}
				}
				if(tran.equals("skip")) {
					tf2 = false;
				}
			} // tf2 end
			
			// --------- 턴마다 변동된 주식 저장 값 ---------
			company_0 = save_company(company_0, percent_ran_array0, ran_array_number); 
			company_1 = save_company(company_1, percent_ran_array1, ran_array_number); 
			company_2 = save_company(company_2, percent_ran_array2, ran_array_number); 
			company_3 = save_company(company_3, percent_ran_array3, ran_array_number);
			company_4 = save_company(company_4, percent_ran_array4, ran_array_number);
			company_5 = save_company(company_5, percent_ran_array5, ran_array_number); 
			company_6 = save_company(company_6, percent_ran_array6, ran_array_number); 
			// -------- 주식 증감률을 출력용에 할당 --------
			print_percent_ran_array0 = percent_ran_array0;       
			print_percent_ran_array1 = percent_ran_array1;  
			print_percent_ran_array2 = percent_ran_array2;  
			print_percent_ran_array3 = percent_ran_array3;  
			print_percent_ran_array4 = percent_ran_array4;  
			print_percent_ran_array5 = percent_ran_array5;  
			print_percent_ran_array6 = percent_ran_array6;  
			print_ran_array_number = ran_array_number;
			tf = ending(sum, account); // 엔딩 조건, 턴 넘기기 전까지는 엔딩조건에 만족해도 안끝남
			turn++; // 턴 증가
			//System.out.println("tf test");
		}	// tf END

	} // MAIN END

} // CLASS END