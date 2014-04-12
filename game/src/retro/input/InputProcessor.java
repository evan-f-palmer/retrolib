package retro.input;

import retro.input.keys.VirtualKeyInput;

public class InputProcessor implements com.badlogic.gdx.InputProcessor{

	public VirtualKeyInput keyInput;
	
	public InputProcessor () {
		keyInput = new VirtualKeyInput();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return keyInput.rawKeyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		return keyInput.rawKeyUp(keycode);
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
