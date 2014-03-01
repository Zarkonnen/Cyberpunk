package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.ScreenMode;

public interface Screen {
	public void input(Input in);
	public void render(Draw d, ScreenMode sm);
}
