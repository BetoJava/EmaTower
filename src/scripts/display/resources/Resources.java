package scripts.display.resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * Classe contenant les listes statiques des ressources.
 */
public class Resources {

	public static final HashMap<String, BufferedImage> TEXTURES = new HashMap<>();

	public static final HashMap<String, BufferedImage> CHARACTERS = new HashMap<>();

	public static final HashMap<String, Font> FONTS = new HashMap<>();

	public static final HashMap<String, File> MUSICS = new HashMap<>();
}
