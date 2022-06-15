package projet.echecmartien.modele

import com.google.gson.*
import java.lang.reflect.Type


/**
 * Classe Pion
 */
abstract class Pion {
	/**
	 * récupère la valeur du score d'un pion
	 * @return la valeur du score
	 */
	abstract fun getScore(): Int


	/**
	 * donne le chemin de coordonnées que constitue le déplacement
	 * du point de départ vers le point d'arrivée. Les déplacements autorisés sont horizontaux, verticaux, diagonaux.
	 *
	 * @param deplacement le déplacement
	 * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée.
	 * La liste ne contient pas les coordonnées du point de départ.
	 *
	 * @throws DeplacementException est levée si le déplacement n'est pas possible
	 */
	abstract fun getDeplacement(deplacement: Deplacement): List<Coordonnee>

}

class PionAdapter : JsonSerializer<Pion?>, JsonDeserializer<Pion?> {
	override fun serialize(p0: Pion?, p1: Type?, context: JsonSerializationContext?): JsonElement {
		val pion = JsonObject()
		if (p0 == null)
			return pion
		val type = when (p0) {
			is MoyenPion -> "MoyenPion"
			is GrandPion -> "GrandPion"
			else -> "PetitPion"
		}
		pion.addProperty(TYPE, type)
		return pion
	}

	@Throws(JsonParseException::class)
	override fun deserialize(
		json: JsonElement, typeOfT: Type?,
		context: JsonDeserializationContext): Pion {
		val jsonObject = json.asJsonObject
		val prim = jsonObject[TYPE] as JsonPrimitive
		val className = prim.asString
		return when {
			"GrandPion" in className -> GrandPion()
			"MoyenPion" in className -> MoyenPion()
			"PetitPion" in className -> PetitPion()
			else -> throw JsonParseException("")
		}
	}

	companion object {
		private const val TYPE = "TYPE"
	}
}