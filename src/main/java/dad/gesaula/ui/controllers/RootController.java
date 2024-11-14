package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Grupo;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // controllers

    private GrupoController grupoController = new GrupoController();
    private AlumnoController alumnoController = new AlumnoController();

    // model

    private StringProperty rutaFichero = new SimpleStringProperty();
    private ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList(
            alumno ->  new Observable[] { alumno.nombreProperty() , alumno.apellidosProperty() , alumno.fechaNacimientoProperty() , alumno.sexoProperty() , alumno.repiteProperty() }
    ));
    private ObjectProperty<Alumno> alumnoSeleccionado = new SimpleObjectProperty<>();

    // view

    @FXML
    private VBox emptyBox;

    @FXML
    private TableView<Alumno> alumnoTable;

    @FXML
    private TableColumn<Alumno, LocalDate> birthdateColumn;

    @FXML
    private TextField fileNameText;

    @FXML
    private TableColumn<Alumno, String> nameColumn;

    @FXML
    private BorderPane placeHolderPane;

    @FXML
    private BorderPane root;

    @FXML
    private TableColumn<Alumno, String> surnameColumn;

    @FXML
    private Tab studentTab;

    @FXML
    private Tab groupTab;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupTab.setContent(grupoController.getRoot());

        // bindings
        grupoController.getGrupo().alumnosProperty().bind(alumnos);
        rutaFichero.bind(fileNameText.textProperty().concat(".xml"));
        alumnoTable.itemsProperty().bind(alumnos);
        alumnoSeleccionado.bind(alumnoTable.getSelectionModel().selectedItemProperty());

        alumnoController.alumnoProperty().bind(alumnoSeleccionado);

        alumnoSeleccionado.addListener((o , ov , nv) -> {
            if (nv == null){
                placeHolderPane.setCenter(emptyBox);
            }
            else {
                placeHolderPane.setCenter(alumnoController.getRoot());
            }
        });

        // cell values factories

        nameColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
        surnameColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
        birthdateColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onNewFileAction(ActionEvent event) {
        grupoController.setGrupo(new Grupo());
        alumnos.setAll();
    }

    @FXML
    void onNewStudentAction(ActionEvent event) {
        Alumno alumno = new Alumno();
        alumno.setNombre("Sin nombre");
        alumno.setApellidos("Sin apellidos");
        alumnos.add(alumno);
    }

    @FXML
    void onRemoveStudentAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar alumno");
        alert.setHeaderText("Se va a eliminar al alumno '" + alumnoSeleccionado.get().getNombre() + " " + alumnoSeleccionado.get().getApellidos() + "'");
        alert.setContentText("¿Está seguro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            alumnos.remove(alumnoSeleccionado.get());
        }


    }

    @FXML
    void onSaveAction(ActionEvent event) throws Exception {
        grupoController.getGrupo().save(new File(rutaFichero.get()));
    }
}
