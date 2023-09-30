package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.extensions.tentaCarregarImagem
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.activity.DetalhesProdutoActivity
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
        holder.cliqueProduto(produto)
    }

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val nome = binding.produtoItemNome
        private val descricao = binding.produtoItemDescricao
        private val valor = binding.produtoItemValor

        fun vincula(produto: Produto) {
            this.nome.text = produto.nome
            this.descricao.text = produto.descricao
            this.valor.text = formataParaMoedaPtBr(produto.valor)
            this.binding.produtoItemImage.tentaCarregarImagem(
                this.binding.root.context,
                produto.imagemUrl
            )
        }

        private fun formataParaMoedaPtBr(valor: BigDecimal): String? {
            val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatador.format(valor)
        }

        fun cliqueProduto(produto: Produto) {
            this.binding.root.setOnClickListener {
                val context = this.binding.root.context
                val intent = Intent(context, DetalhesProdutoActivity::class.java).apply {
                    putExtra("produto", produto)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
