package cat.altimiras.operation;

import cat.altimiras.couchbase.CouchbaseCli;
import cat.altimiras.file.FileEntry;
import cat.altimiras.file.Parser;
import org.apache.commons.cli.CommandLine;

import java.util.List;

//import from file
//cb-cli -ff -h <HOST> -f <FILENAME>
public class InsertFile extends  Operation {


    public static final String OPERATION = "ff";

    public static final String FILE = "f";

    private String host;
    private String fileName;

    public InsertFile(CommandLine args){
        super(args);
    }

    @Override
    protected void validateAndExtract(CommandLine args) {

        if (args.hasOption(FILE)){
            this.fileName = args.getOptionValue(FILE);
        } else {
            throw new IllegalArgumentException("file is required");
        }

        if (args.hasOption(HOST)){
            this.host = args.getOptionValue(HOST);
        } else {
            throw new IllegalArgumentException("host is required");
        }
    }

    @Override
    public void doIt() {

        Parser parser = new Parser();
        List<FileEntry> entries = parser.getDataToImport(this.fileName);

        CouchbaseCli client = new CouchbaseCli(this.host);

        for(FileEntry entry : entries){
            client.insertDocuments(entry.getBucketName(), entry.getBucketPsw(), entry.getDocs());
        }

    }
}
