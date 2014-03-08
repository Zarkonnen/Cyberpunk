package com.zarkonnen.cyberpunk;


public interface Button extends Runnable {
	public String text();
	public String tooltip();
	public boolean enabled();
	@Override
	public void run();
}
