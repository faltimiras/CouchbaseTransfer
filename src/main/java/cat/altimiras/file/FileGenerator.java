package cat.altimiras.file;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;


public class FileGenerator {

    public void writeToFile(String filename, FileEntry entry) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(FileEntry.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);

            File f = new File(filename);
            if (f.isDirectory()) {
                throw new IllegalArgumentException("file is not correct");
            }

            if (!f.exists()) {
                f.createNewFile();
            }

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(entry, sw);

            Files.write(f.toPath(), sw.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (JAXBException e){
            System.out.print("Unable to convert to XML: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e){
            System.out.print("Unable to write file: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
