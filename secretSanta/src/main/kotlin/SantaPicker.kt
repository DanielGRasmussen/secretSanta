class SantaPicker constructor(private val utils: Utils) {
    fun pick() {
        // Picks who the user will be Secret Santa for and prints it to console.
        // It asks if the target can be a previous pick and if they can be close family.
        val family = utils.readFamily()
        print("\nShould it be possible to select your previous pick? (y/n) ")
        val previous = readln()

        print("Should it be possible to select close family? (y/n) ")
        val close = readln()

        var rand: Int
        var selection: Map<String, String>

        var possible = false
        for (person in family) { // Checks if there is a valid option with the above settings.
            val person = utils.toMap(person)
            if ((person["previousYear"] == "false" || previous == "y") && (person["relationship"] != "close family" || close == "y")) {
                possible = true
            }
        }
        // Tells the user no option
        if (!possible) {
            println("\nNo valid selection with current settings.")
            return
        }

        // Runs until the conditions set above are met.
        while (true) {
            rand = (family.indices).random()

            selection = utils.toMap(family[rand])
            // If not all of this:
            // random selection is a previous pick and user selected no on previous
            // or random selection is close family and user said no to close family.
            if (!((selection["previousYear"] == "true" && previous == "n") || (selection["relationship"] == "close family" && close == "n"))) {
                break
            }
        }

        println("\nYou will be Secret Santa for ${selection["name"]}")

        // Changes previous info to mark who is the previous Secret Santa target.
        val prev = getPrevious(family)

        if (prev >= 0) {
            utils.delete(prev)
            val new = utils.toMap(family[prev])
            utils.writeFamily("name=${new["name"]},relationship=${new["relationship"]},previousYear=false\n")
        }

        utils.delete(rand)
        utils.writeFamily("name=${selection["name"]},relationship=${selection["relationship"]},previousYear=true\n")
    }

    private fun getPrevious(family: List<String>): Int {
        // Gets the index of who was the previous Secret Santa target (if none return -1)
        for ((i, item) in family.withIndex()) {
            if (utils.toMap(item)["previousYear"] == "true") {
                return i
            }
        }
        return -1
    }
}