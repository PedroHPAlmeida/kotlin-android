package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProdutoDAO
import br.com.alura.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.formulario_produto_botao_salvar)
        val produtoDAO = ProdutoDAO()
        botaoSalvar.setOnClickListener {
            val produto = criaProduto()
            produtoDAO.adiciona(produto)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoValor = findViewById<EditText>(R.id.formulario_produto_valor)
        val valor = if (campoValor.text.toString().isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(campoValor.text.toString())
        }
        return Produto(
            nome = findViewById<EditText>(R.id.formulario_produto_nome).text.toString(),
            descricao = findViewById<EditText>(R.id.formulario_produto_descricao).text.toString(),
            valor = valor
        )
    }

}
