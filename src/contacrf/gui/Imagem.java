package contacrf.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Imagem {
	private Image imgL = new Image("logo.png"); // ADD IMAGEM
	private ImageView imgLogo = new ImageView(imgL);
	private Image imgCl = new Image("clientes.png"); // ADD IMAGEM
	private ImageView imgCli = new ImageView(imgCl);
	private Image imgC = new Image("cadastrar.png"); // ADD IMAGEM
	private ImageView imgCad = new ImageView(imgC);
	private Image imgR = new Image("relatorio.png"); // ADD IMAGEM
	private ImageView imgRel = new ImageView(imgR);
	// + IMAGENS SEGUE O EX
	public ImageView getImgLogo() {
		return imgLogo;
	}
	public ImageView getImgCli() {
		return imgCli;
	}
	public ImageView getImgCad() {
		return imgCad;
	}
	public ImageView getImgRel() {
		return imgRel;
	}
	public Imagem() {
		this.imgLogo.setFitHeight(500);
		this.imgLogo.setFitWidth(500);
		this.imgRel.setFitHeight(70);
		this.imgRel.setFitWidth(65);
		this.imgCad.setFitHeight(70);
		this.imgCad.setFitWidth(70);
		this.imgCli.setFitHeight(70);
		this.imgCli.setFitWidth(65);
	}
}