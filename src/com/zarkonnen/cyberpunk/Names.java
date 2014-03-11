package com.zarkonnen.cyberpunk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Names {
	public static final ArrayList<String> NAMES = new ArrayList<String>();
	
	static {
		BufferedReader r = null;
		try {
			r = new BufferedReader(new InputStreamReader(Names.class.getResourceAsStream("names.csv"), "UTF-8"));
			String line;
			while ((line = r.readLine()) != null) {
				String[] bits = line.split(",");
				String a = bits[0].trim();
				String b = bits[3].trim();
				if (!a.isEmpty()) {
					NAMES.add(a);
				}
				if (!b.isEmpty()) {
					NAMES.add(b);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			NAMES.add("Bob");
		} finally {
			try { r.close(); } catch (Exception e) {}
		}
	}
	
	public static String random(Random r) {
		return NAMES.get(r.nextInt(NAMES.size()));
	}
}
