package com.onp.util;

import java.util.ArrayList;

public class DataEntry {

	private String name;
	private ArrayList<String> features;
	private String shares;
	
	public DataEntry() {
		setName("");
		setFeatures(new ArrayList<String>());
		setShares("");
	}
	
	public void setDataEntry(String tmpName, ArrayList<String> tmpFeatures, String tmpShares) {
		setName(tmpName);
		setFeatures(tmpFeatures);
		setShares(tmpShares);
	}
	
	public DataEntry getDataEntry() {
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<String> features) {
		this.features = features;
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}
}
