package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;
import utils.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public final class LogOperation extends VcsOperation {

    /**
     * Log operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public LogOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the log operation.
     *
     * @param vcs the VCS visitor
     * @return return code
     */
    @Override
    public int execute(final Vcs vcs) {
        if (operationArgs.size() != 0) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        Branch currentBranch = vcs.getHead();
        List<Commit> commits = currentBranch.getCommits();

        OutputWriter outputWriter = vcs.getOutputWriter();

        outputWriter.write("Commit id: " + commits.get(0).getId() + "\n");
        outputWriter.write("Message: " + commits.get(0).getMessage() + "\n");

        for (int i = 1; i < commits.size(); i++) {
            outputWriter.write("\n");
            outputWriter.write("Commit id: " + commits.get(i).getId() + "\n");
            outputWriter.write("Message: " + commits.get(i).getMessage() + "\n");
        }

        return ErrorCodeManager.OK;
    }
}
