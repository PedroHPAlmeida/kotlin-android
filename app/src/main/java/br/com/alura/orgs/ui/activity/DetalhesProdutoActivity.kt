package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.databinding.DetalhesProdutoActivityBinding
import br.com.alura.orgs.extensions.tentaCarregarImagem
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.utils.formataParaMoedaPtBr


class DetalhesProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        DetalhesProdutoActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        intent.getParcelableExtra<Produto>("produto")?.let {produto: Produto ->
            preencheCampos(produto)
        }
    }

    private fun preencheCampos(produto: Produto) {
        this.binding.detalhesProdutoImageview.tentaCarregarImagem(
            this.binding.root.context,
            produto.imagemUrl
        )
        this.binding.detalhesProdutoValor.text = formataParaMoedaPtBr(produto.valor)
        this.binding.detalhesProdutoTitulo.text = produto.nome
        this.binding.detalhesProdutoDescricao.text = produto.descricao
    }

}