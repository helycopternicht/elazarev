package com.elazarev.spring;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
public class FileEventLogger implements EventLogger {

    private String fileName;

    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private void init() throws IOException {
        this.file = new File(this.fileName);
        if (!this.file.canWrite()) {
            throw new IOException("Can't write to file " + this.file);
        }
    }

    @Override
    public void logEvent(Event e) {
        try {
            FileUtils.writeStringToFile(new File(this.fileName), e.toString(), true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
