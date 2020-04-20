package com.ss.training.object.library;

import java.util.HashMap;

public class FileAction {
	private int type;
	private HashMap<Integer, String> fileMap = new HashMap<Integer, String>();
	public FileAction(int type) {
		super();
		this.type = type;
		this.fileMap.put(1, "resources/author.txt");
		this.fileMap.put(2, "resources/publisher.txt");
		this.fileMap.put(3, "resources/book.txt");
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFileMapItem(int type) {
		return fileMap.get(type);
	}

	
	
	

}
