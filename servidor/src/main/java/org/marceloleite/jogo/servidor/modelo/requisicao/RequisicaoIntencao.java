package org.marceloleite.jogo.servidor.modelo.requisicao;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.modelo.TipoIntencao;

public class RequisicaoIntencao implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "O código de identificação da empresa não pode ser nulo.")
	private UUID idEmpresa;
	
	@NotNull(message = "O tipo de intenção não pode ser nulo.")
	private TipoIntencao tipo;
	
	@NotNull(message = "O código de indentificação do produto não pode ser nulo.")
	private Long idProduto; 
	
	@NotNull(message = "O preço unitário não pode ser nulo.")
	@Min(value = 0, message = "O preço unitário deve ser maior do que zero.")
	private Double precoUnitario;
	
	@NotNull(message = "A quantidade não pode ser nula.")
	@Min(value = 0, message = "Q quantidade deve ser maior do que zero.")
	private Double quantidade;
	
	private RequisicaoIntencao() {
		// Construtor padrão para deserialização de objetos desta classe.
	}

	public UUID getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(UUID idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public TipoIntencao getTipo() {
		return tipo;
	}

	public void setTipo(TipoIntencao tipo) {
		this.tipo = tipo;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
}
