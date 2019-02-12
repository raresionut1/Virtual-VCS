package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class CheckoutOperation extends VcsOperation {

    /**
     * Checkout operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CheckoutOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the checkout operation.
     *
     * @param vcs the VCS visitor
     * @return return code
     */
    @Override
    public int execute(final Vcs vcs) {
        if (operationArgs.size() != 1 && operationArgs.size() != 2) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        if (operationArgs.size() == 1) {
            String branchName = operationArgs.get(0);
            if (!vcs.getMasterBranch().containsBranch(branchName)) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }

            Staging staging = vcs.getStaging();
            if (!staging.isEmpty()) {
                return ErrorCodeManager.VCS_STAGED_OP_CODE;
            }

            vcs.setHead(vcs.getMasterBranch().getBranch(branchName));
            return ErrorCodeManager.OK;
        } else {
            String option = operationArgs.get(0);
            int commitId = Integer.valueOf(operationArgs.get(1));

            switch (option) {
                case "-c":
                    Staging staging = vcs.getStaging();
                    if (!staging.isEmpty()) {
                        return ErrorCodeManager.VCS_STAGED_OP_CODE;
                    }

                    if (!vcs.getHead().containsCommit(commitId)) {
                        return ErrorCodeManager.VCS_BAD_PATH_CODE;
                    }

                    vcs.getHead().checkout(commitId);

                    vcs.getStaging().clear();

                    Branch currentBranch = vcs.getHead();
                    int lastCommitIndex = currentBranch.getCommits().size() - 1;
                    Commit lastCommit = currentBranch.getCommits().get(lastCommitIndex);

                    vcs.setActiveSnapshot(lastCommit.getFileSystemSnapshot());


                    return ErrorCodeManager.OK;

                default:
                    return ErrorCodeManager.VCS_BAD_CMD_CODE;

            }
        }
    }
}
