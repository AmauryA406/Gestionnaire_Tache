/**
 * Exercice 2 : Classes et Classes de Données
 *
 * Objectifs :
 * - Créer une classe et une classe de données
 * - Utiliser des propriétés immutables (val) et mutables (var)
 * - Instancier des objets et accéder à leurs propriétés
 * - Travailler avec MutableList
 */

/**
 * Classe de données Task représentant une tâche
 *
 * @param id Identifiant unique de la tâche
 * @param description Description de la tâche
 * @param isDone Statut de completion de la tâche (par défaut false)
 */
data class Task(
    val id: Int,
    val description: String,
    var isDone: Boolean = false
) {
    /**
     * Fonction pour marquer la tâche comme terminée
     */
    fun markAsDone() {
        isDone = true
    }

    /**
     * Fonction pour marquer la tâche comme non terminée
     */
    fun markAsUndone() {
        isDone = false
    }

    /**
     * Retourne une représentation formatée de la tâche
     */
    fun getFormattedStatus(): String {
        val status = if (isDone) "✅ Terminée" else "⏳ En cours"
        return "[$id] $description - $status"
    }
}

/**
 * Classe TaskManager pour gérer une collection de tâches
 */
class TaskManager {
    // Liste mutable privée pour stocker les tâches
    private val tasks: MutableList<Task> = mutableListOf()

    // Compteur pour générer des IDs uniques
    private var nextId: Int = 1

    /**
     * Ajoute une nouvelle tâche avec la description donnée
     *
     * @param description Description de la nouvelle tâche
     * @return La tâche créée
     */
    fun addTask(description: String): Task {
        val newTask = Task(nextId, description)
        tasks.add(newTask)
        nextId++
        println("✅ Tâche ajoutée : ${newTask.getFormattedStatus()}")
        return newTask
    }

    /**
     * Affiche toutes les tâches avec leur ID et statut
     */
    fun listTasks() {
        if (tasks.isEmpty()) {
            println("📝 Aucune tâche disponible.")
            return
        }

        println("\n📋 === LISTE DES TÂCHES ===")
        tasks.forEach { task ->
            println("   ${task.getFormattedStatus()}")
        }
        println("📊 Total : ${tasks.size} tâche(s)\n")
    }

    /**
     * Retourne le nombre total de tâches
     */
    fun getTaskCount(): Int = tasks.size

    /**
     * Retourne le nombre de tâches terminées
     */
    fun getCompletedTaskCount(): Int = tasks.count { it.isDone }

    /**
     * Retourne le nombre de tâches en cours
     */
    fun getPendingTaskCount(): Int = tasks.count { !it.isDone }

    /**
     * Trouve une tâche par son ID
     *
     * @param id L'ID de la tâche à trouver
     * @return La tâche trouvée ou null si non trouvée
     */
    fun findTaskById(id: Int): Task? {
        return tasks.find { it.id == id }
    }

    /**
     * Retourne une copie en lecture seule de la liste des tâches
     */
    fun getAllTasks(): List<Task> = tasks.toList()

    /**
     * Affiche un résumé des statistiques
     */
    fun displaySummary() {
        println("\n📊 === RÉSUMÉ ===")
        println("📝 Total des tâches : ${getTaskCount()}")
        println("✅ Tâches terminées : ${getCompletedTaskCount()}")
        println("⏳ Tâches en cours : ${getPendingTaskCount()}")

        if (getTaskCount() > 0) {
            val completionRate = (getCompletedTaskCount().toDouble() / getTaskCount() * 100).toInt()
            println("📈 Taux de completion : $completionRate%")
        }
        println()
    }
}

/**
 * Fonction de démonstration des classes de données
 */
fun demonstrateDataClass() {
    println("=== DÉMONSTRATION CLASSE DE DONNÉES ===")

    // Création de tâches
    val task1 = Task(1, "Apprendre Kotlin")
    val task2 = Task(2, "Faire les exercices", true)
    val task3 = Task(3, "Réviser pour l'examen")

    println("📝 Tâches créées :")
    println("   ${task1.getFormattedStatus()}")
    println("   ${task2.getFormattedStatus()}")
    println("   ${task3.getFormattedStatus()}")

    // Modification d'une tâche
    println("\n🔄 Marquage de la tâche 1 comme terminée...")
    task1.markAsDone()
    println("   ${task1.getFormattedStatus()}")

    // Démonstration des fonctions générées automatiquement par data class
    println("\n🔍 Fonctions automatiques des data class :")
    println("   task1.toString() = $task1")
    println("   task1 == Task(1, \"Apprendre Kotlin\", true) = ${task1 == Task(1, "Apprendre Kotlin", true)}")

    // Copy avec modification
    val task1Copy = task1.copy(description = "Maîtriser Kotlin")
    println("   Copie modifiée : $task1Copy")

    println()
}

/**
 * Fonction de démonstration du TaskManager
 */
fun demonstrateTaskManager() {
    println("=== DÉMONSTRATION TASK MANAGER ===")

    // Création du gestionnaire
    val taskManager = TaskManager()

    // Ajout de tâches
    println("➕ Ajout de tâches...")
    taskManager.addTask("Lire la documentation Kotlin")
    taskManager.addTask("Implémenter les exercices")
    taskManager.addTask("Tester le code")
    taskManager.addTask("Rédiger le rapport")

    // Affichage de la liste
    taskManager.listTasks()

    // Affichage du résumé
    taskManager.displaySummary()

    // Test de recherche
    println("🔍 Recherche de la tâche ID 2...")
    val foundTask = taskManager.findTaskById(2)
    if (foundTask != null) {
        println("   Trouvée : ${foundTask.getFormattedStatus()}")
        foundTask.markAsDone()
        println("   Marquée comme terminée : ${foundTask.getFormattedStatus()}")
    } else {
        println("   ❌ Tâche non trouvée")
    }

    // Affichage final
    taskManager.listTasks()
    taskManager.displaySummary()
}

/**
 * Fonction principale pour tester toutes les fonctionnalités
 */
fun main() {
    println("=== EXERCICE 2 : CLASSES ET CLASSES DE DONNÉES ===\n")

    // 1. Démonstration des classes de données
    demonstrateDataClass()

    println("\n" + "=".repeat(50) + "\n")

    // 2. Démonstration du TaskManager
    demonstrateTaskManager()

    println("✅ Exercice 2 terminé !")
}