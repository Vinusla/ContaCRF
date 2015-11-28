package contacrf.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Imagem {
	private Image imgL = new Image("logo.png"); // ADD IMAGEM
	private ImageView imgLogo = new ImageView(imgL);
	private Image imgS = new Image("logoS.png"); // ADD IMAGEM
	private ImageView imgScreen = new ImageView(imgS);
	// + IMAGENS SEGUE O EX
	public ImageView getImgLogo() {
		return imgLogo;
	}
	public ImageView getImgScreen() {
		return imgScreen;
	}
	public Imagem() {
		this.imgLogo.setFitHeight(500);
		this.imgLogo.setFitWidth(500);
	}
}