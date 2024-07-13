package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

/**
 * Represents a branch in a GitHub repository and provides methods to interact with it.
 */
public class GithubBranch {
    
    private final Github gh;
    
    private final String userName;
    private final String repoName;
    private final String branchName;
    
    /**
     * Constructs a GithubBranch instance from a JSONObject.
     *
     * @param gh the Github client instance
     * @param userName the user name that owns the repository
     * @param repoName the repository name
     * @param json the JSON object containing branch information
     */
    GithubBranch(Github gh, String userName, String repoName, JSONObject json) {
        this.gh = gh;
        
        this.userName = userName;
        this.repoName = repoName;
        
        this.branchName = json.getString("name");
    }
    
    /**
     * Retrieves a specific file in the branch.
     *
     * @param filePath the path to the file
     * @return a GithubFile object representing the file, or null if it does not exist
     */
    public GithubFile file(String filePath) {
        return gh.file(userName, repoName, branchName, filePath);
    }
    
    /**
     * Retrieves all files in the specified dir path of the branch.
     *
     * @param dirPath the path to the directory
     * @return a list of GithubFile objects representing the files
     */
    public List<GithubFile> files(String dirPath) {
        return gh.files(userName, repoName, branchName, dirPath);
    }
    
    /**
     * Returns the name of the user.
     *
     * @return the user's name
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Returns the name of the repository.
     *
     * @return the repository's name
     */
    public String getRepoName() {
        return repoName;
    }
    
    /**
     * Returns the name of the repository branch.
     *
     * @return the repository's branch name
     */
    public String getBranchName() {
        return branchName;
    }
}
