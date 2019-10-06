package dominio.enumerados;

import dominio.Prenda;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Capa {
	INFERIOR{
		public boolean sePuedeAgregarArriba(Prenda prenda){
			return prenda.tipo().Capa()!= Capa.INFERIOR;
		}
		public boolean hayCantidadValida(List<Prenda> prendas){
			return prendas.stream().filter(prenda->prenda.tipo().Capa()== this).collect(Collectors.toList()).size()<=2 &&
					prendas.stream().filter(prenda->prenda.tipo().Capa()== this).collect(Collectors.toList()).size()>=1;
		}
		public boolean esMasChica (Capa capa){
			switch(capa){
				case INFERIOR:
					return false;
				case MEDIA:
				case FINAL:
				case ALTA:
					return true;
			}
			return false;
		}
	}, MEDIA{
		public boolean sePuedeAgregarArriba(Prenda prenda) {
			return prenda.tipo().Capa() != Capa.INFERIOR && prenda.tipo().Capa() != Capa.MEDIA;
		}
		public boolean hayCantidadValida(List<Prenda> prendas){
			return prendas.stream().filter(prenda->prenda.tipo().Capa()== this).collect(Collectors.toList()).size()<=2;
		}
		public boolean esMasChica (Capa capa){
			switch(capa){
				case INFERIOR:
				case MEDIA:
					return false;
				case FINAL:
				case ALTA:
					return true;
			}
			return false;
		}
	}, ALTA{
		public boolean sePuedeAgregarArriba(Prenda prenda) {
			return prenda.tipo().Capa() != Capa.INFERIOR && prenda.tipo().Capa() != Capa.MEDIA  && prenda.tipo().Capa() != Capa.ALTA;
		}
		public boolean hayCantidadValida(List<Prenda> prendas){
			return prendas.stream().filter(prenda->prenda.tipo().Capa()== this).collect(Collectors.toList()).size()<=2;
		}
		public boolean esMasChica (Capa capa){
			switch(capa){
				case INFERIOR:
				case MEDIA:
				case FINAL:
					return false;
				case ALTA:
					return true;
			}
			return false;
		}
	}, FINAL{
		public boolean sePuedeAgregarArriba(Prenda prenda) {
			return false;
		}
		public boolean hayCantidadValida(List<Prenda> prendas){
			return prendas.stream().filter(prenda->prenda.tipo().Capa()== this).collect(Collectors.toList()).size()<=1;
		}
		public boolean esMasChica (Capa capa){
			return false;
		}
	};
	public Capa capaMasAlta(List<Prenda> combinacion){
		Capa masAlta = Capa.INFERIOR;
		List<Capa> capas = combinacion.stream().map(prenda->prenda.tipo().Capa()).collect(Collectors.toList());

		for (int i = 0; i < combinacion.size(); i++){
			if (!capas.get(i).esMasChica(masAlta)){
				masAlta = capas.get(i);
			}
		}
		return masAlta;


	}
	public boolean esMasChica (Capa capa){
		return false;
	}
	public abstract boolean hayCantidadValida(List<Prenda> prendas);
}
