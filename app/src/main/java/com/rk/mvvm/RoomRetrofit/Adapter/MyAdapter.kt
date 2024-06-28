import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rk.mvvm.RoomRetrofit.model.Result
import com.rk.mvvm.databinding.QuoteItemBinding

class MyAdapter(private val dataSet: List<Result>,private val context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val bindind:QuoteItemBinding) : RecyclerView.ViewHolder(bindind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =QuoteItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote: Result = dataSet[position]
        holder.bindind.tvAuthor.text = quote.author
        holder.bindind.tvContent.text = quote.content
        holder.bindind.tvDateAdded.text = quote.dateAdded
        holder.bindind.tvDateModified.text = quote.dateModified
    }
    override fun getItemCount() = dataSet.size
}
