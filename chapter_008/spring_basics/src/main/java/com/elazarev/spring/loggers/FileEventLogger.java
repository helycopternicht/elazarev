package com.elazarev.spring.loggers;

import com.elazarev.spring.events.Event;
import com.elazarev.spring.loggers.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
@Component("fileE")
public class FileEventLogger implements EventLogger {

    private String fileName;

    private File file;

    public FileEventLogger(@Value("log.txt")String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
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
