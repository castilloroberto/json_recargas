

package com.raywenderlich.android.alltherecipes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

  private lateinit var listView : ListView
  private val refillsJSON = Refills.getRefillsFromFile("data.json",this)



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    listView = findViewById(R.id.recipe_list_view)
    //val recipeList = Recipe.getRecipesFromFile("data.json",this

  }

  private fun getArrays(json:JSONObject,objName: String):ArrayList<Refills>{
    val refillsList = ArrayList<Refills>()
    val refills = json.getJSONArray(objName)

    //get refills objects from data

    (0 until refills.length()).mapTo(refillsList){
        Refills(
                refills.getJSONObject(it).getString("cod"),
                refills.getJSONObject(it).getString("description"),
                refills.getJSONObject(it).getString("duration"),
                refills.getJSONObject(it).getInt("price"),
                refills.getJSONObject(it).getString("title")
                )
    }
    return refillsList
  }

  fun onRadioButtonClicked(view: View) {
    if (view is RadioButton) {
      // Is the button now checked?
      val checked = view.isChecked

      // Check which radio button was clicked
      when (view.getId()) {
        R.id.rdb_super ->
          if (checked) {
            // rdb_super
            val superList = getArrays(refillsJSON,"super")
            val listSuper = arrayOfNulls<String>(superList.size)
            for (i in 0 until superList.size){
              val recipe = superList[i]
              listSuper[i] = recipe.title
            }
            val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listSuper)
            listView.adapter = adapter

          }
        R.id.rdb_calls ->
          if (checked) {
            // rdb_calls rule
            val callsList = getArrays(refillsJSON,"calls")
            val listCalls = arrayOfNulls<String>(callsList.size)
            for (i in 0 until callsList.size){
              val recipe = callsList[i]
              listCalls[i] = recipe.title
            }
            val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listCalls)
            listView.adapter = adapter
          }
        R.id.rdb_redes ->
          if (checked) {
            // rdb_redes rule
            val socialList = getArrays(refillsJSON,"social")
            val listSocial = arrayOfNulls<String>(socialList.size)
            for (i in 0 until socialList.size){
              val recipe = socialList[i]
              listSocial[i] = recipe.title
            }
            val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listSocial)
            listView.adapter = adapter
          }
      }
    }
  }

}
