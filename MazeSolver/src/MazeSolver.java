import java.util.LinkedList;

public class MazeSolver {
	
	static int[][] maze = {
			{1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
			{0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0},
			{0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0},
			{1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
			{1, 2, 1, 1, 1, 1, 0, 0, 0, 1, 0},
			{0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0}
	};
	//0 = wall 
	//1 = path
	//2 = destination
	
	static LinkedList<Position> path = new LinkedList<Position>();

	public static void main(String[] args) {
		
		if(solveMaze(new Position(4, 9))) {
			System.out.println("You won!!!");
			return;
		}
		System.out.println("No path");
		return;
	}
	private static boolean solveMaze(Position p) {
		path.push(p);
        
		while(true) {
			int y = path.peek().y;
			int x = path.peek().x;
			maze[y][x] = 0;
			
			//down
			if(isValid(y+1, x)) {
				if(maze[y+1][x] == 2) {
					return true;
				}
				else if(maze[y+1][x] == 1) {
					path.push(new Position(y+1, x));
					System.out.println("Moved down");
					continue;
				 }
			}
			//left
			if(isValid(y, x-1)) {
				if(maze[y][x-1] == 2) {
					return true;
				}
				else if (maze[y][x-1] == 1) {
					path.push(new Position(y, x-1));
					System.out.println("Moved left");
					continue;
				}
			}
			//up
			if(isValid(y-1, x)) {
				if(maze[y-1][x] == 2) {
					return true;
				}
				else if (maze[y-1][x] == 1) {
					path.push(new Position(y-1, x));
					continue;
				}
				
			}
			//right
			if(isValid(y, x+1)) {
				if(maze[y][x+1] == 2) {
					return true;
				}
				else if (maze[y][x+1] == 1) {
					path.push(new Position(y, x+1));
					System.out.println("Moved right");
					continue;
				}
			}
			path.pop();
			System.out.println("Moved back");
			if(path.size() <= 0) {
				return false;
			}
		}
	}
	public static boolean isValid(int y, int x ) {
		if (y < 0 || y >= maze.length || x < 0 || x >= maze[y].length ) {
			return false;
		}	
		return true;
		}

}
