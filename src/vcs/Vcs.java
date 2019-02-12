package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.Visitor;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private Staging staging = Staging.getInstance();
    private MasterBranch masterBranch;
    private Branch head;

    public void setActiveSnapshot(final FileSystemSnapshot activeSnapshot) {
        this.activeSnapshot = activeSnapshot;
    }

    public FileSystemSnapshot getActiveSnapshot() {
        return activeSnapshot;
    }

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(final OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        Commit firstCommit = new Commit("First commit", this);
        masterBranch = MasterBranch.getInstance(firstCommit);
        head = masterBranch;
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(final FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(final VcsOperation vcsOperation) {
        return vcsOperation.execute(this);
    }

    public Staging getStaging() {
        return staging;
    }

    public void setStaging(final Staging staging) {
        this.staging = staging;
    }

    public Branch getHead() {
        return head;
    }

    public void setHead(final Branch head) {
        this.head = head;
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public MasterBranch getMasterBranch() {
        return masterBranch;
    }
}
