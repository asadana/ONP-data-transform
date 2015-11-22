package com.onp.util;

import java.util.ArrayList;

public class DataEntry {
	private String name;
	private ArrayList<String> features;
	private String label;
	
	public DataEntry() {
		setName("");
		setFeatures(new ArrayList<String>());
		setLabel("");
	}
	
	public void setDataEntry(String tmpName, ArrayList<String> tmpFeatures, String tmpLabel) {
		setName(tmpName);
		setFeatures(tmpFeatures);
		setLabel(tmpLabel);
	}
	
	public DataEntry getDataEntry() {
		return this;
	}
	
	public ArrayList<String> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<String> features) {
		this.features = features;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
