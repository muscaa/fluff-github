package fluff.github;

import java.util.ArrayList;
import java.util.List;

import fluff.http.HTTP;
import fluff.http.body.HTTPBodyParser;
import fluff.http.path.URLPath;
import fluff.http.response.HTTPResponse;
import fluff.http.response.HTTPResponseStatus;
import fluff.json.JSONArray;
import fluff.json.JSONObject;

/**
 * A simple GitHub client for retrieving information from GitHub.
 */
public class Github {
    
    /**
     * The base URL for the GitHub API.
     */
    public static final URLPath API = URLPath.of("https://api.github.com/");
    
    /**
     * The base URL for GitHub.
     */
    public static final URLPath BASE = URLPath.of("https://github.com/");
    
    final HTTP http;
    
    /**
     * Constructs a new Github instance with the specified HTTP client.
     *
     * @param http the HTTP client to use
     */
    public Github(HTTP http) {
        this.http = http;
    }
    
    /**
     * Constructs a new Github instance with the default HTTP client.
     */
    public Github() {
        this(new HTTP());
    }
    
    /**
     * Retrieves information about a GitHub user.
     *
     * @param user the username of the GitHub user
     * @return a GithubUser object representing the user, or null if the user does not exist
     */
    public GithubUser user(String user) {
        HTTPResponse r = http
                .GET(Github.API.derive("users/" + user))
                .send();
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONObject json = r.getBody()
                .get(HTTPBodyParser.JSON_OBJECT);
        
        return new GithubUser(this, json);
    }
    
    /**
     * Retrieves information about a GitHub repository.
     *
     * @param user the username of the repository owner
     * @param repo the name of the repository
     * @return a GithubRepository object representing the repository, or null if the repository does not exist
     */
    public GithubRepository repository(String user, String repo) {
        HTTPResponse r = http
                .GET(Github.API.derive("repos/" + user + "/" + repo))
                .send();
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONObject json = r.getBody()
                .get(HTTPBodyParser.JSON_OBJECT);
        
        return new GithubRepository(this, json);
    }
    
    /**
     * Retrieves a list of repositories for a GitHub user.
     *
     * @param user the username of the GitHub user
     * @return a list of GithubRepository objects representing the user's repositories
     */
    public List<GithubRepository> repositories(String user) {
        HTTPResponse r = http
                .GET(Github.API.derive("users/" + user + "/repos"))
                .send();
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONArray json = r.getBody()
                .get(HTTPBodyParser.JSON_ARRAY);
        
        List<GithubRepository> list = new ArrayList<>(json.size());
        for (int i = 0; i < json.size(); i++) {
            list.add(new GithubRepository(this, json.getObject(i)));
        }
        return list;
    }
    
    /**
     * Retrieves information about a branch in a GitHub repository.
     *
     * @param user the username of the repository owner
     * @param repo the name of the repository
     * @param branch the name of the branch
     * @return a GithubBranch object representing the branch, or null if the branch does not exist
     */
    public GithubBranch branch(String user, String repo, String branch) {
        HTTPResponse r = http
                .GET(Github.API.derive("repos/" + user + "/" + repo + "/branches/" + branch))
                .send();
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONObject json = r.getBody()
                .get(HTTPBodyParser.JSON_OBJECT);
        
        return new GithubBranch(this, user, repo, json);
    }
    
    /**
     * Retrieves a list of branches in a GitHub repository.
     *
     * @param user the username of the repository owner
     * @param repo the name of the repository
     * @return a list of GithubBranch objects representing the branches in the repository
     */
    public List<GithubBranch> branches(String user, String repo) {
        HTTPResponse r = http
                .GET(Github.API.derive("repos/" + user + "/" + repo + "/branches"))
                .send();
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONArray json = r.getBody()
                .get(HTTPBodyParser.JSON_ARRAY);
        
        List<GithubBranch> list = new ArrayList<>(json.size());
        for (int i = 0; i < json.size(); i++) {
            list.add(new GithubBranch(this, user, repo, json.getObject(i)));
        }
        return list;
    }
    
    /**
     * Retrieves information about a file in a GitHub repository.
     *
     * @param user the username of the repository owner
     * @param repo the name of the repository
     * @param branch the name of the branch (can be null)
     * @param path the path to the file
     * @return a GithubFile object representing the file, or null if the file does not exist
     */
    public GithubFile file(String user, String repo, String branch, String path) {
        branch = branch != null ? "?ref=" + branch : "";
        path = path == null ? "" : path;
        
        HTTPResponse r = http
                .GET(Github.API.derive("repos/" + user + "/" + repo + "/contents/" + path + branch))
                .send();
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONObject json = r.getBody()
                .get(HTTPBodyParser.JSON_OBJECT);
        
        return new GithubFile(this, user, repo, branch, json);
    }
    
    /**
     * Retrieves a list of files in a directory in a GitHub repository.
     *
     * @param user the username of the repository owner
     * @param repo the name of the repository
     * @param branch the name of the branch (can be null)
     * @param path the path to the directory
     * @return a list of GithubFile objects representing the files in the directory
     */
    public List<GithubFile> files(String user, String repo, String branch, String path) {
        branch = branch != null ? "?ref=" + branch : "";
        path = path == null ? "" : path;
        
        HTTPResponse r = http
                .GET(Github.API.derive("repos/" + user + "/" + repo + "/contents/" + path + branch))
                .send();
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONArray json = r.getBody()
                .get(HTTPBodyParser.JSON_ARRAY);
        
        List<GithubFile> list = new ArrayList<>(json.size());
        for (int i = 0; i < json.size(); i++) {
            list.add(new GithubFile(this, user, repo, branch, json.getObject(i)));
        }
        return list;
    }
}
