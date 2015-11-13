package contacrf.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Imagem {
	private Image imgL = new Image("logo.png"); // ADD IMAGEM
	private ImageView imgLogo = new ImageView(imgL);
	// + IMAGENS SEGUE O EX
	public ImageView getImgLogo() {
		return imgLogo;
	}
}