package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.dao.ProdutoDAO
import br.com.alura.orgs.databinding.FormularioProdutoActivityBinding
import br.com.alura.orgs.extensions.tentaCarregarImagem
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        FormularioProdutoActivityBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "Cadastrar produto"
        configuraBotaoSalvar()
        configuraImageView()
        setContentView(this.binding.root)
    }

    private fun configuraImageView() {
        this.binding.formularioProdutoImage.setOnClickListener {
            FormularioImagemDialog(this).mostra(this.url) { imagem ->
                this.url  = imagem
                binding.formularioProdutoImage.tentaCarregarImagem(this, this.url)
            }
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = this.binding.formularioProdutoBotaoSalvar
        val produtoDAO = ProdutoDAO()
        botaoSalvar.setOnClickListener {
            val produto = criaProduto()
            produtoDAO.adiciona(produto)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoValor = this.binding.formularioProdutoValor
        val valor = if (campoValor.text.toString().isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(campoValor.text.toString())
        }
        return Produto(
            nome = this.binding.formularioProdutoNome.text.toString(),
            descricao = this.binding.formularioProdutoDescricao.text.toString(),
            valor = valor,
            imagemUrl = this.url
        )
    }

}
