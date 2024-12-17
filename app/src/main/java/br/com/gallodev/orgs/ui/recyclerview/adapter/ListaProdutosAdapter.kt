package br.com.gallodev.orgs.ui.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.orgs.databinding.ProdutoItemBinding
import br.com.gallodev.orgs.extensions.tentaCareegarImagem
import br.com.gallodev.orgs.model.Produto
import br.com.gallodev.orgs.ui.activity.DetalheProdutoActivity
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    // ViewHolder que usa o View Binding
    class ViewHolder(private val binding: ProdutoItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {


        // Método para vincular os dados com os views
        fun vincula(produto: Produto) {
            // Acessando os elementos do layout e atribuindo os valores
            binding.produtoItemNome.text = produto.nome
            binding.produtoItemDescricao.text = produto.descricao
            //variavel da implementação de moeda
            val valorEmMoeda: String = formataParaMoedaBrasileira(produto.valor)
            binding.produtoItemValor.text = valorEmMoeda

            //argumento que apresenta ou nao imagem de erro
            val visibilidade = if(produto.imagem != null){
                View.VISIBLE
            } else {
                View.GONE
            }

            //chama a variavel que apresenta imagem de erro
            binding.activityFormularioProdutoImagem.visibility = visibilidade
            // busca imagem de erro
            binding.activityFormularioProdutoImagem.tentaCareegarImagem(produto.imagem)

            // Click Listener para abrir a tela de detalhes do produto ao clicar no item
            binding.root.setOnClickListener {
                val detalhesDoProduto = Intent(context, DetalheProdutoActivity::class.java)
                detalhesDoProduto.putExtra("nome", produto.nome)
                detalhesDoProduto.putExtra("descricao", produto.descricao)
                detalhesDoProduto.putExtra("valor", formataParaMoedaBrasileira(produto.valor))
                detalhesDoProduto.putExtra("imagem", produto.imagem)
                context.startActivity(detalhesDoProduto)
            }

        }

        // implementação para formatação de moeda
        private fun formataParaMoedaBrasileira(valor: BigDecimal): String {
            val formatador: NumberFormat =
                NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val valorEmMoeda: String = formatador.format(valor)
            return formatador.format(valor)
        }
    }
    // Criação da ViewHolder com o ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, context)
    }

    // Retorna o número de itens na lista
    override fun getItemCount(): Int = produtos.size

    // Vincula os dados da lista no ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)

    }

    // Método para atualizar a lista de produtos
    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
