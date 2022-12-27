package com.qa.application.models;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@NonNull
public class Partners {
	private Integer id;
	private String name;
	private String image;
	private List<Campaign> campaigns;
}
