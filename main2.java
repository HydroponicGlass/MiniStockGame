
// ---------- ������� ----------
/* 
 * 0. ��δн� : ����, �ʱⰪ 83000, ����ġ �����, ������ ��
 * 1. ��Ʈ���� : ���̿�, �ʱⰪ 218000, ����ġ �����, ������ �ֻ�
 * 2. ���̹� : ����Ʈ����, �ʱⰪ 805000, ����ġ ����, ������ �ֻ�
 * 3. kd���� : ����, �ʱⰪ 56000, ����ġ ����, ������ ��
 * 4. �ƻ�Ǽ� : �Ǽ�, �ʱⰪ 36000, ����ġ ������, ������ ��
 * 5. Korean Telecom : ���, �ʱⰪ 29300, ����ġ ����, ������ ��
 * 6. ������ : ����, �ʱⰪ 259000, ����ġ ������, ������ ��
 */
// ---------------------------



import java.util.Scanner;
import java.util.Random;

public class main2 { 
	// ---------------- �ϸ��� ����Ǵ� �� -----------------
	// ���� �ֽ� ���尪
	public static int save_company(int company, double[] percent_ran_array, int ran_array_number) {
		company += company*percent_ran_array[ran_array_number]/100;
		return company; 
	}
	// ������ ���� ���尪
	public static int plus_account(int account, int company, int tran_number) {
		account +=company*tran_number;
		return account; 
	}
	// ���ҵ� ���� �ֽ� �� ���尪
	public static int minus_stock(int stock_number, int tran_number) {
		stock_number -= tran_number;
		return stock_number;  
	}
	// ���ҵ� ���� ���� ��
	public static int minus_account(int account, int company, int tran_number) {
		account -=company*tran_number;
		return account; 
	}
	// ������ ���� �ֽ� �� ���� ��
	public static int plus_stock(int stock_number, int tran_number) {
		stock_number += tran_number;
		return stock_number; 
	}
	
	//--------------- ���� ���� ���� ------------------
	public static boolean ending(int a, int b) {
		if(a < 0) {
			System.out.println("GAME OVER");
			System.out.println("���� �򰡱ݾ� : " + a);
			return false;
		}
		if(a >= 10000000) {
			System.out.println("VICTORY");
			System.out.println("���� �򰡱ݾ� : " + a);
			return false;
		}
		if(b < -1000000) {
			System.out.println("GAME OVER");
			System.out.println("�������� : " + b);
			return false;
		}
		return true;
	}
	public static void main(String args[]) {
// ------------- ���� ���� �� -----------	
		int turn_interval=3; // �̺�Ʈ ����
		int strike_interval = 5; // ���� ���� ����
		int sum = 0; // �򰡱ݾ�
		int strike = 10000; // �����ݾ�
		//int strike_sum = 0; // �����ݾ� �ʱⰪ, ������ �Ⱦ�
		int account = 500000; // ����(�ʱ��ں���)
		int company_0 = 83000; // ��δн� �ʱⰪ
		int company_1 = 218000; // ��Ʈ���� �ʱⰪ
		int company_2 = 805000; // ���̹� �ʱⰪ
		int company_3 = 56000; // kd���� �ʱⰪ
		int company_4 = 36000; // �ƻ�Ǽ� �ʱⰪ
		int company_5 = 29300; // Korean Telecom �ʱⰪ
		int company_6 = 259000; // ������ �ʱⰪ
//------------------------------------------
		
		int stock_0, stock_1, stock_2, stock_3, stock_4, stock_5, stock_6; // �����ֽ�, �ʱⰪ 0
		stock_0 = stock_1 =  stock_2 =  stock_3 = stock_4 = stock_5 = stock_6 = 0;
		boolean tf = true; // �ڵ� ��ü �ݺ���
		int turn =1;	 // �� ��
		int k=7; // ȸ�� ��
		int print_ran_array_number =0; // �ֽ� ������ ���� ���� ����(��¿�)
		int company_ran_array[] = new int[k]; // �� ���������
		// ------- ȸ�� ����ġ ���� --------
		double print_percent_ran_array0[] = new double[20]; // ��δн�
		double print_percent_ran_array1[] = new double[20]; // ��Ʈ����
		double print_percent_ran_array2[] = new double[20]; // ���̹�
		double print_percent_ran_array3[] = new double[20]; // kd����
		double print_percent_ran_array4[] = new double[20]; // �ƻ�Ǽ�
		double print_percent_ran_array5[] = new double[20]; // Korean Telecom
		double print_percent_ran_array6[] = new double[20]; // ������
		// -------------------------------------
		System.out.println("***** ������ ****************************************************************************************");
		System.out.println("1. �� �ϸ��� �ֽ��� ��� �� �� �ֽ��ϴ�.");
		System.out.println("2. �ְ��� �ϸ��� �����ϸ�, 3�Ͽ� �ѹ��� �̺�Ʈ�� �ش��ϴ� �ֽ��� ũ�� ������ �� �ֽ��ϴ�.");
		System.out.println("3. �򰡱ݾ��� ���� + �� ���� �����ֽ� �� X �ֽİ�ġ �Դϴ�.");
		System.out.println("4. 50������ �ں������� 1000������ �򰡱ݾ��� �޼��ϸ� �¸��մϴ�.");
		System.out.println("5. �鸸�� �ʰ��� ��(����)�� ����ų�, �򰡱ݾ��� 0�� �̸��� �Ǹ� �й��մϴ�. ");
		System.out.println("6. 5��° �Ͽ� ���� ������ �����ϸ�, ���ҷ��� ���� 5�� ���ƿ� ������ �ι辿 �����մϴ�.");
		System.out.println("*********************************************************************************************************");
		System.out.println("\t");
		System.out.println("* �����Ͻ÷��� start �� �Է��ϼ��� *");

		boolean tf_start = true; // ���� �ݺ���, start �Է� ������ ���� �ݺ�
		while(tf_start) {
			Scanner start = new Scanner(System.in);
			String start_string = start.next();
			if(start_string.equals("start")) {
			tf_start = false;
			}
		}
		while(tf) {
			boolean tf2 = true; // �ŷ� �ݺ���
			Random ran_news = new Random();
			int ran_news_number = ran_news.nextInt(31); // ���� ���� ���� ����
			int ran_report_number = ran_news.nextInt(7); // ���ǻ� ����Ʈ ���� ���� ����
			int ran_array_number = ran_news.nextInt(20); // ȸ�� ����ġ ���� ���� ����
			double percent_ran_array0[], percent_ran_array1[], percent_ran_array2[],percent_ran_array3[], percent_ran_array4[], percent_ran_array5[], percent_ran_array6[];
			// ------------- ȸ�� �⺻ ����ġ(�ֽ� ������) �Ҵ� --------------
			percent_ran_array0 = new double[] {-10, -10, -5, -5, -5, -5, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 10, 10, 10};
			percent_ran_array1 = new double[] {-15, -10, -5, -5, -5, -5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 10, 10, 10, 15};
			percent_ran_array2 = new double[] {-15, -15, -10, -10, -5, -5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 5, 10, 10, 15, 15};
			percent_ran_array3 = new double[] {-5, -5, -5, -5, -5, -5, -5, -5, -5, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5};
			percent_ran_array4 = new double[] {-10, -10, -10, -10, -5, -5, -5, -5, -5, 0, 0, 0, 0, 5, 5, 5, 5, 10, 10, 10};
			percent_ran_array5 = new double[] {-10, -10, -10, -5, -5, -5, -5, -5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 10, 10, 10};
			percent_ran_array6 = new double[] {-10, -10, -10, -5, -5, -5, 5, 5, 5, 0, 0, 0, 0, 0, 5, 5, 5, 10, 10, 10};
			// -------------------------------------------------
			System.out.println("\t");
			// -------  n�ϸ��� ���� ���� ---------
			if(turn%strike_interval == 0) {
				//strike_sum +=strike;
				//account -= strike_sum;
				account -= strike;
				System.out.println("----------------------------------------------------------------------------" );
				System.out.println("���� " + turn +"��, ���ݿ��� " + strike + "�� �����Ǿ����ϴ�." );
				System.out.println("--------------------------------------------------------------------------- ." );
				strike = strike*2;
			}
			//-------------------------------
			System.out.println("\t");
			
			while(tf2) { /* �ŷ� �ݺ��� */
			//	sum = sum_fx(account, stock_0, company_0);
				// �򰡱ݾ�
				sum = account + stock_0*company_0 + stock_1*company_1 + stock_2*company_2 + stock_3*company_3 + stock_4*company_4 + stock_5*company_5 + stock_6*company_6;
				System.out.println(" * ���� ���� / �򰡱ݾ� *");
				System.out.println(" l " + account + " / " + sum + " l ");
				System.out.println(" * ���� �ֽ� * ");
				System.out.print("����δн� " + stock_0 +"�� ��");
				System.out.print("�Ӽ�Ʈ���� " + stock_1 +"�� ��");
				System.out.print("�Ӽ��̹� " + stock_2 +"�� ��");
				System.out.print("��kd���� " + stock_3 +"�� ��");
				System.out.print("�Ӿƻ�Ǽ� " + stock_4 +"�� ��");
				System.out.print("��Korean Telecom " + stock_5 +"�� ��");
				System.out.println("�Ӵ����� " + stock_6 +"�� ��");
				System.out.println(" * ���� ���簡 (1�ִ� ����) * ");
				if(percent_ran_array0[print_ran_array_number] > 0) {
					System.out.print("����δн� " + company_0+"�� " + print_percent_ran_array0[print_ran_array_number] +"% �� ��");
				} else if(print_percent_ran_array0[print_ran_array_number] < 0) {
					System.out.print("����δн� " + company_0+"�� " + print_percent_ran_array0[print_ran_array_number] +"% �� ��");
				} else {
					System.out.print("����δн� " + company_0+"�� " + print_percent_ran_array0[print_ran_array_number] +"% �� ��");
				}
				if(print_percent_ran_array1[print_ran_array_number] > 0) {
					System.out.print("�Ӽ�Ʈ���� " + company_1+"�� " + print_percent_ran_array1[print_ran_array_number] +"% �� ��");
				} else if(print_percent_ran_array1[print_ran_array_number] < 0) {
					System.out.print("�Ӽ�Ʈ���� " + company_1+"�� " + print_percent_ran_array1[print_ran_array_number] +"% �� ��");
				} else {
					System.out.print("�Ӽ�Ʈ���� " + company_1+"�� " + print_percent_ran_array1[print_ran_array_number] +"% �� ��");
				}
				if(print_percent_ran_array2[print_ran_array_number] > 0) {
					System.out.print("�Ӽ��̹� " + company_2+"�� " + print_percent_ran_array2[print_ran_array_number] +"% �� ��");
				} else if(print_percent_ran_array2[print_ran_array_number] < 0) {
					System.out.print("�Ӽ��̹� " + company_2+"�� " + print_percent_ran_array2[print_ran_array_number] +"% �� ��");
				} else {
					System.out.print("�Ӽ��̹� " + company_2+"�� " + print_percent_ran_array2[print_ran_array_number] +"% �� ��");
				}
				if(print_percent_ran_array3[print_ran_array_number] > 0) {
					System.out.println("��kd���� " + company_3+"�� " + print_percent_ran_array3[print_ran_array_number] +"% �� ��");
				} else if(print_percent_ran_array3[print_ran_array_number] < 0) {
					System.out.println("��kd���� " + company_3+"�� " + print_percent_ran_array3[print_ran_array_number] +"% �� ��");
				} else {
					System.out.println("��kd���� " + company_3+"�� " + print_percent_ran_array3[print_ran_array_number] +"% �� ��");
				}
				if(print_percent_ran_array4[print_ran_array_number] > 0) {
					System.out.print("�Ӿƻ�Ǽ� " + company_4+"�� " + print_percent_ran_array4[print_ran_array_number] +"% �� ��");
				} else if(print_percent_ran_array4[print_ran_array_number] < 0) {
					System.out.print("�Ӿƻ�Ǽ� " + company_4+"�� " + print_percent_ran_array4[print_ran_array_number] +"% �� ��");
				} else {
					System.out.print("�Ӿƻ�Ǽ� " + company_4+"�� " + print_percent_ran_array4[print_ran_array_number] +"% �� ��");
				}
				if(print_percent_ran_array5[print_ran_array_number] > 0) {
					System.out.print("��Korean Telecom " + company_5+"�� " + print_percent_ran_array5[print_ran_array_number] +"% �� ��");
				} else if(print_percent_ran_array5[print_ran_array_number] < 0) {
					System.out.print("��Korean Telecom " + company_5+"�� " + print_percent_ran_array5[print_ran_array_number] +"% �� ��");
				} else {
					System.out.print("��Korean Telecom " + company_5+"�� " + print_percent_ran_array5[print_ran_array_number] +"% �� ��");
				}
				if(print_percent_ran_array6[print_ran_array_number] > 0) {
					System.out.println("�Ӵ����� " + company_6+"�� " + print_percent_ran_array6[print_ran_array_number] +"% �� ��");
				} else if(print_percent_ran_array6[print_ran_array_number] < 0) {
					System.out.println("�Ӵ����� " + company_6+"�� " + print_percent_ran_array6[print_ran_array_number] +"% �� ��");
				} else {
					System.out.println("�Ӵ����� " + company_6+"�� " + print_percent_ran_array6[print_ran_array_number] +"% �� ��");
				}
				// -----------------  ������ ���� ȸ�� ����ġ ���� ----------------
				if(turn%turn_interval == 0) {
					System.out.println("----------------------------------------------------------------------------");
					switch (ran_news_number) {
						case 0:
							percent_ran_array0 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							System.out.println("[�������Ϻ�] ��δн�, ���� ���� ȣ��");
							break;
						case 1:
							percent_ran_array6 = new double[] {-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-5};
							System.out.println("[�������Ϻ�] ������, ���������� ���� �巯��");
							break;
						case 2:
							percent_ran_array3 = new double[] {10,10,10,10,10,10,15,15,15,15,15,15,15,15,15,25,25,25,25,25};
							System.out.println("[�������Ϻ�] kd����, ������ �귣�� ���");
							break;
						case 3:
							percent_ran_array4 = new double[] {10,10,10,10,10,10,15,15,15,15,15,15,20,20,20,20,20,20,20,20};
							System.out.println("[�������Ϻ�] ��ü���� 44.5% ��������...5ä �̻� ������ 11���� ����");
							break;
						case 4:
							percent_ran_array4 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							System.out.println("[�������Ϻ�] ����, �Ӵ����� ���� �۳��� 50%���");
							break;
						case 5:
							percent_ran_array1 = new double[] {-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5};
							System.out.println("[�������Ϻ�] �缺���̿�������, �������� �ż�");
							break;
						case 6:
							percent_ran_array2 = new double[] {-30,-30,-30,-25,-25,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5};
							System.out.println("[�������Ϻ�] ���̹�, 5�Ⱓ Ż��...1�� 5õ�� ���� ��¡");
							break;
						case 7:
							percent_ran_array2 = new double[] {20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20};
							System.out.println("[�������Ϻ�] ���̹�, ���� ��� ����");
							break;
						case 8:
							percent_ran_array1 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5,-5};
							percent_ran_array4 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5,-5};
							System.out.println("[�������Ϻ�] ���� �޵�...�������� ���");
							break;
						case 9:
							percent_ran_array0 = new double[] {-30,-30,-30,-30,-30,-30,-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10};
							percent_ran_array1 = new double[] {-30,-30,-30,-30,-30,-30,-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10};
							percent_ran_array4 = new double[] {-30,-30,-30,-30,-30,-30,-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10};
							percent_ran_array6 = new double[] {-30,-30,-30,-30,-30,-30,-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10};
							System.out.println("[�������Ϻ�] ��, ICBM ���� �Ϸ� ����");
							break;
						case 10:
							percent_ran_array1 = new double[] {5,5,5,5,5,10,10,10,10,10,10,10,10,10,10,25,25,25,25,25};
							percent_ran_array4 = new double[] {5,5,5,5,5,10,10,10,10,10,10,10,10,10,10,25,25,25,25,25};
							percent_ran_array6 = new double[] {5,5,5,5,5,10,10,10,10,10,10,10,10,10,10,25,25,25,25,25};
							System.out.println("[�������Ϻ�] ������ �������� �̼�����... ȯ��� ��å �̾���");
							break;
						case 11:
							percent_ran_array6 = new double[] {-15,-15,-15,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-10,-10,-5,-5,-5};
							System.out.println("[�������Ϻ�] ���ڴ�� ���� �λ� ���... '�󸶱��� ������'");
							break;
						case 12:
							percent_ran_array3 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5,-5};
							percent_ran_array6 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-5,-5};
							System.out.println("[�������Ϻ�] ���� ���� �ʴ� �Һ��ڵ�");
							break;
						case 13:
							percent_ran_array0 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							System.out.println("[�������Ϻ�] �ڽ��� ��� �ְ� ���");
							break;
						case 14:
							percent_ran_array1 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-5,5,20,20,20,20,25,25,25,25,25};
							percent_ran_array2 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-5,5,20,20,20,20,25,25,25,25,25};
							percent_ran_array6 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-5,5,20,20,20,20,25,25,25,25,25};
							System.out.println("[�������Ϻ�] ����FTA, ������ �Ⱓ �ٰ���");
							break;
						case 15:
							percent_ran_array0 = new double[] {-15,-15,-15,-10,-10,-10,-10,-5,-5,-5,5,5,5,10,10,10,10,15,15,15};
							percent_ran_array1 = new double[] {-15,-15,-15,-10,-10,-10,-10,-5,-5,-5,5,5,5,10,10,10,10,15,15,15};
							percent_ran_array3 = new double[] {-15,-15,-15,-10,-10,-10,-10,-5,-5,-5,5,5,5,10,10,10,10,15,15,15};
							percent_ran_array6 = new double[] {-15,-15,-15,-10,-10,-10,-10,-5,-5,-5,5,5,5,10,10,10,10,15,15,15};

							System.out.println("[�������Ϻ�] ��, 60��° ���� ����... �߰������� �ð�����");
							break;
						case 16:
							percent_ran_array0 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
							percent_ran_array2 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
							percent_ran_array5 = new double[] {-20,-20,-20,-20,-20,-20,-20,-20,-10,-10,10,10,20,20,20,20,20,20,20,20};
							System.out.println("[�������Ϻ�] ���ѹα�, 4����� �Ĺ�����...�׷���");
							break;
						case 17:
							percent_ran_array0 = new double[] {-30,-30,-30,-30,-30,-30,-20,-20,-20,-20,20,20,20,20,30,30,30,30,30,30};
							System.out.println("[�������Ϻ�] ��δн�, �ݵ�ü ���� ����");
							break;
						case 18:
							percent_ran_array1 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-5,5,20,20,20,20,25,25,25,25,25};
							System.out.println("[�������Ϻ�] BUY.L�� ����� B Ư�� ����");
							break;
						case 19:
							percent_ran_array5 = new double[] {-10,-10,-10,-10,-10,-10,-5,-5,-5,-5,5,5,5,5,10,10,10,10,10,10};
							System.out.println("[�������Ϻ�] ��� A��, Korean Telecom ��°�Ƶ�� ���ּ�");
							break;
						case 20:
							percent_ran_array5 = new double[] {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,25,25,25,25,25,25,25,25,25,25};
							System.out.println("[�������Ϻ�] Korean Telecom, South Korean Telecom �μ� ����");
							break;
						case 21:
							System.out.println("[�������Ϻ�] ������ ���߰� �϶�, ���� 200���� �ŷ� ");
							break;
						case 22:
							System.out.println("[�������Ϻ�] ��δн� ����� �豸��, �ӽ� 4����°");
							break;
						case 23:
							percent_ran_array0 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array1 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array2 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array3 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array4 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array5 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							percent_ran_array6 = new double[] {-20,-20,-20,-20,-15,-15,-15,-15,-15,-15,-10,-10,-10,-10,-10,-10,-5,-5,5,5};
							System.out.println("[�Ѱ��� ����ϴ� ����] ���� ���ǰŷ��� ���� �����ҵ�");
							break;
						case 24:
							percent_ran_array0 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array1 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array2 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array3 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array4 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array5 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							percent_ran_array6 = new double[] {-25,-25,-25,-25,-25,-20,-20,-20,-20,-15,-15,-15,-10,-10,-10,-10,-5,5,10,10};
							System.out.println("[�Ѱ��� ����ϴ� ����] ���� GDP ����� ���� Ȯ���Ѱ���");
							break;
						case 25:
							percent_ran_array0 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array1 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array2 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array3 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array4 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array5 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							percent_ran_array6 = new double[] {10,10,15,15,15,20,20,20,20,25,25,25,25,25,30,30,30,30,30,30};
							System.out.println("[�Ѱ��� ����ϴ� ����] ���ΰ� ����ȭ�� �����Ѵٳ׿�");
							break;
						case 26:
							percent_ran_array0 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array1 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array2 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array3 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array4 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array5 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							percent_ran_array6 = new double[] {15,15,15,25,25,25,25,25,30,30,30,30,30,30,30,30,30,30,30,30};
							System.out.println("[�Ѱ��� ����ϴ� ����] �ڽ��� ����, ��� �뷮 �ż��Ѵٴ� �ҹ��Դϴ�.");
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
							System.out.println("[kd����] ������ : �б� ���� ����Ʈ");
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
							System.out.println("[kd����] 4����� ������ ���");
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
							System.out.println("[kd����] �̴��� �׸��� ����");
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
							System.out.println("[kd����] ������ �м� ����Ʈ");
							break;
							
					} // switch end
					System.out.println("----------------------------------------------------------------------------");		
				} //if end
				System.out.println("\t");
				System.out.println("* �ֽ��� �����Ͻ÷��� 'buy', �Ǹ��Ͻ÷��� 'sell', ���� �ѱ�÷��� 'skip'�� �Է��ϼ���* ");
				Scanner scan = new Scanner(System.in);
				String tran = scan.next();
				
				if(tran.equals("buy")) { /* �ֽ� ���� �ݺ��� */
					System.out.println("\t");
					System.out.println("* �����Ͻ� ������ �ڵ�� �ֽ� ���� �Է��ϼ��� *");
					System.out.println("* ����δн� a�ӤӼ�Ʈ���� b�ӤӼ��̹� c�Ӥ�kd���� d�ӤӾƻ�Ǽ� e�Ӥ�Korean Telecom f�ӤӴ����� g�� *");
					System.out.println("* ��δн� 15�ָ� �����ϴ� �� : a 15 *");
					Scanner scan2 = new Scanner(System.in);
					String company = scan.next();
					int tran_number = scan.nextInt();
					if(company.equals("a")) {
						account = minus_account(account, company_0, tran_number); // ���� ����
						stock_0 = plus_stock(stock_0, tran_number); // �����ֽ� ����
					}
					if(company.equals("b")) {
						account = minus_account(account, company_1, tran_number); // ���� ����
						stock_1 = plus_stock(stock_1, tran_number); // �����ֽ� ����
					}
					if(company.equals("c")) {
						account = minus_account(account, company_2, tran_number); // ���� ����
						stock_2 = plus_stock(stock_2, tran_number); // �����ֽ� ����
					}
					if(company.equals("d")) {
						account = minus_account(account, company_3, tran_number); // ���� ����
						stock_3 = plus_stock(stock_3, tran_number); // �����ֽ� ����
					}
					if(company.equals("e")) {
						account = minus_account(account, company_4, tran_number); // ���� ����
						stock_4 = plus_stock(stock_4, tran_number); // �����ֽ� ����
					}
					if(company.equals("f")) {
						account = minus_account(account, company_5, tran_number); // ���� ����
						stock_5 = plus_stock(stock_5, tran_number); // �����ֽ� ����
					}
					if(company.equals("g")) {
						account = minus_account(account, company_6, tran_number); // ���� ����
						stock_6 = plus_stock(stock_6, tran_number); // �����ֽ� ����
					}
				}
		
				if(tran.equals("sell")) { /* �ֽ� �Ǹ� �ݺ��� */
					System.out.println("\t");
					System.out.println("* �Ǹ��Ͻ� ������ �ڵ�� �ֽ� ���� �Է��ϼ��� *");
					System.out.println("* ����δн� a�ӤӼ�Ʈ���� b�ӤӼ��̹� c�Ӥ�kd���� d�ӤӾƻ�Ǽ� e�Ӥ�Korean Telecom f�ӤӴ����� g�� *");
					System.out.println("* ��δн� 15�ָ� �Ǹ��ϴ� �� : a 15 *");
					Scanner scan2 = new Scanner(System.in);
					String company = scan.next();
					int tran_number = scan.nextInt();
					if(company.equals("a")) {
						account = plus_account(account, company_0, tran_number); // ���� ����
						stock_0 = minus_stock(stock_0, tran_number); // �����ֽ� ����
					}
					if(company.equals("b")) {
						account = plus_account(account, company_1, tran_number); // ���� ����
						stock_1 = minus_stock(stock_1, tran_number); // �����ֽ� ����
					}
					if(company.equals("c")) {
						account = plus_account(account, company_2, tran_number); // ���� ����
						stock_2 = minus_stock(stock_2, tran_number); // �����ֽ� ����
					}
					if(company.equals("d")) {
						account = plus_account(account, company_3, tran_number); // ���� ����
						stock_3 = minus_stock(stock_3, tran_number); // �����ֽ� ����
					}
					if(company.equals("e")) {
						account = plus_account(account, company_4, tran_number); // ���� ����
						stock_4 = minus_stock(stock_4, tran_number); // �����ֽ� ����
					}
					if(company.equals("f")) {
						account = plus_account(account, company_5, tran_number); // ���� ����
						stock_5 = minus_stock(stock_5, tran_number); // �����ֽ� ����
					}
					if(company.equals("g")) {
						account = plus_account(account, company_6, tran_number); // ���� ����
						stock_6 = minus_stock(stock_6, tran_number); // �����ֽ� ����
					}
				}
				if(tran.equals("skip")) {
					tf2 = false;
				}
			} // tf2 end
			
			// --------- �ϸ��� ������ �ֽ� ���� �� ---------
			company_0 = save_company(company_0, percent_ran_array0, ran_array_number); 
			company_1 = save_company(company_1, percent_ran_array1, ran_array_number); 
			company_2 = save_company(company_2, percent_ran_array2, ran_array_number); 
			company_3 = save_company(company_3, percent_ran_array3, ran_array_number);
			company_4 = save_company(company_4, percent_ran_array4, ran_array_number);
			company_5 = save_company(company_5, percent_ran_array5, ran_array_number); 
			company_6 = save_company(company_6, percent_ran_array6, ran_array_number); 
			// -------- �ֽ� �������� ��¿뿡 �Ҵ� --------
			print_percent_ran_array0 = percent_ran_array0;       
			print_percent_ran_array1 = percent_ran_array1;  
			print_percent_ran_array2 = percent_ran_array2;  
			print_percent_ran_array3 = percent_ran_array3;  
			print_percent_ran_array4 = percent_ran_array4;  
			print_percent_ran_array5 = percent_ran_array5;  
			print_percent_ran_array6 = percent_ran_array6;  
			print_ran_array_number = ran_array_number;
			tf = ending(sum, account); // ���� ����, �� �ѱ�� �������� �������ǿ� �����ص� �ȳ���
			turn++; // �� ����
			//System.out.println("tf test");
		}	// tf END

	} // MAIN END

} // CLASS END