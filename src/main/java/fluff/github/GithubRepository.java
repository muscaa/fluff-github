package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

/**
 * Represents a GitHub repository and provides methods to retrieve repository-related information.
 */
public class GithubRepository {
    
    private final Github gh;
    
    private final long id;
    private final String name;
    private final String fullName;
    private final String description;
    private final String homepage;
    private final String defaultBranch;
    private final boolean archived;
    private final GithubUser owner;
    
    /**
     * Constructs a GithubRepository instance from a JSONObject.
     *
     * @param gh the Github client instance
     * @param json the JSON object containing repository information
     */
    GithubRepository(Github gh, JSONObject json) {
        this.gh = gh;
        
        this.id = json.getLong("id");
        this.name = json.getString("name");
        this.fullName = json.getString("full_name");
        this.description = json.getString("description");
        this.homepage = json.getString("homepage");
        this.defaultBranch = json.getString("default_branch");
        this.archived = json.getBoolean("archived");
        this.owner = new GithubUser(gh, json.getObject("owner"));
    }
    
    /**
     * Retrieves a specific branch of the repository.
     *
     * @param branch the name of the branch
     * @return a GithubBranch object representing the branch, or null if it does not exist
     */
    public GithubBranch branch(String branch) {
        return gh.branch(owner.getLogin(), name, branch);
    }
    
    /**
     * Retrieves all branches of the repository.
     *
     * @return a list of GithubBranch objects representing the repository's branches
     */
    public List<GithubBranch> branches() {
        return gh.branches(owner.getLogin(), name);
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
     * Returns the name of the repository.
     *
     * @return the repository's name
     */
    public String getName() {
        return name;
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
    
    /**
     * Returns the owner of the repository.
     *
     * @return the GithubUser representing the repository's owner
     */
    public GithubUser getOwner() {
        return owner;
    }
}
