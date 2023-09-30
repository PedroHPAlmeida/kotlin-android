package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.dao.ProdutoDAO
import br.com.alura.orgs.databinding.ListaProdutosActivityBinding
import br.com.alura.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {

    private val produtoDAO = ProdutoDAO()
    private val adapter = ListaProdutosAdapter(context = this, produtos = produtoDAO.buscaTodos())
    private val binding by lazy {
        ListaProdutosActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(this.produtoDAO.buscaTodos())
    }

    private fun configuraRecyclerView() {
        val recyclerView = this.binding.activityListaProdutosRecyclerView
        recyclerView.adapter = this.adapter
    }

    private fun configuraFab() {
        val fab = this.binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

}