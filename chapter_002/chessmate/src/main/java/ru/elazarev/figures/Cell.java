package ru.elazarev.figures;

/**
 * Position on chess desk class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.04.17
 */
public class Cell {

    /**
     * Number of row.
     */
    private int row;

    /**
     * Number of column.
     */
    private int column;

    /**
     * Default constructor.
     * @param row - row
     * @param column - column
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Getter for row field.
     * @return row field.
     */
    public int getRow() {
        return row;
    }

    /**
     * Setter for row field.
     * @param row - row value.
     */
    public void setRow(int row) {
        if (isCorrect(row)) {
            this.row = row;
        }
    }

    /**
     * Getter for column field.
     * @return - column value.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Setter for column field.
     * @param column - column value.
     */
    public void setColumn(int column) {
        if (isCorrect(column)) {
            this.column = column;
        }
    }

    /**
     * Method checks that val is in correct range from 1 to 8.
     * @param val - value to test.
     * @return true or false
     */
    private static boolean isCorrect(int val) {
        if (val < 1 || val > 8) {
            throw new IllegalArgumentException(String.format("Number %d is incorrect", val));
        }
        return true;
    }

    /**
     * Returns true if row and column field are the same.
     * @param o - object to test.
     * @return - true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;
        if (getRow() != cell.getRow()) {
            return false;
        }
        return getColumn() == cell.getColumn();
    }

    /**
     * HashCode.
     * @return integer
     */
    @Override
    public int hashCode() {
        int result = getRow();
        result = 31 * result + getColumn();
        return result;
    }
}
