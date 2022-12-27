package com.qa.application.models;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class Campaign {
	private Integer campaign_id;
	private String campaign_name;
	private String voucher_code;
}
