package com.notedpath.linode;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * Response from an Linode API call
 * 
 * @author theodore nguyen-cao
 * 
 */
public class LinodeResponse {
	private JsonNode json;
	private static final String DATA = "DATA";
	private static final String ACTION = "ACTION";
	private static final String ERRORARRAY = "ERRORARRAY";

	/**
	 * @param json
	 */
	public LinodeResponse(JsonNode json) {
		this.json = json;
	}

	/**
	 * Get the data as an object (could be JSONArray or JSONObject
	 * 
	 * @return data object
	 */
	public JsonNode getData() {
		return json.get(DATA);
	}

	/**
	 * Action value
	 * 
	 * @return
	 */
	public String getAction() {
		return json.asText(ACTION);
	}

	/**
	 * returns JSONArray of errors
	 * 
	 * @return JSONArray of errors
	 */
	public JsonNode getErrorArray() {
		return json.get(ERRORARRAY);
	}

	public JsonNode getJSON() {
		return json;
	}
}
