package fluff.github;

import java.util.List;

import fluff.http.response.HTTPResponse;
import fluff.json.JSONObject;

/**
 * Represents a file in a GitHub repository and provides methods to interact with it.
 */
public class GithubFile {
    
    private final Github gh;
    private final String user;
    private final String repo;
    private final String branch;
    
    private final String name;
    private final String path;
    private final String type;
    private final String downloadUrl;
    private final String sha;
    private final long size;
    
    /**
     * Constructs a GithubFile instance from a JSONObject.
     *
     * @param gh the Github client instance
     * @param user the username or organization name that owns the repository
     * @param repo the repository name
     * @param branch the branch name
     * @param json the JSON object containing file information
     */
    GithubFile(Github gh, String user, String repo, String branch, JSONObject json) {
        this.gh = gh;
        this.user = user;
        this.repo = repo;
        this.branch = branch;
        
        this.name = json.getString("name");
        this.path = json.getString("path");
        this.type = json.getString("type");
        this.downloadUrl = json.getString("download_url");
        this.sha = json.getString("sha");
        this.size = json.getLong("size");
    }
    
    /**
     * Sends an HTTP GET request to download the file.
     *
     * @return the HTTP response
     */
    public HTTPResponse GET() {
        return gh.http.GET(downloadUrl).send();
    }
    
    /**
     * Retrieves a specific file within the directory of this file.
     *
     * @param path the path to the file relative to this file's path
     * @return a GithubFile object representing the file, or null if it does not exist
     */
    public GithubFile file(String path) {
        return gh.file(user, repo, branch, this.path + "/" + path);
    }
    
    /**
     * Retrieves all files within the specified path relative to this file's path.
     *
     * @param path the path to the files relative to this file's path
     * @return a list of GithubFile objects representing the files
     */
    public List<GithubFile> files(String path) {
        return gh.files(user, repo, branch, this.path + "/" + path);
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
     * Returns the branch name.
     *
     * @return the branch name
     */
    public String getBranch() {
        return branch;
    }
    
    /**
     * Returns the file name.
     *
     * @return the file name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the file path.
     *
     * @return the file path
     */
    public String getPath() {
        return path;
    }
    
    /**
     * Returns the type of the file (e.g., file, directory).
     *
     * @return the file type
     */
    public String getType() {
        return type;
    }
    
    /**
     * Returns the download URL of the file.
     *
     * @return the download URL
     */
    public String getDownloadURL() {
        return downloadUrl;
    }
    
    /**
     * Returns the SHA of the file.
     *
     * @return the file SHA
     */
    public String getSHA() {
        return sha;
    }
    
    /**
     * Returns the size of the file in bytes.
     *
     * @return the file size
     */
    public long getSize() {
        return size;
    }
}
