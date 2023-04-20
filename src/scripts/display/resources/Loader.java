package scripts.display.resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Loader {
	/**
	 * Fonction qui charge toutes les ressources dans les listes statiques de la classe Resources.
	 */
	@SuppressWarnings("ConstantConditions")
	public void load() {
		try {
			// Buttons //
			Resources.TEXTURES.put("quitButton", ImageIO.read(new File("./src/resources/img/textures/ui/quitButton.png")));
			Resources.TEXTURES.put("quitButtonPressed", ImageIO.read(new File("./src/resources/img/textures/ui/quitButtonPressed.png")));
			Resources.TEXTURES.put("UISprite1", ImageIO.read(new File("./src/resources/img/textures/ui/UISprite1.png")));
			Resources.TEXTURES.put("UISpritePopup", ImageIO.read(new File("./src/resources/img/textures/ui/UISpritePopup.png")));

			// Fonts //
			File firaFont = new File("./src/resources/font/FiraSans-Black.ttf");
			Resources.FONTS.put("Fira", Font.createFont(Font.TRUETYPE_FONT, firaFont));
			File MiriamFont = new File("./src/resources/font/Miriam.otf");
			Resources.FONTS.put("Miriam", Font.createFont(Font.TRUETYPE_FONT, MiriamFont));
			File MiriamBoldFont = new File("./src/resources/font/MiriamBold.otf");
			Resources.FONTS.put("Miriam Bold", Font.createFont(Font.TRUETYPE_FONT, MiriamBoldFont));

			// Characters //
			Resources.CHARACTERS.put("m5", ImageIO.read(new File("./src/resources/img/characters/m5.png")));
			Resources.CHARACTERS.put("warrior", ImageIO.read(new File("./src/resources/img/characters/warrior.png")));
			Resources.CHARACTERS.put("wizard", ImageIO.read(new File("./src/resources/img/characters/wizard.png")));
			Resources.CHARACTERS.put("assassin", ImageIO.read(new File("./src/resources/img/characters/assassin.png")));
			Resources.CHARACTERS.put("wolf", ImageIO.read(new File("./src/resources/img/characters/wolf.png")));
			Resources.CHARACTERS.put("sanglier", ImageIO.read(new File("./src/resources/img/characters/sanglier.png")));
			Resources.CHARACTERS.put("spider", ImageIO.read(new File("./src/resources/img/characters/spider.png")));
			Resources.CHARACTERS.put("skeleton", ImageIO.read(new File("./src/resources/img/characters/skeleton.png")));
			Resources.CHARACTERS.put("goldenSkeleton", ImageIO.read(new File("./src/resources/img/characters/goldenSkeleton.png")));

			// Music //
			Resources.MUSICS.put("music", new File("./src/resources/musics/music.mid"));

		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
}
