package sk.pavlovsky.input;

import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;


import static sk.pavlovsky.input.Direction.*;

public class DirectionValue {
    public Direction getInput(KeyStroke keyStroke) {
        if (keyStroke == null) {
            return NONE;
        }
        KeyType keyType = keyStroke.getKeyType();
        switch (keyType) {
            case ArrowUp:
                return UP;
            case ArrowDown:
                return DOWN;
            case ArrowLeft:
                return LEFT;
            case ArrowRight:
                return RIGHT;
            default:return NONE;
        }



            }
        }
