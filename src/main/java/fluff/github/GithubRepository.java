package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

/**
 * Represents a GitHub repository and provides methods to retrieve repository-related information.
 */
public class GithubRepository {
    
    private final Github gh;
    
    private final String userName;
    private final String repoName;
    
    private final long id;
    private final String fullName;
    private final String description;
    private final String homepage;
    private final String defaultBranch;
    private final boolean archived;
    
    /**
     * Constructs a GithubRepository instance from a JSONObject.
     *
     * @param gh the Github client instance
     * @param json the JSON object containing repository information
     */
    GithubRepository(Github gh, JSONObject json) {
        this.gh = gh;
        
        this.userName = json.getObject("owner").getString("login");
        this.repoName = json.getString("name");
        
        this.id = json.getLong("id");
        this.fullName = json.getString("full_name");
        this.description = json.getString("description");
        this.homepage = json.getString("homepage");
        this.defaultBranch = json.getString("default_branch");
        this.archived = json.getBoolean("archived");
    }
    
    /**
     * Retrieves a specific branch of the repository.
     *
     * @param branchName the name of the branch
     * @return a GithubBranch object representing the branch, or null if it does not exist
     */
    public GithubBranch branch(String branchName) {
        return gh.branch(userName, repoName, branchName);
    }
    
    /**
     * Retrieves all branches of the repository.
     *
     * @return a list of GithubBranch objects representing the repository's branches
     */
    public List<GithubBranch> branches() {
        return gh.branches(userName, repoName);
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
     * Returns the ID of the repository.
     *
     * @return the repository's ID
     */
    public long getID() {
        return id;
    }
    
    /**
     * Returns the full name of the repository.
     *
     * @return the repository's full name
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Returns the description of the repository.
     *
     * @return the repository's description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the homepage URL of the repository.
     *
     * @return the repository's homepage URL
     */
    public String getHomepage() {
        return homepage;
    }
    
    /**
     * Returns the default branch of the repository.
     *
     * @return the repository's default branch
     */
    public String getDefaultBranch() {
        return defaultBranch;
    }
    
    /**
     * Checks if the repository is archived.
     *
     * @return true if the repository is archived, false otherwise
     */
    public boolean isArchived() {
        return archived;
    }
}
