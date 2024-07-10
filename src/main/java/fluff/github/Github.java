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

public class Github {
	
	public static final URLPath API = URLPath.of("https://api.github.com/");
	public static final URLPath BASE = URLPath.of("https://github.com/");
	
	private final HTTP http;
	
	public Github(HTTP http) {
		this.http = http;
	}
	
	public Github() {
		this(new HTTP());
	}
	
	public GithubUser user(String user) {
		HTTPResponse r = http
				.GET(Github.API.derive("users/" + user))
				.send();
		if (r.getStatus() != HTTPResponseStatus.OK) return null;
		
		JSONObject json = r.getBody()
				.get(HTTPBodyParser.JSON_OBJECT);
		
		return new GithubUser(this, json);
	}
	
	public GithubRepository repository(String user, String repo) {
		HTTPResponse r = http
				.GET(Github.API.derive("repos/" + user + "/" + repo))
				.send();
		if (r.getStatus() != HTTPResponseStatus.OK) return null;
		
		JSONObject json = r.getBody()
				.get(HTTPBodyParser.JSON_OBJECT);
		
		return new GithubRepository(this, json);
	}
	
	public List<GithubRepository> repositories(String user) {
		HTTPResponse r = http
				.GET(Github.API.derive("/users/" + user + "/repos"))
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
	
	public GithubBranch branch(String user, String repo, String branch) {
		HTTPResponse r = http
				.GET(Github.API.derive("repos/" + user + "/" + repo + "/branches/" + branch))
				.send();
		if (r.getStatus() != HTTPResponseStatus.OK) return null;
		
		JSONObject json = r.getBody()
				.get(HTTPBodyParser.JSON_OBJECT);
		
		return new GithubBranch(this, user, repo, json);
	}
	
	public List<GithubBranch> branches(String user, String repo) {
		HTTPResponse r = http
				.GET(Github.API.derive("/repos/" + user + "/" + repo + "/branches"))
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
	
	public GithubFile file(String user, String repo, String branch, String path) {
		branch = branch != null ? "?ref=" + branch : "";
		path = path == null ? "" : path;
		
		HTTPResponse r = http
				.GET(Github.API.derive("repos/" + user + "/" + repo + "/contents/" + path).getPath() + branch)
				.send();
		if (r.getStatus() != HTTPResponseStatus.OK) return null;
		
		JSONObject json = r.getBody()
				.get(HTTPBodyParser.JSON_OBJECT);
		
		return new GithubFile(this, user, repo, branch, json);
	}
	
	public List<GithubFile> files(String user, String repo, String branch, String path) {
		branch = branch != null ? "?ref=" + branch : "";
		path = path == null ? "" : path;
		
		HTTPResponse r = http
				.GET(Github.API.derive("repos/" + user + "/" + repo + "/contents/" + path).getPath() + branch)
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
