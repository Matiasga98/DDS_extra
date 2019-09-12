package dominio;

import java.util.HashSet;
import java.util.Set;
import dominio.enumerados.Categoria;

import javax.persistence.*;

@Entity
public class PrendasPorCategoria {
	
	@Id
	@GeneratedValue
	@Column(name = "prendas_por_categoria_id")
	private long id;
	
	@Enumerated
	@Column(name = "categoria")
	private Categoria categoria;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "prendas", joinColumns = @JoinColumn(name = "prendas_por_categoria_id"))
	@Column(name = "prendas_id")
	private Set<Prenda> prendas = new HashSet<>();
	
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
	
	public Set<Prenda> getPrendas() {
		return this.prendas;
	}
	
	public void agregarPrenda(Prenda prenda) {
		this.prendas.add(prenda);
	}
	
	public void removerPrenda(Prenda prenda) {
		this.prendas.remove(prenda);
	}
	
}
