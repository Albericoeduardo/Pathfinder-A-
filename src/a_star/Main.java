package a_star;

public class Main {

	public static void main(String[] args) {
        Maze maze = new Maze(15, 30);
        
        maze.setPosition(2, 2, Node.Symbol.BEGIN);
        maze.setPosition(12, 25, Node.Symbol.END);
        maze.fillRow(0, Node.Symbol.WALL);
        maze.fillRow(14, Node.Symbol.WALL);
        maze.fillColumn(0, Node.Symbol.WALL);
        maze.fillColumn(29, Node.Symbol.WALL);
        maze.fillColumn(15, Node.Symbol.WALL);
        maze.setNode(1,15, Node.Symbol.FREE);
        maze.fillColumn(22, Node.Symbol.WALL);
        maze.setNode(1,22, Node.Symbol.FREE);
        maze.fillColumn(4, Node.Symbol.WALL);
        maze.setNode(7,4, Node.Symbol.FREE);
        
        AStar aStar = new AStar(maze.getGrid());
        aStar.findPath();

        maze.print();
    }

}
