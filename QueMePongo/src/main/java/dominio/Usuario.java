package dominio;

import javax.persistence.*;

import dominio.clima.ProveedorClima;
import dominio.enumerados.Categoria;
import dominio.enumerados.EstadoAtuendo;
import dominio.enumerados.ModoDeRepeticion;
import dominio.enumerados.SuceptibilidadATemperatura;
import dominio.excepciones.AtuendoNoPerteneceAGuardarropa;
import dominio.excepciones.SuperoLaCantidadDePrendas;

import dominio.Notificadores.Notificador;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



@Entity
@Table(name="Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, nullable = false, unique = true, updatable = false)
    private int id;

    @Column(name="username")
    String username;

    public String getUsername() {
        return username;
    }

    @Column(name="password")
    String contrasenia;
    //Esto está hasheado
    public String getContrasenia() {
        return contrasenia;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @CollectionTable(name = "medios_notificacion_usuario", joinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Notificador> mediosDeNotificacion;

    @Column(name="nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Set<Guardarropa> guardarropas = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Set<Evento> eventos= new HashSet<>();;

    @Column(name="es_premium")
    private boolean esPremium = false;

    @ElementCollection
    @CollectionTable(name = "usuario_friolento_en", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "categoria_id")
    private Set<Categoria> friolentoEn = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "usuario_caluroso_en", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "categoria_id")
    private Set<Categoria> calurosoEn = new HashSet<>();


    public Set<Guardarropa> getGuardarropas() {
        return guardarropas;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Set<Atuendo> loQueMeSugirieron = new HashSet<>();
    @OneToOne
    public Atuendo ultimoAceptado;
    public Set<Atuendo> getLoQueMeSugirieron() {
        return loQueMeSugirieron;
    }
    public void setUltimoAceptado(Atuendo atuendo){
        ultimoAceptado = atuendo;
    }
    public Atuendo getUltimoAceptado (){
        return ultimoAceptado;
    }
    public void setLoQueMeSugirieron(Set<Atuendo> loQueMeSugirieron) {
        this.loQueMeSugirieron = loQueMeSugirieron;
    }

    //Constantes
    //@Transient
    private int prendasMaximas = 20;
    //@Transient
    private int coeficienteSuperior = 25;

    //@Transient
    private int coeficienteInferior = 20;
    //@Transient

    private int coeficienteCalzado = 18;
    //@Transient
    private int coeficienteCabeza = 10;
    //@Transient
    private int coeficienteCuello = 10;
    //@Transient
    private int coeficienteCara = 10;
    //@Transient
    private int coeficienteManos = 10;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    //@Column(name = "atuendoId")
    private Set<Atuendo> atuendosAceptados;

    /*@ElementCollection
    @CollectionTable(name = "atuendos_rechazados", joinColumns = @JoinColumn(name = "guardarropaId"))
    @Column(name = "atuendoId")
    */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Set<Atuendo> atuendosRechazados;

    public Set<Atuendo> getAtuendosAceptados() {
        return atuendosAceptados;
    }

    public void setAtuendosAceptados(Set<Atuendo> atuendosAceptados) {
        this.atuendosAceptados = atuendosAceptados;
    }

    public Set<Atuendo> getAtuendosRechazados() {
        return atuendosRechazados;
    }
    public void setAtuendosRechazados(Set<Atuendo> atuendosRechazados) {
        this.atuendosRechazados = atuendosRechazados;
    }




    public int coeficienteEn(Categoria categoria){
        switch (categoria){
            case PARTE_SUPERIOR:
                return coeficienteSuperior;
            case PARTE_INFERIOR:
                return coeficienteInferior;

            case CALZADO:
                return coeficienteCalzado;

            case CARA:
                return coeficienteCara;

            case MANOS:
                return coeficienteManos;

            case CABEZA:
                return coeficienteCabeza;

            case CUELLO:
                return coeficienteCuello;

            default:
                return 0;

        }
    }

    public  Usuario(String nombre, Set<Evento> eventos){
        this.nombre = nombre;
        this.eventos = eventos;
    }

    public Usuario(String nombreUsuario, String contrasenia) {
        this.username = nombreUsuario;
        this.contrasenia = Encriptador.hashear(contrasenia);
    }

    public Usuario(boolean esPago) {
        this.guardarropas = new HashSet<>();
        this.eventos = new HashSet<>();
        this.esPremium = esPago;
        this.prendasMaximas = 20;
    }

    public Usuario(){
    }

    public void setNombre(String unNombre){
        nombre=unNombre;
    }
    public Set<Categoria> FriolentoEn(){
        return friolentoEn;
    }


    public Set<Categoria> CalurosoEn(){
        return calurosoEn;
    }


    public Set<Guardarropa> guardarropas() {
        return guardarropas;
    }
    public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
        if(esPremium){
            guardarropa.agregarPrendas(prenda);
        }else{
            if (superoElLimiteDePrendas(guardarropa)) {
                throw new SuperoLaCantidadDePrendas("No te queda mas lugar pipi");
            }
            guardarropa.agregarPrendas(prenda);
        }
    }

    public void removerPrenda(Prenda prenda, Guardarropa guardarropa) {
        guardarropa.removerPrenda(prenda);
    }

    public Set<Atuendo> sugerenciasDe(Guardarropa guardarropa) {
        return guardarropa.generarAtuendos();
    }

    public Set<Atuendo> sugerenciasDeAtuendosDeTodosLosGuardarropas() {
        return guardarropas.stream().flatMap(guardarropa -> guardarropa.generarAtuendos().stream()).collect(Collectors.toSet());
    }

    public void agregarEvento (Evento unEvento){
        eventos.add(unEvento);
    }

    public Set<Atuendo> pedirSugerenciaParaEvento (Evento evento, Guardarropa guardarropa, ProveedorClima unProveedor, boolean flexible){
        loQueMeSugirieron.addAll(guardarropa.sugerirParaEvento(evento, unProveedor, flexible, this ));
        return guardarropa.sugerirParaEvento(evento, unProveedor, flexible, this );
    }
    public void aceptarSugerencia (Atuendo atuendo){
        this.atuendosAceptados.add(atuendo);
        atuendo.cambiarEstado(EstadoAtuendo.ACEPTADO);
        atuendo.enUso();
    }
    public void rechazarSugerencia (Atuendo atuendo){
        this.atuendosRechazados.add(atuendo);
        atuendo.cambiarEstado(EstadoAtuendo.RECHAZADO);
        loQueMeSugirieron.remove(atuendo);
    }

    public void deshacerDecision (Atuendo atuendo, Guardarropa guardarropa){
        guardarropaIncluyeAtuendo(guardarropa,atuendo);

        if (atuendo.rechazado()){
            this.atuendosRechazados.remove(atuendo);
        }

        if(atuendo.aceptado()){
            this.atuendosAceptados.remove(atuendo);
        }

        atuendo.cambiarEstado(EstadoAtuendo.NUEVO);
    }

    private void guardarropaIncluyeAtuendo(Guardarropa guardarropa, Atuendo atuendo){
        if(guardarropa.incluye(atuendo)){
            return;
        }
        throw new AtuendoNoPerteneceAGuardarropa("El atuendo no pertenece al guardarropa");
    }

    private boolean superoElLimiteDePrendas(Guardarropa guardarropa){
        return prendasMaximas < guardarropa.cantidadPrendas();
    }

    public void compartirGuardarropa(Guardarropa guardarropa, Usuario usuario){
        usuario.agragarGuardarropa(guardarropa);
    }

    public void agragarGuardarropa(Guardarropa guardarropa){
        this.guardarropas().add(guardarropa);
    }

    public HashSet<Atuendo> pedirSugerenciaParaEventoDeTodosLosGuadaropas(Evento evento, ProveedorClima proveedor, boolean flexible) {
        HashSet<Atuendo> atuendos = new HashSet<Atuendo>();
        this.guardarropas().forEach(guardarropa -> atuendos.addAll(guardarropa.sugerirParaEvento(evento, proveedor, flexible, this)));
        loQueMeSugirieron.addAll(atuendos);
        return atuendos;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Notificador> getMediosDeNotificacion() {
        return mediosDeNotificacion;
    }

    public void setMediosDeNotificacion(Set<Notificador> medios) {
        this.mediosDeNotificacion = medios;
    }

    public void agregarMedioDeNotificacion(Notificador medio) {
        this.getMediosDeNotificacion().add(medio);
    }

    public void removerMedioDeNotificacion(Notificador medio) {
        this.getMediosDeNotificacion().remove(medio);
    }

    public Set<Atuendo> notificarme(Evento evento, ProveedorClima proveedor, boolean flexible) {
        LocalDateTime hoy = LocalDateTime.now();
        if (evento.getFechaYHora().getYear() - hoy.getYear() == 0 && evento.getFechaYHora().getDayOfYear() - hoy.getDayOfYear() == 0 && evento.getFechaYHora().getHour() - hoy.getHour() < 1) {
            HashSet<Atuendo> sugerencias = this.pedirSugerenciaParaEventoDeTodosLosGuadaropas(evento, proveedor, flexible);
            this.getMediosDeNotificacion().forEach(medio -> medio.notificar(evento, sugerencias));
            return sugerencias;
        }
        return null;
    }

    public void crearEvento(String nombre, ProveedorClima proveedor, LocalDateTime unaFecha, ModoDeRepeticion modo, boolean flexible) {
        Evento evento = new Evento(nombre, proveedor, unaFecha, false, modo, this, flexible);
        this.agregarEvento(evento);
    }

    public void removerEvento(Evento evento) {
        this.getEventos().remove(evento);
    }

    public void destruirEvento(Evento evento) {
        this.removerEvento(evento);
    }

    public void serAvisado () {
        this.eventos.forEach(evento -> this.notificarme(evento, evento.getProveedor(), evento.getFlexible()));
    }

    public void modificarCoeficiente(Categoria categoria, int valor){
        switch (categoria){
            case PARTE_SUPERIOR:
                coeficienteSuperior += valor;
                break;
            case PARTE_INFERIOR:
                coeficienteInferior+= valor;
                break;
            case CALZADO:
                coeficienteCalzado+= valor;
                break;
            case CARA:
                coeficienteCara+= valor;
                break;
            case MANOS:
                coeficienteManos+= valor;
                break;
            case CABEZA:
                coeficienteCabeza+= valor;
                break;
            case CUELLO:
                coeficienteCuello+= valor;
                break;
            default:
                System.out.println("algo hiciste mal pipi");
                break;
        }
    }

    public Integer getId() {
        return id;
    }

    public void friolentarEn(Categoria categoria){
        modificarCoeficiente(categoria,-5);
    }

    public void calentarEn(Categoria categoria){
        modificarCoeficiente(categoria,5);
    }

    public void procesarCalificacion (Calificacion calificacion){
        calificacion.friolentoEn.stream().forEach(categoria -> calentarEn(categoria));
        calificacion.calurosoEn.stream().forEach(categoria -> friolentarEn(categoria));
    }

}

