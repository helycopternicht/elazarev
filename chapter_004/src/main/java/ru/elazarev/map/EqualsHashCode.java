package ru.elazarev.map;

import java.util.Arrays;

/**
 * Class to equals and hash code example.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 13.06.17
 */
public class EqualsHashCode {

    /**
     * boolean.
     */
    private boolean field;
    /**
     * byte.
     */
    private byte byteField;
    /**
     * short.
     */
    private short shortField;
    /**
     * char.
     */
    private char charField;
    /**
     * integer.
     */
    private int intField;
    /**
     * long.
     */
    private long longField;
    /**
     * float.
     */
    private float floatField;
    /**
     * double.
     */
    private double doubleField;
    /**
     * array.
     */
    private int[] arrField;
    /**
     * Reference.
     */
    private Object refField;

    /**
     * Equals example.
     * @param o - object to test.
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EqualsHashCode that = (EqualsHashCode) o;
        if (field != that.field) {
            return false;
        }

        if (byteField != that.byteField) {
            return false;
        }
        if (shortField != that.shortField) {
            return false;
        }
        if (charField != that.charField) {
            return false;
        }
        if (intField != that.intField) {
            return false;
        }
        if (longField != that.longField) {
            return false;
        }
        if (Float.compare(that.floatField, floatField) != 0) {
            return false;
        }
        if (Double.compare(that.doubleField, doubleField) != 0) {
            return false;
        }
        if (!Arrays.equals(arrField, that.arrField)) {
            return false;
        }
        return refField != null ? refField.equals(that.refField) : that.refField == null;
    }

    /**
     * hashCode example.
     * @return int
     */
    @Override
    public int hashCode() {
        int result = 17;
        long temp;
        result = 31 * result + (field ? 1 : 0);
        result = 31 * result + (int) byteField;
        result = 31 * result + (int) shortField;
        result = 31 * result + (int) charField;
        result = 31 * result + intField;
        result = 31 * result + (int) (longField ^ (longField >>> 32));
        result = 31 * result + (floatField != +0.0f ? Float.floatToIntBits(floatField) : 0);
        temp = Double.doubleToLongBits(doubleField);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Arrays.hashCode(arrField);
        result = 31 * result + (refField != null ? refField.hashCode() : 0);
        return result;
    }
}
