package cat.altimiras.operation;

import org.apache.commons.cli.CommandLine;


public class OperationFactory {

    public static Operation getOperation(CommandLine args) {
        Operation op;
        if(args.hasOption(InsertFile.OPERATION)){
            op = new InsertFile(args);
        } else if (args.hasOption(InsertCmdLine.OPERATION)){
            op = new InsertCmdLine(args);
        } else if (args.hasOption(WriteFileCmdLine.OPERATION)){
            op = new WriteFileCmdLine(args);
        } else {
            throw new IllegalArgumentException("no op");
        }
        return  op;
    }
}
