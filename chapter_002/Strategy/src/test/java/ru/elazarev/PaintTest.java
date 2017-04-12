package ru.elazarev;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class to test Paint class and Square and Triangle strategy class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 12.04.17
 */
public class PaintTest {

    /**
     * Test for square strategy.
     */
    @Test
    public void squareTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        new Paint().draw(new Square());

        StringBuilder sb = new StringBuilder();
        sb.append(" -----" + System.getProperty("line.separator"));
        sb.append("|     |" + System.getProperty("line.separator"));
        sb.append("|     |" + System.getProperty("line.separator"));
        sb.append(" -----" + System.getProperty("line.separator"));

        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * Test for triangle strategy.
     */
    @Test
    public void triangleTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        new Paint().draw(new Triangle());

        StringBuilder sb = new StringBuilder();
        sb.append("   /\\" + System.getProperty("line.separator"));
        sb.append("  /  \\" + System.getProperty("line.separator"));
        sb.append(" /    \\" + System.getProperty("line.separator"));
        sb.append(" ------" + System.getProperty("line.separator"));

        assertThat(out.toString(), is(sb.toString()));
    }

}
