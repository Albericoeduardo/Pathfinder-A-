package a_star;

public class Maze {
    private Node[][] grid;
    private int rows;
    private int cols;

    // Construtor que inicializa a matriz de Nodes
    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Node[rows][cols];

        // Inicializa todos os nodes da matriz como FREE por padrão
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Node(Node.Symbol.FREE, i, j);
            }
        }
    }

    // Método para definir um Node em uma posição específica
    public void setNode(int row, int col, Node.Symbol symbol) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            grid[row][col] = new Node(symbol, row, col);
        } else {
            throw new IndexOutOfBoundsException("Posição fora dos limites do labirinto.");
        }
    }

    // Método para preencher uma linha inteira com um determinado tipo de Node
    public void fillRow(int row, Node.Symbol symbol) {
        if (row >= 0 && row < rows) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Node(symbol, row, col);
            }
        } else {
            throw new IndexOutOfBoundsException("Linha fora dos limites do labirinto.");
        }
    }

    // Método para preencher uma coluna inteira com um determinado tipo de Node
    public void fillColumn(int col, Node.Symbol symbol) {
        if (col >= 0 && col < cols) {
            for (int row = 0; row < rows; row++) {
                grid[row][col] = new Node(symbol, row, col);
            }
        } else {
            throw new IndexOutOfBoundsException("Coluna fora dos limites do labirinto.");
        }
    }

    // Método para preencher uma posição específica com um determinado tipo de Node
    public void setPosition(int row, int col, Node.Symbol symbol) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            grid[row][col] = new Node(symbol, row, col);
        } else {
            throw new IndexOutOfBoundsException("Posição fora dos limites do labirinto.");
        }
    }

    // Método para imprimir o labirinto
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                switch (grid[i][j].getSymbol()) {
                    case WALL:
                        System.out.print("*");
                        break;
                    case FREE:
                    case VISITED:
                        System.out.print(" ");
                        break;
                    case PATH:
                        System.out.print("o");
                        break;
                    case BEGIN:
                        System.out.print("@");
                        break;
                    case END:
                        System.out.print("X");
                        break;
                }
            }
            System.out.println();
        }
    }
    
    public Node[][] getGrid() {
        return grid;
    }
}
