package br.com.gallodev.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gallodev.orgs.databinding.ActivityDetalheProdutoBinding
import coil.load

class DetalheProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // enableEdgeToEdge()

        // recebe os dados da intent
        val nome = intent.getStringExtra("nome")
        val descricao = intent.getStringExtra("descricao")
        val valor = intent.getStringExtra("valor")
        val imagem = intent.getStringExtra("imagem")

        setProdutoDetalhes(nome, descricao, valor, imagem)
    }

    // Método que atribui os valores recebidos da intent aos elementos do layout
    private fun setProdutoDetalhes(
        nome: String?,
        descricao: String?,
        valor: String?,
        imagem: String?
    ) {
        binding.detalheProdutoValor.text = valor
        binding.detalheProdutoNome.text = nome
        binding.detalheProdutoDescricao.text = descricao
        binding.imagemDetalhe.load(imagem)
    }
}
