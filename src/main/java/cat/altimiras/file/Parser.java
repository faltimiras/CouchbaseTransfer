package cat.altimiras.file;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Parser {

    public List<FileEntry> getDataToImport(String fileName){
        FileEntries customer = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FileEntries.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            customer = getFileEntriesCorrectXML(jaxbUnmarshaller, fileName);

            if(customer == null) {
                customer = getFileEntriesIncompleteXML(jaxbUnmarshaller, fileName);
            }

            return customer.getEntries();
        } catch (Exception e) {
            System.out.print("Unable to parse file, incorrect format");
        }
        return null;
    }

    private FileEntries getFileEntriesCorrectXML(Unmarshaller jaxbUnmarshaller, String fileName){
        try {
            return (FileEntries) jaxbUnmarshaller.unmarshal(new File(fileName));
        } catch (Exception e){
            return null;
        }
    }

    private FileEntries getFileEntriesIncompleteXML(Unmarshaller jaxbUnmarshaller, String fileName){
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(fileName));
            String content =  "<fileEntries><entries>" + new String(encoded, Charset.defaultCharset()) + "</entries></fileEntries>";
            return (FileEntries) jaxbUnmarshaller.unmarshal(new StringBufferInputStream(content));
        } catch (JAXBException e){
            System.out.print("Unable to parse XML file");
            return null;
        } catch (IOException e){
            System.out.print(String.format("File %s do not exist", fileName));
            return null;
        }
    }
}
