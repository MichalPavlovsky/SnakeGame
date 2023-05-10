package sk.pavlovsky.input;

import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;


import static sk.pavlovsky.Game.setFirstDirection;
import static sk.pavlovsky.input.Direction.*;

public class DirectionValue {
    public Direction getInput(KeyStroke keyStroke) {
        if (keyStroke == null) {
            return NONE;
        }
        KeyType keyType = keyStroke.getKeyType();
        switch (keyType) {
            case ArrowUp: setFirstDirection(false);
                return UP;
            case ArrowDown: setFirstDirection(false);
                return DOWN;
            case ArrowLeft: setFirstDirection(false);
                return LEFT;
            case ArrowRight:setFirstDirection(false);
                return RIGHT;
            case Character: {
                Character character = keyStroke.getCharacter();
                switch (character) {
                    case 'q': return QUIT;
                }
            }
            default:return NONE;
        }



            }
        }
