package domain;

import java.io.Serializable;

/**
 * ≤©øÕ¿‡
 *  
 * @author dy5239
 *
 */

public class Blog implements Serializable{
	
	private String path;
	private int[] hashId;
	
	public Blog(){
		path = null;
		hashId = null;
	}
	
	public Blog(String path, int[] hashId){
		this.path = path;
		this.hashId = hashId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int[] getHashId() {
		return hashId;
	}

	public void setHashId(int[] hashId) {
		this.hashId = hashId;
	}
	
	public String toString(){
		return this.path;
	}
	
	public int hashCode(){
		return this.path.hashCode();
	}
	
	public boolean equals(Object e){
		return this.hashCode() == e.hashCode();
	}

}
