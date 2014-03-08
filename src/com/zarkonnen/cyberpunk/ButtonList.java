package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.Pt;
import java.util.List;
import static com.zarkonnen.cyberpunk.DrawUtils.*;

public class ButtonList {
	public int scroll;
	public Model model;
	
	public interface Model {
		public List<Button> buttons();
	}
	
	public void input(Input in, int x, int y, int w, int h) {
		Pt cursor = in.cursor();
		if (cursor != null && cursor.x >= x && cursor.y >= y && cursor.x < x + w && cursor.y < y + h) {
			scroll += in.scrollAmount() / 10;
			if (scroll < 0) { scroll = 0; }
		}
	}
	
	public void draw(Draw d, int x, int y, int w, int h) {
		d.rect(BG, x, y, w, h);
		x += PADDING;
		y += PADDING;
		w -= PADDING * 2;
		h -= PADDING * 2;
		int top = y;
		int bottom = y + h;
		button(d, "^", x, y, w, new Runnable() {
			@Override
			public void run() {
				scroll = scroll + 5;
			}
		}, true, true);
		
		y += BUTTON_H + MARGIN_Y + scroll;
		
		for (Button b : model.buttons()) {
			if (y < top + BUTTON_H) { continue; }
			button(d, x, y, w, b);
			y += BUTTON_H + MARGIN_Y;
			if (y + BUTTON_H + MARGIN_Y + BUTTON_H > bottom) {
				break;
			}
		}
		
		button(d, "v", x, bottom - BUTTON_H, w, new Runnable() {
			@Override
			public void run() {
				scroll = Math.max(0, scroll - 5);
			}
		}, scroll > 0, true);
	}
}
