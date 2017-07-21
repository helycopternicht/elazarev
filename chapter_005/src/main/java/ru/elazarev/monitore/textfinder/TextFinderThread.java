package ru.elazarev.monitore.textfinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Class to find text in file.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.07.17
 */
public class TextFinderThread extends Thread {
    /**
     * File to find text.
     */
    private final File file;
    /**
     * Text to search in file.
     */
    private final String searchText;
    /**
     * List of files where found text.
     */
    private final List<String> foundFiles;

    /**
     * Default constructor.
     * @param file file where search text
     * @param searchText text to search
     * @param foundFiles list of file names
     */
    public TextFinderThread(File file, String searchText, List<String> foundFiles) {
        this.file = file;
        this.foundFiles = foundFiles;
        this.searchText = searchText;
    }

    /**
     * Search text in file and add its name in list if test is found.
     */
    @Override
    public void run() {
        BufferedReader reader;
        String line;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchText)) {
                    foundFiles.add(file.getName());
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
