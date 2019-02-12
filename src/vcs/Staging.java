package vcs;

import utils.AbstractOperation;

import java.util.ArrayList;
import java.util.List;

public final class Staging {
    private static Staging singleInstance = null;

    private List<String> trackedCommands = new ArrayList<String>();

    /**
     * private Staging constructor restricted to this class itself.
     */
    private Staging() {
    }

    /**
     * Create instance of Staging class.
     *
     * @return the instance of Staging class
     */
    public static Staging getInstance() {
        if (singleInstance == null) {
            singleInstance = new Staging();
        }

        return singleInstance;
    }

    /**
     * Returns <tt>true</tt> if the staging contains no tracked commands.
     *
     * @return <tt>true</tt> if the staging contains no tracked commands
     */
    public boolean isEmpty() {
        return trackedCommands.isEmpty();
    }

    /**
     * Gets the list of tracked commands (as Strings).
     *
     * @return the list of tracked commands (as Strings)
     */
    public List<String> getTrackedCommands() {
        return trackedCommands;
    }

    /**
     * Removes all of the tracked commands from this staging. The staging will
     * be empty after this call returns.
     */
    public void clear() {
        trackedCommands.clear();
    }

    /**
     * Appends the specified operation to the end of this staging.
     *
     * @param operation operation to be appended to this staging
     */
    public void add(final AbstractOperation operation) {
        String output = "Changed directory to ";

        switch (operation.getType()) {
            case CHANGEDIR:
                int cdDirNameIndex = operation.getOperationArgs().size() - 1;
                String cdDir = operation.getOperationArgs().get(cdDirNameIndex);
                output = "Changed directory to " + cdDir;
                break;
            case MAKEDIR:
                int mkdirDirNameIndex = operation.getOperationArgs().size() - 1;
                String mkdirDir = operation.getOperationArgs().get(mkdirDirNameIndex);
                output = "Created directory " + mkdirDir;
                break;
            case REMOVE:
                int rmNameIndex = operation.getOperationArgs().size() - 1;
                String rmName = operation.getOperationArgs().get(rmNameIndex);
                output = "Removed " + rmName;
                break;
            case TOUCH:
                int touchFileNameIndex = operation.getOperationArgs().size() - 1;
                String touchFileName = operation.getOperationArgs().get(touchFileNameIndex);
                output = "Created file " + touchFileName;
                break;
            case WRITETOFILE:
                int writeContentIndex = operation.getOperationArgs().size() - 1;
                int writeFileNameIndex = writeContentIndex - 1;
                String writeContent = operation.getOperationArgs().get(writeContentIndex);
                String writeFileName = operation.getOperationArgs().get(writeFileNameIndex);
                String output1 = "Added \"";
                String output2 = "\" to file ";
                output = output1 + writeContent + output2 + writeFileName;
                break;

            default:
        }

        trackedCommands.add(output);
    }
}
