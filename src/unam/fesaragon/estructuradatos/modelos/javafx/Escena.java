package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Escena {
    private String titulo;
    private String urlImageFXMLTitle;
    private Scene escenaCargada;
    private Stage stagePrincipal;

    public Escena(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    public Escena(String titulo, Stage stagePrincipal) {
        this.titulo = titulo;
        this.stagePrincipal = stagePrincipal;
        this.stagePrincipal.setTitle(titulo);
    }

    public void mostrarEscena() {
        stagePrincipal.show();
    }

    public void cambiarEscena(AnchorPane componenteAMostrar) {
        try {
            escenaCargada = new Scene(componenteAMostrar);
            stagePrincipal.setTitle(titulo);
            stagePrincipal.setScene(escenaCargada);
            stagePrincipal.show();
        } catch (Exception e) {
            System.out.println(e);;
        }

    }


    public Scene getEscenaCargada() {
        return escenaCargada;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlImageFXMLTitle() {
        return urlImageFXMLTitle;
    }

    public void setUrlImageFXMLTitle(String urlImageFXMLTitle) {
        this.urlImageFXMLTitle = urlImageFXMLTitle;
    }
}
