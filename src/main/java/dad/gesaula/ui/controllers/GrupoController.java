package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GrupoController implements Initializable {

    // model

    private ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();

    // view

    @FXML
    private Label aptitudePercent;

    @FXML
    private Slider aptitudeSlider;

    @FXML
    private DatePicker beginDate;

    @FXML
    private TextField denominationField;

    @FXML
    private DatePicker endingDate;

    @FXML
    private Label examsPercent;

    @FXML
    private Slider examsSlider;

    @FXML
    private Label practicesPercent;

    @FXML
    private Slider practicesSlider;

    @FXML
    private GridPane root;

    public GrupoController(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grupoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        grupo.set(new Grupo());

        // bindings
        grupo.addListener((o , ov , nv) -> {
            if (ov != null){
                denominationField.textProperty().unbindBidirectional(ov.denominacionProperty());
                beginDate.valueProperty().unbindBidirectional(ov.iniCursoProperty());
                endingDate.valueProperty().unbindBidirectional(ov.finCursoProperty());
                examsSlider.valueProperty().unbindBidirectional(ov.pesosProperty().get().examenesProperty());
                practicesSlider.valueProperty().unbindBidirectional(ov.pesosProperty().get().practicasProperty());
                aptitudeSlider.valueProperty().unbindBidirectional(ov.pesosProperty().get().actitudProperty());

            }
            if  (nv != null){
                denominationField.textProperty().bindBidirectional(nv.denominacionProperty());
                beginDate.valueProperty().bindBidirectional(nv.iniCursoProperty());
                endingDate.valueProperty().bindBidirectional(nv.finCursoProperty());
                examsSlider.valueProperty().bindBidirectional(nv.pesosProperty().get().examenesProperty());
                practicesSlider.valueProperty().bindBidirectional(nv.pesosProperty().get().practicasProperty());
                aptitudeSlider.valueProperty().bindBidirectional(nv.pesosProperty().get().actitudProperty());
            }
        });


        examsPercent.textProperty().bind(examsSlider.valueProperty().asString("%.2f").concat("%"));
        practicesPercent.textProperty().bind(practicesSlider.valueProperty().asString("%.2f").concat("%"));
        aptitudePercent.textProperty().bind(aptitudeSlider.valueProperty().asString("%.2f").concat("%"));

    }

    public GridPane getRoot() {
        return root;
    }

    public Grupo getGrupo() {
        return grupo.get();
    }

    public ObjectProperty<Grupo> grupoProperty() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo.set(grupo);
    }
}
