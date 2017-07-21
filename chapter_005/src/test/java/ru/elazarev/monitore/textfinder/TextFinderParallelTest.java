package ru.elazarev.monitore.textfinder;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Text for TextFinderParallel class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.07.17
 */
public class TextFinderParallelTest {

    /**
     * Test for findText method.
     */
    @Test
    public void searchTest() {

        TextFinderParallel tf = new TextFinderParallel("/Users/macbookpro/Downloads/Files/",
                "test", Arrays.asList("rtf"));

        try {
            List<String> list = tf.findText();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            assertThat(list.size(), is(2));
            assertThat(list.contains("first.rtf"), is(true));
            assertThat(list.contains("second.rtf"), is(true));
        } catch (IOException e) {
            assertTrue(false);
        } catch (InterruptedException e) {
            assertTrue(false);
        }

    }
}