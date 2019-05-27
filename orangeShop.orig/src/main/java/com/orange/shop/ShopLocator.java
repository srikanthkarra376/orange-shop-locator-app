package com.orange.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.Location;

import com.orange.shop.FileShopReader;
import com.orange.shop.Line;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicLine;
import net.sf.geographiclib.GeodesicMask;

public class ShopLocator implements OrangeShopFinder {
	
	private static List<Line> all_data= new ArrayList<Line>();
	private static Geodesic geod = Geodesic.WGS84;
	Location closestLocation;
	int smallestDistance = -1;
	
	public ShopLocator()
	{
		FileShopReader f = new FileShopReader();
		
		all_data = f.setAllLine();
		
		

		
	}



	@Override
	public String findOrangeShopWithMobileAvailable(double longitude, double latitude, String nameMobile) {
		
		double lat1=longitude;
		double long1=latitude;
		nameMobile=nameMobile;

		HashMap<Integer,Double> distances = new HashMap<Integer,Double>();
		if(nameMobile.equalsIgnoreCase("sunusng"))
		{
			int counter=-1;
			
			for (Line line : all_data) {
				counter++;
				if(Integer.parseInt(line.getField4())>0) {
					distances.put(counter, getDistance(lat1, long1, Double.parseDouble(line.getField2()),  Double.parseDouble(line.getField1())));
					
				
				}
				
			}

			distances=sortHashMapByValues(distances);

			 Map.Entry<Integer,Double> entry = distances.entrySet().iterator().next();
			 int key = entry.getKey();
			 double value = entry.getValue();

			
			return ("The nearest range store is at --> "+all_data.get(key).getField3()+"<--, which is approximately"+value+"kms from your Location")+
			("Hurry!!! up there are only "+all_data.get(key).getField4()+" "+nameMobile+" mobile in the stock.");
		}
		if(nameMobile.equalsIgnoreCase("weiwei"))
		{
			int counter=-1;
			
			for (Line line : all_data) {
				counter++;
				if(Integer.parseInt(line.getField6())>0) {
					distances.put(counter, getDistance(lat1, long1, Double.parseDouble(line.getField2()),  Double.parseDouble(line.getField1())));
					
				
				}
				
			}

			distances=sortHashMapByValues(distances);

			 Map.Entry<Integer,Double> entry = distances.entrySet().iterator().next();
			 int key = entry.getKey();
			 double value = entry.getValue();
			
			return ("The nearest range store is at --> "+all_data.get(key).getField3()+"<--, which is "+value+"kms from your Location")+("Hurry!! up there are only "+all_data.get(key).getField6()+" "+nameMobile+" mobile in the stock.");
		}
		if(nameMobile.equalsIgnoreCase("ipom"))
		{
			int counter=-1;
			
			for (Line line : all_data) {
				counter++;
				if(Integer.parseInt(line.getField5())>0) {
					distances.put(counter, getDistance(lat1, long1, Double.parseDouble(line.getField2()),  Double.parseDouble(line.getField1())));
					
				
				}
				
			}

			distances=sortHashMapByValues(distances);

			 Map.Entry<Integer,Double> entry = distances.entrySet().iterator().next();
			 int key = entry.getKey();
			 double value = entry.getValue();
			 
			
			return ("The nearest range store is at --> "+all_data.get(key).getField3()+"<--, which is "+value+"kms from your Location")+("Hurry!! up there are only "+all_data.get(key).getField5()+" "+nameMobile+" mobile in the stock.");
		}
	
		
		return "no available stock for "+nameMobile;
	}
	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		  GeodesicLine line = geod.InverseLine(lat1, lon1, lat2, lon2, GeodesicMask.DISTANCE_IN | GeodesicMask.LATITUDE | GeodesicMask.LONGITUDE);
		  return Math.ceil(line.Distance()/1000);
		}
	
	public HashMap<Integer, Double> sortHashMapByValues(
	        HashMap<Integer, Double> passedMap) {
	    List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
	    List<Double> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    HashMap<Integer, Double> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<Double> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Double val = valueIt.next();
	        Iterator<Integer> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            Integer key = keyIt.next();
	            double comp1 = passedMap.get(key);
	            double comp2 = val;

	            if (comp1==(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}

}
