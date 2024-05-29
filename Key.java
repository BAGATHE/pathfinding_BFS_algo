package ia.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ia.algo.Algorithm;
import ia.map.Map;

public class Key implements KeyListener {
    Map map;

    public Key(Map map) {
        this.map = map;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Algorithm.BFS(map);
            map.repaint(); 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
