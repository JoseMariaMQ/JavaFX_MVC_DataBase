package Controller;

import Model.AccesoBD;
import Model.Instrumento;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class Controller implements EventHandler<ActionEvent> {

    @FXML
    private TextField numeroSerie;
    @FXML
    private TextField cantidad;
    @FXML
    private TextField peso;
    @FXML
    private DatePicker fechaRecepcion;
    @FXML
    private TextField transportista;
    @FXML
    private TextArea observaciones;
    @FXML
    private Button btnRecibido;
    @FXML
    private Label confirmar;

    private int numSerieInt;
    private int cantidadInt;
    private int pesoInt;
    private LocalDate fechaRecepcionString;
    private String transportistaString;
    private String observacionesString;

    public void initialize() {
        btnRecibido.setOnAction(this);

        //Capturamos el evento del ratón cuando de clic en un campo
        //desaparezca el texto de "Insertado correctamente"
        numeroSerie.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                confirmar.setText(null);
            }
        });
        cantidad.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                confirmar.setText(null);
            }
        });
        peso.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                confirmar.setText(null);
            }
        });
        fechaRecepcion.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                confirmar.setText(null);
            }
        });
        transportista.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                confirmar.setText(null);
            }
        });
        observaciones.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                confirmar.setText(null);
            }
        });
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //Acciones cuando se pulsa botón recibido
        if(actionEvent.getSource() == btnRecibido) {
            //Descargamos el texto de los campos y lo convertimos al tipo de datos que necesitamos
            numSerieInt = Integer.parseInt(numeroSerie.getText());
            cantidadInt = Integer.parseInt(cantidad.getText());
            pesoInt = Integer.parseInt(peso.getText());
            fechaRecepcionString = fechaRecepcion.getValue();
            transportistaString = transportista.getText();
            observacionesString = observaciones.getText();

            //Instanciamos instrumento y le introducimos los datos de los campos
            Instrumento instrumento = new Instrumento(numSerieInt, cantidadInt, pesoInt, fechaRecepcionString, transportistaString, observacionesString);
            //Instanciamos clase AccesoBD
            AccesoBD a = new AccesoBD();
            //Llamamos al método para conectar con BBDD
            a.conectar();
            //Llamamos al método que inserta datos en la BBDD
            a.insertarInstrumento(instrumento);

            //Introducimos texto al label indicando que se a insertado
            //correctamente los datos en la BBDD
            confirmar.setText("Insertado correctamente");

            //Vaciamos los campos una vez insertados en BBDD
            numeroSerie.setText(null);
            cantidad.setText(null);
            peso.setText(null);
            fechaRecepcion.setValue(null);
            transportista.setText(null);
            observaciones.setText(null);
        }
    }
}
