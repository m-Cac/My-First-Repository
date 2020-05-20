import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MazeSolver {
	//0 = wall 
	//1 = path
	//2 = destination
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Maze> mazes = readMazes();

		int i = 0;
		 while(i < mazes.size()) {
			if(solveMaze(mazes.get(i))) {
				System.out.println("You won!!!");
			 } else {
				System.out.println("No path");
			}
			i++;
		}
		
	}
	
	
	private static ArrayList<Maze> readMazes() throws FileNotFoundException {
		ArrayList<Maze> mazes = new ArrayList<Maze>();
		Scanner in = new Scanner(new File("mazes.txt"));
		while(in.hasNext()) {
			
			Maze m = new Maze();
			int rows = Integer.parseInt(in.nextLine());
			m.maze = new int[rows][];
			for(int i =0; i < rows; i++) {
				String line = in.nextLine();
				m.maze[i] = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
			}

			m.start = new Position(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));
			in.nextLine();
			mazes.add(m);
		}
		
		in.close();
		return mazes;
	}


	private static boolean solveMaze(Maze m) {
		Position p = m.start;
		m.path.push(p);
        
		while(true) {
			int y = m.path.peek().y;
			int x = m.path.peek().x;
			m.maze[y][x] = 0;
			
			//down
			if(isValid(y+1, x, m)) {
				if(m.maze[y+1][x] == 2) {
					return true;
				}
				else if(m.maze[y+1][x] == 1) {
					m.path.push(new Position(y+1, x));
					System.out.println("Moved down");
					continue;
				 }
			}
			//left
			if(isValid(y, x-1, m)) {
				if(m.maze[y][x-1] == 2) {
					return true;
				}
				else if (m.maze[y][x-1] == 1) {
					m.path.push(new Position(y, x-1));
					System.out.println("Moved left");
					continue;
				}
			}
			//up
			if(isValid(y-1, x, m)) {
				if(m.maze[y-1][x] == 2) {
					return true;
				}
				else if (m.maze[y-1][x] == 1) {
					m.path.push(new Position(y-1, x));
					continue;
				}
				
			}
			//right
			if(isValid(y, x+1, m)) {
				if(m.maze[y][x+1] == 2) {
					return true;
				}
				else if (m.maze[y][x+1] == 1) {
					m.path.push(new Position(y, x+1));
					System.out.println("Moved right");
					continue;
				}
			}
			m.path.pop();
			System.out.println("Moved back");
			if(m.path.size() <= 0) {
				return false;
			}
		}
	}
	public static boolean isValid(int y, int x, Maze m) {
		if (y < 0 || y >= m.maze.length || x < 0 || x >= m.maze[y].length ) {
			return false;
		}	
		return true;
		}

}
