package dominio;

import java.util.HashSet;
import java.util.Set;
import dominio.enumerados.Categoria;

import javax.persistence.*;

@Entity
@Table(name="prendas_por_categoria")
public class PrendasPorCategoria {
	
	@Id
	@GeneratedValue
	@Column(name = "prendas_por_categoria_id")
	private long id;
	
	@Enumerated
	@Column(name = "categoria")
	private Categoria categoria;
	
	@OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "prendas_por_categoria_id")
	private Set<Prenda> prendas = new HashSet<>();

	public PrendasPorCategoria(){
	}

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
