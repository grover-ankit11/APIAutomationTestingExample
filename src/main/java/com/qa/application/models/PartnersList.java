package com.qa.application.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.List;

@Data
@JsonRootName("data")
public class PartnersList {
	private List<Partners> partners;
}
