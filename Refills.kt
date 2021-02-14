class Refills(
        val cod : String,
        val description : String,
        val duration : String,
        val price : Int,
        val title : String
) {

    companion object{

        fun getRefillsFromFile(fileName: String, context : Context): JSONObject{

            //val refillsList = ArrayList<Refills>()
            lateinit var refillsJSON : JSONObject
            try {
                // loading data
                val jsonString = loadJsonFromAssets(fileName,context)
                val json = JSONObject(jsonString)
                refillsJSON = json
                //val refills = json.getJSONArray(objName)

                //get refills objects from data

                /*(0 until refills.length()).mapTo(refillsList){
                    Refills(
                            refills.getJSONObject(it).getString("cod"),
                            refills.getJSONObject(it).getString("description"),
                            refills.getJSONObject(it).getString("duration"),
                            refills.getJSONObject(it).getInt("price"),
                            refills.getJSONObject(it).getString("title")


                            )
                }*/

            }catch (e: JSONException){
                e.printStackTrace()
            }

            return refillsJSON
        }

        private fun loadJsonFromAssets(filename: String, context: Context): String?{

            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            }catch (ex: java.io.IOException){
                ex.printStackTrace()
                return null
            }

            return json
        }
    }

}
