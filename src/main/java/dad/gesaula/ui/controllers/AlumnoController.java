package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable {

    // model

    private ObjectProperty<Alumno> alumno = new SimpleObjectProperty<>();

    // view

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField nameField;

    @FXML
    private CheckBox repiteCheckbox;

    @FXML
    private ComboBox<Sexo> sexCombo;

    @FXML
    private TextField surnameField;

    @FXML
    private GridPane root;

    public AlumnoController (){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/alumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // bindings

        alumno.addListener((o , ov , nv) -> {
            if (ov != null) {
               nameField.textProperty().unbindBidirectional(ov.nombreProperty());
               surnameField.textProperty().unbindBidirectional(ov.apellidosProperty());
               birthdate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
               sexCombo.valueProperty().unbindBidirectional(ov.sexoProperty());
               repiteCheckbox.selectedProperty().unbindBidirectional(ov.repiteProperty());
            }
            if (nv != null) {
                nameField.textProperty().bindBidirectional(nv.nombreProperty());
                surnameField.textProperty().bindBidirectional(nv.apellidosProperty());
                birthdate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
                sexCombo.valueProperty().bindBidirectional(nv.sexoProperty());
                repiteCheckbox.selectedProperty().bindBidirectional(nv.repiteProperty());
            }
        });

        // populate comboBox
        sexCombo.getItems().setAll(Sexo.values());


    }

    public GridPane getRoot() {
        return root;
    }

    public Alumno getAlumno() {
        return alumno.get();
    }

    public ObjectProperty<Alumno> alumnoProperty() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno.set(alumno);
    }
}
