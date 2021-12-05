package flashcards

fun main() {
    println("Input the number of cards:")
    val numCards = readLine()!!.toInt()
    val deck = Flashcards(mutableListOf<Flashcard>())

    deck.createDeck(numCards)
    deck.testStudent()
}

data class Flashcard(val term: String, val definition: String) {}

class Flashcards(val flashcards: MutableList<Flashcard>) {

    fun createDeck(numCards: Int) {
        var term: String
        var definition: String

        if (numCards > 0) {
            for (i in 0 until numCards) {
                println("Card #${i+1}:")
                while (true) {
                    term = readLine()!!.toString()
                    if (!isDuplicateTerm(term)) break
                    println("The term \"$term\" already exists. Try again:")
                 }

                println("The definition for card #${i+1}:")
                while (true) {
                    definition = readLine()!!
                    if (!isDuplicateDefinition(definition)) break
                    println("The definition \"$definition\" already exists. Try again:")
                }

                flashcards.add(Flashcard(term, definition))
            }
        }
    }

    fun testStudent() {
        var answer: String
        for (card in flashcards) {
            println("Print the definition of \"${card.term}\":")
            answer = readLine() ?: "error"
            if (answer.lowercase().trim() == card.definition.lowercase().trim()) {
                println("Correct!")
                break
            } else if (isDiffQuestion(answer, card.term)) {
                println ("Wrong.  The right answer is \"${card.definition}\", but your definition is correct for \"${card.term}\".")
            } else {
                println("Wrong. The right answer is \"${card.definition}\".")
            }
        }
    }

    fun isDuplicateTerm(term: String) : Boolean {
        for (card in flashcards) {
            if (term.lowercase().trim() == card.term.lowercase().trim()) {
                return true
            }
        }
        return false
    }

    fun isDuplicateDefinition(definition: String) : Boolean {
        for (card in flashcards) {
            if (definition.lowercase().trim() == card.definition.lowercase().trim()) {
                return true
            }
        }
        return false
    }

    fun isDiffQuestion(answer: String, targetQuestion: String): Boolean {
        val prepAnswer = answer.lowercase().trim()
        var prepDefinition: String
        val prepTargetQuestion = targetQuestion.lowercase().trim()

        for (card in flashcards) {
            prepDefinition = card.definition.lowercase().trim()
            if (prepAnswer == prepDefinition) {
                if(prepTargetQuestion != card.term.lowercase().trim()) {
                    return true
                }
            }
        }
        return false
    }
}
