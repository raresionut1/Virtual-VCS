package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class BranchOperation extends VcsOperation {

    /**
     * Branch operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public BranchOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the branch operation.
     *
     * @param vcs the VCS visitor
     * @return return code
     */
    @Override
    public int execute(final Vcs vcs) {
        if (operationArgs.size() != 1) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        String branchName = operationArgs.get(0);
        if (vcs.getMasterBranch().containsBranch(branchName)) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        vcs.getMasterBranch().makeBranch(branchName);

        return ErrorCodeManager.OK;
    }
}
