package dominio.enumerados;

import dominio.Atuendo;
import dominio.Combinador;
import dominio.Prenda;
import dominio.Usuario;
import net.sf.oval.internal.util.LinkedSet;

import java.util.*;
import java.util.stream.Collectors;

public enum Categoria {
    PARTE_SUPERIOR {

        public Set<List<Prenda>> eliminarCombinacionesInvalidasDeSuperior (Set<List<Prenda>> prendas ){
            return prendas.stream().filter(combinacion->esValidaLaCombinacion(combinacion)).collect(Collectors.toSet());
        }
        public boolean esValidaLaCombinacion (List<Prenda> prendas){

            return tieneCantidadValidaDeCapa(prendas,Capa.INFERIOR) &&
                    tieneCantidadValidaDeCapa(prendas,Capa.MEDIA) &&
                    tieneCantidadValidaDeCapa(prendas,Capa.ALTA) &&
                    tieneCantidadValidaDeCapa(prendas,Capa.FINAL);
        }

        public boolean tieneCantidadValidaDeCapa(List<Prenda>prendas, Capa unaCapa){
            return unaCapa.hayCantidadValida(prendas);
        }

        public List<Prenda> convertirALista (Prenda prenda){
            List<Prenda> conjunto = new ArrayList<>(Arrays.asList(prenda));
            return conjunto;
        }
        public boolean tieneCantidadValidaDePrendas (List<Prenda> prendas) {return false;}
    },
    CALZADO {
        public  Set<List<Prenda>> eliminarCombinacionesInvalidasDeSuperior (Set<List<Prenda>> prendas ){return null;};
        public boolean tieneCantidadValidaDePrendas (List<Prenda> prendas) {return false;}

    }
    ,PARTE_INFERIOR{
        public  Set<List<Prenda>> eliminarCombinacionesInvalidasDeSuperior (Set<List<Prenda>> prendas ){return null;};
        public boolean tieneCantidadValidaDePrendas (List<Prenda> prendas) {return false;}

    }
    ,CABEZA{
        public  Set<List<Prenda>> eliminarCombinacionesInvalidasDeSuperior (Set<List<Prenda>> prendas ){return null;};
        public boolean tieneCantidadValidaDePrendas (List<Prenda> prendas){
            return prendas.stream().filter(prenda->prenda.tipo().equals(this)).collect(Collectors.toList()).size()<=1;
        }


    }
    ,CARA{
        public  Set<List<Prenda>> eliminarCombinacionesInvalidasDeSuperior (Set<List<Prenda>> prendas ){return null;};
        public boolean tieneCantidadValidaDePrendas (List<Prenda> prendas){
            return prendas.stream().filter(prenda->prenda.tipo().equals(this)).collect(Collectors.toList()).size()<=1;
        }

    }
    ,MANOS{
        public  Set<List<Prenda>> eliminarCombinacionesInvalidasDeSuperior (Set<List<Prenda>> prendas ){return null;};
        public boolean tieneCantidadValidaDePrendas (List<Prenda> prendas){
            return prendas.stream().filter(prenda->prenda.tipo().equals(this)).collect(Collectors.toList()).size()<=1;
        }

    }
    ,CUELLO{
        public  Set<List<Prenda>> eliminarCombinacionesInvalidasDeSuperior (Set<List<Prenda>> prendas ){return null;};
        public boolean tieneCantidadValidaDePrendas (List<Prenda> prendas){
            return prendas.stream().filter(prenda->prenda.tipo().equals(this)).collect(Collectors.toList()).size()<=1;
        }

    };


    public static boolean estaAbrigadoEn(Atuendo atuendo, Double temperatura, Usuario usuario, int coeficiente, Categoria categoria) {
        return atuendo.abrigoEn(categoria) >= (coeficiente - temperatura) && atuendo.abrigoEn(categoria) <= (coeficiente - temperatura + 10);

    }
    public abstract  Set<List<Prenda>> eliminarCombinacionesInvalidasDeSuperior (Set<List<Prenda>> prendas );



    public static  Set<List<Prenda>> eliminarCombinacionesInvalidasDeAccesorios(Set<List<Prenda>> prendas ){
        return prendas.stream().filter(combinacion->esValidaLaCombinacionDeAccesorios(combinacion)).collect(Collectors.toSet());
    }
    public static boolean esValidaLaCombinacionDeAccesorios(List<Prenda> prendas){
        return Categoria.CABEZA.tieneCantidadValidaDePrendas(prendas) &&
                Categoria.MANOS.tieneCantidadValidaDePrendas(prendas) &&
                Categoria.CUELLO.tieneCantidadValidaDePrendas(prendas) &&
                Categoria.CARA.tieneCantidadValidaDePrendas(prendas);

    }

    public abstract boolean tieneCantidadValidaDePrendas (List<Prenda> prendas);

}


