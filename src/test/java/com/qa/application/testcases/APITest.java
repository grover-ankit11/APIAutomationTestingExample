package com.qa.application.testcases;

import com.qa.application.models.Campaign;
import com.qa.application.models.PartnersList;
import com.qa.application.util.DataValidator;
import com.qa.application.util.JsonDataProvider;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.application.models.Partners;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.util.HashSet;


public class APITest extends BaseTest{
	public Logger Log = Logger.getLogger(getClass());
	private DataValidator dataValidator = new DataValidator();
	HashSet<Integer> uniquePartnerID=  new HashSet<Integer>();
	@Test(dataProvider="getFromJson", priority=-1)
	public void validatePartnerDataFields(Partners partner) {
		test.assignCategory("Data validations");
		childTest = test.createNode("Validating Not Null, Not Empty, Valid URL checks on Partner Info");

		Assert.assertNotNull(partner.getId(), "Found a null Partner ID");
		Log.info("Running data validations for Partner attributes belonging to Partner ID: "
				+ partner.getId());

		Assert.assertTrue(dataValidator.validateString(partner.getName()), "Found an invalid Partner name for " +
				"Partner ID: " + partner.getId());
		Assert.assertTrue(dataValidator.validateImage(partner.getImage()), "Found an invalid Partner image URL for " +
				"Partner ID: " + partner.getId());
	}
	@Test(dataProvider="getFromJson", priority=0, dependsOnMethods = {"validatePartnerDataFields"})
	public void validateCampaignDataFields(Partners partner) {
		test.assignCategory("Data validations");
		childTest = test.createNode("Validating Not Null, Not Empty, Valid Voucher checks on Campaign Info "
				+ "belonging to  Partner ID: " + partner.getId());
		Log.info("Running data validations for campaigns attributes belonging to  Partner ID: "
				+ partner.getId());

		for(Campaign campaigns : partner.getCampaigns()){
			Assert.assertNotNull(campaigns.getCampaign_id(), "Found a null Campaign ID");
			Assert.assertTrue(dataValidator.validateString(campaigns.getCampaign_name()),
					"Found an invalid Campaign name for Campaign ID: " + campaigns.getCampaign_id() +
							" belonging to Partner ID: " + partner.getId());
			Assert.assertTrue(dataValidator.validateVoucher(campaigns.getVoucher_code()),
					"Found an invalid Voucher Code for Campaign ID: " + campaigns.getCampaign_id() +
							" belonging to Partner ID: " + partner.getId());
		}
	}

	@Test(dataProvider="getFromJson", priority=1, dependsOnMethods = {"validatePartnerDataFields"})
	public void validateDuplicatePartners(Partners partner) {
		test.assignCategory("Duplicate ID validations");
		childTest = test.createNode("Validating Duplicate IDs doesnot exist for Partner Info for "
				+ "Partner ID: " + partner.getId());
		Log.info("Running validation for duplicate Partner IDs for " + partner.getId());

		Assert.assertFalse(uniquePartnerID.contains(partner.getId()), "Partner ID " + partner.getId() +
				" is already assigned to another partner");
		uniquePartnerID.add(partner.getId());
	}
	@Test(dataProvider="getFromJson", priority=2, dependsOnMethods = {"validateCampaignDataFields"})
	public void validateDuplicateCampaigns(Partners partner) {
		test.assignCategory("Duplicate ID validations");
		childTest = test.createNode("Validating Duplicate IDs doesnot exist for Campaigns Info belonging to "
				+ "Partner ID: " + partner.getId());
		Log.info("Running validation for duplicate Campaign IDs belonging to Partner ID: " + partner.getId());

		HashSet<Integer> uniqueCampaignID=  new HashSet<Integer>();
		for(Campaign campaigns : partner.getCampaigns()){
			Assert.assertFalse(uniqueCampaignID.contains(campaigns.getCampaign_id()), "Campaign ID " + campaigns.getCampaign_id() +
					" is already assigned to another Campaign under Partner ID: " + partner.getId());
			uniqueCampaignID.add(campaigns.getCampaign_id());
		}
	}
	@DataProvider(name = "getFromJson")
	public Object[][] getFromJson() throws IOException, JsonParseException, JsonMappingException {
		PartnersList partnerData = JsonDataProvider.loadData();
		return partnerData.getPartners().stream().map(partner -> new Object[] { partner }).toArray(Object[][]::new);
	}
}
