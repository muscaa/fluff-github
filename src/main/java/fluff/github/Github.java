package fluff.github;

import java.util.ArrayList;
import java.util.List;

import fluff.http.HTTP;
import fluff.http.body.HTTPBodyParser;
import fluff.http.head.HTTPHead;
import fluff.http.head.HTTPHeader;
import fluff.http.path.URLPath;
import fluff.http.request.HTTPRequest;
import fluff.http.response.HTTPResponse;
import fluff.http.response.HTTPResponseStatus;
import fluff.json.JSONArray;
import fluff.json.JSONObject;

/**
 * A simple GitHub client for retrieving information from GitHub.
 */
public class Github {
    
	/**
     * The base URL for GitHub.
     */
    public static final URLPath BASE = URLPath.of("https://github.com/");
	
    /**
     * The base URL for the GitHub API.
     */
    public static final URLPath API = URLPath.of("https://api.github.com/");
    
    /**
     * The base URL for raw content from GitHub repositories.
     */
    public static final URLPath RAW = URLPath.of("https://raw.githubusercontent.com/");
    
    final HTTP http;
    private final String token;
    
    /**
     * Constructs a new Github instance with the specified HTTP client and Github personal token.
     *
     * @param http the HTTP client to use
     * @param token the Github personal token to use
     */
    public Github(HTTP http, String token) {
        this.http = http;
        this.token = token;
    }
    
    /**
     * Constructs a new Github instance with the specified Github personal token.
     *
     * @param token the Github personal token to use
     */
    public Github(String token) {
        this(new HTTP(), token);
    }
    
    /**
     * Constructs a new Github instance with the specified HTTP client and no Github personal token.
     *
     * @param http the HTTP client to use
     */
    public Github(HTTP http) {
        this(http, null);
    }
    
    /**
     * Constructs a new Github instance with the default HTTP client and no Github personal token.
     */
    public Github() {
        this(new HTTP(), null);
    }
    
    /**
     * Retrieves information about a GitHub user.
     *
     * @param userName the username of the GitHub user
     * @return a GithubUser object representing the user, or null if the user does not exist
     */
    public GithubUser user(String userName) {
        HTTPResponse r = response(API.derive("users/" + userName));
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONObject json = r.getBody()
                .get(HTTPBodyParser.JSON_OBJECT);
        
        return new GithubUser(this, json);
    }
    
    /**
     * Retrieves information about a GitHub repository.
     *
     * @param userName the user name of the repository owner
     * @param repoName the name of the repository
     * @return a GithubRepository object representing the repository, or null if the repository does not exist
     */
    public GithubRepository repository(String userName, String repoName) {
        HTTPResponse r = response(API.derive("repos/" + userName + "/" + repoName));
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONObject json = r.getBody()
                .get(HTTPBodyParser.JSON_OBJECT);
        
        return new GithubRepository(this, json);
    }
    
    /**
     * Retrieves a list of repositories for a GitHub user.
     *
     * @param userName the user name of the GitHub user
     * @return a list of GithubRepository objects representing the user's repositories
     */
    public List<GithubRepository> repositories(String userName) {
        HTTPResponse r = response(API.derive("users/" + userName + "/repos"));
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
     * @param userName the user name of the repository owner
     * @param repoName the name of the repository
     * @param branchName the name of the branch
     * @return a GithubBranch object representing the branch, or null if the branch does not exist
     */
    public GithubBranch branch(String userName, String repoName, String branchName) {
        HTTPResponse r = response(API.derive("repos/" + userName + "/" + repoName + "/branches/" + branchName));
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONObject json = r.getBody()
                .get(HTTPBodyParser.JSON_OBJECT);
        
        return new GithubBranch(this, userName, repoName, json);
    }
    
    /**
     * Retrieves a list of branches in a GitHub repository.
     *
     * @param userName the user name of the repository owner
     * @param repoName the name of the repository
     * @return a list of GithubBranch objects representing the branches in the repository
     */
    public List<GithubBranch> branches(String userName, String repoName) {
        HTTPResponse r = response(API.derive("repos/" + userName + "/" + repoName + "/branches"));
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONArray json = r.getBody()
                .get(HTTPBodyParser.JSON_ARRAY);
        
        List<GithubBranch> list = new ArrayList<>(json.size());
        for (int i = 0; i < json.size(); i++) {
            list.add(new GithubBranch(this, userName, repoName, json.getObject(i)));
        }
        return list;
    }
    
    /**
     * Retrieves information about a file in a GitHub repository.
     *
     * @param userName the user name of the repository owner
     * @param repoName the name of the repository
     * @param branchName the name of the branch (can be null)
     * @param filePath the path to the file
     * @return a GithubFile object representing the file, or null if the file does not exist
     */
    public GithubFile file(String userName, String repoName, String branchName, String filePath) {
    	branchName = branchName != null ? "?ref=" + branchName : "";
    	filePath = filePath == null ? "" : filePath;
        
        HTTPResponse r = response(API.derive("repos/" + userName + "/" + repoName + "/contents/" + filePath + branchName));
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONObject json = r.getBody()
                .get(HTTPBodyParser.JSON_OBJECT);
        
        return new GithubFile(this, userName, repoName, branchName, json);
    }
    
    /**
     * Retrieves a list of files in a directory in a GitHub repository.
     *
     * @param userName the user name of the repository owner
     * @param repoName the name of the repository
     * @param branchName the name of the branch (can be null)
     * @param dirPath the path to the directory
     * @return a list of GithubFile objects representing the files in the directory
     */
    public List<GithubFile> files(String userName, String repoName, String branchName, String dirPath) {
    	branchName = branchName != null ? "?ref=" + branchName : "";
    	dirPath = dirPath == null ? "" : dirPath;
        
        HTTPResponse r = response(API.derive("repos/" + userName + "/" + repoName + "/contents/" + dirPath + branchName));
        if (r.getStatus() != HTTPResponseStatus.OK) return null;
        
        JSONArray json = r.getBody()
                .get(HTTPBodyParser.JSON_ARRAY);
        
        List<GithubFile> list = new ArrayList<>(json.size());
        for (int i = 0; i < json.size(); i++) {
            list.add(new GithubFile(this, userName, repoName, branchName, json.getObject(i)));
        }
        return list;
    }
    
    /**
     * Retrieves a raw file from a GitHub repository.
     *
     * @param userName the user name of the repository owner
     * @param repoName the name of the repository
     * @param branchName the name of the branch
     * @param filePath the path to the file
     * @return a GithubRawFile object representing the raw file
     */
    public GithubRawFile rawFile(String userName, String repoName, String branchName, String filePath) {
    	return new GithubRawFile(this, RAW.derive(userName + "/" + repoName + "/" + branchName + "/" + filePath));
    }
    
    HTTPResponse response(URLPath path) {
        HTTPRequest r = http.GET(path);
        if (token != null) {
        	r.setHead(HTTPHead.builder()
        		.add(HTTPHeader.AUTHORIZATION, "Bearer " + token)
        		.build());
        }
        return r.send();
    }
}
