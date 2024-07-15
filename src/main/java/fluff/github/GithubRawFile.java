package fluff.github;

import fluff.http.path.URLPath;
import fluff.http.response.HTTPResponse;

/**
 * Represents a raw file in a GitHub repository.
 */
public class GithubRawFile {
	
	private final Github gh;
	
	private final URLPath downloadUrl;
	
	/**
     * Constructs a new GithubRawFile instance with the specified GitHub client and download URL.
     *
     * @param gh the GitHub client to use
     * @param downloadUrl the URL to download the raw file
     */
	public GithubRawFile(Github gh, URLPath downloadUrl) {
		this.gh = gh;
		this.downloadUrl = downloadUrl;
	}
	
    /**
     * Retrieves a specific raw file within the directory of this raw file.
     *
     * @param subPath the sub path to the raw file relative to this raw file's path
     * @return a GithubRawFile object representing the raw content of the file
     */
	public GithubRawFile rawFile(String subPath) {
		return new GithubRawFile(gh, downloadUrl.derive(subPath));
	}
	
    /**
     * Sends an HTTP GET request to download the file.
     *
     * @return the HTTP response
     */
    public HTTPResponse GET() {
        return gh.response(downloadUrl);
    }
	
    /**
     * Returns the download URL of the file.
     *
     * @return the download URL
     */
	public URLPath getDownloadURL() {
		return downloadUrl;
	}
}
