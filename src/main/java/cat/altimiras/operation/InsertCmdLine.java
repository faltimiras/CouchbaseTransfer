package cat.altimiras.operation;

import cat.altimiras.couchbase.CouchbaseCli;
import cat.altimiras.couchbase.InsertDoc;
import org.apache.commons.cli.CommandLine;

import java.util.Scanner;

//insert from cmd
//cb-cli -cl -h <HOST> -b <BUCKET> -p <PASSWORD?> -k <KEY> -d <DOCUMENT?>
//if document is not present is read from cmd
public class InsertCmdLine extends Operation {

    public static final String OPERATION = "cl";

    private String document;
    private String host;
    private String key;
    private String bucket;
    private String password;

    public InsertCmdLine(CommandLine args) {
        super(args);
    }

    @Override
    protected void validateAndExtract(CommandLine args) {

        if (args.hasOption(DOCUMENT)){
            this.document = args.getOptionValue(DOCUMENT);
        }
        if (args.hasOption(HOST)){
            this.host = args.getOptionValue(HOST);
        } else {
            throw new IllegalArgumentException("host is required");
        }
        if (args.hasOption(BUCKET)){
            this.bucket = args.getOptionValue(BUCKET);
        } else {
            throw new IllegalArgumentException("bucket is required");
        }
        if (args.hasOption(KEY)){
            this.key = args.getOptionValue(KEY);
        } else {
            throw new IllegalArgumentException("key is required");
        }
        if (args.hasOption(PSW)) {
            this.password = args.getOptionValue(PSW);
        }
    }

    @Override
    public void doIt() {

        if (document == null) {
            Scanner scanner = new Scanner(System.in);
            StringBuffer doc = new StringBuffer();
            String line;
            while ((line = scanner.nextLine()).length() != 0) {
                doc.append(line);
            }

            this.document = doc.toString();
        }

        CouchbaseCli client = new CouchbaseCli(this.host);
        client.insertDocument(this.bucket, new InsertDoc(this.key, this.document));
    }
}
