package fluff.github;

import java.util.List;

import fluff.http.response.HTTPResponse;
import fluff.json.JSONObject;

/**
 * Represents a file in a GitHub repository and provides methods to interact with it.
 */
public class GithubFile {
    
    private final Github gh;
    
    private final String userName;
    private final String repoName;
    private final String branchName;
    private final String filePath;
    private final String fileName;
    
    private final String type;
    private final String downloadUrl;
    private final String sha;
    private final long size;
    
    /**
     * Constructs a GithubFile instance from a JSONObject.
     *
     * @param gh the Github client instance
     * @param userName the user name that owns the repository
     * @param repoName the repository name
     * @param branchName the branch name
     * @param json the JSON object containing file information
     */
    GithubFile(Github gh, String userName, String repoName, String branchName, JSONObject json) {
        this.gh = gh;
        
        this.userName = userName;
        this.repoName = repoName;
        this.branchName = branchName;
        
        this.filePath = json.getString("path");
        this.fileName = json.getString("name");
        
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
     * @param subPath the sub path to the file relative to this file's path
     * @return a GithubFile object representing the file, or null if it does not exist
     */
    public GithubFile file(String subPath) {
        return gh.file(userName, repoName, branchName, filePath + "/" + subPath);
    }
    
    /**
     * Retrieves all files within the specified sub path relative to this file's path.
     *
     * @param subPath the sub path to the files relative to this file's path
     * @return a list of GithubFile objects representing the files
     */
    public List<GithubFile> files(String subPath) {
        return gh.files(userName, repoName, branchName, filePath + "/" + subPath);
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
    
    /**
     * Returns the path of the file.
     *
     * @return the file's path
     */
    public String getFilePath() {
        return filePath;
    }
    
    /**
     * Returns the name of the file.
     *
     * @return the file's name
     */
    public String getFileName() {
        return fileName;
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
