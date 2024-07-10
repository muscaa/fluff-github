package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

public class GithubBranch {
	
	private final Github gh;
	private final String user;
	private final String repo;
	
	private final String name;
	
	GithubBranch(Github gh, String user, String repo, JSONObject json) {
		this.gh = gh;
		this.user = user;
		this.repo = repo;
		
		this.name = json.getString("name");
	}
	
	public GithubFile file(String path) {
		return gh.file(user, repo, name, path);
	}
	
	public List<GithubFile> files(String path) {
		return gh.files(user, repo, name, path);
	}
	
	public String getUser() {
		return user;
	}
	
	public String getRepo() {
		return repo;
	}
	
	public String getName() {
		return name;
	}
}
