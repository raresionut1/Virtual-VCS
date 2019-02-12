package filesystem;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class PrintOperation extends FileSystemOperation {
    /**
     * Print operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public PrintOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the print operation.
     *
     * @param fileSystemSnapshot the active file system snapshot
     * @return return code
     */
    @Override
    public int execute(final FileSystemSnapshot fileSystemSnapshot) {
        fileSystemSnapshot.getRoot().print("", fileSystemSnapshot.getOutputWriter());

        return ErrorCodeManager.OK;
    }
}
