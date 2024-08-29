package a_star;

import java.util.Objects;

public class Node {
    public enum Symbol {
        FREE(0),
        WALL(100),
        BEGIN(10),
        END(20),
        PATH(1),
        VISITED(2);

        private final double cost;

        Symbol(double cost) {
            this.cost = cost;
        }

        public double getCost() {
            return cost;
        }
    }

    private Symbol symbol;
    private double cost;
    private final int row;
    private final int col;

    public Node(Symbol symbol, int row, int col) {
        this.symbol = symbol;
        this.cost = symbol.getCost();
        this.row = row;
        this.col = col;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
        this.cost = symbol.getCost();
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public double getCost() {
        return cost;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Node{" +
                "symbol=" + symbol +
                ", cost=" + cost +
                ", row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return row == node.row && col == node.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
