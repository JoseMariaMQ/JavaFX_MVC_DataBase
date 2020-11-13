package Controller;

import Model.AccesoBD;
import Model.Instrumento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.singletonObservableList;

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

    @FXML
    private Button btnMostrar;

    @FXML
    private TableView<Instrumento> tabla;
    @FXML
    private TableColumn<Instrumento, Integer> columnSerie;
    @FXML
    private TableColumn<Instrumento, Integer> columnInstrumentos;
    @FXML
    private TableColumn<Instrumento, Integer> columnPeso;
    @FXML
    private TableColumn<Instrumento, LocalDate> columnFecha;
    @FXML
    private TableColumn<Instrumento, String> columnTransportista;
    @FXML
    private TableColumn<Instrumento, String> columnObservaciones;

    private ObservableList datos;

    private int numSerieInt;
    private int cantidadInt;
    private int pesoInt;
    private LocalDate fechaRecepcionString;
    private String transportistaString;
    private String observacionesString;

    public void initialize() {
        btnRecibido.setOnAction(this);
        btnMostrar.setOnAction(this::mostrar);

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
            if(numeroSerie.getText() == null || cantidad.getText() == null || peso.getText() == null || fechaRecepcion.getValue() == null || transportista.getText() == null || observaciones.getText() == null) {
                System.out.println("Completa todos los datos!");
            } else {
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

    public void mostrar(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnMostrar) {
            ArrayList array = new ArrayList();
            ArrayList instr = new ArrayList();
            Instrumento instrumento;
            AccesoBD b = new AccesoBD();
            b.conectar();
            b.mostrarIntrumentos();


            for(int i=0; i<b.tablaInstrumentos.length; i++) {
                for(int j=0; j<b.tablaInstrumentos[i].length; j++) {
                    array.add(b.tablaInstrumentos[i][j]);
                }
                instrumento = new Instrumento(Integer.parseInt((String) array.get(0)), Integer.parseInt((String) array.get(1)), Integer.parseInt((String) array.get(2)),
                        LocalDate.parse(array.get(3).toString()), array.get(4).toString(), array.get(5).toString());

                instr.add(instrumento);

                array.clear();
            }

            datos = FXCollections.observableArrayList(instr);
            columnSerie.setCellValueFactory(new PropertyValueFactory<Instrumento, Integer>("numSerie"));
            columnInstrumentos.setCellValueFactory(new PropertyValueFactory<Instrumento, Integer>("numInstrumento"));
            columnPeso.setCellValueFactory(new PropertyValueFactory<Instrumento, Integer>("peso"));
            columnFecha.setCellValueFactory(new PropertyValueFactory<Instrumento, LocalDate>("fechaRecepcion"));
            columnTransportista.setCellValueFactory(new PropertyValueFactory<Instrumento, String>("transportista"));
            columnObservaciones.setCellValueFactory(new PropertyValueFactory<Instrumento, String>("observaciones"));

            tabla.setItems(datos);

        }
    }
}
