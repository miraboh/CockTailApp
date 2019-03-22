package com.example.cocktailapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var volleyCocktails: RequestQueue? = null
    var cocktailsList: ArrayList<Cocktails>? = null
    var cocktailsAdapter: CocktailListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val urlString = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic"

        volleyCocktails = Volley.newRequestQueue(this)
        cocktailsList = ArrayList<Cocktails>()

        cocktailsAdapter = CocktailListAdapter(cocktailsList!!, this)
        layoutManager = LinearLayoutManager(this)
        recyclerViewID.layoutManager = layoutManager
        recyclerViewID.adapter = cocktailsAdapter

        getCocktails(urlString)

    }

    fun getCocktails(url: String){
        val cocktailsRequest = JsonObjectRequest(Request.Method.GET,
            url, Response.Listener {
                response: JSONObject ->
                try {

                    val resultArray = response.getJSONArray("drinks")
                    for (i in 0..resultArray.length() - 1){
                        val cocktailsObj = resultArray.getJSONObject(i)

                        val des = cocktailsObj.getString("strDrink")
                        val id = cocktailsObj.getString("idDrink")
                        val img = cocktailsObj.getString("strDrinkThumb")

                        val cocktails = Cocktails()

                        cocktails.des = des
                        cocktails.img = img
                        cocktails.id = id

                        cocktailsList!!.add(cocktails)

                        cocktailsAdapter = CocktailListAdapter(cocktailsList!!, this)
                        layoutManager = LinearLayoutManager(this)
                        recyclerViewID.layoutManager = layoutManager
                        recyclerViewID.adapter = cocktailsAdapter
                    }

                    cocktailsAdapter?.notifyDataSetChanged()

                }catch (e: JSONException){e.printStackTrace()}
            },
            Response.ErrorListener { 
                error: VolleyError? ->
                try {

                }catch (e: JSONException){e.printStackTrace()}
            })

        volleyCocktails!!.add(cocktailsRequest)
    }
}
