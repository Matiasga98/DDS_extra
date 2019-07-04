package ui;

import dominio.Evento;
import dominio.Usuario;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MainWindow;
import ui.representacion.Fecha;
import ui.representacion.RepresentacionUsuario;
import ui.representacion.SiONo;


//IMPORTANTE: correr con -Djava.system.class.loader=com.uqbar.apo.APOClassLoader
public class VerEventosWindow extends MainWindow<VerEventos> {
    public VerEventosWindow() {
        super(new VerEventos());
    }

    @Override
    public void createContents(Panel mainPanel) {

        setTitle("Ver eventos en un intervalo de tiempo");

        new Label(mainPanel).setText("Usuarios...");

        List<Usuario> listaUsuarios = new List<Usuario>(mainPanel);
        listaUsuarios
                .bindItemsToProperty("usuarios")
                .setAdapter(new RepresentacionUsuario("nombre"));
        listaUsuarios.bindValueToProperty("seleccionado");
        listaUsuarios.setWidth(400);

        new Button(mainPanel)
                .setCaption("buscar")
                .onClick(()->getModelObject().buscarSucesoDeUsuario());

        new Label(mainPanel).setText("Intervalo Tiempo");

        Panel fechas = new Panel(mainPanel);
        fechas.setLayout(new HorizontalLayout());

        Fecha fecha = new Fecha();

        Panel fechaInicio = new Panel(fechas);
        new Label(fechaInicio).setText("Fecha de Inicio");
        new TextBox(fechaInicio)
                .bindValueToProperty("inicio");

        Panel fechaFin = new Panel(fechas);
        new Label(fechaFin).setText("Fecha de Fin");
        new TextBox(fechaFin)
                .bindValueToProperty("fin");

        new Button(mainPanel)
                .setCaption("filtrar")
                .onClick(() -> getModelObject().filtarPorFecha());

        Table<Evento> tabla = new Table<Evento>(mainPanel,Evento.class);
        tabla.setHeight(500);
        tabla.setWidth(500);
        tabla.bindItemsToProperty("eventosDeUnUsuario");

        new Column<Evento>(tabla)
                .setTitle("Sugerencias")
                .alignCenter()
                .bindContentsToProperty("tieneSugerencias")
                .setTransformer(new SiONo());

        new Column<Evento>(tabla)
                .setTitle(" Evento ")
                .alignCenter()
                .bindContentsToProperty("nombre");

        new Column<Evento>(tabla)
                .setTitle("    Fecha   ")
                .alignCenter()
                .bindContentsToProperty("fechaYHora")
                .setTransformer(new Fecha());
    }

    // -Djava.system.class.loader=org.uqbar.apo.APOClassLoader
    public static void main(String[] args) {
        new VerEventosWindow().startApplication();
    }

}
