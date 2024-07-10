package fluff.github;

import java.util.List;

import fluff.json.JSONObject;

public class GithubRepository {
	
	private final Github gh;
	
	private final long id;
	private final String name;
	private final String fullName;
	private final String description;
	private final String homepage;
	private final String defaultBranch;
	private final boolean archived;
	private final GithubUser owner;
	
	GithubRepository(Github gh, JSONObject json) {
		this.gh = gh;
		
		this.id = json.getLong("id");
		this.name = json.getString("name");
		this.fullName = json.getString("full_name");
		this.description = json.getString("description");
		this.homepage = json.getString("homepage");
		this.defaultBranch = json.getString("default_branch");
		this.archived = json.getBoolean("archived");
		this.owner = new GithubUser(gh, json.getObject("owner"));
	}
	
	public GithubBranch branch(String branch) {
		return gh.branch(owner.getLogin(), name, branch);
	}
	
	public List<GithubBranch> branches() {
		return gh.branches(owner.getLogin(), name);
	}
	
	public long getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getHomepage() {
		return homepage;
	}
	
	public String getDefaultBranch() {
		return defaultBranch;
	}
	
	public boolean isArchived() {
		return archived;
	}
	
	public GithubUser getOwner() {
		return owner;
	}
}
