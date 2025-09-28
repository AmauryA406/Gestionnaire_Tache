/**
 * Exercice 1 : Variables, Types, Fonctions, Entrée/Sortie
 *
 * Objectifs :
 * - Comprendre val vs var
 * - Travailler avec les types de base (Int, String, Boolean)
 * - Lire depuis et imprimer vers la console
 * - Gérer les valeurs nulles en toute sécurité (null-safety)
 */

/**
 * Fonction qui demande le nom à l'utilisateur et le salue
 */
fun greetUser() {
    print("Entrez votre nom : ")
    val userName = readLine()

    // Gestion de la null-safety
    if (userName != null && userName.isNotBlank()) {
        println("Bonjour, $userName ! Bienvenue dans le gestionnaire de tâches.")
    } else {
        println("Bonjour, utilisateur anonyme !")
    }
}

/**
 * Fonction qui additionne deux entiers
 */
fun addNumbers(a: Int, b: Int): Int {
    return a + b
}

/**
 * Fonction pour tester la lecture d'un entier depuis la console
 */
fun testIntegerInput() {
    println("\n--- Test de lecture d'entier ---")
    print("Entrez un nombre entier : ")

    val userInput = readLine()?.toIntOrNull()

    if (userInput != null) {
        println("Vous avez entré le nombre : $userInput")
        println("Le double de ce nombre est : ${userInput * 2}")
    } else {
        println("❌ Erreur : Valeur invalide ! Veuillez entrer un nombre entier.")
    }
}

/**
 * Fonction pour démontrer val vs var
 */
fun demonstrateValVar() {
    println("\n--- Démonstration val vs var ---")

    // val : immutable (ne peut pas être réassigné)
    val constantValue = 42
    println("Valeur constante (val) : $constantValue")

    // var : mutable (peut être réassigné)
    var mutableValue = 10
    println("Valeur mutable initiale (var) : $mutableValue")

    mutableValue = 20
    println("Valeur mutable après modification : $mutableValue")

    // Exemple avec des types
    val name: String = "Kotlin"  // Type explicite
    val age = 25                 // Type inféré (Int)
    val isStudent: Boolean = true

    println("Nom : $name, Âge : $age, Étudiant : $isStudent")
}

/**
 * Fonction principale pour tester toutes les fonctionnalités
 */
fun main() {
    println("=== EXERCICE 1 : VARIABLES, TYPES, FONCTIONS ===")

    // 1. Saluer l'utilisateur
    greetUser()

    // 2. Test de la fonction addNumbers
    println("\n--- Test de la fonction addNumbers ---")
    val num1 = 15
    val num2 = 25
    val sum = addNumbers(num1, num2)
    println("$num1 + $num2 = $sum")

    // 3. Test de lecture d'entier avec gestion d'erreur
    testIntegerInput()

    // 4. Démonstration val vs var
    demonstrateValVar()

    println("\n✅ Exercice 1 terminé !")
}