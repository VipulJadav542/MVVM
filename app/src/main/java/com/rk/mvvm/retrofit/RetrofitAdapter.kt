package com.rk.mvvm.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.rk.mvvm.Home
import com.rk.mvvm.databinding.MyRetrofitItemBinding
import com.rk.mvvm.databinding.UpdateRetrofitBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class RetrofitAdapter(private val dataSet: List<Item>, private val context: Context) :
    RecyclerView.Adapter<RetrofitAdapter.ViewHolder>() {

    class ViewHolder(val bindind: MyRetrofitItemBinding) : RecyclerView.ViewHolder(bindind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MyRetrofitItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote1: Item = dataSet[position]
        holder.bindind.id.text = quote1._uuid
        holder.bindind.name.text = "Name : " + quote1.name
        holder.bindind.city.text = "City : " + quote1.city
        holder.bindind.state.text = "State :" + quote1.state
        holder.itemView.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("confirmation")
            builder.setMessage("Choose Action")
            builder.setPositiveButton("Delete") { _, _ ->
                val quote = RetrofitHelper.getInstance().create(MyAPI::class.java)
                val requestCall = quote.deleteTask(
//                    "application/json",
//                    "Bearer j1O1luD59q3dmkJDkz1lcI-7v5hGOjiS7XiHelHgTFmGDyHXiw",
                    quote1._uuid
                )

                requestCall.enqueue(object : Callback<Void> {
                    override fun onResponse(p0: Call<Void>, p1: retrofit2.Response<Void>) {
                        val intent = Intent(context, Home::class.java)
                        context.startActivity(intent)
                        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(p0: Call<Void>, p1: Throwable) {
                        Toast.makeText(context, "something went wrong", Toast.LENGTH_LONG).show()
                    }
                })
            }

            builder.setNeutralButton("Cancel") { _, _ ->
            }

            builder.setNegativeButton("Update") { _, _ ->
                val dialogBinding = UpdateRetrofitBinding.inflate(LayoutInflater.from(context))
                val dialog = AlertDialog.Builder(context).setView(dialogBinding.root)
                    .setTitle("Update Note")
                    .setPositiveButton("Update") { dialog, _ ->
                        val text1 = dialogBinding.name1.text.toString()
                        val text2 = dialogBinding.city1.text.toString()
                        val text3 = dialogBinding.state1.text.toString()
                        updateTask(text1, text2, text3, quote1._uuid)
                        val intent = Intent(context, Home::class.java)
                        context.startActivity(intent)
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                dialogBinding.name1.setText(dataSet[position].name)
                dialogBinding.city1.setText(dataSet[position].city)
                dialogBinding.state1.setText(dataSet[position].state)
                dialog.show()

            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

        }
    }

    override fun getItemCount() = dataSet.size

    private fun updateTask(name1: String, city1: String, state1: String, uuid1: String) {
        val requestBody = JSONObject().apply {
            put("name", name1)
            put("city", city1)
            put("state", state1)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val quote = RetrofitHelper.getInstance().create(MyAPI::class.java)
        val requestCall = quote.updateTask(
//            "application/json",
//            "Bearer j1O1luD59q3dmkJDkz1lcI-7v5hGOjiS7XiHelHgTFmGDyHXiw",
            uuid1,
            requestBody
        )

        requestCall.enqueue(object : Callback<insertItem> {
            override fun onResponse(p0: Call<insertItem>, p1: retrofit2.Response<insertItem>) {
                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onFailure(p0: Call<insertItem>, p1: Throwable) {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}
