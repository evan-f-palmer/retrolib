package retro.input.keys;

import java.util.HashSet;
import java.util.LinkedList;

class VirtualKey implements RawKeyPressedListener, RawKeyRelasedListener {
	private HashSet<Integer> keysPressed;
	private LinkedList<VirtualKeyRelasedListener> vKeysRelasedListener;
	private LinkedList<VirtualKeyPressedListener> vKeysPressedListener;
	private VirtualKeyPressedListener firstPressed;
	public VirtualKeyRelasedListener finalRelased;
	
	VirtualKey() {
		keysPressed = new HashSet<Integer>();
		vKeysRelasedListener = new LinkedList<VirtualKeyRelasedListener>();
		vKeysPressedListener = new LinkedList<VirtualKeyPressedListener>();
		firstPressed = null;
		finalRelased = null;
	}
		
	public boolean isPressed() {
		return keysPressed.size() > 0;
	}

	public boolean isRelased() {
		return keysPressed.size() <= 0;
	}
	
	@Override
	public boolean preformPressedKeyAction(int keyCode) {
		if(keysPressed.size() == 0 && firstPressed != null) {
			firstPressed.keyPressed();
		}
				
		for(VirtualKeyPressedListener vKeyPressedListener : vKeysPressedListener) {
			vKeyPressedListener.keyPressed();
		}
		
		keysPressed.add(keyCode);
		
		return true;
	}

	@Override
	public boolean preformRelasedKeyAction(int keyCode) {
		for(VirtualKeyRelasedListener vKeyRelasedListener: vKeysRelasedListener) {
			vKeyRelasedListener.keyRelased();
		}		
		
		if(keysPressed.size() == 1 && finalRelased != null) {
			finalRelased.keyRelased();
		}
		
		keysPressed.remove(keyCode);
		return true;
	}
	
	public void setFirstPressedListener(VirtualKeyPressedListener listener) {
		firstPressed = listener;
	}
	
	public void setFinalRelasedListener(VirtualKeyRelasedListener listener) {
		finalRelased = listener;
	}
	
	public void addKeyPressedListener(VirtualKeyPressedListener listener) {
		vKeysPressedListener.add(listener);
	}
	
	public void addKeyRelasedListener(VirtualKeyRelasedListener listener) {
		vKeysRelasedListener.add(listener);
	}
}
