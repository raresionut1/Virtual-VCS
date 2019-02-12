package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class RollbackOperation extends VcsOperation {

    /**
     * Rollback operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public RollbackOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the rollback operation.
     *
     * @param vcs the VCS visitor
     * @return return code
     */
    @Override
    public int execute(final Vcs vcs) {
        if (operationArgs.size() != 0) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        vcs.getStaging().clear();

        //Get the last commit of the current branch
        Branch currentBranch = vcs.getHead();
        int lastCommitIndex = currentBranch.getCommits().size() - 1;
        Commit lastCommit = currentBranch.getCommits().get(lastCommitIndex);

        vcs.setActiveSnapshot(lastCommit.getFileSystemSnapshot());

        return ErrorCodeManager.OK;
    }
}
