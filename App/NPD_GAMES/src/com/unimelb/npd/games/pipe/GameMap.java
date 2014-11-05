package com.unimelb.npd.games.pipe;		
public class GameMap{
	static int [][][] map = {
			{	//level1
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 399, 0, 0, 0, 0 }, 
				{ 0, 398, 0, 0, 0, 0 },
				{ 0, 397, 0, 0, 0, 0 }, 
				{ 0, 396, 0, 0, 0, 0 }, 
				{ 0, 295, 294, 293, 292, 991 },
				{ 0, 0, 0, 0, 0, 0 }
			},
			{	//level2
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 294, 293, 392, 0 },
				{ 0, 0, 195, 0, 291, 989 }, 
				{ 0, 0, 196, 0, 0, 0 }, 
				{ 299, 298, 197, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }
			},
			{	//level3
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 985, 0, 0 }, 
				{ 299, 398, 0, 186, 487, 488 },
				{ 0, 397, 0, 0, 0, 189 }, 
				{ 0, 296, 395, 0, 291, 190 }, 
				{ 0, 0, 294, 293, 192, 0 },
				{ 0, 0, 0, 0, 0, 0 }
			}
	};
	public static int[][] getMap(int level){
		return map[level-1];
	}
	public static int getRows(int level){
		return map[level-1].length;
	}
	public static int getCols(int level){
		return map[level-1][0].length;
	}
}