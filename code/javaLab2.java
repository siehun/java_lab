
import java.util.*;
public class Main {
 
	public static int player = 0;
	public static int[][] life = new int[2][8];
	public static int[][] hurt = new int[2][8];
	public static int result = 0;
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		String astr = in.nextLine();//消除回车的影响
		String[] commends = new String[num];
		for(int i = 0;i<num;i++){
			commends[i] = in.nextLine();
		}
		
		for(int i = 0;i<8;i++){
			life[0][i] = 0;
			life[1][i] = 0;
			hurt[0][i] = 0;
			hurt[1][i] = 0;
		}		
		life[0][0] = 30;
		life[1][0] = 30;
		
		for(int i = 0;i<num;i++){
			action(commends[i]);
			//System.out.println("第"+i+"条指令执行后的结果为：");
			//printSituation();
			if(result!=0){
				printResult();
				System.exit(0);
			}
			
		}
		//printSituation();
		printResult();
		
	}
	
	public static void action(String str){
		//System.out.println("当前处理的指令："+str);
		
		if(str.equals("end")){
			player = 1-player;
			return;
		}
		else if(str.length()>3){
		
			if(str.substring(0, 6).equals("summon")){
				int position = (int)str.charAt(7)-48;
				int attack = 0;
				int health = 0;
				int index = 9;
				while(index<str.length() && (int)str.charAt(index)-48<10 && (int)str.charAt(index)-48>=0){
					attack = attack*10+(int)str.charAt(index)-48;
					index++;
				}
				index++;
				while(index<str.length() && (int)str.charAt(index)-48<10 && (int)str.charAt(index)-48>=0){
					health = health*10+(int)str.charAt(index)-48;
					index++;
				}
				
				if(life[player][position]<=0){
					int now = position;
					while(life[player][now]<=0 && now>0){
						now--;
					}
					life[player][now+1] = health;
					hurt[player][now+1] = attack;
				}
				else{
					int last = position;
					while(life[player][last]>0){
						last++;
					}
					for(int i = last-1;i>=position;i--){
						life[player][i+1] = life[player][i];
						hurt[player][i+1] = hurt[player][i];
					}
					life[player][position] = health;
					hurt[player][position] = attack;
				}
				return;
			}
			else if(str.substring(0, 6).equals("attack")){
				int attacker = (int)str.charAt(7)-48;
				int defender = (int)str.charAt(9)-48;
				//System.out.println(player+"号玩家的"+attacker+"攻击"+defender);
				life[player][attacker] -= hurt[1-player][defender];
				life[1-player][defender] -= hurt[player][attacker];
				//printSituation();
				if(gameover()){
					return;
				}
				if(life[player][attacker]<=0 && attacker<7){
					if(life[player][attacker+1]>0){
						int now = attacker+1;
						while(now<8 && life[player][now]>0){
							life[player][now-1] = life[player][now];
							hurt[player][now-1] = hurt[player][now];
							now++;
						}
						life[player][now-1] = 0;
						hurt[player][now-1] = 0;
					}
				}
				if(life[player][attacker]<=0 && attacker<7){
					life[player][attacker] = 0;
					hurt[player][attacker] = 0;
				}
				if(life[1-player][defender]<=0 && defender<7){
					if(life[1-player][defender+1]>0){
						int now = defender+1;
						while(now<8 && life[1-player][now]>0){
							life[1-player][now-1] = life[1-player][now];
							hurt[1-player][now-1] = hurt[1-player][now];
							now++;
						}
						life[1-player][now-1] = 0;
						hurt[1-player][now-1] = 0;
					}
				}				
				
			}
			return;
		}
	}
	
	public static boolean gameover(){
		if(life[0][0]<=0){
			result = -1;
			return true;
		}else if(life[1][0]<=0){
			result = 1;
			return true;
		}
		return false;
	}
	
	public static void printResult(){		
		
		System.out.println(result);
		System.out.println(life[0][0]);
		int num = 0;
		for(int i = 1;i<8;i++){
			if(life[0][i]<=0)
				break;
			num++;
		}
		System.out.print(num);
		for(int i = 1;i<=num;i++){
			System.out.print(" "+life[0][i]);
		}
		System.out.println();
		
		System.out.println(life[1][0]);
		num = 0;
		for(int i = 1;i<8;i++){
			if(life[1][i]<=0)
				break;
			num++;
		}
		System.out.print(num);
		for(int i = 1;i<=num;i++){
			System.out.print(" "+life[1][i]);
		}
		System.out.println();
	}
	
	/**
	 * 打印当前的情况
	 */
	public static void printSituation(){
		System.out.println("============================");
		System.out.println("--------------");
		System.out.println("先手生命值：");
		for(int i = 0;i<8;i++){
			System.out.print(life[0][i]+" ");
		}
		System.out.println();
		System.out.println("先手攻击值：");
		for(int i = 0;i<8;i++){
			System.out.print(hurt[0][i]+" ");
		}
		System.out.println();
		System.out.println("后手生命值：");
		for(int i = 0;i<8;i++){
			System.out.print(life[1][i]+" ");
		}
		System.out.println();
		System.out.println("后手攻击值：");
		for(int i = 0;i<8;i++){
			System.out.print(hurt[1][i]+" ");
		}
		System.out.println();
		System.out.println("--------------");
		System.out.println("============================");
	}
 
}