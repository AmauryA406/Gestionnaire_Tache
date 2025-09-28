/**
 * Exercice 3 : Collections, Boucles et Menu Interactif
 * Version autonome avec toutes les classes nécessaires
 *
 * Objectifs :
 * - Utiliser MutableList et List
 * - Travailler avec les boucles (for, while) et les conditions (if, when)
 * - Créer un menu console interactif
 * - Compléter TaskManager avec markTaskDone et removeTask
 */

/**
 * Classe de données Task pour l'Exercice 3
 */
data class TaskEx3(
    val id: Int,
    val description: String,
    var isDone: Boolean = false
) {
    fun markAsDone() {
        isDone = true
    }

    fun markAsUndone() {
        isDone = false
    }

    fun getFormattedStatus(): String {
        val status = if (isDone) "✅ Terminée" else "⏳ En cours"
        return "[$id] $description - $status"
    }
}

/**
 * Classe TaskManager complète pour l'Exercice 3
 */
class TaskManagerEx3 {
    private val tasks: MutableList<TaskEx3> = mutableListOf()
    private var nextId: Int = 1

    /**
     * Ajoute une nouvelle tâche
     */
    fun addTask(description: String): TaskEx3 {
        val newTask = TaskEx3(nextId, description)
        tasks.add(newTask)
        nextId++
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
        tasks.forEachIndexed { index, task ->
            println("   ${index + 1}. ${task.getFormattedStatus()}")
        }
        println("📊 Total : ${tasks.size} tâche(s)")

        // Affichage par catégorie
        val completedTasks = tasks.filter { it.isDone }
        val pendingTasks = tasks.filter { !it.isDone }

        if (completedTasks.isNotEmpty()) {
            println("\n✅ Tâches terminées (${completedTasks.size}) :")
            completedTasks.forEach { task ->
                println("   • ${task.description}")
            }
        }

        if (pendingTasks.isNotEmpty()) {
            println("\n⏳ Tâches en cours (${pendingTasks.size}) :")
            pendingTasks.forEach { task ->
                println("   • ${task.description}")
            }
        }
        println()
    }

    /**
     * Marque une tâche comme terminée par son ID
     */
    fun markTaskDone(id: Int): Boolean {
        val task = findTaskById(id)
        return if (task != null) {
            if (task.isDone) {
                println("ℹ️ La tâche '$id' est déjà terminée.")
                false
            } else {
                task.markAsDone()
                println("✅ Tâche '$id' marquée comme terminée : ${task.description}")
                true
            }
        } else {
            println("❌ Aucune tâche trouvée avec l'ID '$id'.")
            false
        }
    }

    /**
     * Supprime une tâche par son ID
     */
    fun removeTask(id: Int): Boolean {
        val task = findTaskById(id)
        return if (task != null) {
            tasks.remove(task)
            println("🗑️ Tâche '$id' supprimée : ${task.description}")
            true
        } else {
            println("❌ Aucune tâche trouvée avec l'ID '$id'.")
            false
        }
    }

    /**
     * Recherche une tâche par mot-clé dans la description
     */
    fun searchTasks(keyword: String): List<TaskEx3> {
        if (keyword.isBlank()) {
            println("❌ Le mot-clé de recherche ne peut pas être vide.")
            return emptyList()
        }

        val foundTasks = tasks.filter {
            it.description.contains(keyword, ignoreCase = true)
        }

        if (foundTasks.isEmpty()) {
            println("🔍 Aucune tâche trouvée contenant '$keyword'.")
        } else {
            println("\n🔍 Tâches trouvées contenant '$keyword' (${foundTasks.size}) :")
            foundTasks.forEach { task ->
                println("   ${task.getFormattedStatus()}")
            }
            println()
        }

        return foundTasks
    }

    /**
     * Change le statut d'une tâche (terminée ↔ en cours)
     */
    fun toggleTaskStatus(id: Int): Boolean {
        val task = findTaskById(id)
        return if (task != null) {
            if (task.isDone) {
                task.markAsUndone()
                println("⏳ Tâche '$id' marquée comme en cours : ${task.description}")
            } else {
                task.markAsDone()
                println("✅ Tâche '$id' marquée comme terminée : ${task.description}")
            }
            true
        } else {
            println("❌ Aucune tâche trouvée avec l'ID '$id'.")
            false
        }
    }

    /**
     * Supprime toutes les tâches terminées
     */
    fun clearCompletedTasks(): Int {
        val completedTasks = tasks.filter { it.isDone }
        val removedCount = completedTasks.size

        if (removedCount == 0) {
            println("ℹ️ Aucune tâche terminée à supprimer.")
            return 0
        }

        tasks.removeAll(completedTasks)
        println("🧹 $removedCount tâche(s) terminée(s) supprimée(s).")
        return removedCount
    }

    // Méthodes utilitaires
    fun findTaskById(id: Int): TaskEx3? = tasks.find { it.id == id }
    fun getTaskCount(): Int = tasks.size
    fun getCompletedTaskCount(): Int = tasks.count { it.isDone }
    fun getPendingTaskCount(): Int = tasks.count { !it.isDone }
    fun getAllTasks(): List<TaskEx3> = tasks.toList()

    /**
     * Affiche les statistiques détaillées
     */
    fun displayDetailedStats() {
        val total = getTaskCount()
        val completed = getCompletedTaskCount()
        val pending = getPendingTaskCount()

        println("\n📊 === STATISTIQUES DÉTAILLÉES ===")
        println("📝 Total des tâches : $total")
        println("✅ Tâches terminées : $completed")
        println("⏳ Tâches en cours : $pending")

        if (total > 0) {
            val completionRate = (completed.toDouble() / total * 100).toInt()
            println("📈 Taux de completion : $completionRate%")

            val progressBar = generateProgressBar(completionRate)
            println("📊 Progrès : $progressBar")
        }

        if (tasks.isNotEmpty()) {
            val longestTask = tasks.maxByOrNull { it.description.length }
            val shortestTask = tasks.minByOrNull { it.description.length }

            println("\n📏 Analyse des descriptions :")
            println("   Plus longue : ${longestTask?.description?.take(30)}${if (longestTask?.description?.length ?: 0 > 30) "..." else ""}")
            println("   Plus courte : ${shortestTask?.description}")
        }
        println()
    }

    private fun generateProgressBar(percentage: Int): String {
        val barLength = 20
        val filledLength = (percentage * barLength / 100)
        val filled = "█".repeat(filledLength)
        val empty = "░".repeat(barLength - filledLength)
        return "[$filled$empty] $percentage%"
    }
}

/**
 * Classe pour gérer l'interface utilisateur du menu
 */
class TaskMenuUI(private val taskManager: TaskManagerEx3) {

    /**
     * Affiche le menu principal
     */
    fun displayMenu() {
        println("\n" + "=".repeat(50))
        println("📋 GESTIONNAIRE DE TÂCHES - MENU PRINCIPAL")
        println("=".repeat(50))
        println("1. ➕ Ajouter une tâche")
        println("2. 📋 Lister toutes les tâches")
        println("3. ✅ Marquer une tâche comme terminée")
        println("4. 🗑️  Supprimer une tâche")
        println("5. 🔍 Rechercher des tâches")
        println("6. 🔄 Changer le statut d'une tâche")
        println("7. 🧹 Supprimer toutes les tâches terminées")
        println("8. 📊 Afficher les statistiques")
        println("9. 🆘 Aide")
        println("0. 🚪 Quitter")
        println("=".repeat(50))
        print("👉 Choisissez une option (0-9) : ")
    }

    /**
     * Gère l'ajout d'une tâche
     */
    fun handleAddTask() {
        println("\n➕ === AJOUTER UNE TÂCHE ===")
        print("📝 Entrez la description de la tâche : ")
        val description = readLine()?.trim()

        when {
            description.isNullOrBlank() -> {
                println("❌ La description ne peut pas être vide.")
            }
            description.length > 200 -> {
                println("❌ La description est trop longue (maximum 200 caractères).")
            }
            else -> {
                val task = taskManager.addTask(description)
                println("✅ Tâche ajoutée avec succès !")
                println("   ${task.getFormattedStatus()}")
            }
        }
    }

    /**
     * Gère le marquage d'une tâche comme terminée
     */
    fun handleMarkTaskDone() {
        println("\n✅ === MARQUER COMME TERMINÉE ===")
        if (taskManager.getTaskCount() == 0) {
            println("❌ Aucune tâche disponible.")
            return
        }

        taskManager.listTasks()
        print("👉 Entrez l'ID de la tâche à marquer comme terminée : ")
        val id = readLine()?.toIntOrNull()

        if (id != null) {
            taskManager.markTaskDone(id)
        } else {
            println("❌ ID invalide. Veuillez entrer un nombre entier.")
        }
    }

    /**
     * Gère la suppression d'une tâche
     */
    fun handleRemoveTask() {
        println("\n🗑️ === SUPPRIMER UNE TÂCHE ===")
        if (taskManager.getTaskCount() == 0) {
            println("❌ Aucune tâche disponible.")
            return
        }

        taskManager.listTasks()
        print("👉 Entrez l'ID de la tâche à supprimer : ")
        val id = readLine()?.toIntOrNull()

        if (id != null) {
            print("⚠️ Êtes-vous sûr de vouloir supprimer cette tâche ? (o/n) : ")
            val confirmation = readLine()?.lowercase()

            if (confirmation == "o" || confirmation == "oui") {
                taskManager.removeTask(id)
            } else {
                println("❌ Suppression annulée.")
            }
        } else {
            println("❌ ID invalide. Veuillez entrer un nombre entier.")
        }
    }

    /**
     * Gère la recherche de tâches
     */
    fun handleSearchTasks() {
        println("\n🔍 === RECHERCHER DES TÂCHES ===")
        if (taskManager.getTaskCount() == 0) {
            println("❌ Aucune tâche disponible.")
            return
        }

        print("👉 Entrez un mot-clé à rechercher : ")
        val keyword = readLine()?.trim()

        if (!keyword.isNullOrBlank()) {
            taskManager.searchTasks(keyword)
        } else {
            println("❌ Le mot-clé ne peut pas être vide.")
        }
    }

    /**
     * Gère le changement de statut d'une tâche
     */
    fun handleToggleTaskStatus() {
        println("\n🔄 === CHANGER LE STATUT D'UNE TÂCHE ===")
        if (taskManager.getTaskCount() == 0) {
            println("❌ Aucune tâche disponible.")
            return
        }

        taskManager.listTasks()
        print("👉 Entrez l'ID de la tâche à modifier : ")
        val id = readLine()?.toIntOrNull()

        if (id != null) {
            taskManager.toggleTaskStatus(id)
        } else {
            println("❌ ID invalide. Veuillez entrer un nombre entier.")
        }
    }

    /**
     * Affiche l'aide
     */
    fun displayHelp() {
        println("\n🆘 === AIDE ===")
        println("📋 Comment utiliser le gestionnaire de tâches :")
        println()
        println("➕ Ajouter une tâche :")
        println("   • Choisissez l'option 1")
        println("   • Tapez la description de votre tâche")
        println("   • La tâche sera automatiquement ajoutée avec un ID unique")
        println()
        println("📋 Lister les tâches :")
        println("   • Choisissez l'option 2")
        println("   • Toutes vos tâches s'afficheront avec leur statut")
        println()
        println("✅ Marquer comme terminée :")
        println("   • Choisissez l'option 3")
        println("   • Entrez l'ID de la tâche à terminer")
        println()
        println("🗑️ Supprimer une tâche :")
        println("   • Choisissez l'option 4")
        println("   • Entrez l'ID de la tâche à supprimer")
        println("   • Confirmez la suppression")
        println()
        println("🔍 Rechercher :")
        println("   • Choisissez l'option 5")
        println("   • Entrez un mot-clé présent dans la description")
        println()
        println("💡 Conseils :")
        println("   • Les IDs sont attribués automatiquement (1, 2, 3...)")
        println("   • Vous pouvez modifier le statut d'une tâche avec l'option 6")
        println("   • Les statistiques (option 8) vous donnent un aperçu global")
        println()
    }

    /**
     * Boucle principale du menu
     */
    fun runMenu() {
        println("🎉 Bienvenue dans le Gestionnaire de Tâches !")
        println("📝 Commencez par ajouter quelques tâches...")

        // Boucle principale avec while
        var running = true
        while (running) {
            displayMenu()

            when (val choice = readLine()?.toIntOrNull()) {
                1 -> handleAddTask()
                2 -> taskManager.listTasks()
                3 -> handleMarkTaskDone()
                4 -> handleRemoveTask()
                5 -> handleSearchTasks()
                6 -> handleToggleTaskStatus()
                7 -> taskManager.clearCompletedTasks()
                8 -> taskManager.displayDetailedStats()
                9 -> displayHelp()
                0 -> {
                    println("\n👋 Merci d'avoir utilisé le Gestionnaire de Tâches !")
                    println("📊 Résumé final :")
                    taskManager.displayDetailedStats()
                    running = false
                }
                else -> {
                    println("❌ Option invalide. Veuillez choisir un nombre entre 0 et 9.")
                }
            }

            // Petite pause pour la lisibilité
            if (running) {
                print("\nAppuyez sur Entrée pour continuer...")
                readLine()
            }
        }
    }
}

/**
 * Fonction pour créer des tâches d'exemple
 */
fun createSampleTasks(taskManager: TaskManagerEx3) {
    println("📦 Création de tâches d'exemple...")

    val sampleTasks = listOf(
        "Apprendre les bases de Kotlin",
        "Implémenter les classes Task et TaskManager",
        "Créer le menu interactif",
        "Tester toutes les fonctionnalités",
        "Rédiger la documentation",
        "Optimiser les performances",
        "Ajouter la gestion d'erreurs"
    )

    // Ajouter les tâches d'exemple
    sampleTasks.forEach { description ->
        taskManager.addTask(description)
    }

    // Marquer quelques tâches comme terminées
    taskManager.markTaskDone(1) // Apprendre les bases
    taskManager.markTaskDone(2) // Implémenter les classes
    taskManager.markTaskDone(4) // Tester

    println("✅ ${sampleTasks.size} tâches d'exemple créées !")
    println("🎯 ${taskManager.getCompletedTaskCount()} tâches déjà terminées pour la démonstration.")
}

/**
 * Fonction principale
 */
fun main() {
    println("=== EXERCICE 3 : GESTIONNAIRE DE TÂCHES INTERACTIF ===\n")

    // Créer le gestionnaire de tâches
    val taskManager = TaskManagerEx3()

    // Demander si l'utilisateur veut des tâches d'exemple
    print("🤔 Voulez-vous créer des tâches d'exemple pour tester ? (o/n) : ")
    val createSamples = readLine()?.lowercase()

    if (createSamples == "o" || createSamples == "oui") {
        createSampleTasks(taskManager)
        println()
    }

    // Créer et lancer l'interface utilisateur
    val menuUI = TaskMenuUI(taskManager)
    menuUI.runMenu()

    println("\n✅ Exercice 3 terminé ! Au revoir ! 👋")
}