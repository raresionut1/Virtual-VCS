package vcs;

import java.util.ArrayList;
import java.util.List;

//Class that implements Singleton Design Pattern
public final class MasterBranch extends Branch {
    private static final String MASTER_BRANCH_NAME = "master";

    // static variable singleInstance of type MasterBranch
    private static MasterBranch singleInstance = null;
    private List<List<Branch>> branchesLists = new ArrayList<List<Branch>>();

    /**
     * private MasterBranch constructor restricted to this class itself.
     *
     * @param firstCommit the first commit in the master branch
     */
    private MasterBranch(final Commit firstCommit) {
        super(MASTER_BRANCH_NAME, firstCommit);
        List<Branch> branches = new ArrayList<Branch>();
        branchesLists.add(branches);
    }

    /**
     * Create instance of MasterBranch class.
     *
     * @param firstCommit the first commit in the master branch
     * @return the instance of MasterBranch class
     */
    public static MasterBranch getInstance(final Commit firstCommit) {
        if (singleInstance == null) {
            singleInstance = new MasterBranch(firstCommit);
        }

        return singleInstance;
    }

    /**
     * Makes a new branch, starting from the latest commit.
     *
     * @param name the name of the new branch
     * @return the new branch
     */
    public Branch makeBranch(final String name) {
        //Get the last commit in the master branch
        int lastCommitIndex = commits.size() - 1;
        Commit lastCommit = commits.get(lastCommitIndex);
        Commit commitClone = lastCommit.clone();
        Branch branch = new Branch(name, commitClone);
        branchesLists.get(lastCommitIndex).add(branch);
        return branch;
    }

    /**
     * Add a commit to the master branch.
     *
     * @param commit the commit to be added to the master branch
     */
    @Override
    public void add(final Commit commit) {
        super.add(commit);
        List<Branch> branches = new ArrayList<Branch>();
        branchesLists.add(branches);
    }

    /**
     * Perform a checkout operation to an older commit of the master branch.
     *
     * @param commitId the id of the commit
     */
    @Override
    public void checkout(final int commitId) {
        super.checkout(commitId);
        //Modify the branchesLists list
        int lastIndexOld = branchesLists.size() - 1;
        int lastIndexNew = commits.size() - 1;
        for (int i = lastIndexOld; i > lastIndexNew; i--) {
            branchesLists.remove(i);
        }
    }

    /**
     * Gets the branch with that specific name.
     *
     * @param branchName the name of the branch
     * @return found branch
     */
    public Branch getBranch(final String branchName) {
        for (int i = 0; i < branchesLists.size(); i++) {
            for (int j = 0; j < branchesLists.get(i).size(); j++) {
                if (branchesLists.get(i).get(j).getName().equals(branchName)) {
                    return branchesLists.get(i).get(j);
                }
            }
        }

        if (branchName.equals("master")) {
            return this;
        }

        return null;
    }

    /**
     * Finds out if there is any branch with that specific name.
     *
     * @param branchName the name of the branch
     * @return whether or not there is a branch with that specific name
     */
    public boolean containsBranch(final String branchName) {
        Branch branch = getBranch(branchName);
        if (branch == null) {
            return false;
        }
        if (branchName.equals("master")) {
            return true;
        }
        return true;
    }
}
