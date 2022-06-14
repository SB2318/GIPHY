import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy.DataModel
import com.example.giphy.R

class DataAdapter(ctx:Context,modelList:ArrayList<DataModel>):
     RecyclerView.Adapter<DataAdapter.DataHolder>() {

   private val ctx: Context
   private val   modelList:ArrayList<DataModel>
   private var itemClickListener: OnitemClickListener?=null

     init{
        this.ctx=ctx;
        this.modelList= modelList;
     }

    interface OnitemClickListener{
        fun onItemClick( pos:Int)
    }

    fun setOnItemClickListener( listener:OnitemClickListener){

        itemClickListener= listener
    }


    override fun onCreateViewHolder( parent: ViewGroup, viewType:Int):DataHolder{

        val view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return DataHolder(view);
    }


    override fun onBindViewHolder(holder:DataAdapter.DataHolder,  position:Int) {

        val data = modelList[position];

        Glide.with(ctx).load(data.imageUrl).into(holder.imageView)

        holder.imageView.setOnClickListener {

            if (itemClickListener != null) {
                if (position != RecyclerView.NO_POSITION) {

                    itemClickListener!!.onItemClick(position);
                }
            }
        }
    }


        class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val imageView: ImageView;

            init {
                imageView = itemView.findViewById(R.id.item_image);
            }

        }


        override fun getItemCount(): Int {
            return modelList.size
        }

    }
