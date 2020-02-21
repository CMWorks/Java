package game.gfx;

import javax.swing.JPanel;

public class MapMaker extends JPanel{
	private final int randomCon = 100;
	private int mapSize;
	private int count = 0;
	private int x;
	private int y;
	private int choice;
	private char[][] dataChar; //0;g=grass, 1;r=river, 2;s=sand;
	public MapMaker(int x, int y, char[][] dataChar, int mapSize){
		this.x = x;
		this.y = y;
		this.dataChar = dataChar;
		this.mapSize = mapSize;
		calc();
	}
	private void calc(){
		if(y==0){
			if(x==0){
				count=(int)(Math.random()*3);
				if(count==0){
					dataChar[y][x]='g';
				}
				else if(count==1){
					dataChar[y][x]='r';
				}
				else{
					dataChar[y][x]='s';
				}	
			}//x==0
			else{
				if(dataChar[y][x-1]=='g'){
					choice = (int)(Math.random()*randomCon);
					if(choice<randomCon-1){
						count=0;
						dataChar[y][x]='g';
					}else{
						count=(int)(Math.random()*3);
						if(count==0){
							dataChar[y][x]='g';
						}
						else if(count==1){
							dataChar[y][x]='r';
						}
						else{
							dataChar[y][x]='s';
						}
					}					
				}
				else if(dataChar[y][x-1]=='r'){
					choice = (int)(Math.random()*randomCon);
					if(choice<randomCon-1){
						count=1;
						dataChar[y][x]='r';
					}else{
						count=(int)(Math.random()*3);
						if(count==0){
							dataChar[y][x]='g';
						}
						else if(count==1){
							dataChar[y][x]='r';
						}
						else{
							dataChar[y][x]='s';
						}
					}					
				}
				else{
					choice = (int)(Math.random()*randomCon);
					if(choice<randomCon-1){
						count=2;
						dataChar[y][x]='s';
					}else{
						count=(int)(Math.random()*3);
						if(count==0){
							dataChar[y][x]='g';
						}
						else if(count==1){
							dataChar[y][x]='r';
						}
						else{
							dataChar[y][x]='s';
						}//else
					}//else
				}//x-1=='s'
			}//x>0
		}//y==0
		else{
			if(x==0){
				choice = (int)(Math.random()*2);
				if(choice==0){
					if(dataChar[y-1][x]=='g'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=0;
							dataChar[y][x]='g';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is grass
					else if(dataChar[y-1][x]=='r'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=1;
							dataChar[y][x]='r';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is road
					else{
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=2;
							dataChar[y][x]='s';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is stone
				}//box above
				else{
					if(dataChar[y-1][x+1]=='g'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=0;
							dataChar[y][x]='g';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is grass
					else if(dataChar[y-1][x+1]=='r'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=1;
							dataChar[y][x]='r';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is road
					else{
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=2;
							dataChar[y][x]='s';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is stone
				}//box top right
			}//x==0
			else{
				choice = (int)(Math.random()*5);
				if(choice==0){
					if(dataChar[y-1][x]=='g'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=0;
							dataChar[y][x]='g';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is grass
					else if(dataChar[y-1][x]=='r'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=1;
							dataChar[y][x]='r';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is road
					else{
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=2;
							dataChar[y][x]='s';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box above is stone
				}//choosing the top box
				else if(choice==1){
					if(dataChar[y][x-1]=='g'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=0;
							dataChar[y][x]='g';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box left is grass
					else if(dataChar[y][x-1]=='r'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=1;
							dataChar[y][x]='r';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box left is road
					else{
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=2;
							dataChar[y][x]='s';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box left is stone
				}//choosing the left box
				else if(choice==2){
					if(dataChar[y-1][x-1]=='g'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=0;
							dataChar[y][x]='g';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box top left is grass
					else if(dataChar[y-1][x-1]=='r'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=1;
							dataChar[y][x]='r';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box top left is road
					else{
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=2;
							dataChar[y][x]='s';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box top left is stone
				}//choosing the top left box
				else if(x<mapSize-1){
					if(dataChar[y-1][x+1]=='g'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=0;
							dataChar[y][x]='g';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box top left is grass
					else if(dataChar[y-1][x+1]=='r'){
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=1;
							dataChar[y][x]='r';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box top left is road
					else{
						choice = (int)(Math.random()*randomCon);
						if(choice<randomCon-1){
							count=2;
							dataChar[y][x]='s';
						}else{
							count=(int)(Math.random()*3);
							if(count==0){
								dataChar[y][x]='g';
							}
							else if(count==1){
								dataChar[y][x]='r';
							}
							else{
								dataChar[y][x]='s';
							}//else
						}//else
					}//box top left is stone
				}//choosing the top right box
			}//x>0
		}//y>0
	}//calc
	public int getCount(){
	return count;
	}
	public char getChar(int x, int y){
	return dataChar[y][x];
	}
}
