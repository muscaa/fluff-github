package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

/**
 * Represents a GitHub user and provides methods to retrieve user-related information.
 */
public class GithubUser {
    
    private final Github gh;
    
    private final long id;
    private final String login;
    private final String displayName;
    private final String location;
    private final String avatarUrl;
    private final String bio;
    private final int reposSize;
    private final int gistsSize;
    
    /**
     * Constructs a GithubUser instance from a JSONObject.
     *
     * @param gh the Github client instance
     * @param json the JSON object containing user information
     */
    GithubUser(Github gh, JSONObject json) {
        this.gh = gh;
        
        this.id = json.getLong("id");
        this.login = json.getString("login");
        this.displayName = json.getString("name");
        this.location = json.getString("location");
        this.avatarUrl = json.getString("avatar_url");
        this.bio = json.getString("bio");
        this.reposSize = json.getInt("public_repos");
        this.gistsSize = json.getInt("public_gists");
    }
    
    /**
     * Retrieves a specific repository of the user.
     *
     * @param repo the name of the repository
     * @return a GithubRepository object representing the repository, or null if it does not exist
     */
    public GithubRepository repository(String repo) {
        return gh.repository(login, repo);
    }
    
    /**
     * Retrieves all public repositories of the user.
     *
     * @return a list of GithubRepository objects representing the user's repositories
     */
    public List<GithubRepository> repositories() {
        return gh.repositories(login);
    }
    
    /**
     * Returns the ID of the user.
     *
     * @return the user's ID
     */
    public long getID() {
        return id;
    }
    
    /**
     * Returns the login name of the user.
     *
     * @return the user's login name
     */
    public String getLogin() {
        return login;
    }
    
    /**
     * Returns the display name of the user.
     *
     * @return the user's display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the location of the user.
     *
     * @return the user's location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Returns the URL of the user's avatar.
     *
     * @return the user's avatar URL
     */
    public String getAvatarURL() {
        return avatarUrl;
    }
    
    /**
     * Returns the bio of the user.
     *
     * @return the user's bio
     */
    public String getBio() {
        return bio;
    }
    
    /**
     * Returns the number of public repositories the user has.
     *
     * @return the number of public repositories
     */
    public int getReposSize() {
        return reposSize;
    }
    
    /**
     * Returns the number of public gists the user has.
     *
     * @return the number of public gists
     */
    public int getGistsSize() {
        return gistsSize;
    }
}
