package cat.altimiras.file;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fileEntries")
public class FileEntries {

    @XmlElements({
            @XmlElement(name="fileEntry", type=FileEntry.class),
    })
    @XmlElementWrapper
    private List<FileEntry> entries;


    public List<FileEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<FileEntry> entries) {
        this.entries = entries;
    }
}
