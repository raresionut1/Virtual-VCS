package filesystem;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class InvalidFileSystemOperation extends FileSystemOperation {
    /**
     * Invalid file system operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public InvalidFileSystemOperation(final OperationType type,
                                      final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the invalid file system operation operation.
     *
     * @param fileSystemSnapshot the active file system snapshot
     * @return return code
     */
    @Override
    public int execute(final FileSystemSnapshot fileSystemSnapshot) {
        return ErrorCodeManager.SYS_BAD_CMD_CODE;
    }
}
