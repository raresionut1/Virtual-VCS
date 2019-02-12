package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class CommitOperation extends VcsOperation {

    /**
     * Commit operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CommitOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the commit operation.
     *
     * @param vcs the VCS visitor
     * @return return code
     */
    @Override
    public int execute(final Vcs vcs) {
        if (operationArgs.size() == 0) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        String option = operationArgs.remove(0);

        switch (option) {
            case "-m":
                Staging staging = vcs.getStaging();
                if (staging.isEmpty()) {
                    return ErrorCodeManager.VCS_BAD_CMD_CODE;
                }

                String message = "";
                for (String operationArg : operationArgs) {
                    message = message.concat(operationArg);
                    message = message.concat(" ");
                }
                message = message.substring(0, message.length() - 1);

                Commit commit = new Commit(message, vcs);
                vcs.getHead().add(commit);
                staging.clear();

                return ErrorCodeManager.OK;

            default:
                return ErrorCodeManager.VCS_BAD_CMD_CODE;

        }
    }
}
