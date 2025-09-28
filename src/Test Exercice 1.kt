/**
 * Tests unitaires pour l'Exercice 1
 *
 * Note: Ces tests utilisent des assertions simples.
 * Dans un projet réel, vous utiliseriez JUnit ou KotlinTest.
 */

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Classe utilitaire pour capturer les sorties console
 */
class ConsoleCapture {
    private val originalOut = System.out
    private val outputStream = ByteArrayOutputStream()

    fun captureOutput(action: () -> Unit): String {
        System.setOut(PrintStream(outputStream))
        action()
        System.setOut(originalOut)
        return outputStream.toString().trim()
    }

    fun simulateInput(input: String, action: () -> Unit): String {
        val inputStream = ByteArrayInputStream(input.toByteArray())
        val originalIn = System.`in`
        System.setIn(inputStream)

        val output = captureOutput(action)

        System.setIn(originalIn)
        return output
    }
}

/**
 * Tests pour la fonction addNumbers
 */
fun testAddNumbers() {
    println("🧪 Test: addNumbers()")

    // Test 1: Addition positive
    val result1 = addNumbers(5, 3)
    assert(result1 == 8) { "❌ Échec: addNumbers(5, 3) devrait retourner 8, mais retourne $result1" }
    println("✅ Test 1 réussi: addNumbers(5, 3) = $result1")

    // Test 2: Addition avec zéro
    val result2 = addNumbers(10, 0)
    assert(result2 == 10) { "❌ Échec: addNumbers(10, 0) devrait retourner 10, mais retourne $result2" }
    println("✅ Test 2 réussi: addNumbers(10, 0) = $result2")

    // Test 3: Addition avec nombres négatifs
    val result3 = addNumbers(-5, 3)
    assert(result3 == -2) { "❌ Échec: addNumbers(-5, 3) devrait retourner -2, mais retourne $result3" }
    println("✅ Test 3 réussi: addNumbers(-5, 3) = $result3")

    // Test 4: Addition de grands nombres
    val result4 = addNumbers(1000000, 2000000)
    assert(result4 == 3000000) { "❌ Échec: addNumbers(1000000, 2000000) devrait retourner 3000000, mais retourne $result4" }
    println("✅ Test 4 réussi: addNumbers(1000000, 2000000) = $result4")

    println("🎉 Tous les tests addNumbers() ont réussi!\n")
}

/**
 * Tests pour la fonction greetUser
 */
fun testGreetUser() {
    println("🧪 Test: greetUser()")
    val capture = ConsoleCapture()

    // Test 1: Nom valide
    val output1 = capture.simulateInput("Alice\n") { greetUser() }
    assert(output1.contains("Bonjour, Alice !")) { "❌ Échec: Le salut pour 'Alice' devrait contenir 'Bonjour, Alice !'" }
    println("✅ Test 1 réussi: Salut avec nom valide")

    // Test 2: Nom vide
    val output2 = capture.simulateInput("\n") { greetUser() }
    assert(output2.contains("utilisateur anonyme")) { "❌ Échec: Le salut pour nom vide devrait contenir 'utilisateur anonyme'" }
    println("✅ Test 2 réussi: Salut avec nom vide")

    // Test 3: Espaces seulement
    val output3 = capture.simulateInput("   \n") { greetUser() }
    assert(output3.contains("utilisateur anonyme")) { "❌ Échec: Le salut pour espaces seulement devrait contenir 'utilisateur anonyme'" }
    println("✅ Test 3 réussi: Salut avec espaces seulement")

    println("🎉 Tous les tests greetUser() ont réussi!\n")
}

/**
 * Tests pour la fonction testIntegerInput (version modifiée pour les tests)
 */
fun testIntegerInputFunction() {
    println("🧪 Test: testIntegerInput()")
    val capture = ConsoleCapture()

    // Test 1: Nombre valide
    val output1 = capture.simulateInput("42\n") { testIntegerInput() }
    assert(output1.contains("Vous avez entré le nombre : 42")) { "❌ Échec: Devrait accepter le nombre 42" }
    assert(output1.contains("Le double de ce nombre est : 84")) { "❌ Échec: Le double de 42 devrait être 84" }
    println("✅ Test 1 réussi: Nombre valide (42)")

    // Test 2: Texte invalide
    val output2 = capture.simulateInput("abc\n") { testIntegerInput() }
    assert(output2.contains("❌ Erreur : Valeur invalide")) { "❌ Échec: Devrait rejeter 'abc'" }
    println("✅ Test 2 réussi: Texte invalide rejeté")

    // Test 3: Nombre négatif
    val output3 = capture.simulateInput("-15\n") { testIntegerInput() }
    assert(output3.contains("Vous avez entré le nombre : -15")) { "❌ Échec: Devrait accepter le nombre -15" }
    assert(output3.contains("Le double de ce nombre est : -30")) { "❌ Échec: Le double de -15 devrait être -30" }
    println("✅ Test 3 réussi: Nombre négatif (-15)")

    println("🎉 Tous les tests testIntegerInput() ont réussi!\n")
}

/**
 * Tests pour la démonstration val vs var
 */
fun testValVarConcepts() {
    println("🧪 Test: Concepts val vs var")

    // Test val (immutable)
    val constantValue = 42
    assert(constantValue == 42) { "❌ Échec: La valeur constante devrait être 42" }
    println("✅ Test 1 réussi: val (immutable) fonctionne")

    // Test var (mutable)
    var mutableValue = 10
    assert(mutableValue == 10) { "❌ Échec: La valeur mutable initiale devrait être 10" }

    mutableValue = 20
    assert(mutableValue == 20) { "❌ Échec: La valeur mutable modifiée devrait être 20" }
    println("✅ Test 2 réussi: var (mutable) fonctionne")

    // Test types
    val name: String = "Kotlin"
    val age = 25  // Type inféré
    val isStudent: Boolean = true

    assert(name == "Kotlin") { "❌ Échec: Le nom devrait être 'Kotlin'" }
    assert(age == 25) { "❌ Échec: L'âge devrait être 25" }
    assert(isStudent == true) { "❌ Échec: isStudent devrait être true" }
    println("✅ Test 3 réussi: Types (String, Int, Boolean) fonctionnent")

    println("🎉 Tous les tests val vs var ont réussi!\n")
}

/**
 * Tests pour la fonction toIntOrNull()
 */
fun testToIntOrNull() {
    println("🧪 Test: toIntOrNull()")

    // Test 1: Conversion réussie
    val result1 = "123".toIntOrNull()
    assert(result1 == 123) { "❌ Échec: '123'.toIntOrNull() devrait retourner 123" }
    println("✅ Test 1 réussi: Conversion '123' → 123")

    // Test 2: Conversion échouée
    val result2 = "abc".toIntOrNull()
    assert(result2 == null) { "❌ Échec: 'abc'.toIntOrNull() devrait retourner null" }
    println("✅ Test 2 réussi: Conversion 'abc' → null")

    // Test 3: Nombre négatif
    val result3 = "-456".toIntOrNull()
    assert(result3 == -456) { "❌ Échec: '-456'.toIntOrNull() devrait retourner -456" }
    println("✅ Test 3 réussi: Conversion '-456' → -456")

    // Test 4: Chaîne vide
    val result4 = "".toIntOrNull()
    assert(result4 == null) { "❌ Échec: ''.toIntOrNull() devrait retourner null" }
    println("✅ Test 4 réussi: Conversion '' → null")

    println("🎉 Tous les tests toIntOrNull() ont réussi!\n")
}

/**
 * Fonction principale pour exécuter tous les tests
 */
fun main() {
    println("=== 🧪 TESTS UNITAIRES - EXERCICE 1 ===\n")

    try {
        // Tests des fonctions individuelles
        testAddNumbers()
        testValVarConcepts()
        testToIntOrNull()

        // Tests des fonctions avec entrée utilisateur
        testGreetUser()
        testIntegerInputFunction()

        println("🎉 🎉 🎉 TOUS LES TESTS ONT RÉUSSI ! 🎉 🎉 🎉")
        println("✅ Votre Exercice 1 est parfaitement fonctionnel !")

    } catch (e: AssertionError) {
        println("❌ ÉCHEC DU TEST: ${e.message}")
        println("🔍 Vérifiez votre code et réessayez.")
    } catch (e: Exception) {
        println("❌ ERREUR INATTENDUE: ${e.message}")
        e.printStackTrace()
    }
}