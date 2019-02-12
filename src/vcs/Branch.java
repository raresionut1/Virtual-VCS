package vcs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Branch {

    protected String name;
    protected List<Commit> commits = new ArrayList<Commit>();

    /**
     * Branch constructor.
     *
     * @param name        the name of the branch
     * @param firstCommit the first commit in the branch
     */
    public Branch(final String name, final Commit firstCommit) {
        this.name = name;
        firstCommit.setMessage("First commit");
        commits.add(firstCommit);
    }

    /**
     * Gets the name.
     *
     * @return name of this branch
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name of this branch
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the list of commits.
     *
     * @return the list of commits of this branch
     */
    public List<Commit> getCommits() {
        return commits;
    }

    /**
     * Add a commit to the branch.
     *
     * @param commit the commit to be added to the branch
     */
    public void add(final Commit commit) {
        commits.add(commit);
    }

    /**
     * Perform a checkout operation to an older commit of the branch.
     *
     * @param commitId the id of the commit
     */
    public void checkout(final int commitId) {
        Iterator<Commit> commitsIterator = commits.iterator();
        while (commitsIterator.hasNext()) {
            Commit currentCommit = commitsIterator.next();
            if (currentCommit.getId() == commitId) {
                //Removes all the commits past the found one
                while (commitsIterator.hasNext()) {
                    commitsIterator.next();
                    commitsIterator.remove();
                }
                break;
            }
        }
    }

    /**
     * Finds out if the branch contains a commit with a specific id.
     *
     * @param commitId the id of the commit
     * @return whether or not the branch contains that commit
     */
    public boolean containsCommit(final int commitId) {
        return commits.stream().anyMatch(commit -> commit.getId() == commitId);
    }
}
