package guiLab.presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Validator {
    public static boolean isPresent(JTextComponent c, String title) {
        if (c.getText().length() == 0) {
            showMessage(c, title + " can not be empty.");
            c.requestFocusInWindow();
            return false;
        }
        return true;
    }

    public static boolean isInteger(JTextComponent c, String title) {
        try {
            int i = Integer.parseInt(c.getText());
            return true;
        } catch (NumberFormatException e) {
            showMessage(c, title + " must be integer.");
            c.requestFocusInWindow();
            return false;
        }
    }

    public static boolean isDouble(JTextComponent c, String title) {
        try {
            double i = Double.parseDouble(c.getText());
            if (i > 0) return true;
            else throw (new IOException());
        } catch (IOException e) {
            showMessage(c, title + " must be more than zero.");
            return false;
        } catch (NumberFormatException e) {
            showMessage(c, title + " must be double.");
            //c.requestFocusInWindow();
            return false;
        }
    }

    public static boolean isYear(JTextComponent c, String title) {
        try {
            int i = Integer.parseInt(c.getText());
            if (i <= 2020 && i >= 1990)
                return true;
            else
                throw new IOException();
        } catch (NumberFormatException e) {
            showMessage(c, title + " must be integer.");
            //c.requestFocusInWindow();
            return false;
        } catch (IOException e) {
            showMessage(c, title + " must be between 1990 and 2020.");
            //c.requestFocusInWindow();
            return false;
        }
    }

    public static boolean isUserName(JTextComponent c, String name) {
        if (name.matches("[a-zA-Z0-9]{6}") == false) {
            showMessage(c, "User name should be 6 characters(can mix with numbers)");
            c.requestFocusInWindow();
            return false;
        }
        return true;
    }

    public static boolean isPassword(JTextComponent c, char[] password) {
        if (password.length == 0) {
            showMessage(c, "Password length should be more than zero");
            c.requestFocusInWindow();
            return false;
        }
        if (password.length > 6 || password.length == 0) {
            showMessage(c, "Password length should be less than or equal to six");
            c.requestFocusInWindow();
            return false;
        }
        return true;
    }

    public static boolean isUrl(JTextComponent c, String title) {
        String url = c.getText();
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            showMessage(c, title + " is not valid.");
            c.requestFocusInWindow();
            return false;
        }
        return true;
    }

    public static boolean isValidImg(JTextComponent c, String title) {
        try {
            Image img = ImageIO.read(new URL(c.getText()));
            if (img == null) {
                showMessage(c, title + " is not valid.");
                return false;
            }
        } catch (IOException e) {
            showMessage(c, title + " is not valid.");
            return false;
        }
        return true;
    }

    private static void showMessage(JTextComponent c, String message) {
        JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean isModel(String c, String model) {
    	
    	if(c.indexOf("select")>0) {
    		 JOptionPane.showMessageDialog(null, model+" is not valid", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
    		
    		    return false;
    	}
    	return true;
    }
}
