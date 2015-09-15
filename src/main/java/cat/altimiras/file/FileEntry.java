package cat.altimiras.file;

import cat.altimiras.couchbase.InsertDoc;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fileEntry")
public class FileEntry {

    @XmlAttribute
    private String bucketName;
    @XmlAttribute
    private String bucketPsw;
    @XmlElements({
            @XmlElement(name="doc", type=InsertDoc.class),
    })
    @XmlElementWrapper
    private List<InsertDoc> docs;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketPsw() {
        return bucketPsw;
    }

    public void setBucketPsw(String bucketPsw) {
        this.bucketPsw = bucketPsw;
    }

    public List<InsertDoc> getDocs() {
        return docs;
    }

    public void setDocs(List<InsertDoc> docs) {
        this.docs = docs;
    }

    public void addDoc(InsertDoc doc){
        if (docs == null) {
            docs = new ArrayList<>();
        }
        docs.add(doc);
    }
}
