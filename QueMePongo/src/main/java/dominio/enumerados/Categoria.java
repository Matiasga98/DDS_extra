package dominio.enumerados;

import dominio.Atuendo;
import dominio.Prenda;
import dominio.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Categoria {
    PARTE_SUPERIOR {
        public Set<List<Prenda>> obtenerCombinacionSuperiores(Set<Prenda> superiores) {
            Set<List<Prenda>> combinacionesSuperioresValidas = new HashSet<List<Prenda>>();


            //Method Mati

            List<Prenda> superioresDeCapaBaja = superiores.stream().filter(prenda -> prenda.tipo().Capa() <=2).collect(Collectors.toList());
            superioresDeCapaBaja.stream().forEach(prenda -> this.armarCondicionInicialSuperiores(prenda, combinacionesSuperioresValidas,superiores));
            return combinacionesSuperioresValidas;
        }

        public void armarCondicionInicialSuperiores(Prenda prenda, Set<List<Prenda>> combinacionesSuperioresValidas,Set<Prenda> superiores){
            List<Prenda> conjuntoHastaAhora = new ArrayList<>();

            conjuntoHastaAhora.add(prenda);

            combinacionesSuperioresValidas.add(conjuntoHastaAhora);
            List<Prenda> conjunto = new ArrayList<>(conjuntoHastaAhora);
            this.armarParteSuperior(conjunto, combinacionesSuperioresValidas,superiores);
        }
        public void armarParteSuperior(List<Prenda> conjunto, Set<List<Prenda>> combinacionesSuperioresValidas,Set<Prenda> superiores){
            List<Prenda> superioresAPonerArriba = this.armarSuperioresAPonerArriba(conjunto,superiores);
            if (superioresAPonerArriba.stream().anyMatch(prenda -> prenda.tipo().Capa() > conjunto.get(conjunto.size()-1).tipo().Capa())){
                superioresAPonerArriba.stream().forEach(unaPrenda-> this.completarParteSuperior(conjunto,unaPrenda, combinacionesSuperioresValidas,superiores));
            }
        }

        public void completarParteSuperior (List<Prenda> conjuntoHastaAhora, Prenda prenda, Set<List<Prenda>> combinacionesSuperioresValidas,Set<Prenda> superiores){
            if(this.seCumpleCondicionParaAgregarSuperior(conjuntoHastaAhora,prenda) && !conjuntoHastaAhora.contains(prenda)){
                List<Prenda> conjuntoNuevo = new ArrayList<>();
                conjuntoNuevo.addAll(conjuntoHastaAhora);
                conjuntoNuevo.add(prenda);
                combinacionesSuperioresValidas.add(conjuntoNuevo);
                this.armarParteSuperior(conjuntoNuevo, combinacionesSuperioresValidas,superiores);
            }
        }

        public boolean seCumpleCondicionParaAgregarSuperior(List<Prenda> conjunto, Prenda prenda){
            if (prenda.tipo().Capa() == conjunto.get(conjunto.size()-1).tipo().Capa()){
                return conjunto.stream().filter(unaPrenda -> unaPrenda.tipo().Capa() == prenda.tipo().Capa() ).collect(Collectors.toList()).size()<2;

            }
            return true;
        }
        public List<Prenda> armarSuperioresAPonerArriba (List<Prenda> conjunto,Set<Prenda> superiores){

            return superiores.stream().filter(unaPrenda->unaPrenda.tipo().Capa() >=conjunto.get(conjunto.size() - 1).tipo().Capa()).collect(Collectors.toList());
        }


    },
    CALZADO {
        public  Set<List<Prenda>> obtenerCombinacionSuperiores(Set<Prenda> superiores){return null;};
    }
    ,PARTE_INFERIOR{
        public  Set<List<Prenda>> obtenerCombinacionSuperiores(Set<Prenda> superiores){return null;};
    }
    ,CABEZA{
        public  Set<List<Prenda>> obtenerCombinacionSuperiores(Set<Prenda> superiores){return null;};
    }
    ,CARA{
        public  Set<List<Prenda>> obtenerCombinacionSuperiores(Set<Prenda> superiores){return null;};
    }
    ,MANOS{
        public  Set<List<Prenda>> obtenerCombinacionSuperiores(Set<Prenda> superiores){return null;};
    }
    ,CUELLO{
        public  Set<List<Prenda>> obtenerCombinacionSuperiores(Set<Prenda> superiores){return null;};
    }
    ;


    public static boolean estaAbrigadoEn(Atuendo atuendo, Double temperatura, Usuario usuario, int coeficiente, Categoria categoria) {
        return atuendo.abrigoEn(categoria) >= (coeficiente - temperatura) && atuendo.abrigoEn(categoria) <= (coeficiente - temperatura + 10);

    }

    public abstract Set<List<Prenda>> obtenerCombinacionSuperiores(Set<Prenda> superiores);



    public static Set<List<Prenda>> obtenerCombinacionAccesorios(Set<Prenda> accesorios){
        Set<List<Prenda>> combinacionesAccesoriosValidas = new HashSet<List<Prenda>>();
        Set<Set<Prenda>> combinacionesAccesoriosValidasConRepeticion = new HashSet<Set<Prenda>>();
        accesorios.stream().forEach(unAccesorio->armarCondicionInicialAccesorios(unAccesorio,combinacionesAccesoriosValidasConRepeticion,accesorios));
        combinacionesAccesoriosValidasConRepeticion.forEach(lista -> combinacionesAccesoriosValidas.add(new ArrayList<>(lista)));
        return combinacionesAccesoriosValidas;
    }

    public static void armarCondicionInicialAccesorios(Prenda prenda, Set<Set<Prenda>> combinacionesAccesoriosValidasConRepeticion,Set<Prenda> accesorios){
        Set<Prenda> conjuntoAccesorios = new HashSet<>();
        conjuntoAccesorios.add(prenda);
        combinacionesAccesoriosValidasConRepeticion.add(conjuntoAccesorios);
        Set<Prenda> conjunto = new HashSet<>(conjuntoAccesorios);
        armarAccesorios(conjunto,combinacionesAccesoriosValidasConRepeticion,accesorios);
    }

    public static void armarAccesorios(Set<Prenda> combinacion, Set<Set<Prenda>> combinacionesAccesoriosValidasConRepeticion, Set<Prenda> accesorios){
        List<Prenda> accesoriosACombinar = arnarAccesoriosACombinar(combinacion, accesorios);
        accesoriosACombinar.stream().forEach(unAccesorio->completarAccesorios(combinacion,unAccesorio,combinacionesAccesoriosValidasConRepeticion, accesorios));
    }

    public static void completarAccesorios(Set<Prenda> combinacion, Prenda accesorio, Set<Set<Prenda>> combinacionesAccesoriosValidasConRepeticion, Set<Prenda> accesorios){
        if (!combinacion.contains(accesorio)) {
            Set<Prenda> conjuntoNuevo = new HashSet<>();
            conjuntoNuevo.addAll(combinacion);
            conjuntoNuevo.add(accesorio);

            combinacionesAccesoriosValidasConRepeticion.add(conjuntoNuevo);

            armarAccesorios(conjuntoNuevo, combinacionesAccesoriosValidasConRepeticion, accesorios);
        }
    }


    public static boolean seCumpleCondicionParaAgregarAccesorio(Set<Prenda> combinacion, Prenda prenda){
        return combinacion.stream().allMatch(accesorio->accesorio.tipo().categoria()!=prenda.tipo().categoria());
    }


    public static List<Prenda> arnarAccesoriosACombinar(Set<Prenda> combinacion, Set<Prenda> accesorios){

          return accesorios.stream().filter(unaPrenda->seCumpleCondicionParaAgregarAccesorio(combinacion,unaPrenda)).collect(Collectors.toList());
    }
}


