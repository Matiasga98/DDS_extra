package dominio;

import java.util.HashSet;
import dominio.enumerados.Categoria;

import javax.persistence.*;

public class PrendasPorCategoria {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	private Categoria categoria;
	
	@OneToMany
	@JoinColumn(name = "prendas_por_categoria_id")
	private HashSet<Prenda> prendas = new HashSet<>();
	
	public PrendasPorCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}
	
	public void setPrendas(HashSet<Prenda> prendas) {
		this.prendas = prendas;
	}
	
	public HashSet<Prenda> getPrendas() {
		return this.prendas;
	}
	
	public void agregarPrenda(Prenda prenda) {
		this.prendas.add(prenda);
	}
	
	public void removerPrenda(Prenda prenda) {
		this.prendas.remove(prenda);
	}
	
}
