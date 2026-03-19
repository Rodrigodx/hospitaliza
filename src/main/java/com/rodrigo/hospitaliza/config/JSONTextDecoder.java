package com.rodrigo.hospitaliza.config;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.JsonbException;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;



public class JSONTextDecoder implements Decoder.Text<JsonObject> {

	@Override
	public JsonObject decode(String s) throws DecodeException {
		try (JsonReader jsonReader = Json.createReader(new StringReader(s))){
			return jsonReader.readObject();
		}
	}

	@Override
	public boolean willDecode(String s) {
		try(JsonReader jsonReader = Json.createReader(new StringReader(s))){
			jsonReader.readObject();
			return true;
		} catch(JsonbException e) {
			return false;
		}
	}

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

}
