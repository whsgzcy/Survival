package com.tundersoft.survival.util;

import java.util.ArrayList;
import java.util.List;

public class SurvivalHelper {

	/**
	 * get role
	 * 
	 * @param start_
	 *            [START 1 25]
	 * @return
	 */
	public static int getRole(String start_) {
		start_ = start_.replaceAll("\\[", "");
		start_ = start_.replaceAll("\\]", "");
		String[] arrays = start_.split(" ");
		if (arrays.length == 3) {
			return Integer.parseInt(arrays[1].trim());
		}
		return -1;
	}

	/**
	 * get map data
	 * 
	 * @param maps
	 * @return
	 */
	public static String getMap(String maps) {
		int index = maps.indexOf("]");
		String map = maps.substring(index + 1 + 1, index + 1 + 1 + 625);
		return map;
	}

	/**
	 * get token
	 * 
	 * @param maps
	 * @return
	 */
	public static String getToken(String maps) {
		int index = maps.indexOf("]");
		int sIndex = maps.indexOf(" ");
		String token = maps.substring(sIndex + 1, index);
		return token;
	}

	/**
	 * get all players location
	 * 
	 * @param maps
	 * @return
	 */
	public static List<String> getAllPlayersLocation(String maps) {
		int index = maps.indexOf("]");
		maps = maps.substring(index + 1 + 1 + 1 + 625, maps.length());
		String[] array = maps.split("]");
		if (array.length > 1) {
			List<String> locations = new ArrayList<>();
			String prx = array[1].trim();
			String[] prxArray = prx.split(" ");
			for (int i = 0; i < prxArray.length; i++) {
				locations.add(prxArray[i]);
			}
			return locations;
		}
		return null;
	}
	
	/**
	 * get all players score
	 * @param maps
	 * @return
	 */
	public static List<String> getAllPlayersScore(String maps){
		int index = maps.indexOf("]");
		maps = maps.substring(index + 1 + 1 + 1 + 625, maps.length());
		String[] array = maps.split("]");
		if (array.length > 3) {
			List<String> scores = new ArrayList<>();
			String prx = array[3].trim();
			String[] prxArray = prx.split(" ");
			for (int i = 0; i < prxArray.length; i++) {
				scores.add(prxArray[i]);
			}
			return scores;
		}
		return null;
	}

}
