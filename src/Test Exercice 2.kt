/**
 * Tests unitaires pour l'Exercice 2 - Classes et Classes de Données
 */

/**
 * Tests pour la classe de données Task
 */
fun testTaskDataClass() {
    println("🧪 Test: Task Data Class")

    // Test 1: Création de tâche avec valeurs par défaut
    val task1 = Task(1, "Test task")
    assert(task1.id == 1) { "❌ Échec: L'ID devrait être 1" }
    assert(task1.description == "Test task") { "❌ Échec: La description devrait être 'Test task'" }
    assert(task1.isDone == false) { "❌ Échec: isDone devrait être false par défaut" }
    println("✅ Test 1 réussi: Création avec valeurs par défaut")

    // Test 2: Création de tâche avec isDone = true
    val task2 = Task(2, "Completed task", true)
    assert(task2.isDone == true) { "❌ Échec: isDone devrait être true" }
    println("✅ Test 2 réussi: Création avec isDone = true")

    // Test 3: Fonction markAsDone()
    val task3 = Task(3, "To be completed")
    task3.markAsDone()
    assert(task3.isDone == true) { "❌ Échec: markAsDone() devrait mettre isDone à true" }
    println("✅ Test 3 réussi: markAsDone() fonctionne")

    // Test 4: Fonction markAsUndone()
    task3.markAsUndone()
    assert(task3.isDone == false) { "❌ Échec: markAsUndone() devrait mettre isDone à false" }
    println("✅ Test 4 réussi: markAsUndone() fonctionne")

    // Test 5: getFormattedStatus() pour tâche en cours
    val task4 = Task(4, "Pending task")
    val formattedPending = task4.getFormattedStatus()
    assert(formattedPending.contains("[4]")) { "❌ Échec: Le format devrait contenir l'ID [4]" }
    assert(formattedPending.contains("Pending task")) { "❌ Échec: Le format devrait contenir la description" }
    assert(formattedPending.contains("⏳ En cours")) { "❌ Échec: Le format devrait indiquer 'En cours'" }
    println("✅ Test 5 réussi: getFormattedStatus() pour tâche en cours")

    // Test 6: getFormattedStatus() pour tâche terminée
    val task5 = Task(5, "Done task", true)
    val formattedDone = task5.getFormattedStatus()
    assert(formattedDone.contains("✅ Terminée")) { "❌ Échec: Le format devrait indiquer 'Terminée'" }
    println("✅ Test 6 réussi: getFormattedStatus() pour tâche terminée")

    // Test 7: Égalité des data class
    val taskA = Task(1, "Same task")
    val taskB = Task(1, "Same task")
    assert(taskA == taskB) { "❌ Échec: Deux tâches identiques devraient être égales" }
    println("✅ Test 7 réussi: Égalité des data class")

    // Test 8: Copie avec modification
    val originalTask = Task(1, "Original", false)
    val copiedTask = originalTask.copy(description = "Modified")
    assert(copiedTask.id == originalTask.id) { "❌ Échec: L'ID de la copie devrait être identique" }
    assert(copiedTask.description == "Modified") { "❌ Échec: La description devrait être modifiée" }
    assert(copiedTask.isDone == originalTask.isDone) { "❌ Échec: isDone devrait être copié" }
    println("✅ Test 8 réussi: Copy avec modification")

    println("🎉 Tous les tests Task ont réussi!\n")
}

/**
 * Tests pour la classe TaskManager
 */
fun testTaskManager() {
    println("🧪 Test: TaskManager Class")

    // Test 1: Création et état initial
    val manager = TaskManager()
    assert(manager.getTaskCount() == 0) { "❌ Échec: Le manager vide devrait avoir 0 tâche" }
    assert(manager.getCompletedTaskCount() == 0) { "❌ Échec: Le manager vide devrait avoir 0 tâche terminée" }
    assert(manager.getPendingTaskCount() == 0) { "❌ Échec: Le manager vide devrait avoir 0 tâche en cours" }
    println("✅ Test 1 réussi: État initial du TaskManager")

    // Test 2: Ajout de tâches
    val task1 = manager.addTask("Première tâche")
    assert(manager.getTaskCount() == 1) { "❌ Échec: Après ajout, devrait avoir 1 tâche" }
    assert(task1.id == 1) { "❌ Échec: La première tâche devrait avoir l'ID 1" }
    assert(task1.description == "Première tâche") { "❌ Échec: Description incorrecte" }
    assert(task1.isDone == false) { "❌ Échec: Nouvelle tâche devrait être non terminée" }
    println("✅ Test 2 réussi: Ajout de première tâche")

    val task2 = manager.addTask("Deuxième tâche")
    assert(manager.getTaskCount() == 2) { "❌ Échec: Après 2 ajouts, devrait avoir 2 tâches" }
    assert(task2.id == 2) { "❌ Échec: La deuxième tâche devrait avoir l'ID 2" }
    println("✅ Test 3 réussi: Ajout de deuxième tâche")

    // Test 3: Recherche par ID
    val foundTask = manager.findTaskById(1)
    assert(foundTask != null) { "❌ Échec: findTaskById(1) devrait trouver la tâche" }
    assert(foundTask?.id == 1) { "❌ Échec: La tâche trouvée devrait avoir l'ID 1" }
    assert(foundTask?.description == "Première tâche") { "❌ Échec: Description incorrecte" }
    println("✅ Test 4 réussi: Recherche par ID existant")

    val notFoundTask = manager.findTaskById(999)
    assert(notFoundTask == null) { "❌ Échec: findTaskById(999) devrait retourner null" }
    println("✅ Test 5 réussi: Recherche par ID inexistant")

    // Test 4: Modification de statut via le manager
    foundTask?.markAsDone()
    assert(manager.getCompletedTaskCount() == 1) { "❌ Échec: Devrait avoir 1 tâche terminée" }
    assert(manager.getPendingTaskCount() == 1) { "❌ Échec: Devrait avoir 1 tâche en cours" }
    println("✅ Test 6 réussi: Modification de statut")

    // Test 5: getAllTasks()
    val allTasks = manager.getAllTasks()
    assert(allTasks.size == 2) { "❌ Échec: getAllTasks() devrait retourner 2 tâches" }
    assert(allTasks[0].id == 1) { "❌ Échec: Première tâche devrait avoir ID 1" }
    assert(allTasks[1].id == 2) { "❌ Échec: Deuxième tâche devrait avoir ID 2" }
    println("✅ Test 7 réussi: getAllTasks()")

    // Test 6: Ajout de plusieurs tâches et statistiques
    manager.addTask("Troisième tâche")
    manager.addTask("Quatrième tâche")

    // Marquer une autre tâche comme terminée
    manager.findTaskById(3)?.markAsDone()

    assert(manager.getTaskCount() == 4) { "❌ Échec: Devrait avoir 4 tâches au total" }
    assert(manager.getCompletedTaskCount() == 2) { "❌ Échec: Devrait avoir 2 tâches terminées" }
    assert(manager.getPendingTaskCount() == 2) { "❌ Échec: Devrait avoir 2 tâches en cours" }
    println("✅ Test 8 réussi: Statistiques avec plusieurs tâches")

    // Test 7: Immutabilité de getAllTasks()
    val tasksCopy = manager.getAllTasks()
    val originalSize = tasksCopy.size
    // La liste retournée devrait être une copie, pas la liste originale
    assert(tasksCopy !== manager.getAllTasks()) { "❌ Échec: getAllTasks() devrait retourner une nouvelle instance" }
    println("✅ Test 9 réussi: Immutabilité de getAllTasks()")

    println("🎉 Tous les tests TaskManager ont réussi!\n")
}

/**
 * Tests d'intégration pour les interactions entre Task et TaskManager
 */
fun testIntegration() {
    println("🧪 Test: Intégration Task + TaskManager")

    val manager = TaskManager()

    // Scénario complet d'utilisation
    val task1 = manager.addTask("Écrire tests unitaires")
    val task2 = manager.addTask("Exécuter les tests")
    val task3 = manager.addTask("Corriger les bugs")

    // Vérification des IDs séquentiels
    assert(task1.id == 1 && task2.id == 2 && task3.id == 3) {
        "❌ Échec: Les IDs devraient être séquentiels (1, 2, 3)"
    }
    println("✅ Test 1 réussi: IDs séquentiels")

    // Marquer des tâches comme terminées
    task1.markAsDone()
    task2.markAsDone()

    // Vérification cohérence
    assert(manager.getCompletedTaskCount() == 2) { "❌ Échec: 2 tâches devraient être terminées" }
    assert(manager.getPendingTaskCount() == 1) { "❌ Échec: 1 tâche devrait être en cours" }
    println("✅ Test 2 réussi: Cohérence entre Task et TaskManager")

    // Test que les modifications sur les objets Task affectent le TaskManager
    val foundTask = manager.findTaskById(3)
    foundTask?.markAsDone()
    assert(manager.getCompletedTaskCount() == 3) { "❌ Échec: Toutes les tâches devraient être terminées" }
    assert(manager.getPendingTaskCount() == 0) { "❌ Échec: Aucune tâche ne devrait être en cours" }
    println("✅ Test 3 réussi: Modifications propagées")

    println("🎉 Tous les tests d'intégration ont réussi!\n")
}

/**
 * Tests de cas limites et robustesse
 */
fun testEdgeCases() {
    println("🧪 Test: Cas limites")

    val manager = TaskManager()

    // Test 1: Description vide
    val emptyTask = manager.addTask("")
    assert(emptyTask.description == "") { "❌ Échec: Description vide devrait être acceptée" }
    println("✅ Test 1 réussi: Description vide acceptée")

    // Test 2: Description très longue
    val longDescription = "A".repeat(1000)
    val longTask = manager.addTask(longDescription)
    assert(longTask.description == longDescription) { "❌ Échec: Description longue devrait être acceptée" }
    println("✅ Test 2 réussi: Description longue acceptée")

    // Test 3: Recherche avec ID 0 et négatifs
    assert(manager.findTaskById(0) == null) { "❌ Échec: ID 0 devrait retourner null" }
    assert(manager.findTaskById(-1) == null) { "❌ Échec: ID négatif devrait retourner null" }
    println("✅ Test 3 réussi: IDs invalides gérés")

    // Test 4: Multiple markAsDone/markAsUndone
    val task = manager.addTask("Test task")
    task.markAsDone()
    task.markAsDone() // Double appel
    assert(task.isDone == true) { "❌ Échec: Double markAsDone ne devrait pas poser problème" }

    task.markAsUndone()
    task.markAsUndone() // Double appel
    assert(task.isDone == false) { "❌ Échec: Double markAsUndone ne devrait pas poser problème" }
    println("✅ Test 4 réussi: Appels multiples gérés")

    println("🎉 Tous les tests de cas limites ont réussi!\n")
}

/**
 * Fonction principale pour exécuter tous les tests
 */
fun main() {
    println("=== 🧪 TESTS UNITAIRES - EXERCICE 2 ===\n")

    try {
        // Tests de la classe Task
        testTaskDataClass()

        // Tests de la classe TaskManager
        testTaskManager()

        // Tests d'intégration
        testIntegration()

        // Tests de cas limites
        testEdgeCases()

        println("🎉 🎉 🎉 TOUS LES TESTS ONT RÉUSSI ! 🎉 🎉 🎉")
        println("✅ Votre Exercice 2 est parfaitement fonctionnel !")
        println("📊 Classes testées : Task (data class) + TaskManager")
        println("🔧 Fonctionnalités testées : Création, modification, recherche, statistiques")

    } catch (e: AssertionError) {
        println("❌ ÉCHEC DU TEST: ${e.message}")
        println("🔍 Vérifiez votre code et réessayez.")
    } catch (e: Exception) {
        println("❌ ERREUR INATTENDUE: ${e.message}")
        e.printStackTrace()
    }
}