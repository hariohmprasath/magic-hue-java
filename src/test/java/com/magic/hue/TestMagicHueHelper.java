package com.magic.hue;

import com.magic.hue.data.Status;
import org.junit.Assert;
import org.junit.Test;

public class TestMagicHueHelper {

	private static final String IP_ADDRESS = "192.168.1.70";

	@Test
	public void testTurnOffWithStatus() {
		MagicHueHelper.turnOff(IP_ADDRESS);
		try {
			Thread.sleep(1000);
			Assert.assertEquals(MagicHueHelper.getStatus(IP_ADDRESS).getStatus(), Status.OFF);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testTurnOnWithStatus() {
		MagicHueHelper.turnOn(IP_ADDRESS);
		try {
			Thread.sleep(1000);
			Assert.assertEquals(MagicHueHelper.getStatus(IP_ADDRESS).getStatus(), Status.ON);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testWarm() {
		MagicHueHelper.setWarm(IP_ADDRESS, "150");
	}

	@Test
	public void testCool() {
		MagicHueHelper.setCool(IP_ADDRESS, "150");
	}

	@Test
	public void testRgb() {
		String rgb = "100,100,100";
		MagicHueHelper.setRgb(IP_ADDRESS, rgb);
		try {
			Thread.sleep(1000);
			Assert.assertEquals(MagicHueHelper.getStatus(IP_ADDRESS).getRbg(), rgb);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
