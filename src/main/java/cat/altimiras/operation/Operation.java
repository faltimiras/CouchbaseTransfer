package cat.altimiras.operation;

import org.apache.commons.cli.CommandLine;

public abstract class Operation {

    public static final String HOST = "h";
	public static final String TIMEOUT = "t";
	
    public static final String BUCKET = "b";
    public static final String PSW = "p";
    public static final String DOCUMENT = "d";
    public static final String KEY= "k";
    public static final String FAIL_EXIST = "x";

    public static final String FILE = "f";

    public Operation(CommandLine args){
        validateAndExtract(args);
    }

    protected abstract  void validateAndExtract(CommandLine args);

    public abstract void doIt();
}
