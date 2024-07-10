package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

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
	
	public GithubRepository repository(String repo) {
		return gh.repository(login, repo);
	}
	
	public List<GithubRepository> repositories() {
		return gh.repositories(login);
	}
	
	public long getID() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getAvatarURL() {
		return avatarUrl;
	}
	
	public String getBio() {
		return bio;
	}
	
	public int getReposSize() {
		return reposSize;
	}
	
	public int getGistsSize() {
		return gistsSize;
	}
}
