package cat.altimiras.couchbase;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doc")
public class InsertDoc {

    @XmlAttribute
    private String key;
    @XmlValue
    private String content;
    @XmlAttribute
    private boolean failIfPresent = false;

    public InsertDoc() {
    }

    public InsertDoc(String key, String content) {
        this.key = key;
        this.content = content;
    }


    public InsertDoc(String key, String content, boolean failIfPresent) {
        this(key,content);
        this.failIfPresent = failIfPresent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFailIfPresent() {
        return failIfPresent;
    }

    public void setFailIfPresent(boolean failIfPresent) {
        this.failIfPresent = failIfPresent;
    }
}
