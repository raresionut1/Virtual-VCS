package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;
import utils.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public final class StatusOperation extends VcsOperation {

    /**
     * Status operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public StatusOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the status operation.
     *
     * @param vcs the VCS visitor
     * @return return code
     */
    @Override
    public int execute(final Vcs vcs) {
        if (this.operationArgs.size() != 0) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        OutputWriter outputWriter = vcs.getOutputWriter();

        outputWriter.write("On branch: ");
        outputWriter.write(vcs.getHead().getName());
        outputWriter.write("\n");

        outputWriter.write("Staged changes:\n");

        List<String> trackedCommands = vcs.getStaging().getTrackedCommands();
        for (String s : trackedCommands) {
            outputWriter.write("\t");
            outputWriter.write(s + "\n");
        }

        return ErrorCodeManager.OK;
    }
}
