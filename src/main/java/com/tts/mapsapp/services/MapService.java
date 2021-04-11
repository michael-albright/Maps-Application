package com.tts.mapsapp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tts.mapsapp.deserialization.GeocodingResponse;
import com.tts.mapsapp.deserialization.GeocodingReverseResponse;
import com.tts.mapsapp.deserialization.Location;

@Service
public class MapService 
{
	@Value("${api_key}")
	private String apiKey;
	
	public void addCoordinates(Location location)
	{
		//BUILDING A URL THAT WILL FIND THE CITY AND STATE ALONG WITH OUR apiKey

		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
			    location.getCity() + "," + location.getState() + "&key=" + apiKey;
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		GeocodingResponse response =
				//grab whatever response is in the url and store it in 
				//GeocodingResponse at the provided top level class
				restTemplate.getForObject(url,  GeocodingResponse.class);
		
		Location coordinates = response.getResults().get(0).getGeometry().getLocation();
		location.setLat(coordinates.getLat());
		location.setLng(coordinates.getLng());
	}
	
	public void addPlace(Location location)
	{
		String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + 
			    location.getLat() + "," + location.getLng() + "&key=" + apiKey;
		
		RestTemplate restTemplate = new RestTemplate();
	
		GeocodingReverseResponse response =
			restTemplate.getForObject(url, GeocodingReverseResponse.class);
		
		if(response.getResults().size() == 0) 
		{
			location.setCity("Unknown");
		}
		else
		{
			String formattedAddress = response.getResults().get(0).getFormatted_address();
			location.setCity(formattedAddress);;
		}
		
	}
	
}
