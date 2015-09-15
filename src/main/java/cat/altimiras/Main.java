package cat.altimiras;

import cat.altimiras.operation.*;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        //import from file
        //cb-cli -ff -h <HOST> -f <FILENAME>

        //insert from cmd
        //cb-cli -cl -h <HOST> -b <BUCKET> -p <PASSWORD?> -k <KEY> -d <DOCUMENT>

        //write file
        //cb-cli -wf -f <FILENAME> -b <BUCKET> -p <PASSWORD?> -k <KEY> -d <DOCUMENT> -x


        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        options.addOption(InsertFile.OPERATION, null, false, "import from file");
        options.addOption(InsertCmdLine.OPERATION, null, false, "insert document from command line");
        options.addOption(WriteFileCmdLine.OPERATION, null, false, String.format("write insert to a file to be importe later with [%s]", InsertFile.FILE));

        options.addOption(Operation.FILE, "file", true, "file to load");
        options.addOption(Operation.KEY, "key", true, "key of document will be inserted");
        options.addOption(Operation.DOCUMENT, "document", true, "document will be inserted, if it is not set can be writed or copied directly on the console.");
        options.addOption(Operation.PSW, "password", true, "password to connect to bucket");
        options.addOption(Operation.HOST, "host", true, "url to couchbase, with port");
        options.addOption(Operation.BUCKET, "bucket", true, "bucket where insert");
        options.addOption(Operation.FAIL_EXIST, "fail-update", false, "fail is key is already stored");

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            Operation operation = OperationFactory.getOperation(cmd);
            operation.doIt();

        } catch (Exception e) {
            e.printStackTrace(); //TODO treure
            // automatically generate the help statement
            formatter.printHelp("cb-cli", options, true);
        }
    }
}
