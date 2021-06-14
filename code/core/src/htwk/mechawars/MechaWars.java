package htwk.mechawars;

import com.badlogic.gdx.Game;

/**
* Main game class.
*/
public class MechaWars extends Game {

    @Override
    public void create() {
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }
}
