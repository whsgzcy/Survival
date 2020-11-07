package com.tundersoft.survival.bean;

import java.util.ArrayList;
import java.util.List;

import com.tundersoft.survival.constant.SurvivalConstant;
import com.tundersoft.survival.util.SurvivalHelper;

public class MapBean {

	// role
	int role = -1;

	// current direction
	// 1up 2down 3left 4right -1error
	int currentDirection;

	// all user location
	List<Integer> allUserLocations = new ArrayList<Integer>();

	// all user Score
	List<Integer> allUserScores = new ArrayList<Integer>();

	// current score
	int currentScore = -1;

	// map info
	String maps = null;
	
	String mAllMaps = null;
	
	String token;

	/********access all user role **********/
	public void setRole(int role) {
		this.role = role;
	}

	public int getRole() {
		return role;
	}

	/********access current user direction **********/
	public int getCurrentDirection() {

		if (null == maps) return -1;
		if (maps.length() != 625) return -1;
		int location = getCurrentLocation();

		String direction = maps.charAt(location) + "";

		if (direction.equals(SurvivalConstant.W)) {
			return 1;
		}

		if (direction.equals(SurvivalConstant.S)) {
			return 2;
		}

		if (direction.equals(SurvivalConstant.A)) {
			return 3;
		}

		if (direction.equals(SurvivalConstant.D)) {
			return 4;
		}
		return -1;
	}

	/********access current user scores **********/
	public int getCurrentScores() {
		if (role < 0) return -1;
		if (role < allUserScores.size()) return -1;

		return allUserScores.get(role);
	}

	/********access current user locations **********/
	public int getCurrentLocation() {
		if (role < 0) return -1;
		if (role < allUserLocations.size()) return -1;

		return allUserLocations.get(role);
	}
	
	/********access all user locations **********/
	public void setAllUserLocations(List<Integer> allUserLocations){
		this.allUserLocations = allUserLocations;
	}
	
	public List<Integer> getAllUserLocations(){
		return allUserLocations;
	}

	/********access maps **********/
	public void setMaps(String map) {
		this.maps = map;
	}

	public String getMaps() {
		return maps;
	}
	
	/********access user token **********/
	public String getToken(){
		return token;
	}
	
	public void setStartInfo(String startInfo){
		role = SurvivalHelper.getRole(startInfo);
	}
	
	/********access allMaps **********/
	public void setAllMapsInfo(String mapsInfo){
		mAllMaps = mapsInfo;
		maps = SurvivalHelper.getMap(mapsInfo);
		token = SurvivalHelper.getToken(mapsInfo);
		allUserLocations = SurvivalHelper.getAllPlayersLocation(mapsInfo);
		allUserScores = SurvivalHelper.getAllPlayersScore(mapsInfo);
		
	}
	
	/********access all user locations and direction **********/
	public List<UserDirectionBean> getAllUserLocationAndDirecrion(){
		
		if(null == maps) return null;
		
		List<UserDirectionBean> allUserAndDirection = new ArrayList<>();
		
		for(int i = 0; i < allUserLocations.size(); i++){
			
			UserDirectionBean bean = new UserDirectionBean();
			bean.setLocation(allUserLocations.get(i));
			
			String direction = maps.charAt(allUserLocations.get(i)) + "";

			int direct = -1;
			if (direction.equals(SurvivalConstant.W)) {
				direct =  1;
			}

			if (direction.equals(SurvivalConstant.S)) {
				direct = 2;
			}

			if (direction.equals(SurvivalConstant.A)) {
				direct = 3;
			}

			if (direction.equals(SurvivalConstant.D)) {
				direct =  4;
			}			
			bean.setDirection(direct);
			
			allUserAndDirection.add(bean);
		}
				
		return allUserAndDirection;
	}

	class UserDirectionBean {

		int location;
		int direction;

		public int getLocation() {
			return location;
		}

		public void setLocation(int location) {
			this.location = location;
		}

		public int getDirection() {
			return direction;
		}

		public void setDirection(int direction) {
			this.direction = direction;
		}
	}

}
