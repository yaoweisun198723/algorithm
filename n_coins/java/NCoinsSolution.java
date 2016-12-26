import java.lang.Math;
import java.lang.Exception;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class NCoinsSolution {
	int[] coins;
	//len
	int len;
	//firstMax[i][j]代表在区间[j,j+i]上先手所得最大值
	//j代表区间起始位置，i代表结束位置相对起始位置的偏移量 //区间总长度为i+1
	int[][] firstMax;
	//firstPickSum，先手玩家最终获得币值总和
	int firstPickSum=-1;
	//allCoinsSum
	int allCoinsSum=-1;
	//pick value
	int pickValue=-1;

	public NCoinsSolution(int[] coins) {
		this.coins = coins;
		this.len = this.coins.length;
		firstMax = new int[len][len];
	}

	public int[] pickAndReturnLeft(int[] coins) {
		rePick(coins);
		int picked = getPickValue();
		int[] leftCoins={};
		if (picked == coins[coins.length-1]) {
			leftCoins = Arrays.copyOfRange(coins, 0, coins.length-1);
		}
		else if (picked == coins[0]) {
			leftCoins = Arrays.copyOfRange(coins, 1, coins.length);
		}
		else {
			System.err.println("Test Error!");
		}
		return leftCoins;
	}

	public void rePick(int[] coins) {
		this.coins = coins;
		this.len = this.coins.length;
		firstMax = new int[len][len];
		doPick();
	}

	public void doPick() {
		int[] sums = new int[len+1];
		sums[0] = 0;
		for(int k=1; k < len+1; k++) {
			sums[k] = sums[k-1] + this.coins[k-1];
		}
		this.allCoinsSum = sums[len];

		//初始化firstMax[0]，即
		for(int i=0; i < len; i++) {
			firstMax[0][i] = this.coins[i]; }

		int valueIfPickHead=0;
		int valueIfPickTail=0;
		int picked=firstMax[0][0];
		for(int i=1; i < len; i++) {
			for(int j=0; j+i < len; j++) {
				valueIfPickHead = this.coins[j] + (sums[j+i+1] - sums[j+1]) - firstMax[i-1][j+1];
				valueIfPickTail = this.coins[j+i] + (sums[j+i] - sums[j]) - firstMax[i-1][j];
				if (valueIfPickHead > valueIfPickTail) {
					firstMax[i][j] = valueIfPickHead;
					picked = this.coins[j];
				} else {
					firstMax[i][j] = valueIfPickTail;
					picked = this.coins[j+i];
				}
			}
		}
		this.firstPickSum = firstMax[len-1][0];
		this.pickValue = picked;
	}

	public boolean firstWillWin() throws Exception {
		if (this.firstPickSum < 0)
			throw new Exception("Please call doPick() first!");
		if (this.firstPickSum * 2 > this.allCoinsSum)
			return true;
		else
			return false;
	}

	public void printSteps() {
		int len = this.coins.length;
		for(int i=0; i < len; i++) {
			for(int j=0; j+i < len; j++) {
				System.out.printf("%s ", firstMax[i][j]);
			}
			System.out.println();
		}
	}

	public int getPickValue() {
		return this.pickValue;
	}

	private static void test1(int[] coins) throws Exception {
		NCoinsSolution solution = new NCoinsSolution(coins);
		solution.doPick();
		System.out.println("First Player will win? " + solution.firstWillWin());
		System.out.println("Picked value: " + solution.getPickValue());
		solution.printSteps();
	}

	private static void test2(int[] coins) {
		NCoinsSolution solution1 = new NCoinsSolution(coins);
		NCoinsSolution solution2 = new NCoinsSolution(coins);
		int[] leftCoins = coins;
		while (leftCoins.length > 0) {
			leftCoins = solution1.pickAndReturnLeft(leftCoins);
			/*
			System.out.println("After solution1 picked:");
			for (int coin:leftCoins)
				System.out.print(coin);
				*/
			System.out.println("Solution1 picked: " + solution1.getPickValue());
			leftCoins = solution2.pickAndReturnLeft(leftCoins);
			System.out.println("Solution2 picked: " + solution2.getPickValue());
		}
	}

	private static void test3(int[] coins) {
		NCoinsSolution solution = new NCoinsSolution(coins);
		int[] leftCoins=coins;
		int round = 1;
		int tmp = -1;
		while (leftCoins.length > 0) {
			System.out.printf("======Round %d==========\n", round++);
			System.out.println("Initial array of coins: "+ Arrays.toString(leftCoins));
			leftCoins = solution.pickAndReturnLeft(leftCoins);
			System.out.println("Solution picked: " + solution.getPickValue());
			System.out.printf("LeftCoins are: %s\n", Arrays.toString(leftCoins));
			while (true) {
				tmp = Integer.parseInt(getStdInput("Please pick either the head or tail coin"));
				if (tmp == leftCoins[0]) {
					leftCoins = Arrays.copyOfRange(leftCoins, 1, leftCoins.length);
					break;
				} else if (tmp == leftCoins[leftCoins.length-1]) {
					leftCoins = Arrays.copyOfRange(leftCoins, 0, leftCoins.length-1);
					break;
				} else {
					System.out.println("You don't pick the head or tail coin.");
					continue;
				}
			}
		}

	}

	private static String getStdInput(String promote) {
		System.out.print(promote + " ");
		BufferedReader lineOfInput = new BufferedReader(new InputStreamReader(System.in));
		try {
			return lineOfInput.readLine();
		} catch (IOException ioe) {
			System.err.println(ioe.toString());
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		int[] coins = {5,1,8,9,4,3,2,7,10,6};
		System.out.println("===========================TEST1======================");
		int[] coins2 = {1};
		test1(coins2);
		System.out.println("===========================TEST2======================");
		test2(coins);
		System.out.println("===========================TEST3======================");
		test3(coins);
	}
}
