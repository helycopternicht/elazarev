package ru.elazarev.model;

/**
 * File table model.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 20.12.17
 */
public class File {
    /**
     * File id.
     */
    private int id;
    /**
     * File url.
     */
    private String url;

    /**
     * Default constructor.
     * @param id id.
     * @param url url.
     */
    public File(int id, String url) {
        this.id = id;
        this.url = url;
    }

    /**
     * Getter.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter.
     * @return url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter.
     * @param url new url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Default to string.
     * @return string representation of file model.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("File{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
