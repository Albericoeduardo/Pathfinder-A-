package a_star;

import java.util.*;

public class AStar {

    private Node[][] grid;
    private Node startNode;
    private Node endNode;
    private int rows;
    private int cols;

    private Map<Node, Double> gScore;
    private Map<Node, Double> fScore;

    public AStar(Node[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.startNode = findNode(Node.Symbol.BEGIN);
        this.endNode = findNode(Node.Symbol.END);

        // Initialize gScore and fScore maps
        this.gScore = new HashMap<>();
        this.fScore = new HashMap<>();
    }

    private Node findNode(Node.Symbol symbol) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].getSymbol() == symbol) {
                    return grid[i][j]; // Return existing Node instance
                }
            }
        }
        return null; // If not found
    }

    private double heuristic(Node node) {
        return Math.abs(node.getRow() - endNode.getRow()) + Math.abs(node.getCol() - endNode.getCol());
    }

    public void findPath() {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(node -> fScore.getOrDefault(node, Double.MAX_VALUE)));
        Set<Node> closedSet = new HashSet<>();
        Map<Node, Node> cameFrom = new HashMap<>();

        // Initialize gScore and fScore
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Node node = grid[i][j];
                gScore.put(node, Double.MAX_VALUE);
                fScore.put(node, Double.MAX_VALUE);
            }
        }

        // Set starting node values
        gScore.put(startNode, 0.0);
        fScore.put(startNode, heuristic(startNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(endNode)) {
                reconstructPath(cameFrom);
                return;
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) continue;

                double tentativeGScore = gScore.get(current) + neighbor.getCost();
                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        System.out.println("Path not found.");
    }

    private void reconstructPath(Map<Node, Node> cameFrom) {
        Node current = endNode;
        while (current != null && !current.equals(startNode)) {
            grid[current.getRow()][current.getCol()].setSymbol(Node.Symbol.PATH);
            current = cameFrom.get(current);
        }
        if (current != null) {
            grid[current.getRow()][current.getCol()].setSymbol(Node.Symbol.PATH);
        }
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int nodeRow = node.getRow();
        int nodeCol = node.getCol();

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newRow = nodeRow + dir[0];
            int newCol = nodeCol + dir[1];

            if (isValidPosition(newRow, newCol) && grid[newRow][newCol].getSymbol() != Node.Symbol.WALL) {
                neighbors.add(grid[newRow][newCol]);
            }
        }
        return neighbors;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
