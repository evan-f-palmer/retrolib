package retro.input.keys;

import java.util.HashMap;

public class RawKeyInput {
	private HashMap<Integer, RawKeyPressedListener> keysDown;
	private HashMap<Integer, RawKeyRelasedListener> keysUp;
	
	public static boolean DEBUG_KEY_INPUT = true;
	
	RawKeyInput() {
		keysDown  = new HashMap<Integer, RawKeyPressedListener>();
		keysUp    = new HashMap<Integer, RawKeyRelasedListener>();
	}
	
	boolean keyDown(int keyCode) {
		if(keysDown.containsKey(keyCode)) {
			return keysDown.get(keyCode).preformPressedKeyAction(keyCode);
		} else {
			nullKeyAction(keyCode, "Key Down");
			return false;
		}
	}
	
	boolean keyUp(int keyCode) {
		if(keysUp.containsKey(keyCode)) {
			return keysUp.get(keyCode).preformRelasedKeyAction(keyCode);
		} else {
			nullKeyAction(keyCode, "Key Up");
			return false;
		}
	}
	
	void setKeyDownAction(int keyCode, RawKeyPressedListener action) {
		if(keysDown.containsKey(keyCode)) {
			keysDown.remove(keyCode);
		}
		
		keysDown.put(keyCode, action);
	}
	
	void setKeyUpAction(int keyCode, RawKeyRelasedListener action) {
		if(keysUp.containsKey(keyCode)) {
			keysUp.remove(keyCode);
		}
		
		keysUp.put(keyCode, action);
	}
	
	private void nullKeyAction(int keyCode, String type) {
		if(DEBUG_KEY_INPUT) {
			System.out.println(type + " " + keyCode + " no action");
		}
	}
}
