package ru.elazarev.monitore.textfinder;

import ru.elazarev.monitore.threadsafecollections.DynamicArrayThreadSafe;

import java.io.IOException;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to find text in files in specified directory.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.07.17
 */
public class TextFinderParallel {

    /**
     * Directory to begin find.
     */
    private File root;

    /**
     * Filter of files to find.
     */
    private FileFilter filter;

    /**
     * Thread safe list of files where found text.
     */
    private List<String> foundFiles;

    /**
     * Text to find.
     */
    private String searchTest;

    /**
     * Pool of finding text threads.
     */
    private List<Thread> threadPool;

    /**
     * Default constructor.
     * @param rootPath absolute path to catalog
     * @param searchTest text to find in files
     * @param extensions list of file extensions
     */
    public TextFinderParallel(String rootPath, String searchTest, List<String> extensions) {
        root = new File(rootPath);
        foundFiles = new DynamicArrayThreadSafe<>();
        threadPool = new ArrayList<>();
        this.searchTest = searchTest;

        filter = new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.isDirectory() || endsWith(file.getPath());
            }

            public boolean endsWith(String path) {
                for (String ext : extensions) {
                    if (path.endsWith("." +  ext)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * Finds specified text in file with specified extensions in specified catalog.
     * @return list of fonded files or empty list.
     * @throws IOException if directory is not found
     * @throws InterruptedException if some thread is interrupted
     */
    public List<String> findText() throws IOException, InterruptedException {
        foundFiles.clear();
        threadPool.clear();
        if (!root.exists()) {
            throw new IOException("Folder does't exists");
        }

        if (!root.isDirectory()) {
            throw new IOException("Is not folder");
        }

        find(root);

        for (Thread th : threadPool) {
            th.join();
        }

        return foundFiles;
    }

    /**
     * Searching text in specified file or stats search if path is directory.
     * @param path file to search text
     */
    private void find(File path) {
        for (File f : path.listFiles(filter)) {
            if (f.isFile()) {

                Thread thread = new TextFinderThread(f, searchTest, foundFiles);
                thread.start();

                threadPool.add(thread);
                continue;
            }
            find(f);
        }
    }
}


