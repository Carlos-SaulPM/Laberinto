package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Escena {
    private String urlFXML;
    private String titulo;
    private String urlImageFXMLTitle;
    Scene escenaCargada;
    Stage stagePrincipal;

    public Escena(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    public Escena(String urlFXML, String titulo, Stage stagePrincipal) {
        this.urlFXML = urlFXML;
        this.titulo = titulo;
        this.stagePrincipal = stagePrincipal;
        cargarFXML(urlFXML, titulo);
    }

    public void cambiarEscena(Scene nuevaEscena){
        stagePrincipal.setScene(escenaCargada);
        stagePrincipal.show();
    }

    public void cargarFXML(String urlFXML, String titulo) {

        try {
            FXMLLoader leerFXML = FXMLLoader.load(getClass().getResource(urlFXML));
            AnchorPane contenedorNuevaEscena = leerFXML.load();
            escenaCargada = new Scene(contenedorNuevaEscena);
        }catch (Exception e ){
            System.out.println("Error al cargar la escena: \n" + e);
        }
    }

    public Scene getEscenaCargada() {
        return escenaCargada;
    }

    public String getUrlFXML() {
        return urlFXML;
    }

    public void setUrlFXML(String urlFXML) {
        this.urlFXML = urlFXML;
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
