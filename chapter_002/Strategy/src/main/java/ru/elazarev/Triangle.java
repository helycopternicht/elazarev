package ru.elazarev;

/**
 * CLass represent triangle shape.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 12.04.17
 */
public class Triangle implements Shape {
    /**
     * Returns string representation of triangle.
     * @return string square
     */
    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        sb.append("   /\\" + System.getProperty("line.separator"));
        sb.append("  /  \\" + System.getProperty("line.separator"));
        sb.append(" /    \\" + System.getProperty("line.separator"));
        sb.append(" ------");
        return sb.toString();
    }
}
