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
		int location = maps.indexOf("LOCATION");
		maps = maps.substring(location - 2 - 625, location - 2);
		return maps;
	}

	/**
	 * get token
	 * 
	 * @param maps
	 * @return
	 */
	public static String getToken(String maps) {
		maps = maps.substring(maps.indexOf("MAP"), maps.indexOf("LOCATION"));
		String[] array = maps.split(" ");
		if (array.length > 1) {
			return array[1];
		}
		return null;
	}

	/**
	 * get all players location
	 * 
	 * @param maps
	 * @return
	 */
	public static List<Integer> getAllPlayersLocation(String maps) {
		maps = maps.substring(maps.indexOf("LOCATION") + getToken(maps).length() + 8 + 1 + 1, maps.indexOf("SCORE") -1 -1);
		String[] array = maps.split(" ");
		List<Integer> locations = new ArrayList<>();
		for(int i = 0; i < array.length; i++){
			locations.add(Integer.parseInt(array[i].trim()));
		}
		return locations;
	}

	/**
	 * get all players score
	 * 
	 * @param maps
	 * @return
	 */
	public static List<Integer> getAllPlayersScore(String maps) {
		maps = maps.substring(maps.indexOf("SCORE") + getToken(maps).length() + 5 + 1 + 1, maps.lastIndexOf("]"));
		String[] array = maps.split(" ");
		List<Integer> scores = new ArrayList<>();
		for(int i = 0; i < array.length; i++){
			scores.add(Integer.parseInt(array[i].trim()));
		}
		return scores;
	}
	
}
