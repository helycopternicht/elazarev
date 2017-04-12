package ru.elazarev;

/**
 * CLass represent Square shape.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 12.04.17
 */
public class Square implements Shape {

    /**
     * Returns string representation of square.
     * @return string square
     */
    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        sb.append(" -----" + System.getProperty("line.separator"));
        sb.append("|     |" + System.getProperty("line.separator"));
        sb.append("|     |" + System.getProperty("line.separator"));
        sb.append(" -----");
        return sb.toString();
    }
}
