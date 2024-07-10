package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

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
	
	public GithubFile file(String path) {
		return gh.file(user, repo, branch, this.path + "/" + path);
	}
	
	public List<GithubFile> files(String path) {
		return gh.files(user, repo, branch, this.path + "/" + path);
	}
	
	public String getUser() {
		return user;
	}
	
	public String getRepo() {
		return repo;
	}
	
	public String getBranch() {
		return branch;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getType() {
		return type;
	}
	
	public String getDownloadURL() {
		return downloadUrl;
	}
	
	public String getSHA() {
		return sha;
	}
	
	public long getSize() {
		return size;
	}
}
