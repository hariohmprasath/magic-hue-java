package com.magic.hue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.magic.hue.data.LightStatusDetails;
import com.magic.hue.data.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for Magic Hue LED lights
 */
public class MagicHueHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(MagicHueHelper.class);
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	/**
	 * Turn's on the light and sets RGB
	 *
	 * @param ip  IP address sample: 192.168.1.1
	 * @param rgb RGB color sample: 100,155,75
	 */
	public static void setRgb(String ip, String rgb) {
		// Sample rgb 100,155,75
		byte[] buff = toByteArray(processRgb(rgb), true);
		send(ip, buff, false);
	}

	/**
	 * Sets cool
	 *
	 * @param ip   IP address sample: 192.168.1.1
	 * @param cool Sample: 150
	 */
	public static void setCool(String ip, String cool) {
		// Sample cool 150
		cool = Integer.toHexString(Integer.parseInt(cool));
		String str = String.format(MagicHueConstants.COOL, cool);
		byte[] buff = toByteArray(processRaw(str), true);
		send(ip, buff, false);
	}

	/**
	 * Sets warm
	 *
	 * @param ip   IP address sample: 192.168.1.1
	 * @param warm Sample: 150
	 */
	public static void setWarm(String ip, String warm) {
		// Sample warm 150
		warm = Integer.toHexString(Integer.parseInt(warm));
		String str = String.format(MagicHueConstants.WARM, warm);
		byte[] buff = toByteArray(processRaw(str), true);
		send(ip, buff, false);
	}

	/**
	 * Turn's off the light
	 *
	 * @param ip IP address sample: 192.168.1.1
	 */
	public static void turnOff(String ip) {
		// Turn off the light
		byte[] buff = toByteArray(processRaw(MagicHueConstants.TURN_OFF), true);
		send(ip, buff, false);
	}

	/**
	 * Turn's on the light
	 *
	 * @param ip IP address sample: 192.168.1.1
	 */
	public static void turnOn(String ip) {
		// Turn on the light
		byte[] buff = toByteArray(processRaw(MagicHueConstants.TURN_ON), true);
		send(ip, buff, false);
	}

	/**
	 * Get status of the light
	 *
	 * @param ip IP address sample: 192.168.1.1
	 * @return POJO representation of {status=OFF, rgb=0,0,0, warm=0}
	 */
	public static LightStatusDetails getStatus(String ip) {
		LightStatusDetails details = new LightStatusDetails();
		byte[] buff = toByteArray(processRaw(MagicHueConstants.STATUS), false);
		String data = send(ip, buff, true);

		if (data == null) {
			LOGGER.error("Unknown response from LED controller");
			return new LightStatusDetails(Status.UNKNOWN);
		}

		// Set power
		String powerIdentifier = data.substring(4, 6);
		if ("23".equals(powerIdentifier))
			details.setStatus(Status.ON);
		else if ("24".equals(powerIdentifier))
			details.setStatus(Status.OFF);
		else
			details.setStatus(Status.UNKNOWN);

		// RGB
		StringBuilder rgbBuilder = new StringBuilder();
		String rgbIdentifier = data.substring(12, 18);
		rgbBuilder.append(hexToInteger(rgbIdentifier.substring(0, 2))).append(",");
		rgbBuilder.append(hexToInteger(rgbIdentifier.substring(2, 4))).append(",");
		rgbBuilder.append(hexToInteger(rgbIdentifier.substring(4, 6)));
		details.setRbg(rgbBuilder.toString());

		// Warm
		details.setWarm(String.valueOf(hexToInteger(data.substring(18, 20))));
		return details;
	}

	private static String send(String ip, byte[] buff, boolean waitForResponse) {
		try (Socket socket = new Socket(ip, MagicHueConstants.PORT);
			 OutputStream socketOutputStream = socket.getOutputStream();
			 InputStream socketInputStream = socket.getInputStream();
			 BufferedInputStream bufferedInputStream = new BufferedInputStream(socketInputStream);
			 DataInputStream dataInputStream = new DataInputStream(bufferedInputStream)) {

			// Send request for check status
			socketOutputStream.write(buff);

			// Read response
			if (waitForResponse) {
				byte[] response = new byte[1024];
				if (dataInputStream.read(response) == 0)
					LOGGER.error("Empty response from the LED controller");
				else
					return bytesToHex(response);
			}
		}
		catch (Exception e) {
			LOGGER.error("Error while sending request {}", e.getMessage(), e);
		}

		return null;
	}

	private static List<Integer> processRgb(String rgb) {
		String[] splits = rgb.split(",");
		List<Integer> val = new LinkedList<>();
		val.add(49);
		val.add(Integer.parseInt(splits[0]));
		val.add(Integer.parseInt(splits[1]));
		val.add(Integer.parseInt(splits[2]));
		val.add(0);
		val.add(0);
		val.add(240);
		val.add(15);
		return val;
	}

	private static byte[] toByteArray(List<Integer> val, boolean checkSum) {
		int sum = 0;
		if (checkSum) {
			sum = val.stream().reduce(Integer::sum).get();
		}

		List<Byte> byteList = val.stream().map(x -> (byte) x.intValue()).collect(Collectors.toList());
		if (checkSum)
			byteList.add((byte) (sum & 0xff));

		int len = byteList.size();
		byte[] buff = new byte[len];
		for (int i = 0; i < len; i++) {
			buff[i] = byteList.get(i);
		}

		return buff;
	}

	private static List<Integer> processRaw(String command) {
		return Arrays.stream(command.split(":"))
				.map(x -> Integer.parseInt(x, 16)).collect(Collectors.toList());
	}

	private static int hexToInteger(String hex) {
		return Long.decode("0x" + hex).intValue();
	}

	private static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}
}
