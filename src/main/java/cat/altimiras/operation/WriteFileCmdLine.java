package cat.altimiras.operation;

import cat.altimiras.couchbase.InsertDoc;
import cat.altimiras.file.FileEntry;
import cat.altimiras.file.FileGenerator;
import org.apache.commons.cli.CommandLine;

import java.util.Scanner;

//write file
//cb-cli -wf -f <FILENAME> -b <BUCKET> -p <PASSWORD?> -k <KEY> -d <DOCUMENT> -x
public class WriteFileCmdLine extends Operation {

    public static final String OPERATION = "wf";

    private String document;
    private String key;
    private String bucket;
    private String password;
    private String fileName;
    private boolean failIfpresent = false;

    public WriteFileCmdLine(CommandLine args) {
        super(args);
    }

    @Override
    protected void validateAndExtract(CommandLine args) {

        if (args.hasOption(DOCUMENT)){
            this.document = args.getOptionValue(DOCUMENT);
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
        if (args.hasOption(FILE)){
            this.fileName = args.getOptionValue(FILE);
        } else {
            throw new IllegalArgumentException("file is required");
        }

        this.failIfpresent = args.hasOption(FAIL_EXIST);

    }

    @Override
    public void doIt() {

        //read form command line if not present
        if (document == null) {
            Scanner scanner = new Scanner(System.in);
            StringBuffer doc = new StringBuffer();
            String line;
            while ((line = scanner.nextLine()).length() != 0) {
                doc.append(line);
            }

            this.document = doc.toString();
        }

        FileGenerator generator = new FileGenerator();
        FileEntry entry = new FileEntry();
        entry.setBucketName(this.bucket);
        entry.setBucketPsw(this.password);
        entry.addDoc(new InsertDoc(key, document, failIfpresent));
        generator.writeToFile(fileName, entry);

    }
}
