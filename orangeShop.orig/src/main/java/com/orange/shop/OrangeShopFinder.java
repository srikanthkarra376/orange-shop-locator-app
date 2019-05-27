package com.orange.shop;

/**
 * Recherche de boutiques Orange.
 * 
 * Le point d'entr�e doit impl�menter cette interface.
 * 
 * 
 */
public interface OrangeShopFinder {

	String findOrangeShopWithMobileAvailable(double longitude, double latitude, String nameMobile);

}

