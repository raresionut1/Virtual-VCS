package vcs;

import filesystem.FileSystemSnapshot;
import utils.IDGenerator;

public class Commit {
    private String message;
    private int id;
    private Vcs vcs;
    private FileSystemSnapshot fileSystemSnapshot;

    /**
     * Commit constructor.
     *
     * @param message     the message of this commit
     * @param vcs the VCS visitor
     */
    public Commit(final String message, final Vcs vcs) {
        this.message = message;
        this.id = IDGenerator.generateCommitID();
        this.vcs = vcs;
        this.fileSystemSnapshot = vcs
                .getActiveSnapshot()
                .cloneFileSystem();
    }

    /**
     * Gets the message.
     *
     * @return message of this commit
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the message of this commit
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets the file system snapshot.
     *
     * @return file system snapshot of this commit
     */
    public FileSystemSnapshot getFileSystemSnapshot() {
        return fileSystemSnapshot;
    }

    /**
     * Gets the id.
     *
     * @return id of this commit
     */
    public int getId() {
        return id;
    }

    /**
     * Clone a commit.
     *
     * @return the cloned commit
     */
    public Commit clone() {
        Commit commitClone = new Commit(this.message, this.vcs);
        commitClone.fileSystemSnapshot = this.fileSystemSnapshot.cloneFileSystem();
        return commitClone;
    }
}
