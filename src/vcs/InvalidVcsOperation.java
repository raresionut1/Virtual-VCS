package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class InvalidVcsOperation extends VcsOperation {
    /**
     * Invalid VCS operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public InvalidVcsOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the invalid VCS operation.
     *
     * @param vcs the VCS visitor
     * @return return code
     */
    @Override
    public int execute(final Vcs vcs) {
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
