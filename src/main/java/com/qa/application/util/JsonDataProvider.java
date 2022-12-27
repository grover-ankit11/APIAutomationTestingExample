package com.qa.application.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.application.models.PartnersList;

public class JsonDataProvider {
	public static PartnersList loadData() throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		File file = new File("src/test/resources/testData/testSampleRespone.json");
		PartnersList partnerData = mapper.readValue(file, new TypeReference<PartnersList>(){});
		return partnerData;
	}
}
