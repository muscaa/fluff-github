package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

/**
 * Represents a branch in a GitHub repository and provides methods to interact with it.
 */
public class GithubBranch {
    
    private final Github gh;
    private final String user;
    private final String repo;
    
    private final String name;
    
    /**
     * Constructs a GithubBranch instance from a JSONObject.
     *
     * @param gh the Github client instance
     * @param user the username or organization name that owns the repository
     * @param repo the repository name
     * @param json the JSON object containing branch information
     */
    GithubBranch(Github gh, String user, String repo, JSONObject json) {
        this.gh = gh;
        this.user = user;
        this.repo = repo;
        
        this.name = json.getString("name");
    }
    
    /**
     * Retrieves a specific file in the branch.
     *
     * @param path the path to the file
     * @return a GithubFile object representing the file, or null if it does not exist
     */
    public GithubFile file(String path) {
        return gh.file(user, repo, name, path);
    }
    
    /**
     * Retrieves all files in the specified path of the branch.
     *
     * @param path the path to the files
     * @return a list of GithubFile objects representing the files
     */
    public List<GithubFile> files(String path) {
        return gh.files(user, repo, name, path);
    }
    
    /**
     * Returns the username or organization name that owns the repository.
     *
     * @return the username or organization name
     */
    public String getUser() {
        return user;
    }
    
    /**
     * Returns the repository name.
     *
     * @return the repository name
     */
    public String getRepo() {
        return repo;
    }
    
    /**
     * Returns the name of the branch.
     *
     * @return the branch name
     */
    public String getName() {
        return name;
    }
}
