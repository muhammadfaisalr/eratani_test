package id.muhammadfaisal.eratanitestinterview.model

data class Query(
    var queryTo: String,
    var queryValue: String
) {
    companion object {
        const val BRAND = "Brand"
        const val TYPE = "Type"
        const val YEAR = "Year"
        const val ALL = "Year"


        fun copyWith(queryTo: String, queryValue: String): Query {
            return Query(queryTo, queryValue)
        }
    }

}
